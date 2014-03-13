package test;

import static org.junit.Assert.assertEquals;
import model.ExpectedReturn;
import model.TestingNameConstants;

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
        assertEquals("relaxedAssertEquals(" + value.getReturnValue() + ", "
                + TestingNameConstants.RETURN_VALUE + ");", value.toString());
    }
}
