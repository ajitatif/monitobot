package com.monitobot.search;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.monitobot.error.TrackNotFoundException;
import com.monitobot.search.google.GoogleSearchResult;
import com.monitobot.search.google.GoogleSearchService;
import io.netty.handler.codec.http.HttpResponseStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.ws.rs.InternalServerErrorException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Optional;

import static com.monitobot.common.DateHelper.currentLocalTimeInUtc;

/**
 * @author Gökalp Gürbüzer (gokalp.gurbuzer@gmail.com)
 */
@ApplicationScoped
@Transactional
public class TrackService {

    private static final Logger logger = LoggerFactory.getLogger(TrackService.class);

    GoogleSearchService googleSearchService;
    ObjectMapper objectMapper;

    public TrackService(GoogleSearchService googleSearchService, ObjectMapper objectMapper) {
        this.googleSearchService = googleSearchService;
        this.objectMapper = objectMapper;
    }

    public TrackResult getNewResults(final String trackPublicId) {

        logger.debug("Getting new results for track {}", trackPublicId);
        Optional<TrackEntity> track =
                TrackEntity.find("publicId", trackPublicId)
                        .singleResultOptional();

        return runTrack(track.orElseThrow(() -> new TrackNotFoundException(trackPublicId)));
    }

    private TrackResult runTrack(TrackEntity track) {

        HashMap<String, TrackResult.TrackDiff> results = getResultsFromAllSearchEngines(track);
        return new TrackResult(LocalDateTime.now(), results);
    }

    private HashMap<String, TrackResult.TrackDiff> getResultsFromAllSearchEngines(TrackEntity track) {
        HashMap<String, TrackResult.TrackDiff> allResults = new HashMap<>();
        getGoogleSearchResult(track).ifPresent(result -> allResults.put("google", result));
        track.lastRunOnUtc = currentLocalTimeInUtc();
        return allResults;
    }

    private Optional<TrackResult.TrackDiff> getGoogleSearchResult(TrackEntity track) {
        InternetSearchResult internetSearchResult = googleSearchService.search(track.searchCriteria);

        Optional<SearchResultEntity> previousResult = SearchResultEntity.find(
                "from SearchResultEntity where track = ?1 and searchEngine = ?2 and statusCode = ?3 order by createdOnUtc desc",
                        track,
                        "google",
                        200)
                .firstResultOptional();

        if (internetSearchResult.statusCode() != HttpResponseStatus.OK.code()) {
            logger.error("Google search returned {} for track {}. Response: {}", internetSearchResult.statusCode(), track.id, internetSearchResult.rawData());
            return Optional.empty();
        }

        persistSearchResult(internetSearchResult, "google", track);

        GoogleSearchResult searchResult = (GoogleSearchResult) internetSearchResult.result();
        if (previousResult.isPresent()) {
            try {
                GoogleSearchResult previousGoogleSearchResult = objectMapper.readValue(previousResult.get().rawData, GoogleSearchResult.class);
                GoogleSearchResult diff = googleSearchService.diffResults(searchResult, previousGoogleSearchResult);
                return Optional.of(wrapSearchResult(previousResult.get().createdOnUtc, diff));
            } catch (JsonProcessingException e) {
                logger.error("Cannot process JSON from previousGoogleSearchResult");
                throw new InternalServerErrorException(e);
            }
        } else {
            return Optional.of(wrapSearchResult(internetSearchResult.runOnUtc(), searchResult));
        }
    }

    private void persistSearchResult(InternetSearchResult internetSearchResult, String searchEngine, TrackEntity track) {
        SearchResultEntity searchResultEntity = new SearchResultEntity();
        searchResultEntity.searchEngine = searchEngine;
        searchResultEntity.createdOnUtc = internetSearchResult.runOnUtc();
        searchResultEntity.statusCode = internetSearchResult.statusCode();
        searchResultEntity.track = track;
        searchResultEntity.rawData = internetSearchResult.rawData();

        searchResultEntity.persist();
        track.lastRunOnUtc = searchResultEntity.createdOnUtc;
        track.persist();
    }

    private static TrackResult.TrackDiff wrapSearchResult(LocalDateTime previousRunOnUtc, GoogleSearchResult searchResult) {
        return new TrackResult.TrackDiff(previousRunOnUtc, searchResult);
    }

}
