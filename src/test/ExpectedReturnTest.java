package test;

import static org.junit.Assert.assertTrue;
import model.ExpectedReturn;
import model.TestingNameConstants;

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
        assertTrue(value.getReturnValue().equals(""));

        value.setReturnValue("false");
        assertTrue(value.getReturnValue().equals("false"));

        value = new ExpectedReturn("3.14");
        assertTrue(value.getReturnValue().equals("3.14"));

        System.out.println(value.toString());
        assertTrue(value.toString(), value.toString().equals(
                "relaxedAssertEquals(" + TestingNameConstants.RETURN_VALUE
                + ", " + value.getReturnValue() + ");"));
    }
}
