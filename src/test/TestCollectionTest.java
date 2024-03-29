package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.nio.file.Paths;

import model.TestCase;
import model.TestCollection;

import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Test Class for TestCollection
 * 
 * @author Cody
 */
public class TestCollectionTest {

    private TestCollection collection;
    private static File tempDir;

    /**
     * Create a temporary directory for us to dump file into.
     * 
     * @throws IOException
     *             If unable to create the temporary directory
     */
    @BeforeClass
    public static void createTempDir() throws IOException {
        String baseDir = System.getProperty("java.io.tmpdir");
        String path = Paths.get(baseDir, "AutoAutoTemp").toString();
        tempDir = new File(path);
        tempDir.setWritable(true, false);
        FileUtils.deleteDirectory(tempDir);
        tempDir.delete();
        tempDir.mkdir();
    }

    /**
     * Remove the temporary directory.
     */
    @AfterClass
    public static void removeTempDir() {
        tempDir.delete();
    }

    /**
     * Create a new TestCollection Object before each JUnit test case runs.
     * 
     * @throws Exception
     *             If something goes wrong with reflection
     */
    @Before
    public void resetTestCollection() throws Exception {
        Class<TestCollection> testCollectionClass = TestCollection.class;
        Constructor<TestCollection> constructor;
        constructor = testCollectionClass.getDeclaredConstructor();
        constructor.setAccessible(true);
        collection = constructor.newInstance();
    }

    /**
     * Ensure that the initial test can be added to the collection.
     */
    @Test
    public void testAddingFirstTest() {
        assertEquals(0, collection.testCount());
        collection.newTest();
        assertEquals(1, collection.testCount());
        assertNotNull(collection.getTest(0));
    }

    /**
     * Ensure that many tests can be added to the collection.
     */
    @Test
    public void testAddingManyTests() {
        collection.newTest();
        collection.newTest();
        collection.newTest();
        assertEquals(3, collection.testCount());
        assertNotNull(collection.getTest(0));
        assertNotNull(collection.getTest(1));
        assertNotNull(collection.getTest(2));
    }

    /**
     * Ensure that tests can be removed from the collection.
     */
    @Test
    public void testRemovingTests() {
        collection.newTest();
        collection.newTest();
        collection.newTest();

        collection.removeTest(2);
        assertEquals(2, collection.testCount());

        collection.removeTest(0);
        assertEquals(1, collection.testCount());

        collection.removeTest(0);
        assertEquals(0, collection.testCount());
    }

    /**
     * Ensure that a .java file can be written to disk.
     * 
     * @throws Exception
     *             If the test file couldn't be written
     */
    @Test
    public void testExportingTests() throws Exception {
        collection.newTest();
        TestCase theTest = collection.getTest(0);
        theTest.setMethodName("helloWorld");
        theTest.setExpectedStandardOutput("Hello World!");
        String base = tempDir.toString();
        String savePath = Paths.get(base, "ExampleTest.java").toString();
        String realPath = Paths.get(base, "src", "ExampleTest.java").toString();
        collection.export(savePath);
        assertTrue(new File(realPath).exists());
    }

    /**
     * Ensure that a .java file can be written to disk.
     * 
     * @throws Exception
     *             If the test file couldn't be written
     */
    @Test
    public void testExportingTestWithoutJavaExtension() throws Exception {
        collection.newTest();
        String base = tempDir.toString();
        String savePath = Paths.get(base, "ExampleTest").toString();
        String realPath = Paths.get(base, "src", "ExampleTest").toString();
        collection.export(savePath);
        realPath += ".java";
        assertTrue(new File(realPath).exists());
    }

    /**
     * Ensure that getInstance() actually returns a TestCollection Object.
     */
    @Test
    public void testGetInstanceReturnsATestCollection() {
        TestCollection theCollection = TestCollection.getInstance();
        assertTrue(theCollection instanceof TestCollection);
    }

    /**
     * Ensure that get instance returns the same Object each time
     */
    @Test
    public void testGetInstanceReturnsTheSameInstance() {
        TestCollection collection1 = TestCollection.getInstance();
        TestCollection collection2 = TestCollection.getInstance();
        assertSame(collection1, collection2);
    }
    
    /**
     * Test loading by useing our testSaving function to ensure consistency
     * @throws Exception if anything goes wrong
     */
    @Test
    public void testLoading() throws Exception {
        doSave(); // run save method so we know exactly what we are loading
        resetTestCollection(); // reset so old instance doesn't muddle results
        collection.load("test_output/SaveTest.xml");
        
        TestCase test1 = collection.getTest(0);
        assertEquals("test1", test1.getTestName());
        assertEquals("1", test1.getArgs());
        assertEquals("class1", test1.getClassName());
        assertEquals("2", test1.getExpectedReturn());
        assertEquals("", test1.getExpectedStandardOutput());
        assertEquals(2, test1.getFloatPrecision());
        assertEquals(false, test1.isIgnoreCasing());
        assertEquals(false, test1.isIgnorePunctuation());
        assertEquals(false, test1.isIgnoreWhitespace());
        assertEquals(false, test1.isVoid());
        assertEquals("method1", test1.getMethodName());
        assertEquals(1000, test1.getTimeoutTime());
        
        TestCase test2 = collection.getTest(1);
        assertEquals("test2", test2.getTestName());
        assertEquals("2", test2.getArgs());
        assertEquals("class2", test2.getClassName());
        assertEquals("3", test2.getExpectedReturn());
        assertEquals("", test2.getExpectedStandardOutput());
        assertEquals(3, test2.getFloatPrecision());
        assertEquals(true, test2.isIgnoreCasing());
        assertEquals(true, test2.isIgnorePunctuation());
        assertEquals(true, test2.isIgnoreWhitespace());
        assertEquals(true, test2.isVoid());
        assertEquals("method2", test2.getMethodName());
        assertEquals(1111, test2.getTimeoutTime());
    }
    
    /**
     * Ensure that that .xml extension is automatically added to saved
     * tests if it's missing.
     * 
     * @throws Exception If the tests can't save for whatever reason.
     */
    @Test
    public void testSaveWithoutXMLExtension() throws Exception {
        collection.save("test_output/SaveTest");
        File f = new File("test_output/SaveTest.xml");
        assertTrue(f.exists());
    }

    /**
     * Saves a test, should have no exceptions
     * @throws Exception if the test fails
     */
    private void doSave() throws Exception {
        TestCase test = collection.newTest();
        test.setTestName("test1");
        test.setArgs("1");
        test.setClassName("class1");
        test.setExpectedReturn("2");
        test.setExpectedStandardOutput("");
        test.setFloatPrecision(2);
        test.setIgnoreCasing(false);
        test.setIgnorePunctuation(false);
        test.setIgnoreWhitespace(false);
        test.setIsVoid(false);
        test.setMethodName("method1");
        test.setStockedInput("");
        test.setTimeoutTime(1000);
        
        test = collection.newTest();
        test.setTestName("test2");
        test.setArgs("2");
        test.setClassName("class2");
        test.setExpectedReturn("3");
        test.setExpectedStandardOutput("");
        test.setFloatPrecision(3);
        test.setIgnoreCasing(true);
        test.setIgnorePunctuation(true);
        test.setIgnoreWhitespace(true);
        test.setIsVoid(true);
        test.setMethodName("method2");
        test.setStockedInput("");
        test.setTimeoutTime(1111);
        collection.save("test_output/SaveTest.xml");
    }
}
