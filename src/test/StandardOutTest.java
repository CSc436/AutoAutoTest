package test;

import static org.junit.Assert.assertEquals;
import model.ExpectedStandardOut;

import org.junit.Test;

/**
 * Test for the class ExpectedStandardOut.
 * 
 * @author Ricky
 */
public class StandardOutTest {

    /**
     * test empty constructor.
     */
    @Test
    public void standardOutEmptyConstructorTest() {
        ExpectedStandardOut so = new ExpectedStandardOut();
        assertEquals("", so.getStandardOutValue());
    }

    /**
     * test non-empty constructor.
     */
    @Test
    public void standardOutNonEmptyConstructorTest() {
        ExpectedStandardOut so = new ExpectedStandardOut("a different"
                + System.lineSeparator() + " message");
        assertEquals("a different" + System.lineSeparator() + " message",
                so.getStandardOutValue());
    }

    /**
     * test setter.
     */
    @Test
    public void standardOutSetterTest() {
        ExpectedStandardOut so = new ExpectedStandardOut();

        so.setStandardOutValue("a line of text");
        assertEquals("a line of text", so.getStandardOutValue());
    }

    /**
     * test toString.
     */
    @Test
    public void standardOutToStringTest() {
        ExpectedStandardOut so = new ExpectedStandardOut("a different message");
        String expected = "relaxedAssertEquals(\"a different message\"," 
                + " fso.getOutput(), IS_IGNORE_CASING, IS_IGNORE_WHITESPACE, IS_IGNORE_PUNCTUATION, FLOAT_PRECISION);";
        assertEquals(expected, so.toString());
    }
}
