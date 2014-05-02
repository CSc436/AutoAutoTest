package model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author William Cohen
 * 
 */
public class RelaxedStringFloatCheck {

    // instance variables for RelaxedStringFloatCheck
    private boolean ignoreCasing;
    private boolean ignoreWhitespace;
    private boolean ignorePunctuation;
    /**
     * Stores the number of decimal places to which the expected and actual must
     * round to the same value. For example, if a decimal places precision of 2
     * is used, then the expected and actual are both multiplied by 10^2, then
     * Math.Round is invoked, and the results should be equal.
     */
    private int decPlacesPrecision;
    private Pattern whitespacePattern;
    private Pattern punctuationPattern;
    /**
     * This is the default number of decimal places to evaluate to.
     */
    public static final int DEFAULT_PRECISION = 2;
    private static final String NUMBER_REGEX = "[-+]?[0-9]*\\.?[0-9]+";
    private static final String WHITESPACE_REGEX = "\\s";
    private static final String PUNCTUATION_REGEX = "\\p{Punct}";

    /**
     * @param inputCasing
     *            If set to true, specifies that character casing shall be
     *            ignored.
     * @param inputWhitespace
     *            If set to true, specifies that differences in whitespace (e.g.
     *            spaces, tabs, carriage returns) shall be ignored.
     * @param inputPunct
     *            If set to true, specifies that differences in punctuation
     *            characters shall be ignored.
     * @param inputPrecision
     *            Specifies the number of decimal places of precision we are
     *            testing for. All numbers in a line of actual input will be
     *            compared with the corresponding values in the expected line of
     *            input to see if they are within this level of precision.
     * 
     */
    public RelaxedStringFloatCheck(boolean inputCasing,
            boolean inputWhitespace, boolean inputPunct, int inputPrecision) {
        ignoreCasing = inputCasing;
        ignoreWhitespace = inputWhitespace;
        ignorePunctuation = inputPunct;
        whitespacePattern = Pattern.compile(WHITESPACE_REGEX);
        punctuationPattern = Pattern.compile(PUNCTUATION_REGEX);

        if (inputPrecision >= 0) {
            decPlacesPrecision = inputPrecision;
        } else {
            // invalid precision specified
            decPlacesPrecision = DEFAULT_PRECISION;
        }
    }

    /**
     * Private helper method to see if a character is whitespace.
     * 
     * @param ch
     *            the character to check for being whitespace
     * 
     * @return true if the character is whitespace or false if not
     */
    private boolean isWhitespace(char ch) {
        Matcher test = whitespacePattern.matcher("" + ch);
        return test.matches();
    }

    /**
     * Private helper method to see if a character is punctuation.
     * 
     * @param ch
     *            the character to check for being punctuation
     * 
     * @return true if the character is punctuation or false if not
     */
    private boolean isPunctuation(char ch) {
        Matcher test = punctuationPattern.matcher("" + ch);
        return test.matches();
    }

    /**
     * Private helper method to see if two characters are equivalent, taking
     * into account the ignore casing setting.
     * 
     * @param chA
     *            the first character to compare
     * @param chB
     *            the second character to compare
     * @return true if the characters are equivalent or false if not
     */
    private boolean charactersMatch(char chA, char chB) {
        if (!ignoreCasing) {
            return (chA == chB);
        }
        // if we get here, then do a case-insensitive comparison using String
        String strA = "" + chA;
        String strB = "" + chB;
        return strA.equalsIgnoreCase(strB);
    } // end of charactersMatch

    /**
     * @param ch
     *            character to check
     * @return true if we can ignore this character in the actual or expected
     *         string
     */
    private boolean canAdvanceOver(char ch) {
        if (ignorePunctuation && isPunctuation(ch)) {
            return true;
        } else {
            return (ignoreWhitespace && isWhitespace(ch));
        }
    }

    /**
     * This method is used when one of the two strings (student output or
     * expected output) has been exhausted, but the other string still has
     * unexamined characters remaining. This method will make a pass through the
     * unexhausted string, starting at the character index which exhausted the
     * other string. The method determines if the remaining characters can be
     * ignored given our settings or not.
     * 
     * @param inputStr
     *            The string which we are checking.
     * @param probeLocation
     *            The current character index within the string, which we are
     *            examining.
     * @return Returns true if the remaining characters can be ignored or false
     *         if they cannot.
     */
    private boolean canAdvanceToEnd(StringBuilder inputStr, int probeLocation) {
        if (probeLocation == inputStr.length()) {
            return true;
        }
        boolean stringIsExhausted = false;
        int inputLength = inputStr.length();
        char probeChar = inputStr.charAt(probeLocation);

        while ((canAdvanceOver(probeChar)) && (!stringIsExhausted)) {
            probeLocation++;
            if (probeLocation == inputLength) {
                stringIsExhausted = true;
            } else {
                probeChar = inputStr.charAt(probeLocation);
            }
        }
        // if we managed to exhaust the string, then the string was acceptable
        return stringIsExhausted;
    }

    /**
     * @param inputStr
     *            The original input string.
     * @return Returns a new StringBuilder containing the original string, with
     *         all floating-point numbers multiplied by 10^(decimal places
     *         precision) and then rounded to the nearest integer, so that the
     *         numbers no longer contain a decimal point.
     */
    private StringBuilder roundAllNumbersInString(String inputStr) {
        // use the regular expression numberRegex to instantiate a
        // number-matching pattern
        Pattern numberPattern = Pattern.compile(NUMBER_REGEX);
        StringBuilder result = new StringBuilder(inputStr);
        Matcher inputMatcher = numberPattern.matcher(inputStr);

        while (inputMatcher.find()) {
            String numberAsString = inputMatcher.group();
            // we now have captured one number, and know its start and end
            // positions in the input string
            double numberAsDecimal = Double.parseDouble(numberAsString);
            long numberAsRounded = Math.round(Math.pow(10, decPlacesPrecision)
                    * numberAsDecimal);
            // we now replace the original number with the rounded number (which
            // is now an integer because it was multiplied by
            // 10^decPlacesPrecision)
            int newIndex = result.indexOf(numberAsString);
            result.replace(newIndex, newIndex + numberAsString.length(), ""
                    + numberAsRounded);
        } // end of looping thru all numbers in inputStr
        return result;
    } // end of roundAllNumbersInString

    /**
     * Determines if the student output is acceptable, compared to the expected
     * output, taking into account whatever relaxed checks have been enabled.
     * 
     * @param expected
     *            The entire expected output, as a string.
     * @param actual
     *            The entire actual output, as a string.
     * @return Returns true if the student output is acceptable, or false if it
     *         is not acceptable.
     */
    public boolean isAcceptable(String expected, String actual) {
        int expectedIndex = 0; // tracks the index as we parse expected string
        int actualIndex = 0; // tracks the index as we parse actual string
        int expectedLength = 0;
        int actualLength = 0;
        char chFromActual = '0';
        char chFromExpected = '0';

        StringBuilder newExpected = roundAllNumbersInString(expected);
        StringBuilder newActual = roundAllNumbersInString(actual);

        // Now, the StringBuilder variables contain the expected and actual with
        // any numeric values rounded to the specified number of decimal places.
        expectedLength = newExpected.length();
        actualLength = newActual.length();

        // parse the new strings for being acceptable
        expectedIndex = 0;
        actualIndex = 0;
        boolean expectedIsExhausted = expectedIndex == expectedLength;
        boolean actualIsExhausted = actualIndex == actualLength;
        while ((!expectedIsExhausted) && (!actualIsExhausted)) {
            // update current characters to probe
            chFromExpected = newExpected.charAt(expectedIndex);
            chFromActual = newActual.charAt(actualIndex);

            // advance probe character for each string until we see something we
            // cannot ignore
            while (canAdvanceOver(chFromExpected)) {
                expectedIndex++;
                // check to see if string has been exhausted
                if (expectedIndex == expectedLength) {
                    expectedIsExhausted = true;
                    break;
                }
                // otherwise, advance probe location
                chFromExpected = newExpected.charAt(expectedIndex);
            }

            while (canAdvanceOver(chFromActual)) {
                actualIndex++;
                // check to see if string has been exhausted
                if (actualIndex == actualLength) {
                    actualIsExhausted = true;
                    break;
                }
                // otherwise, advance probe location
                chFromActual = newActual.charAt(actualIndex);
            }

            // if we get here, the we are either at the next pair of characters
            // to compare, or at the end of one or both strings

            // check to see if we are at the end of both strings, in which case
            // we could be looking at characters which don't match, but are
            // supposed to be ignored
            if ((actualIsExhausted) && (expectedIsExhausted)) {
                return true; // both strings exhausted
            } else if (actualIsExhausted) { // we know that expected cannot be
                                            // exhausted
                return false; // student output is too short
            } else if (expectedIsExhausted) { // we know that actual cannot be
                                              // exhausted
                return false; // student output has extraneous characters which
                              // are not ignorable
            } else if ((expectedIndex == expectedLength - 1)
                    && (actualIndex == actualLength - 1)) {
                return charactersMatch(chFromExpected, chFromActual);
            }

            // if we get here, then we need to compare two characters which are
            // not at the end of their respective strings
            if (!(charactersMatch(chFromExpected, chFromActual))) {
                // the next non-ignored characters do not match, so the student
                // output is wrong
                return false; // quit out of procedure
            }
            // proceed to next iteration of while loop, because there are still
            // characters remaining in the strings which need to be compared.
            expectedIndex++;
            actualIndex++;

            if (expectedIndex == expectedLength) {
                expectedIsExhausted = true;
            }

            if (actualIndex == actualLength) {
                actualIsExhausted = true;
            }

        } // end of while loop

        // if we get here, then expected or actual was shorter, but we need to
        // see if the difference is due to ignorable characters
        if (expectedIsExhausted) {
            return canAdvanceToEnd(newActual, actualIndex);
        } else { // actual is exhausted
            return canAdvanceToEnd(newExpected, expectedIndex);
        }
    } // end of isAcceptable
}
