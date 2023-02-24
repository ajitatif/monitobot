package com.monitobot.search;

/**
 * @author Gökalp Gürbüzer (gokalp.gurbuzer@gmail.com)
 */
public interface InternetSearchService<R extends SearchResult> {

    InternetSearchResult search(SearchCriteria searchCriteria);

    R diffResults(R current, R previous);
}
