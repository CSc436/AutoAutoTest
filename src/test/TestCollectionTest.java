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
    public void testSavingTests() throws Exception {
        collection.newTest();
        TestCase theTest = collection.getTest(0);
        theTest.setMethodName("helloWorld");
        theTest.setExpectedStandardOutput("Hello World!");
        String base = tempDir.toString();
        String path = Paths.get(base, "ExampleTest.java").toString();
        collection.save(path);
        assertTrue(new File(path).exists());
    }

    /**
     * Ensure that a .java file can be written to disk.
     * 
     * @throws Exception
     *             If the test file couldn't be written
     */
    @Test
    public void testSavingTestWithoutJavaExtension() throws Exception {
        collection.newTest();
        String base = tempDir.toString();
        String path = Paths.get(base, "ExampleTest").toString();
        collection.save(path);
        path += ".java";
        assertTrue(new File(path).exists());
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

}
