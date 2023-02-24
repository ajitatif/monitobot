package com.monitobot.search;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * @author Gökalp Gürbüzer (gokalp.gurbuzer@gmail.com)
 */
public record TrackResult (
        LocalDateTime thisRunUtc,
        Map<String, TrackDiff> results
) {

    public record TrackDiff(
        LocalDateTime previousRunUtc,
        SearchResult result) { }
}
