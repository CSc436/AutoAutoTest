package model;

import java.io.IOException;
import java.io.OutputStream;

/**
 * 
 * @author Carlos G This is a class that returns a string instance variable that
 *         was created using a byte array.
 * 
 */
public class StringOutputStream extends OutputStream {

    private String string1;

    /**
     * Summary: This is the constructor for the StringOutputStream class.
     */
    public StringOutputStream() {
        string1 = "";
    } // end of StringOutputStream

    /**
     * Summary: This is a void method that will take an array of bites, and
     * create a string out of them.
     * 
     * @param array
     *            is the array that is going to be passed into the method.
     */
    public void write(byte[] array) {
        String something = "";
        try {
            something = new String(array, "UTF8");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        string1 += something;
    } // end of write method with array arg

    /**
     * Summary: This is a getter that simply returns the string instance
     * variable after it has been manipulated.
     * 
     * @return String
     */
    public String getString() {
        return string1;
    } // end of getString method

    /**
     * This is a write method that was overwritten. It is not used.
     * 
     * @throws IOException
     *             if newStream is not created.
     * @param arg0 will be used as input, and put into a string, then put into
     *  an instance variable.
     */
    @SuppressWarnings("javadoc")
    @Override
    public void write(int arg0) throws IOException {
        String something = "";
        string1 += something;
    } // end of write method with int arg

} // end of StringOutputStream class
