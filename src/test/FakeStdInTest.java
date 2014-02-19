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
     * Performs the first set of unit tests on the FakeStandardIn class.
     */
    @Test
    public void testFakeStdIn() {
        FakeStandardIn myFSI = new FakeStandardIn();
        String testString = "Orange\nApple\nStrawberry\nCherry\nLemon\n";
        testString = testString + "Tangerine\nGrapefruit";

        myFSI.setString(testString);

        System.setIn(myFSI); /*
                              * re-direct System.in to our FakeStdIn object
                              * instead
                              */
        Scanner keyboard = new Scanner(System.in, "UTF8"); /*
                                                    * create fake std in rather
                                                    * than using actual keyboard
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
     * Performs the second set of unit tests on the FakeStandardInput class.
     * 
     * @throws IOException
     *             (This should never actually happen.)
     */
    @Test
    public void testFSI2() throws IOException {
        /* test with trying to set the FSI to a null string */
        FakeStandardIn myFSI = new FakeStandardIn();
        myFSI.setString(null);
        byte x = (byte) myFSI.read();
        String s = new String(new byte[] {x}, "UTF8");
        assertEquals("\n", s);

        /* test with trying to set the FSI to an empty string */
        myFSI = new FakeStandardIn();
        String p = "";
        myFSI.setString(p);
        x = (byte) myFSI.read();
        s = new String(new byte[] {x}, "UTF8");
        assertEquals("\n", s);

        /* Try reading from the FSI now that the buffer is exhausted */
        int charsRead = myFSI.read();
        assertEquals(-1, charsRead);

        /* Try reading into a byte array which can hold the entire buffer */
        myFSI = new FakeStandardIn();
        myFSI.setString("lemon\n");
        byte[] ba = new byte[10];
        charsRead = myFSI.read(ba); // read the entire buffer as bytes into ba
        s = new String(ba, 0, charsRead, "UTF8");
        assertEquals(6, charsRead);
        assertEquals(6, s.length());
        assertEquals("lemon\n", s);

        /* Try reading into a byte array which is null, or has length 0 */
        charsRead = myFSI.read(null);
        assertEquals(0, charsRead);
        byte[] dummy = new byte[0];
        charsRead = myFSI.read(dummy);
        assertEquals(0, charsRead);

        /* Try reading now that the entire contents of buffer has been used */
        charsRead = myFSI.read(ba);
        assertEquals(-1, charsRead);
    }

    /**
     * Performs the third set of unit tests on the FakeStandardInput class.
     * 
     * @throws IOException
     *             (This should never actually happen.)
     */
    @Test
    public void testFSI3() throws IOException {
        /*
         * Try reading into a byte array which will hold SOME but not all of the
         * characters in buffer
         */
        FakeStandardIn myFSI = new FakeStandardIn();
        myFSI.setString("Pineapple");
        byte[] ba = new byte[6];
        int charsRead = myFSI.read(ba);
        assertEquals(6, charsRead);

        myFSI = new FakeStandardIn();
        myFSI.setString("Raspberry");
        charsRead = myFSI.read(ba, 4, 0); /* test asking to read 0 chars */
        assertEquals(0, charsRead);

        charsRead = myFSI.read(ba, -2, 2); /* test invalid offset */
        assertEquals(0, charsRead);

        byte[] dummy = new byte[0];
        charsRead = myFSI.read(dummy, 0, 2); // test invalid destination array
        assertEquals(0, charsRead);

        charsRead = myFSI.read(ba, 0, 10); /*
                                            * try to read more than the array
                                            * can hold
                                            */
        assertEquals(6, charsRead);
    }

}
