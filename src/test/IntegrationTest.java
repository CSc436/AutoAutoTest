package test;

import model.TestCase;
import model.TestCollection;

import org.junit.Test;

public class IntegrationTest {

    @Test
    public void doTest() {
        TestCollection testCollection = TestCollection.getInstance();
        TestCase testCase = testCollection.newTest();
        testCase.setArgs("1");
        testCase.setClassName("StudentSolutionSet1");
        testCase.setExpectedReturn("2");
        testCase.setMethodName("add1");
        testCase.setTimeoutTime(1000);
        testCase.setFloatPrecision(2);
        try {
            testCollection.save("res/src/SampleTest.java");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
