package test;

import model.TestCase;
import model.TestCollection;

import org.junit.Test;

/**
 * @author Ricky
 *
 * Generates a number of test files to use on StudentSolutionSet1
 */
public class IntegrationTest {

    /**
     * Makes a pair of tests for ints, 
     * one method should work fine, the other doesn't
     * @throws Exception if saving file goes wrong.
     */
    @Test
    public void makeIntTest() throws Exception {
        TestCollection testCollection = TestCollection.getInstance();
        TestCase testCase = testCollection.newTest();
        testCase.setTestName("add1Test");
        testCase.setArgs("1");
        testCase.setClassName("StudentSolutionSet1");
        testCase.setExpectedReturn("2");
        testCase.setMethodName("add1");
        testCase.setTimeoutTime(1000);
        testCase.setFloatPrecision(2);
        
        TestCase testCase2 = testCollection.newTest();
        testCase2.setTestName("badAdd1Test");
        testCase2.setArgs("1");
        testCase2.setClassName("StudentSolutionSet1");
        testCase2.setExpectedReturn("2");
        testCase2.setMethodName("badAdd1");
        testCase2.setTimeoutTime(1000);
        testCase2.setFloatPrecision(2);
        
        testCollection.save("res/src/SampleIntTest.java");
    }
    
    /**
     * Makes a pair of tests for strings, 
     * one method should work fine, the other doesn't
     * @throws Exception if saving file goes wrong.
     */
    @Test
    public void makeStringTest() throws Exception {
        TestCollection testCollection = TestCollection.getInstance();
        TestCase testCase = testCollection.newTest();
        testCase.setTestName("appendComTest");
        testCase.setArgs("bannana");
        testCase.setClassName("StudentSolutionSet1");
        testCase.setExpectedReturn("bannana.com");
        testCase.setMethodName("appendCom");
        testCase.setTimeoutTime(1000);
        testCase.setFloatPrecision(2);
        
        TestCase testCase2 = testCollection.newTest();
        testCase2.setTestName("badAppendComTest");
        testCase2.setArgs("bannana");
        testCase2.setClassName("StudentSolutionSet1");
        testCase2.setExpectedReturn("bannana.com");
        testCase2.setMethodName("badAppendCom");
        testCase2.setTimeoutTime(1000);
        testCase2.setFloatPrecision(2);
        
        testCollection.save("res/src/SampleStringTest.java");
    }
    
    /**
     * Makes a pair of tests for booleans, 
     * one method should work fine, the other doesn't
     * @throws Exception if saving file goes wrong.
     */
    @Test
    public void makeBooleanTest() throws Exception {
        TestCollection testCollection = TestCollection.getInstance();
        TestCase testCase = testCollection.newTest();
        testCase.setTestName("oppositeTest");
        testCase.setArgs("false");
        testCase.setClassName("StudentSolutionSet1");
        testCase.setExpectedReturn("true");
        testCase.setMethodName("opposite");
        testCase.setTimeoutTime(1000);
        testCase.setFloatPrecision(2);
        
        TestCase testCase2 = testCollection.newTest();
        testCase2.setTestName("badOppositeTest");
        testCase2.setArgs("false");
        testCase2.setClassName("StudentSolutionSet1");
        testCase2.setExpectedReturn("true");
        testCase2.setMethodName("badOpposite");
        testCase2.setTimeoutTime(1000);
        testCase2.setFloatPrecision(2);
        
        testCollection.save("res/src/SampleBooleanTest.java");
    }
    
    /**
     * Makes a pair of tests for standard out, 
     * one method should work fine, the other doesn't
     * @throws Exception if saving file goes wrong.
     */
    @Test
    public void makeStandardOutTest() throws Exception {
        TestCollection testCollection = TestCollection.getInstance();
        TestCase testCase = testCollection.newTest();
        testCase.setTestName("helloWorldTest");
        testCase.setClassName("StudentSolutionSet1");
        testCase.setExpectedStandardOutput("Hello World!");
        testCase.setMethodName("helloWorld");
        testCase.setTimeoutTime(1000);
        testCase.setFloatPrecision(2);
        testCase.setIsVoid(true);
        
        TestCase testCase2 = testCollection.newTest();
        testCase2.setTestName("badHelloWorldTest");
        testCase2.setClassName("StudentSolutionSet1");
        testCase.setExpectedStandardOutput("Hello World!");
        testCase2.setMethodName("badHelloWorld");
        testCase2.setTimeoutTime(1000);
        testCase2.setFloatPrecision(2);
        testCase.setIsVoid(true);
        
        testCollection.save("res/src/SampleStandardOutTest.java");
    }
}
