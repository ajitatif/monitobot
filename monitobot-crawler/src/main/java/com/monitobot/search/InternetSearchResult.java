package com.monitobot.search;

import java.time.LocalDateTime;

/**
 * @author Gökalp Gürbüzer (gokalp.gurbuzer@gmail.com)
 */
public record InternetSearchResult(
        String searchEngine,
        LocalDateTime runOnUtc,
        Integer statusCode,
        String rawData,
        SearchResult result) { }
