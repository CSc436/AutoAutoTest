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
     * @param ignoreWhiteSpace
     * @param ignorePunct
     * @param caseInsensitive
     * @param floatPrecision
     */
    public FuzzyMatchingModel(Boolean ignoreWhiteSpace, Boolean ignorePunct,
            Boolean caseInsensitive, int floatPrecision) {
        this.floatPrecision = floatPrecision;
        this.ignoreWhiteSpace = ignoreWhiteSpace;
        this.ignorePunct = ignorePunct;
        this.caseInsensitive = caseInsensitive;
    }

    /**
     * @param one
     * @param two
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
    
    private String filterCasing(String str) {
        if(caseInsensitive){
            return str.toLowerCase();
        }
        return str;
    }
    
    private String filterWhitespace(String str) {
        if (ignoreWhiteSpace) {
            Pattern whitespacePattern = Pattern.compile("\\s");
            Matcher whitespaceMatcher = whitespacePattern.matcher(str);
            return whitespaceMatcher.replaceAll("");
        }
        return str;
    }
    
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
    
    private String filterString(String str) {
        str = filterWhitespace(str);
        str = filterPunctuation(str);
        str = filterCasing(str);
        str = roundFloats(str);
        return str;
    }
    
    /**
     * @param one
     * @param two
     * @param expectedRatio
     * @return
     * TODO
     */
    public boolean getResult(String one, String two, double expectedRatio){
        one = filterString(one);
        two = filterString(two);
        double actualRatio = getRatio(one, two);
        return actualRatio >= expectedRatio;
    }
    
}
