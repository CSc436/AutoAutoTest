package model;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

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
     */
    @Test
    public void testFakeOutputStreamWithObjects() {
        FakeStandardOutput newOutput = new FakeStandardOutput();
        System.setOut(newOutput);

        System.out.print((Object) 20);
        assertEquals("20", newOutput.getOutput());

        System.out.println((Object) "something");
        assertEquals("20something", newOutput.getOutput());
    } // end of testFakeOutputStreamWithObjects

    /**
     * This is a test that tests insertion of Strings.
     */
    @Test
    public void testFakeOutputStreamWithStrings() {
        FakeStandardOutput newOutput = new FakeStandardOutput();
        System.setOut(newOutput);

        System.out.print("something");
        assertEquals("something", newOutput.getOutput());

        System.out.println("somethingelse");
        assertEquals("somethingsomethingelse", newOutput.getOutput());
    } // end of testFakeOutputStreamWithStrings

    /**
     * This is a test that tests insertion of Ints.
     */
    @Test
    public void testFakeOutputStreamWithInts() {
        FakeStandardOutput newOutput = new FakeStandardOutput();
        System.setOut(newOutput);

        System.out.print(5);
        assertEquals("5", newOutput.getOutput());

        System.out.println(6);
        assertEquals("56", newOutput.getOutput());
    } // end of testFakeOutputStreamWithInts

    /**
     * This is a test that tests insertion of Doubles.
     */
    @Test
    public void testFakeOutputStreamWithDoubles() {
        FakeStandardOutput newOutput = new FakeStandardOutput();
        System.setOut(newOutput);

        System.out.print(5.0d);
        assertEquals("5.0", newOutput.getOutput());

        System.out.println(6.0d);
        assertEquals("5.06.0", newOutput.getOutput());
    } // end of testFkeOutputStreamWithInts

    /**
     * This is a test that tests insertion of Floats.
     */
    @Test
    public void testFakeOutputStreamWithFloats() {
        FakeStandardOutput newOutput = new FakeStandardOutput();
        System.setOut(newOutput);

        System.out.print(5.0f);
        assertEquals("5.0", newOutput.getOutput());

        System.out.println(6.0f);
        assertEquals("5.06.0", newOutput.getOutput());
    } // end of testFkeOutputStreamWithFloats

    /**
     * This is a test that tests insertion of Bytes.
     */
    @Test
    public void testFakeOutputStreamWithBytes() {
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
     */
    @Test
    public void testFakeOutputStreamWithShorts() {
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
     */
    @Test
    public void testFakeOutputStreamWithLongs() {
        FakeStandardOutput newOutput = new FakeStandardOutput();
        System.setOut(newOutput);

        System.out.print(5L);
        assertEquals("5", newOutput.getOutput());

        System.out.println(6L);
        assertEquals("56", newOutput.getOutput());
    } // end of testFkeOutputStreamWithLongs

    /**
     * This is a test that tests insertion of Booleans.
     */
    @Test
    public void testFakeOutputStreamWithBooleans() {
        FakeStandardOutput newOutput = new FakeStandardOutput();
        System.setOut(newOutput);

        System.out.print(false);
        assertEquals("false", newOutput.getOutput());

        System.out.println(true);
        assertEquals("falsetrue", newOutput.getOutput());
    } // end of testFkeOutputStreamWithBooleans

    /**
     * This is a test that tests insertion of Chars.
     */
    @Test
    public void testFakeOutputStreamWithChars() {
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
        @SuppressWarnings("resource")
        StringOutputStream newStream = new StringOutputStream();
        byte[] something = "1".getBytes();
        newStream.write(something);
        assertEquals("1", newStream.getString());
        newStream.write(5);
        assertEquals("1", newStream.getString());
    } // end of testStringOutputStream

} // end of TestFakeStandardOutput
