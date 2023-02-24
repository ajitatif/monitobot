package com.monitobot.search.google;

import com.monitobot.search.SearchResult;

import java.util.List;

/**
 * @author Gökalp Gürbüzer (gokalp.gurbuzer@gmail.com)
 */

public record GoogleSearchResult(
    GoogleSearchResultQueryMeta queries,
    GoogleSearchResultContext context,
    GoogleSearchResultSearchInformation searchInformation,
    List<GoogleSearchResultItem> items
) implements SearchResult {

    public record GoogleSearchResultQueryMeta(
            List<GoogleSearchResultRequest> request,
            List<GoogleSearchResultRequest> nextPage
    ) {}

    public record GoogleSearchResultRequest(
            String title,
            String totalResults,
            String searchTerms,
            Integer count,
            Integer startIndex,
            String inputEncoding,
            String outputEncoding,
            String safe,
            String cx,
            String gl,
            String excludeTerms) {}

    public record GoogleSearchResultContext(String title) {}

    public record GoogleSearchResultSearchInformation(Long totalResults) {}

    public record GoogleSearchResultItem(
            String kind,
            String title,
            String link,
            String displayLink,
            String snippet
    ) {}
}
