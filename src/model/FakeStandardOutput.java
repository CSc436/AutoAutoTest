package model;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import org.apache.logging.log4j.LogManager;

/**
 * This class creates a "FakeStandardOutput", as a string so testing can be
 * easily done between student code correct answer.
 * 
 * @author Carlos G
 */
public class FakeStandardOutput extends PrintStream {

    private StringOutputStream innerStream;

    /**
     * Create a new FakeStandardOutput stream with an empty buffer.
     * 
     * @throws UnsupportedEncodingException
     *             if the machine somehow doesn't support UTF8
     */
    public FakeStandardOutput() throws UnsupportedEncodingException {
        super(new StringOutputStream(), true, "UTF8");
        innerStream = (StringOutputStream) super.out;
    }

    /**
     * Add the given object to the end of the buffer as a string.
     * 
     * @param someObj
     *            The object to append to the end of the buffer.
     */
    public void print(Object someObj) {
        this.print(someObj.toString());
    }

    /**
     * This function appends the given string to the output buffer.
     * 
     * @param someString
     *            the string appended to the end of the buffer.
     */
    public void print(String someString) {
        try {
            innerStream.write(someString.getBytes("UTF8"));
        } catch (Exception e) {
            LogManager.getRootLogger().error(e);
        }
    }

    /**
     * Add the given int to the end of the buffer as a string.
     * 
     * @param someInt
     *            The int to append to the end of the buffer.
     */
    public void print(int someInt) {
        print(someInt + "");
    }

    /**
     * Add the given double to the end of the buffer as a string.
     * 
     * @param someDouble the double to add at the end of the buffer.
     */
    public void print(double someDouble) {
        print(someDouble + "");
    }

    /**
     * Add the given float to the end of the buffer as a string.
     * 
     * @param someFloat the float to add at the end of the buffer.
     */
    public void print(float someFloat) {
        print(someFloat + "");
    }

    /**
     * Add the given long to the end of the buffer as a string.
     * 
     * @param someLong
     *            The long to append to the end of the buffer.
     */
    public void print(long someLong) {
        print(someLong + "");
    }

    /**
     * This is a method that will take in a boolean, and put it into an instance
     * variable as a string.
     * 
     * @param someBoolean
     *            is an object that can be passed in and converted to a string.
     */
    public void print(boolean someBoolean) {
        print(someBoolean + "");
    }

    /**
     * Add the given char to the end of the buffer as a string.
     * 
     * @param someChar
     *            The char to append to the end of the buffer.
     */
    public void print(char someChar) {
        print(someChar + "");
    }

    /**
     * This is a getter method that returns the private string instance
     * variable.
     * 
     * @return String
     */
    public String getOutput() {
        return innerStream.getString();
    }

}
