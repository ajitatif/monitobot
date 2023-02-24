package com.monitobot.http;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.monitobot.search.SearchResult;
import com.monitobot.error.DownstreamServiceException;
import com.monitobot.search.InternetSearchResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static com.monitobot.common.DateHelper.currentLocalTimeInUtc;

/**
 * @author Gökalp Gürbüzer (gokalp.gurbuzer@gmail.com)
 */
@ApplicationScoped
public class HttpRequestHandler {

    private static final Logger logger = LoggerFactory.getLogger(HttpRequestHandler.class);

    private final HttpClient.Builder httpClientBuilder;
    ObjectMapper objectMapper;

    public HttpRequestHandler(ObjectMapper objectMapper) {
        httpClientBuilder = HttpClient.newBuilder();
        this.objectMapper = objectMapper;
    }

    public  <T extends SearchResult> InternetSearchResult handleSearchRequest(HttpRequest httpRequest, String searchEngine, Class<T> type) {
        final String requestId = String.format("%1$h", System.currentTimeMillis());
        HttpResponse<String> httpResponse;
        try {
            logger.debug("Sending request {} to {}", requestId, httpRequest.uri().toString());
            httpResponse = httpClientBuilder.build().send(httpRequest, HttpResponse.BodyHandlers.ofString());
            logger.debug("Request {} successful", requestId);
            logger.trace("Response for request {}:", httpResponse.body());
        } catch (IOException e) {
            throw new DownstreamServiceException("Cannot reach to google search in request " + requestId, e);
        } catch (InterruptedException e) {
            throw new DownstreamServiceException("The request " + requestId + " was interrupted");
        }
        String responseBody = httpResponse.body();
        int statusCode = httpResponse.statusCode();
        try {
            final T searchResult = objectMapper.readValue(responseBody, type);
            return new InternetSearchResult(searchEngine, currentLocalTimeInUtc(), statusCode, responseBody, searchResult);
        } catch (JsonProcessingException e) {
            logger.error("Response for request {} could not be parsed (status [{}]): {}", requestId, statusCode, httpResponse.body());
            return new InternetSearchResult(searchEngine, currentLocalTimeInUtc(), statusCode, responseBody, null);
        }
    }
}
