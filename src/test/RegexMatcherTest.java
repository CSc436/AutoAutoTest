package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import model.RegexMatcher;

import org.junit.Test;

/**
 * Tests the Regex Matcher class
 */
public class RegexMatcherTest {

    /**
     * Ensure that a basic regex match comes back as true when there's a match
     */
    @Test
    public void testRegexMatchNoMercyWhenCorrect() {
        String regex = ".*answer.*42";
        String actual = "The answer is 42";
        RegexMatcher matcher = new RegexMatcher(false, false, false, 2);
        assertTrue(matcher.matches(regex, actual));
    }
    
    /**
     * Ensure that a basic regex match comes back as false when the output
     * string doesn't conform to the regex.
     */
    @Test
    public void testRegexMatchNoMercyWhenNotCorrect() {
        String regex = ".*answer.*42";
        String actual = "The answer is 41";
        RegexMatcher matcher = new RegexMatcher(false, false, false, 2);
        assertFalse(matcher.matches(regex, actual));
    }
    
    /**
     * Ensure that we can ignore literal spaces.
     */
    @Test
    public void testRegexMatchIgnoringLiteralSpaces() {
        String regex = "The answer is 42";
        String actual = "The a n s w e r is 42 ";
        RegexMatcher matcher = new RegexMatcher(true, false, false, 2);
        assertTrue(matcher.matches(regex, actual));
    }
    
    /**
     * Ensure that we can ignore the whitespace class.
     */
    @Test
    public void testRegexMatchIgnoringWhiteSpaceClass() {
        String regex = "The\\p{Space}answer\\p{Space}+is42";
        String actual = "The answer is 42 ";
        RegexMatcher matcher = new RegexMatcher(true, false, false, 2);
        assertTrue(matcher.matches(regex, actual));
    }

    /**
     * Ensure that we can ignore whitespace with modifiers attached.
     */
    @Test
    public void testRegexMatchIgnoringWhiteSpaceWithModifiers() {
        String regex = "\\s{1:3}.*a\\s?ns\\s+w\\p{Space}+e   r\\s*is\\s42";
        String actual = "The answer is 42 ";
        RegexMatcher matcher = new RegexMatcher(true, false, false, 2);
        assertTrue(matcher.matches(regex, actual));
    }
    
    /**
     * Ensure that whitespace in classes is ignored.
     */
    @Test
    public void testRegexMatchIgnoringWhiteSpaceInClasses() {
        String regex = "[\\s \t]+The answer is 42";
        String actual = "The answer is 42 ";
        RegexMatcher matcher = new RegexMatcher(true, false, false, 2);
        assertTrue(matcher.matches(regex, actual));
    }
    
    /**
     * Ensure that literal punctuation is ignored.
     */
    @Test
    public void testRegexMatchIgnoringLiteralPunctuation() {
        String regex = "\\.an\\,s\\{wer\\:.*42";
        String actual = "answer; is 42.";
        RegexMatcher matcher = new RegexMatcher(false, true, false, 2);
        assertTrue(matcher.matches(regex, actual));
    }

    /**
     * Ensure that punctuation classes are ignored.
     */
    @Test
    public void testRegexMatchIgnoringPunctuationClasses() {
        String regex = "answ\\p{Punct}er.*42";
        String actual = "answer: is 42.";
        RegexMatcher matcher = new RegexMatcher(false, true, false, 2);
        assertTrue(matcher.matches(regex, actual));
    }

    /**
     * Ensure that punctuation with modifiers are ignored.
     */
    @Test
    public void testRegexMatchIgnoringPunctuationWithModifiers() {
        String regex = "\\.*.*ans\\{?w\\p{Punct}+er\\:{1:5}.*42";
        String actual = "answer: is 42.";
        RegexMatcher matcher = new RegexMatcher(false, true, false, 2);
        assertTrue(matcher.matches(regex, actual));
    }

    /**
     * Ensure that punctuation inside of classes is ignored.
     */
    @Test
    public void testRegexMatchIgnoringPunctuationInClasses() {
        String regex = "\\..*an\\,s\\{w\\p{Punct}er\\:.*42";
        String actual = "answer: is 42.";
        RegexMatcher matcher = new RegexMatcher(false, true, false, 2);
        assertTrue(matcher.matches(regex, actual));
    }
    
    /**
     * Ensure that we can ignore differences in casing.
     */
    @Test
    public void testCaseInsensitiveMatch() {
        String regex = ".*answer is 42";
        String actual = "THE ANSWER IS 42";
        RegexMatcher matcher = new RegexMatcher(false, false, true, 2);
        assertTrue(matcher.matches(regex, actual));
    }
    
    /**
     * Ensure that long floats in output are rounded.
     */
    @Test
    public void testRegexMatchRoundsFloatsInActual() {
        String regex = "98.73% of people aren't human";
        String actual = "98.7348295743% of people aren't human";
        RegexMatcher matcher = new RegexMatcher(false, false, false, 2);
        assertTrue(matcher.matches(regex, actual));
    }

    /**
     * Ensure that long floats in the regex are rounded.
     */
    @Test
    public void testRegexMatchRoundsFloatsInRegex() {
        String regex = "98.73439264393% of people aren't human";
        String actual = "98.73% of people aren't human";
        RegexMatcher matcher = new RegexMatcher(false, false, false, 2);
        assertTrue(matcher.matches(regex, actual));
    }
    
}
