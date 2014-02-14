package model;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.junit.Test;

/**
 * @author Carlos G
 * 
 *         This is a test class that tests both the FakeStandardOutput and
 *         StringOutputStream.
 * 
 */
public class FakeStandardOutputTest {

    /**
     * This is a test that tests insertion of Objects.
     * 
     * @throws UnsupportedEncodingException will be thrown if error occurs.
     */
    @Test
    public void testFakeOutputStreamWithObjects()
            throws UnsupportedEncodingException {
        FakeStandardOutput newOutput = new FakeStandardOutput();
        System.setOut(newOutput);

        System.out.print((Object) 20);
        assertEquals("20", newOutput.getOutput());

        System.out.println((Object) "something");
        assertEquals("20something", newOutput.getOutput());
    } // end of testFakeOutputStreamWithObjects

    /**
     * This is a test that tests insertion of Strings.
     * 
     * @throws UnsupportedEncodingException will be thrown if error occurs.
     */
    @Test
    public void testFakeOutputStreamWithStrings()
            throws UnsupportedEncodingException {
        FakeStandardOutput newOutput = new FakeStandardOutput();
        System.setOut(newOutput);

        System.out.print("something");
        assertEquals("something", newOutput.getOutput());

        System.out.println("somethingelse");
        assertEquals("somethingsomethingelse", newOutput.getOutput());
    } // end of testFakeOutputStreamWithStrings

    /**
     * This is a test that tests insertion of Ints.
     * 
     * @throws UnsupportedEncodingException will be thrown if error occurs.
     */
    @Test
    public void testFakeOutputStreamWithInts()
            throws UnsupportedEncodingException {
        FakeStandardOutput newOutput = new FakeStandardOutput();
        System.setOut(newOutput);

        System.out.print(5);
        assertEquals("5", newOutput.getOutput());

        System.out.println(6);
        assertEquals("56", newOutput.getOutput());
    } // end of testFakeOutputStreamWithInts

    /**
     * This is a test that tests insertion of Doubles.
     * 
     * @throws UnsupportedEncodingException will be thrown if error occurs.
     */
    @Test
    public void testFakeOutputStreamWithDoubles()
            throws UnsupportedEncodingException {
        FakeStandardOutput newOutput = new FakeStandardOutput();
        System.setOut(newOutput);

        System.out.print(5.0d);
        assertEquals("5.0", newOutput.getOutput());

        System.out.println(6.0d);
        assertEquals("5.06.0", newOutput.getOutput());
    } // end of testFkeOutputStreamWithInts

    /**
     * This is a test that tests insertion of Floats.
     * 
     * @throws UnsupportedEncodingException will be thrown if error occurs.
     */
    @Test
    public void testFakeOutputStreamWithFloats()
            throws UnsupportedEncodingException {
        FakeStandardOutput newOutput = new FakeStandardOutput();
        System.setOut(newOutput);

        System.out.print(5.0f);
        assertEquals("5.0", newOutput.getOutput());

        System.out.println(6.0f);
        assertEquals("5.06.0", newOutput.getOutput());
    } // end of testFkeOutputStreamWithFloats

    /**
     * This is a test that tests insertion of Bytes.
     * 
     * @throws UnsupportedEncodingException will be thrown if error occurs.
     */
    @Test
    public void testFakeOutputStreamWithBytes()
            throws UnsupportedEncodingException {
        FakeStandardOutput newOutput = new FakeStandardOutput();
        System.setOut(newOutput);

        Byte foo = 12;
        System.out.print(foo);
        assertEquals("12", newOutput.getOutput());

        Byte bar = 16;
        System.out.println(bar);
        assertEquals("1216", newOutput.getOutput());
    } // end of testFkeOutputStreamWithBytes

    /**
     * This is a test that tests insertion of Shorts.
     * 
     * @throws UnsupportedEncodingException will be thrown if error occurs.
     */
    @Test
    public void testFakeOutputStreamWithShorts()
            throws UnsupportedEncodingException {
        FakeStandardOutput newOutput = new FakeStandardOutput();
        System.setOut(newOutput);

        short foo = 12;
        System.out.print(foo);
        assertEquals("12", newOutput.getOutput());

        short bar = 16;
        System.out.println(bar);
        assertEquals("1216", newOutput.getOutput());
    } // end of testFkeOutputStreamWithShorts

    /**
     * This is a test that tests insertion of Longs.
     * 
     * @throws UnsupportedEncodingException will be thrown if error occurs.
     */
    @Test
    public void testFakeOutputStreamWithLongs()
            throws UnsupportedEncodingException {
        FakeStandardOutput newOutput = new FakeStandardOutput();
        System.setOut(newOutput);

        System.out.print(5L);
        assertEquals("5", newOutput.getOutput());

        System.out.println(6L);
        assertEquals("56", newOutput.getOutput());
    } // end of testFkeOutputStreamWithLongs

    /**
     * This is a test that tests insertion of Booleans.
     * 
     * @throws UnsupportedEncodingException will be thrown if error occurs.
     */
    @Test
    public void testFakeOutputStreamWithBooleans()
            throws UnsupportedEncodingException {
        FakeStandardOutput newOutput = new FakeStandardOutput();
        System.setOut(newOutput);

        System.out.print(false);
        assertEquals("false", newOutput.getOutput());

        System.out.println(true);
        assertEquals("falsetrue", newOutput.getOutput());
    } // end of testFkeOutputStreamWithBooleans

    /**
     * This is a test that tests insertion of Chars.
     * 
     * @throws UnsupportedEncodingException will be thrown if error occurs.
     */
    @Test
    public void testFakeOutputStreamWithChars()
            throws UnsupportedEncodingException {
        FakeStandardOutput newOutput = new FakeStandardOutput();
        System.setOut(newOutput);

        System.out.print('f');
        assertEquals("f", newOutput.getOutput());

        System.out.println('g');
        assertEquals("fg", newOutput.getOutput());
    } // end of testFkeOutputStreamWithChars

    /**
     * Test method that tests the StringOutputStream method.
     * 
     * @throws IOException
     *             if newStream is not created.
     */
    @Test
    public void testStringOutputStream() throws IOException {
        StringOutputStream newStream = new StringOutputStream();
        byte[] something = "1".getBytes("UTF8");
        newStream.write(something);
        assertEquals("1", newStream.getString());
        newStream.write(5);
        assertEquals("1", newStream.getString());
        newStream.close();
    } // end of testStringOutputStream

    /**
     * Test throws an exception in StringOutputStream solely for code coverage.
     * @throws IOException will be thrown if stream does not close properly.
     */
    @Test
    public void testThrowingExceptions() throws IOException {
        StringOutputStream newStream = new StringOutputStream();
        newStream.write(null);
        newStream.close();
    } // end of testThrowingExceptions

    /**
     * Test throws an exception in FakeStandardOutput solely for code coverage
     * @throws UnsupportedEncodingException will be thrown if error occurs.
     */
    @Test
    public void testThrowingExceptions2() throws UnsupportedEncodingException {
        FakeStandardOutput newStream = null;
        newStream = new FakeStandardOutput();
        newStream.print((String) null);
        newStream.close();
    } // end of testThrowingExceptions2

} // end of TestFakeStandardOutput
