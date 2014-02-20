package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import model.StockedStandardInput;

import org.junit.Test;

/**
 * Tests for the StockedStandardInput class.
 * 
 * @author Cody
 */
public class StockedStandardInputTest {

    /**
     * Ensure that input is blank by default.
     */
    @Test
    public void testInputIsEmptyByDefualt() {
        StockedStandardInput ssi = new StockedStandardInput();
        assertEquals("", ssi.getInputString());
    }

    /**
     * Make sure that when you set an input string, you get the same input
     * string back.
     */
    @Test
    public void testInputCanBeGetAndSet() {
        StockedStandardInput ssi = new StockedStandardInput();
        ssi.setInput("Hello\n");
        assertEquals("Hello\n", ssi.getInputString());
    }

    /**
     * Ensure that toString produces Java code.
     */
    @Test
    public void testToStringReturnsJavaCode() {
        StockedStandardInput ssi = new StockedStandardInput();
        ssi.setInput("Hello\n");
        String javaCode = ssi.toString();
        assertTrue(javaCode.startsWith(
                "FakeStandardInput stdin = new FakeStandardInput(\"Hello\n\")"
        ));
        assertTrue(javaCode.endsWith("System.setIn(stdin);\n"));
    }
}
