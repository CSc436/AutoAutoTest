package model;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Test for the class ExpectedStandardOut.
 * 
 * @author Ricky
 */
public class StandardOutTest {

    /**
     * very basic tests of the wrapper class.
     */
    @Test
    public void standardOutTest() {

        ExpectedStandardOut so = new ExpectedStandardOut();
        assertTrue(so.getStandardOutValue().equals(""));

        so.setStandardOutValue("a line of text");
        assertTrue(so.getStandardOutValue().equals("a line of text"));

        so = new ExpectedStandardOut("a different\n message");
        assertTrue(so.getStandardOutValue().equals("a different\n message"));

        assertTrue(so.toString().equals(
                "assertEquals(" + TestingNameConstants.STANDARD_OUT_VALUE
                        + ", " + so.getStandardOutValue() + ");"));
    }
}
