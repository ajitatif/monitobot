package com.monitobot.search.google;

import com.monitobot.configuration.MonitobotCrawlerConfiguration;
import com.monitobot.http.HttpRequestHandler;
import com.monitobot.search.InternetSearchResult;
import com.monitobot.search.InternetSearchService;
import com.monitobot.search.SearchCriteria;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.net.http.HttpRequest;
import java.util.HashSet;

import static com.monitobot.common.StringHelper.getTrimmedString;

/**
 * @author Gökalp Gürbüzer (gokalp.gurbuzer@gmail.com)
 */
@ApplicationScoped
public class GoogleSearchService implements InternetSearchService<GoogleSearchResult> {

    MonitobotCrawlerConfiguration crawlerConfiguration;
    HttpRequestHandler httpRequestHandler;


    public GoogleSearchService(MonitobotCrawlerConfiguration crawlerConfiguration, HttpRequestHandler httpRequestHandler) {
        this.crawlerConfiguration = crawlerConfiguration;
        this.httpRequestHandler = httpRequestHandler;
    }

    public InternetSearchResult search(SearchCriteria searchCriteria) {

        URI uri = getGoogleSearchUri(searchCriteria);
        HttpRequest httpRequest = HttpRequest.newBuilder().GET().uri(uri).build();
        return httpRequestHandler.handleSearchRequest(httpRequest, "google", GoogleSearchResult.class);
    }

    @Override
    public GoogleSearchResult diffResults(GoogleSearchResult current, GoogleSearchResult previous) {
        HashSet<GoogleSearchResult.GoogleSearchResultItem> diff = new HashSet<>(current.items());
        diff.removeAll(new HashSet<>(previous.items()));
        return new GoogleSearchResult(current.queries(), current.context(), current.searchInformation(), diff.stream().toList());
    }

    private URI getGoogleSearchUri(SearchCriteria searchCriteria) {
        UriBuilder uriBuilder = UriBuilder.fromUri(URI.create(crawlerConfiguration.search().google().baseUrl()))
                .queryParam("safe", "off")
                .queryParam("q", searchCriteria.query())
                .queryParam("cx", crawlerConfiguration.search().google().customSearchEngineId())
                .queryParam("key", crawlerConfiguration.search().google().apiKey());
        populateOptionalCriteria(uriBuilder ,"exactTerms", searchCriteria.exactTerms());
        populateOptionalCriteria(uriBuilder, "excludeTerms", searchCriteria.excludeTerms());
        populateOptionalCriteria(uriBuilder, "lr", searchCriteria.language());
        populateOptionalCriteria(uriBuilder, "cl", searchCriteria.locationCountryCode());
        return uriBuilder.build();
    }

    private void populateOptionalCriteria(UriBuilder uriBuilder, String queryParam, Object criteria) {
        if (criteria instanceof String s) {
            getTrimmedString(s).ifPresent(c -> uriBuilder.queryParam(queryParam, c));
        } else if (criteria instanceof Number n) {
            uriBuilder.queryParam(queryParam, n);
        }
    }
}
