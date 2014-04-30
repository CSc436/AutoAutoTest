package test;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import model.TestCase;
import model.TestCollection;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author Ricky
 * 
 *         Generates a number of test files to use on StudentSolutionSet1
 */
public class IntegrationTest {

    private static String tempFileName;
    private static File tempDir;

    @BeforeClass
    public static void setUp() throws IOException {
        tempDir = Files.createTempDirectory("AutoAutoTest").toFile();
        Path path = Paths.get(tempDir.getAbsolutePath(), "SampleTests.java");
        tempFileName = path.toString();
    }

    private static String getAntCommand() throws IOException {
        if (System.getProperty("os.name").contains("Windows")) {
            ProcessBuilder pb = new ProcessBuilder("where", "ant");
            Process p = pb.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    p.getInputStream()));
            return reader.readLine();
        }
        return "ant";
    }

    /**
     * Makes a pair of tests for ints, one method should work fine, the other
     * doesn't
     * 
     * @throws Exception
     *             if saving file goes wrong.
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
    }

    /**
     * Makes a pair of tests for strings, one method should work fine, the other
     * doesn't
     * 
     * @throws Exception
     *             if saving file goes wrong.
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
    }

    /**
     * Makes a pair of tests for booleans, one method should work fine, the
     * other doesn't
     * 
     * @throws Exception
     *             if saving file goes wrong.
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
    }

    /**
     * Makes a pair of tests for standard out, one method should work fine, the
     * other doesn't
     * 
     * @throws Exception
     *             if saving file goes wrong.
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
    }

    /**
     * Saves the number of expected bad tests so we can assert them later when
     * testing our generated tests.
     * 
     * @throws Exception
     *             FileNotFoundException and generic Exception due to saving the
     *             test collection and the number of expected failing tests.
     */
    public static void saveGeneratedTests() throws Exception {
        TestCollection collection = TestCollection.getInstance();
        collection.save(tempFileName);
    }

    /**
     * Tests the test by running ant in the res folder with a ProcessBuilder.
     * Then checks the number of failed tests against the number of expected
     * failures.
     * 
     * @throws Exception
     *             Due to reading the input stream of ant from the process, and
     *             saving the TestCollection Class.
     */
    @AfterClass
    public static void testOurTest() throws Exception {
        saveGeneratedTests();
        int actualFailures = 0;
        ProcessBuilder pb = new ProcessBuilder(getAntCommand());
        pb.directory(tempDir);
        Process p = pb.start();
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                p.getInputStream()));
        String line = null;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
            if (line.contains("Failures: ")) {
                String end = line.substring(line.indexOf("Failures: "));
                end = end.substring(10);
                actualFailures = Integer.valueOf(end);
            }
        }
        System.out.println(tempDir);
        tempDir.delete();
        assertEquals(4, actualFailures);
    }

}
