package model;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Check to see if a student's string output matches a regular expression
 * specified by the tester.
 * 
 * @author Cody
 */
public class RegexMatcher {

    private boolean ignoreCasing;
    private boolean ignorePunctuation;
    private boolean ignoreWhitespace;
    private int floatPrecision;

    /**
     * Create a RegexMatcher with the following options
     * 
     * @param spaceFlag
     *            True if it should not consider whitespace
     * @param punctFlag
     *            True if it should not consider punctuation
     * @param casingFlag
     *            True if it should not consider upper/lower casing
     * @param deciamlPrecision
     *            The number of decimal places to check
     */
    public RegexMatcher(boolean spaceFlag, boolean punctFlag,
            boolean casingFlag, int deciamlPrecision) {
        ignoreCasing = casingFlag;
        ignorePunctuation = punctFlag;
        ignoreWhitespace = spaceFlag;
        floatPrecision = deciamlPrecision;
    }

    /**
     * @param regex
     *            The regex to check the student code
     * @param actualOutput
     *            The output from the student code
     * @return True if the string matches the regex with the given flags.
     */
    public boolean matches(String regex, String actualOutput) {
        actualOutput = applyIgnoreWhitespace(actualOutput);
        actualOutput = applyIgnorePunctuation(actualOutput);
        actualOutput = roundFloats(actualOutput);
        regex = filterRegexWhitespace(regex);
        regex = filterRegexPunctuation(regex);
        regex = filterEmptyClasses(regex);
        regex = roundFloats(regex);
        Pattern p = null;
        if (ignoreCasing) {
            p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        } else {
            p = Pattern.compile(regex);
        }
        return p.matcher(actualOutput).matches();
    }

    /**
     * @param whitespaceString
     *            A string maybe containing whitespace
     * @return The string without whitespace
     */
    private String applyIgnoreWhitespace(String whitespaceString) {
        if (ignoreWhitespace) {
            Pattern whitespace = Pattern.compile("\\s");
            return whitespace.matcher(whitespaceString).replaceAll("");
        }
        return whitespaceString;
    }

    /**
     * @param punctuationString
     *            Any string
     * @return The string without punctuation
     */
    private String applyIgnorePunctuation(String punctuationString) {
        if (ignorePunctuation) {
            Pattern punctuation = Pattern.compile("\\p{Punct}");
            return punctuation.matcher(punctuationString).replaceAll("");
        }
        return punctuationString;
    }

    /**
     * Remove whitespace from the given regex string. This means removing
     * literal whitespace and whitespace character classes.
     * 
     * @param regex
     *            Any regular expression
     * @return The regex without whitespace
     */
    private String filterRegexWhitespace(String regex) {
        if (ignoreWhitespace) {
            Pattern whitespace = Pattern.compile("\\s");
            regex = whitespace.matcher(regex).replaceAll("");
            regex = removeRegexModifiers(regex, "\\\\p\\{Space\\}");
            regex = regex.replace("\\p{Space}", "\\s");
            regex = removeRegexModifiers(regex, "\\\\s");
            regex = regex.replace("\\s", "");
        }
        return regex;
    }

    /**
     * Remove punctuation from the given regex string. This means removing
     * literal punctuation and punctuation character classes.
     * 
     * @param regex
     *            Any regular expression
     * @return The regex without punctuation
     */
    private String filterRegexPunctuation(String regex) {
        if (ignorePunctuation) {
            regex = removeRegexModifiers(regex, "\\\\\\p{Punct}");
            Pattern actualPunctuation = Pattern.compile("\\\\\\p{Punct}");
            regex = actualPunctuation.matcher(regex).replaceAll("");
            regex = removeRegexModifiers(regex, "\\\\p\\{Punct\\}");
            Pattern loosePunctuation = Pattern.compile("\\\\p\\{Punct\\}");
            regex = loosePunctuation.matcher(regex).replaceAll("");
        }
        return regex;
    }

    /**
     * Remove empty classes from regex. Empty classes can show up if other
     * filters remove the stuff inside of it.
     * 
     * @param regex
     *            Any regex
     * @return The regex without empty classes
     */
    private String filterEmptyClasses(String regex) {
        regex = removeRegexModifiers(regex, "\\[\\]");
        return regex.replace("[]", "");
    }

    /**
     * Remove modifiers on regex pieces.
     * 
     * @param host
     *            The string to replace in
     * @param replacePattern
     *            The thing to remove modifiers from
     * @return The host without modifiers on the replace pattern
     */
    private String removeRegexModifiers(String host, String replacePattern) {
        String[] extensions = {"\\+", "\\?", "\\*", "\\{.*\\}"};
        for (String extension : extensions) {
            String fullPattern = replacePattern + extension;
            Matcher matcher = Pattern.compile(fullPattern).matcher(host);
            host = matcher.replaceAll(replacePattern);
        }
        return host;
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

}
