package test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import model.FakeStandardIn;
import model.TestCollection;

import org.junit.Test;

/**
 * A class to put all the testing for exceptions so we can get full code
 * coverage and pass the build script.
 * 
 * @author Ricky
 * 
 */
public class ExceptionsTest {



    /**
     * Ensure that an exception is thrown when trying to write to a non .java
     * file
     * 
     * @throws Exception
     *             Because we tried to save a .py file instead of .java
     */
    @Test(expected = RuntimeException.class)
    public void testSavingToNonJavaFile() throws Exception {
        TestCollection.getInstance().save("Lol.py");
    }

    /**
     * Ensure that an exception is thrown when trying to write to a non .java
     * file
     * 
     * @throws Exception
     *             Because we tried to save a ..java file instead of .java
     */
    @Test(expected = RuntimeException.class)
    public void testSavingToFileWithTwoDots() throws Exception {
        TestCollection.getInstance().save("Lol..java");
    }
    
    /**
     * Try reading more elements than the size of the array.
     * 
     * @throws IOException
     *             (This should not happen.)
     */
    @SuppressWarnings("resource")
    @Test(expected = IndexOutOfBoundsException.class)
    public void testReadMoreThanArrCanHold() throws IOException {
        FakeStandardIn myFSI = new FakeStandardIn();
        myFSI.setString("Boysenberry");
        byte[] ba = new byte[6];
        myFSI.read(ba, 0, 10);
    }
    
    /**
     * Test trying to read a negative amount of characters.
     * @throws IOException (This should never happen)
     */
    @SuppressWarnings("resource")
    @Test(expected = IndexOutOfBoundsException.class)
    public void testReadWithNegativeLength() throws IOException {
        FakeStandardIn myFSI = new FakeStandardIn();
        myFSI.setString("Coconut");
        byte[] ba = new byte[6];
        myFSI.read(ba, 0, -1);
    }

    /**
     * Test when trying to read to an invalid offset of the destination array.
     * 
     * @throws IOException
     *             (This should not happen.)
     */
    @SuppressWarnings("resource")
    @Test(expected = IndexOutOfBoundsException.class)
    public void testInvalidOffset() throws IOException {
        FakeStandardIn myFSI = new FakeStandardIn();
        myFSI.setString("Strawberry");
        byte[] ba = new byte[6];
        myFSI.read(ba, -2, 2); // test invalid offset
    }

    /**
     * Test reading into an array with no space.
     * 
     * @throws IOException
     *             (This should not happen.)
     */
    @SuppressWarnings("resource")
    @Test(expected = IndexOutOfBoundsException.class)
    public void testInvalidArray() throws IOException {
        FakeStandardIn myFSI = new FakeStandardIn();
        myFSI.setString("Grape");

        byte[] dummy = new byte[0];
        myFSI.read(dummy, 0, 2); // test invalid destination array
    }

    /**
     * Test reading to a null array.
     * 
     * @throws IOException
     *             (This test is designed to cause an exception.)
     */
    @SuppressWarnings("resource")
    @Test(expected = NullPointerException.class)
    public void testReadToNullArray() throws IOException {
        FakeStandardIn myFSI = new FakeStandardIn();
        myFSI.setString("lemon\n");
        int charsRead = 0;

        /* Try reading into a byte array which is null, or has length 0 */
        charsRead = myFSI.read(null); // exception happens here
        assertEquals(0, charsRead);

    }

}
