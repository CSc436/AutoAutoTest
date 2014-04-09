package model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author William Cohen
 * 
 */
public class RelaxedStringFloatCheck {
    /**
     * Stores a boolean value which if true means that we ignore casing (i.e. we
     * treat capital and lowercase letters the same). This relaxation applies
     * only to the ASCII characters A-Z and a-z.
     */
    // instance variables for RelaxedStringFloatCheck
    boolean ignoreCasing;
    /**
     * Stores a boolean value which if true means that we ignore spaces and tabs
     * in the output. if false, then we do not ignore spaces and tabs.
     */
    boolean ignoreWhitespace;
    /**
     * Stores a boolean value which if true means that we ignore special
     * characters in the output (basically any Unicode characters which are NOT
     * digits, numbers, or spaces). If false, then we do not ignore special
     * characters.
     */
    boolean ignorePunctuation;
    /**
     * Stores the number of decimal places to which the expected and actual must
     * round to the same value. For example, if a decimal places precision of 2
     * is used, then the expected and actual are both multiplied by 10^2, then
     * Math.Round is invoked, and the results should be equal.
     */
    int decPlacesPrecision;
    /**
     * This is the default number of decimal places to evaluate to.
     */
    public static final int DEFAULT_PRECISION = 2;

    private static final String numberRegex = "[-+]?[0-9]*\\.?[0-9]+";
    private static final String whitespaceRegex = "\\s";
    private static final String punctuationRegex = "\\p{Punct}";
    
    private Pattern whitespacePattern = Pattern.compile(whitespaceRegex);
    private Pattern punctuationPattern = Pattern.compile(punctuationRegex);

    private boolean isWhitespace(char ch) {
        Matcher test = whitespacePattern.matcher("" + ch);
        return test.matches();
    }

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
        if (ignoreCasing == false) {
            return (chA == chB);
        }
        // if we get here, then do a case-insensitive comparison using String
        String strA = "" + chA;
        String strB = "" + chB;
        return strA.equalsIgnoreCase(strB);
    } // end of charactersMatch

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
        if (inputPrecision >= 0)
            decPlacesPrecision = inputPrecision;
        else
            // invalid precision specified
            decPlacesPrecision = DEFAULT_PRECISION;
    }
    
    private boolean checkLastCharacters(char chFromExpected, char chFromActual)
    {
        boolean ignoreExpected = false;
        boolean ignoreActual = false;
        String exp = "" + chFromExpected;
        String act = "" + chFromActual;
        
        // check to see if the two characters are ignorable, in which case
        // they are deemed to match, or not, in which case they must
        // actually match
        
        if (ignoreWhitespace)
        {
            if (isWhitespace(chFromExpected))
                ignoreExpected = true;
            
            if (isWhitespace(chFromActual))
                ignoreActual = true;
        }
        
        if (ignorePunctuation)
        {
            if (isPunctuation(chFromExpected))
                ignoreExpected = true;
            
            if (isPunctuation(chFromActual))
                ignoreActual = true;
        }
        
        if (ignoreExpected && ignoreActual)
        {
            return true;  // both characters are deemed acceptable
        }
        else if (ignoreCasing)
        {
            return exp.equalsIgnoreCase(act);
        }
        else
        return exp.equals(act);
    }

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
        char chFromActual;
        char chFromExpected;

        // use the regular expression numberRegex to instantiate a
        // number-matching pattern
        Pattern numberPattern = Pattern.compile(numberRegex);

        // we will construct a new StringBuilder for each of the two input
        // strings, because we cannot modify the original strings (nor would we
        // want to, even if we could)
        StringBuilder newExpected = new StringBuilder(expected);
        StringBuilder newActual = new StringBuilder(actual);

        int nbrStartIndex = 0; // used for round and replace
        int nbrEndIndex = 0; // used for round and replace
        int newIndex = 0;  // used for StringBuilder replace
        String numberAsString = "";
        double numberAsDecimal = 0;
        long numberAsRounded = 0;

        // Note that the pattern matcher is linked to the original input,
        // not the new StringBuilder we are building.
        Matcher expectedMatcher = numberPattern.matcher(expected);

        while (expectedMatcher.find()) {
            numberAsString = expectedMatcher.group();
            nbrStartIndex = expectedMatcher.start();
            nbrEndIndex = expectedMatcher.end();
            // we now have captured one number, and know its start and end
            // positions in the input string
            numberAsDecimal = Double.parseDouble(numberAsString);
            numberAsRounded = Math.round(Math.pow(10, decPlacesPrecision)
                    * numberAsDecimal);
            // we now replace the original number with the rounded number (which
            // is now an integer because it was multiplied by
            // 10^decPlacesPrecision)
            newIndex = newExpected.indexOf(numberAsString);
            newExpected.replace(newIndex, newIndex + numberAsString.length(), ""
                    + numberAsRounded);
        } // end of looping thru all numbers in newExpected

        // Note that the pattern matcher is linked to the original input,
        // not the new StringBuilder we are building.
        Matcher actualMatcher = numberPattern.matcher(actual);

        while (actualMatcher.find()) {
            numberAsString = actualMatcher.group();
            nbrStartIndex = actualMatcher.start();
            nbrEndIndex = actualMatcher.end();
            // we now have captured one number, and know its start and end
            // positions in the input string
            numberAsDecimal = Double.parseDouble(numberAsString);
            numberAsRounded = Math.round(Math.pow(10, decPlacesPrecision)
                    * numberAsDecimal);
            // we now replace the original number with the rounded number (which
            // is now an integer because it was multiplied by
            // 10^decPlacesPrecision)
            newIndex = newActual.indexOf(numberAsString);
            newActual.replace(newIndex, newIndex + numberAsString.length(), ""
                    + numberAsRounded);
        } // end of looping thru all numbers in newActual

        // Now, the StringBuilder variables contain the expected and actual with
        // any numeric values rounded to the specified number of decimal places.
        expectedLength = newExpected.length();
        actualLength = newActual.length();

        // parse the new strings for being acceptable
        expectedIndex = 0;
        actualIndex = 0;
        while ((expectedIndex < expectedLength) && (actualIndex < actualLength)) {
            chFromExpected = newExpected.charAt(expectedIndex);
            chFromActual = newActual.charAt(actualIndex);

            // if we are ignoring whitespace, then we skip over any whitespace
            // characters
            if (ignoreWhitespace) {
                // advance through any whitespaces in the expected
                while ((isWhitespace(chFromExpected))
                        && (expectedIndex < expectedLength - 1)) {
                    expectedIndex++;
                    chFromExpected = newExpected.charAt(expectedIndex);

                }
                // advance through any whitespaces in the actual
                while ((isWhitespace(chFromActual))
                        && (actualIndex < actualLength - 1)) {
                    actualIndex++;
                    chFromActual = newActual.charAt(actualIndex);
                }
            } // end of skipping over whitespace in each string

            if (ignorePunctuation) {
                // advance through any punctuation in the expected
                while ((isPunctuation(chFromExpected))
                        && (expectedIndex < expectedLength - 1)) {
                    expectedIndex++;
                    chFromExpected = newExpected.charAt(expectedIndex);
                }
                // advance through any punctuation in the actual
                while ((isPunctuation(chFromActual))
                        && (actualIndex < actualLength - 1)) {
                    actualIndex++;
                    chFromActual = newActual.charAt(actualIndex);
                }
            } // end of skipping over punctuation in each string

            // re-update current probe locations in each string, and check to
            // see if the strings have been exhausted
            if (expectedIndex < expectedLength)
                chFromExpected = newExpected.charAt(expectedIndex);
            else { // expected has been exhausted
                if (actualIndex < actualLength) {
                    // there are non-ignored characters remaining in actual,
                    // while expected has been exhausted, so the actual is wrong
                    System.out
                            .println("Student output contains one or more extraneous characters at the end");
                    System.out.println("Extraneous characters:"
                            + newActual.substring(actualIndex));
                    return false;
                }
                // we have exhausted the expected and actual together, so the
                // student output is acceptable
                return true;
            }
            // if we get here, then we know there is at least one more
            // unprocessed character in actual which we can compare to the next
            // non-ignored character from expected, which we are probing
            chFromActual = newActual.charAt(actualIndex);
            
            // check to see if we are at the end of both strings, in which case
            // we could be looking at characters which don't match, but are
            // supposed to be ignored
            if ((expectedIndex == expectedLength-1) && (actualIndex == actualLength-1))
            {
                return checkLastCharacters(chFromExpected, chFromActual);
            }

            if (charactersMatch(chFromExpected, chFromActual) == false) {
                // the next non-ignored characters do not match, so the student
                // output is wrong
                System.out
                        .println("Student output contains one or more non-matching characters.");
                System.out
                        .println("Expected output (beginning with first difference):"
                                + newExpected.substring(expectedIndex));
                System.out
                        .println("Actual output (beginning with first difference):"
                                + newActual.substring(actualIndex));
                return false;  // quit out of procedure
            }
            // proceed to next iteration of while loop, because there are still
            // characters remaining in the strings which need to be compared.
            expectedIndex++;
            actualIndex++;

        } // end of while loop
          // if we get here, then the expected and actual have matched (taking
          // into account any relaxed checks)
        return true;
    } // end of isAccaptable
    
}
