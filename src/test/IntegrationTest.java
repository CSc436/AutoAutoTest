package test;

import model.TestCase;
import model.TestCollection;

import org.junit.Test;

public class IntegrationTest {

    @Test
    public void makeIntTest() {
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
        
        try {
            testCollection.save("res/src/SampleIntTest.java");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void makeStringTest() {
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
        
        try {
            testCollection.save("res/src/SampleStringTest.java");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void makeBooleanTest() {
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
        
        try {
            testCollection.save("res/src/SampleBooleanTest.java");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void makeStandardOutTest() {
        TestCollection testCollection = TestCollection.getInstance();
        TestCase testCase = testCollection.newTest();
        testCase.setTestName("helloWorldTest");
        testCase.setClassName("StudentSolutionSet1");
        testCase.setExpectedStandardOutput("Hello World!");
        testCase.setMethodName("helloWorld");
        testCase.setTimeoutTime(1000);
        testCase.setFloatPrecision(2);
        
        TestCase testCase2 = testCollection.newTest();
        testCase2.setTestName("badHelloWorldTest");
        testCase2.setClassName("StudentSolutionSet1");
        testCase.setExpectedStandardOutput("Hello World!");
        testCase2.setMethodName("badHelloWorld");
        testCase2.setTimeoutTime(1000);
        testCase2.setFloatPrecision(2);
        try {
            testCollection.save("res/src/SampleStandardOutTest.java");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
