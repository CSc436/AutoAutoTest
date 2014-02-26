package test;

import static org.junit.Assert.assertTrue;
import model.ExpectedStandardOut;
import model.TestingNameConstants;

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
        assertTrue(so.getStandardOutValue().equals(""));
    }

    /**
     * test non-empty constructor.
     */
    @Test
    public void standardOutNonEmptyConstructorTest() {
        ExpectedStandardOut so = new ExpectedStandardOut(
                "a different\n message");
        assertTrue(so.getStandardOutValue().equals("a different\n message"));
    }

    /**
     * test setter.
     */
    @Test
    public void standardOutSetterTest() {
        ExpectedStandardOut so = new ExpectedStandardOut();

        so.setStandardOutValue("a line of text");
        assertTrue(so.getStandardOutValue().equals("a line of text"));
    }

    /**
     * test toString.
     */
    @Test
    public void standardOutToStringTest() {
        ExpectedStandardOut so = new ExpectedStandardOut(
                "a different\n message");

        assertTrue(so.toString().equals(
                "assertEquals(" + TestingNameConstants.STANDARD_OUT_VALUE
                        + ", " + so.getStandardOutValue() + ");"));
    }
}
