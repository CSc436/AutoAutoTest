package model;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

/**
 * @author Carlos G
 * 
 */
public class FuzzyMatchingModel {

    private int floatPrecision;
    private boolean ignoreWhiteSpace;
    private boolean ignorePunct;
    private boolean caseInsensitive;

    /**
     * @param igWhiteSpace
     *            is a boolean to see if ignoreWhiteSpace is to be used.
     * @param igPunct
     *            is a boolean to see if ignorePunct is to be used.
     * @param caseInsen
     *            is a boolean to see if caseInsensitive is to be used.
     * @param floatPrec
     *            is a boolean to show the rounding precision.
     */
    public FuzzyMatchingModel(Boolean igWhiteSpace, Boolean igPunct,
            Boolean caseInsen, int floatPrec) {
        this.floatPrecision = floatPrec;
        this.ignoreWhiteSpace = igWhiteSpace;
        this.ignorePunct = igPunct;
        this.caseInsensitive = caseInsen;
    }

    /**
     * @param one
     *            is the first string to be compared.
     * @param two
     *            is the second string to be compared.
     * @return result, which is the the ratio of the two of total number num of
     *         combined cars minus the number of changes needed to change one
     *         string to the other, divided by the total number of chars from
     *         both strings.
     */
    public double getRatio(String one, String two) {
        double changes = StringUtils.getLevenshteinDistance(one, two);
        int totalChars = one.length() + two.length();
        double result = (totalChars - changes) / totalChars;
        return result;
    }

    /**
     * 
     * @param str is the string to be filtered.
     * @return the string filtered.
     */
    private String filterCasing(String str) {
        if (caseInsensitive) {
            return str.toLowerCase();
        }
        return str;
    }

    /**
     * 
     * @param str is the string to be filtered.
     * @return the string filtered.
     */
    private String filterWhitespace(String str) {
        if (ignoreWhiteSpace) {
            Pattern whitespacePattern = Pattern.compile("\\s");
            Matcher whitespaceMatcher = whitespacePattern.matcher(str);
            return whitespaceMatcher.replaceAll("");
        }
        return str;
    }

    /**
     * 
     * @param str is the string to be filtered.
     * @return the string filtered.
     */
    private String filterPunctuation(String str) {
        if (ignorePunct) {
            Pattern punctuationPattern = Pattern.compile("\\p{Punct}");
            Matcher punctuationMatcher = punctuationPattern.matcher(str);
            return punctuationMatcher.replaceAll("");
        }
        return str;
    }

    /**
     * @param str
     *            Any string
     * @return The string with floating point numbers rounded to the precision
     *         for this matcher.
     */
    private String roundFloats(String str) {
        Pattern floatPattern = Pattern.compile("\\d*\\.\\d+");
        Matcher floatMatcher = floatPattern.matcher(str);
        while (floatMatcher.find()) {
            String numberAsString = floatMatcher.group();
            BigDecimal num = new BigDecimal(numberAsString);
            num = num.setScale(floatPrecision, BigDecimal.ROUND_HALF_EVEN);
            String roundedNumber = "" + num.doubleValue();
            str = str.replace(numberAsString, roundedNumber);
        }
        return str;
    }

    /**
     * 
     * @param str is the string that will be filtered.
     * @return the string filtered depending on booleans.
     */
    private String filterString(String str) {
        str = roundFloats(str);
        str = filterWhitespace(str);
        str = filterPunctuation(str);
        str = filterCasing(str);
        return str;
    }

    /**
     * @param one
     *            is the first string to be compared.
     * @param two
     *            is the second string to be compared.
     * @param expectedRatio
     *            is the ratio that we are testing for.
     * @return whether or not the ratio expected was achieved or not. TODO
     */
    public boolean getResult(String one, String two, double expectedRatio) {
        one = filterString(one);
        two = filterString(two);
        double actualRatio = getRatio(one, two);
        return actualRatio >= expectedRatio;
    }

}
