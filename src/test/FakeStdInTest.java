package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.Scanner;

import org.junit.Test;

import model.FakeStandardIn;

/**
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
        String testString = "Orange\nApple\nStrawberry\nCherry\nLemon\n";
        testString = testString + "Tangerine\nGrapefruit";

        myFSI.setString(testString);

        System.setIn(myFSI); /*
                              * re-direct System.in to our FakeStdIn object
                              * instead
                              */
        Scanner keyboard = new Scanner(System.in, "UTF8"); /*
                                                            * create fake std in
                                                            * rather than using
                                                            * actual keyboard
                                                            * input
                                                            */

        assertTrue(keyboard.hasNext());

        String input = keyboard.next();
        assertEquals("Orange", input);

        assertTrue(keyboard.hasNext());
        input = keyboard.next();
        assertEquals("Apple", input);

        assertTrue(keyboard.hasNext());
        input = keyboard.next();
        assertEquals("Strawberry", input);

        assertTrue(keyboard.hasNext());
        input = keyboard.next();
        assertEquals("Cherry", input);

        assertTrue(keyboard.hasNext());
        input = keyboard.next();
        assertEquals("Lemon", input);

        assertTrue(keyboard.hasNext());
        input = keyboard.next();
        assertEquals("Tangerine", input);

        assertTrue(keyboard.hasNext());
        input = keyboard.next();
        assertEquals("Grapefruit", input);

        assertFalse(keyboard.hasNext());

    }

    /**
     * Test trying to fill our FakeStandardIn with a null string and an empty.
     * string.
     * 
     * @throws IOException
     *             (This should not happen.)
     */
    @Test
    public void testNullStrgAndEmptyStrg() throws IOException {
        /* test with trying to set the FSI to a null string */
        FakeStandardIn myFSI = new FakeStandardIn();
        myFSI.setString(null);
        byte x = (byte) myFSI.read();
        String s = new String(new byte[] { x }, "UTF8");
        assertEquals("\n", s);

        /* test with trying to set the FSI to an empty string */
        myFSI = new FakeStandardIn();
        String p = "";
        myFSI.setString(p);
        x = (byte) myFSI.read();
        s = new String(new byte[] { x }, "UTF8");
        assertEquals("\n", s);
    }

    /**
     * Test attempting to read after the entire buffer contents have been used.
     * 
     * @throws IOException
     *             (This should not happen.)
     */
    @Test
    public void testReadFromExhaustedBuffer() throws IOException {
        FakeStandardIn myFSI = new FakeStandardIn();
        String p = "aaa";
        myFSI.setString(p);
        byte[] ba = new byte[5];
        // read the only token in the buffer
        myFSI.read(ba);
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
    public void testReadToByteArray() throws IOException {
        /* Try reading into a byte array which can hold the entire buffer */
        FakeStandardIn myFSI = new FakeStandardIn();
        myFSI.setString("lemon\n");
        byte[] ba = new byte[10];
        int charsRead = myFSI.read(ba); // read the entire buffer as bytes into
                                        // ba
        String s = new String(ba, 0, charsRead, "UTF8");
        assertEquals(6, charsRead);
        assertEquals(6, s.length());
        assertEquals("lemon\n", s);
        /* Try reading now that the entire contents of buffer has been used */
        charsRead = myFSI.read(ba);
        assertEquals(-1, charsRead);
    }

    /**
     * Test reading to a null array.
     * 
     * @throws IOException
     *             (This test is designed to cause an exception.)
     */
    @Test(expected = NullPointerException.class)
    public void testReadToNullArray() throws IOException {
        FakeStandardIn myFSI = new FakeStandardIn();
        myFSI.setString("lemon\n");
        int charsRead = 0;

        /* Try reading into a byte array which is null, or has length 0 */
        charsRead = myFSI.read(null); // exception happens here
        assertEquals(0, charsRead);

    }

    /**
     * Test reading a token into an array with no empty space.
     * 
     * @throws IOException
     *             (This will never happen.)
     */
    @Test
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
    public void testReadingToSmallArray() throws IOException {
        /*
         * Try reading into a byte array which will hold SOME but not all of the
         * characters in buffer
         */
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
    public void testReading0Chars() throws IOException {

        FakeStandardIn myFSI = new FakeStandardIn();
        myFSI.setString("Raspberry");
        byte[] ba = new byte[6];
        int charsRead = myFSI.read(ba, 4, 0); // test asking to read 0 chars
        assertEquals(0, charsRead);
    }

    /**
     * Test when trying to read to an invalid offset of the destination array.
     * 
     * @throws IOException
     *             (This should not happen.)
     */
    @Test
    public void testInvalidOffset() throws IOException {
        FakeStandardIn myFSI = new FakeStandardIn();
        myFSI.setString("Strawberry");
        byte[] ba = new byte[6];
        int charsRead = myFSI.read(ba, -2, 2); /* test invalid offset */
        assertEquals(0, charsRead);
    }

    /**
     * Test reading into an array with no space.
     * 
     * @throws IOException
     *             (This should not happen.)
     */
    @Test
    public void testReadFromInvalidArray() throws IOException {
        FakeStandardIn myFSI = new FakeStandardIn();
        myFSI.setString("Grape");

        byte[] dummy = new byte[0];
        int charsRead = myFSI.read(dummy, 0, 2); // test invalid destination
                                                 // array
        assertEquals(0, charsRead);
    }

    /**
     * Try reading more elements than the size of the array.
     * 
     * @throws IOException
     *             (This should not happen.)
     */
    @Test
    public void testReadMoreThanArrCanHold() throws IOException {
        FakeStandardIn myFSI = new FakeStandardIn();
        myFSI.setString("Boysenberry");
        byte[] ba = new byte[6];
        int charsRead = myFSI.read(ba, 0, 10);
        assertEquals(6, charsRead);
    }

}
