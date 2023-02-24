package com.monitobot.search;

/**
 * @author Gökalp Gürbüzer (gokalp.gurbuzer@gmail.com)
 * @apiNote Defines a search criteria for document search. Possible extensions for image search should be added
 * as nested records
 */
public record SearchCriteria(
        String query,
        String exactTerms,
        String excludeTerms,
        String locationCountryCode,
        String language
) { }
