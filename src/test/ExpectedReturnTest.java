package test;

import static org.junit.Assert.assertEquals;
import model.ExpectedReturn;
import org.apache.logging.log4j.LogManager;
import org.junit.Test;

/**
 * Test the class ExpectedReturn.
 * 
 * @author Ricky
 * 
 */
public class ExpectedReturnTest {

    /**
     * very basic tests of the wrapper class.
     */
    @Test
    public void returnValuesTest() {

        ExpectedReturn value = new ExpectedReturn();
        assertEquals("", value.getReturnValue());

        value.setReturnValue("false");
        assertEquals("false", value.getReturnValue());

        value = new ExpectedReturn("3.14");
        assertEquals("3.14", value.getReturnValue());
        LogManager.getRootLogger().info(value.toString());
        assertEquals(
                "if (!relaxedAssertEquals("
                        + value.getReturnValue()
                        + ", "
                        + "returnValue[0]"
                        + ", IS_IGNORE_CASING, IS_IGNORE_WHITESPACE, "
                        + "IS_IGNORE_PUNCTUATION, FLOAT_PRECISION)) {\n"
                        + "  testFailed = true;\n}",
                value.toString());
    }
}
