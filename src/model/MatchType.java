package model;

/**
 * @author Carlos G
 */

    public enum MatchType{
        /**
         * Regex uses regular expressions to compare strings
         */
        REGEX,
        /**
         * Fuzzy uses fuzzy matchingb to compare strings
         */
        FUZZY,
        /**
         * Exact uses exact matching to compare strings
         */
        EXACT;
    }