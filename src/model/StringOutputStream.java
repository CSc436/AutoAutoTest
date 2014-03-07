package model;

import java.io.IOException;
import java.io.OutputStream;

import org.apache.logging.log4j.LogManager;

/**
 * This is a class that returns a string instance variable that was created
 * using a byte array.
 * 
 * @author Carlos G
 * 
 */
public class StringOutputStream extends OutputStream {

    private String outputBuffer;

    /**
     * This is the constructor for the StringOutputStream class.
     */
    public StringOutputStream() {
        outputBuffer = "";
    }

    /**
     * This is a void method that will take an array of bites, and create a
     * string out of them.
     * 
     * @param array
     *            is the array that is going to be passed into the method.
     */
    public void write(byte[] array) {
        String bytesAsString = "";
        try {
            bytesAsString = new String(array, "UTF8");
        } catch (Exception e) {
            LogManager.getRootLogger().error(
                    "writing error in the void write(byte[] array) method");
        }
        outputBuffer += bytesAsString;
    }

    /**
     * This is a getter that simply returns the string instance variable after
     * it has been manipulated.
     * 
     * @return String
     */
    public String getString() {
        return outputBuffer;
    }

    @Override
    /**
     * This is a write method that was overwritten. It is not used.
     * 
     * @throws IOException
     *             if newStream is not created.
     * @param arg0 
     *          will be used as input, and put into a string, then put into
     *          an instance variable.
     */
    public void write(int arg0) throws IOException {
        char something = (char) arg0;
        outputBuffer += something;
    }

}
