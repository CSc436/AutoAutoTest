package test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import model.FakeStandardOutput;
import model.StringOutputStream;

import org.junit.Test;


/**
 * This is a test class that tests both the FakeStandardOutput and
 * StringOutputStream.
 *            
 * @author Carlos G
 */
public class FakeStandardOutputTest {

    /**
     * Verifies Objects that are printed after replacing standard out end up 
     * in the fake stream rather than the console.
     * 
     * @throws UnsupportedEncodingException
     *             will be thrown if error occurs.
     */
    @Test
    public void testFakeOutputStreamWithObjects()
            throws UnsupportedEncodingException {
        FakeStandardOutput newOutput = new FakeStandardOutput();
        System.setOut(newOutput);
        
        ArrayList<Integer> newList = new ArrayList<Integer>();
        newList.add(1);

        System.out.print(newList);

        System.out.print((Object) "something");
        assertEquals("[1]something", newOutput.getOutput());
    } 

    /**
     * Verifies Strings that are printed after replacing standard out end up 
     * in the fake stream rather than the console
     * 
     * @throws UnsupportedEncodingException
     *             will be thrown if error occurs.
     */
    @Test
    public void testFakeOutputStreamWithStrings()
            throws UnsupportedEncodingException {
        FakeStandardOutput newOutput = new FakeStandardOutput();
        System.setOut(newOutput);

        System.out.print("something");

        System.out.print("somethingelse");
        assertEquals("somethingsomethingelse", newOutput.getOutput());
    } 

    /**
     * Verifies Ints that are printed after replacing standard out end up 
     * in the fake stream rather than the console
     * 
     * @throws UnsupportedEncodingException
     *             will be thrown if error occurs.
     */
    @Test
    public void testFakeOutputStreamWithInts()
            throws UnsupportedEncodingException {
        FakeStandardOutput newOutput = new FakeStandardOutput();
        System.setOut(newOutput);

        System.out.print(5);

        System.out.print(6);
        assertEquals("56", newOutput.getOutput());
    } 

    /**
     * Verifies Doubles that are printed after replacing standard out end up 
     * in the fake stream rather than the console
     * 
     * @throws UnsupportedEncodingException
     *             will be thrown if error occurs.
     */
    @Test
    public void testFakeOutputStreamWithDoubles()
            throws UnsupportedEncodingException {
        FakeStandardOutput newOutput = new FakeStandardOutput();
        System.setOut(newOutput);

        System.out.print(5.0d);

        System.out.print(6.0d);
        assertEquals("5.06.0", newOutput.getOutput());
    } 

    /**
     * Verifies Floats that are printed after replacing standard out end up 
     * in the fake stream rather than the console
     * 
     * @throws UnsupportedEncodingException
     *             will be thrown if error occurs.
     */
    @Test
    public void testFakeOutputStreamWithFloats()
            throws UnsupportedEncodingException {
        FakeStandardOutput newOutput = new FakeStandardOutput();
        System.setOut(newOutput);

        System.out.print(5.0f);

        System.out.print(6.0f);
        assertEquals("5.06.0", newOutput.getOutput());
    } 

    /**
     * Verifies Bytes that are printed after replacing standard out end up 
     * in the fake stream rather than the console
     * 
     * @throws UnsupportedEncodingException
     *             will be thrown if error occurs.
     */
    @Test
    public void testFakeOutputStreamWithBytes()
            throws UnsupportedEncodingException {
        FakeStandardOutput newOutput = new FakeStandardOutput();
        System.setOut(newOutput);

        byte foo = 12;
        System.out.print(foo);

        byte bar = 16;
        System.out.print(bar);
        assertEquals("1216", newOutput.getOutput());
    } 

    /**
     * Verifies Shorts that are printed after replacing standard out end up 
     * in the fake stream rather than the console
     * 
     * @throws UnsupportedEncodingException
     *             will be thrown if error occurs.
     */
    @Test
    public void testFakeOutputStreamWithShorts()
            throws UnsupportedEncodingException {
        FakeStandardOutput newOutput = new FakeStandardOutput();
        System.setOut(newOutput);

        short foo = 12;
        System.out.print(foo);

        short bar = 16;
        System.out.print(bar);
        assertEquals("1216", newOutput.getOutput());
    } 

    /**
     * Verifies Longs that are printed after replacing standard out end up 
     * in the fake stream rather than the console
     * 
     * @throws UnsupportedEncodingException
     *             will be thrown if error occurs.
     */
    @Test
    public void testFakeOutputStreamWithLongs()
            throws UnsupportedEncodingException {
        FakeStandardOutput newOutput = new FakeStandardOutput();
        System.setOut(newOutput);

        System.out.print(5L);

        System.out.print(6L);
        assertEquals("56", newOutput.getOutput());
    } 

    /**
     * Verifies Booleans that are printed after replacing standard out end up 
     * in the fake stream rather than the console
     * 
     * @throws UnsupportedEncodingException
     *             will be thrown if error occurs.
     */
    @Test
    public void testFakeOutputStreamWithBooleans()
            throws UnsupportedEncodingException {
        FakeStandardOutput newOutput = new FakeStandardOutput();
        System.setOut(newOutput);

        System.out.print(false);

        System.out.print(true);
        assertEquals("falsetrue", newOutput.getOutput());
    } 

    /**
     * Verifies Chars that are printed after replacing standard out end up 
     * in the fake stream rather than the console
     * 
     * @throws UnsupportedEncodingException
     *             will be thrown if error occurs.
     */
    @Test
    public void testFakeOutputStreamWithChars()
            throws UnsupportedEncodingException {
        FakeStandardOutput newOutput = new FakeStandardOutput();
        System.setOut(newOutput);

        System.out.print('f');

        System.out.print('g');
        assertEquals("fg", newOutput.getOutput());
    } 

    /**
     * Test method that tests the StringOutputStream method.
     * 
     * @throws IOException
     *             if newStream is not created.
     */
    @Test
    public void testStringOutputStream() throws IOException {
        StringOutputStream newStream = new StringOutputStream();
        byte[] bytesAsString = "1".getBytes("UTF8");
        newStream.write(bytesAsString);
        newStream.write(65);
        assertEquals("1A", newStream.getString());
        newStream.close();
    } 

    /**
     * Test throws an exception in StringOutputStream solely for code coverage.
     * 
     * @throws IOException
     *             will be thrown if stream does not close properly.
     */
    @Test
    public void testThrowingExceptions() throws IOException {
        StringOutputStream newStream = new StringOutputStream();
        newStream.write(null);
        newStream.close();
    }

    /**
     * Test throws an exception in FakeStandardOutput solely for code coverage
     * 
     * @throws UnsupportedEncodingException
     *             will be thrown if error occurs.
     */
    @Test
    public void testThrowingExceptions2() throws UnsupportedEncodingException {
        FakeStandardOutput newStream = null;
        newStream = new FakeStandardOutput();
        newStream.print((String) null);
        newStream.close();
    }

} 
