package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.Scanner;

import org.junit.Test;

import model.FakeStandardIn;

/**
 * Test class for FakeStandardIn.
 * 
 * @author wcohen
 */
public class FakeStdInTest {

    /**
     * Tests creating a FakeStandardIn, loading it with several tokens, and
     * linking it to a Scanner to retrieve the tokens.
     */
    @Test
    public void testFakeStdInWithScanner() {
        FakeStandardIn myFSI = new FakeStandardIn();
        String testString = "Orange\nApple\nStrawberry\n";
        myFSI.setString(testString);

        // re-direct System.in to our FakeStdIn object instead
        System.setIn(myFSI);
        Scanner keyboard = new Scanner(System.in, "UTF8");

        assertTrue(keyboard.hasNext());
        String input = keyboard.next();
        assertEquals("Orange", input);

        assertTrue(keyboard.hasNext());
        input = keyboard.next();
        assertEquals("Apple", input);

        assertTrue(keyboard.hasNext());
        input = keyboard.next();
        assertEquals("Strawberry", input);

        assertFalse(keyboard.hasNext());
        keyboard.close();
    }

    /**
     * Test trying to fill our FakeStandardIn with a null string.
     * 
     * @throws IOException
     *             (This should not happen.)
     */
    @Test
    @SuppressWarnings("resource")
    public void testNullString() throws IOException {
        FakeStandardIn myFSI = new FakeStandardIn();
        myFSI.setString(null);
        byte charRead = (byte) myFSI.read();
        String s = new String(new byte[] {charRead}, "UTF8");
        assertEquals("\n", s);
    }
    
    /**
     * Test trying to fill our FakeStandardIn with an empty string.
     * @throws IOException (This should never happen)
     */
    @Test
    @SuppressWarnings("resource")
    public void testEmptyString() throws IOException {
        FakeStandardIn myFSI = new FakeStandardIn();
        String p = "";
        myFSI.setString(p);
        byte charRead = (byte) myFSI.read();
        String s = new String(new byte[] {charRead}, "UTF8");
        assertEquals("\n", s);
    }

    /**
     * Test attempting to read after the entire buffer contents have been used.
     * 
     * @throws IOException
     *             (This should not happen.)
     */
    @Test
    @SuppressWarnings("resource")
    public void testReadFromExhaustedBuffer() throws IOException {
        FakeStandardIn myFSI = new FakeStandardIn();
        String p = "aaa";
        myFSI.setString(p);
        byte[] ba = new byte[5];
        myFSI.read(ba); // read the only token in the buffer
        // Try reading from the FSI now that the buffer is exhausted
        int charsRead = myFSI.read();
        assertEquals(-1, charsRead);
    }

    /**
     * Test reading the entire buffer contents to a byte array.
     * 
     * @throws IOException
     *             (This should not happen.)
     */
    @Test
    @SuppressWarnings("resource")
    public void testReadToByteArray() throws IOException {
        // Try reading into a byte array which can hold the entire buffer
        FakeStandardIn myFSI = new FakeStandardIn();
        myFSI.setString("lemon\n");
        byte[] ba = new byte[10];

        // read the entire buffer as bytes into ba
        int charsRead = myFSI.read(ba);
        String s = new String(ba, 0, charsRead, "UTF8");

        assertEquals(6, charsRead);
        assertEquals(6, s.length());
        assertEquals("lemon\n", s);
    }

    /**
     * Test reading a token into an array with no empty space.
     * 
     * @throws IOException
     *             (This will never happen.)
     */
    @Test
    @SuppressWarnings("resource")
    public void testReadToEmptyArray() throws IOException {
        FakeStandardIn myFSI = new FakeStandardIn();
        myFSI.setString("peaches\n");
        byte[] dummy = new byte[0];
        int charsRead = myFSI.read(dummy);
        assertEquals(0, charsRead);
    }

    /**
     * Test reading to a valid array, which is too small to hold the entire
     * buffer contents.
     * 
     * @throws IOException
     *             (This should never actually happen.)
     */
    @Test
    @SuppressWarnings("resource")
    public void testReadingToSmallArray() throws IOException {
        FakeStandardIn myFSI = new FakeStandardIn();
        myFSI.setString("Pineapple");
        byte[] ba = new byte[6];
        int charsRead = myFSI.read(ba);
        assertEquals(6, charsRead);
    }

    /**
     * Test asking to read 0 characters into a valid array.
     * 
     * @throws IOException
     *             (This should not happen.)
     */
    @Test
    @SuppressWarnings("resource")
    public void testReading0Chars() throws IOException {
        FakeStandardIn myFSI = new FakeStandardIn();
        myFSI.setString("Raspberry");
        byte[] ba = new byte[6];
        int charsRead = myFSI.read(ba, 4, 0); // test asking to read 0 chars
        assertEquals(0, charsRead);
    }

}

