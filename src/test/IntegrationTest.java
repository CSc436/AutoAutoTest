package test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import model.TestCase;
import model.TestCollection;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author Ricky
 *
 * Generates a number of test files to use on StudentSolutionSet1
 */
public class IntegrationTest {
    private static int numBadTests; // this is used for testing our tests
    
    /**
     * This is used to initialize the number of bad tests 
     * that we are expecting. i.e. The number of tests that we 
     * expect to fail.
     */
    @BeforeClass
    public static void initializeNumBadTests() {
        numBadTests = 0;
    }

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
        
        //testCollection.save("res/src/SampleIntTest.java");
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
        testCase.setArgs("\"bannana\"");
        testCase.setClassName("StudentSolutionSet1");
        testCase.setExpectedReturn("\"bannana.com\"");
        testCase.setMethodName("appendCom");
        testCase.setTimeoutTime(1000);
        testCase.setFloatPrecision(2);
        
        TestCase testCase2 = testCollection.newTest();
        testCase2.setTestName("badAppendComTest");
        testCase2.setArgs("\"bannana\"");
        testCase2.setClassName("StudentSolutionSet1");
        testCase2.setExpectedReturn("\"bannana.com\"");
        testCase2.setMethodName("badAppendCom");
        testCase2.setTimeoutTime(1000);
        testCase2.setFloatPrecision(2);
        
        //testCollection.save("res/src/SampleStringTest.java");
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
        
        //testCollection.save("res/src/SampleBooleanTest.java");
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
        testCase2.setExpectedStandardOutput("Hello World!");
        testCase2.setMethodName("badHelloWorld");
        testCase2.setTimeoutTime(1000);
        testCase2.setFloatPrecision(2);
        testCase2.setIsVoid(true);
        
        //testCollection.save("res/src/SampleStandardOutTest.java");
    }
    
    /**
     * Increments the number of bad tests we expect after every test is run.
     * This uses an after tag because this class creates test cases in pairs, 
     * so it is assumed that there is a bad test case generated for every test 
     * that is run.
     */
    @After
    public void incNumBadTests() {
        numBadTests++;
    }
    
    /**
     *Saves the number of expected bad tests so we can assert them later
     *when testing our generated tests. 
     */
    @AfterClass
    public static void saveNumBadTests() {
        TestCollection collection = TestCollection.getInstance();
        try {
            collection.save("res/src/SampleTests.java");
            File pathToRes = new File(System.getProperty("user.dir") 
                    + System.getProperty("file.separator") 
                    + "res" + System.getProperty("file.separator") + "numBadTests.txt");
            PrintWriter saver = new PrintWriter(pathToRes);
            saver.write(Integer.toString(numBadTests));
            saver.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
