package model;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


//Carlos Gallego
//Feb 7, 2014

/**
 * This class creates a "FakeStandardOutput", as a string so testing can be
 * easily done between student code correct answer.
 * 
 * @author Carlos G
 */
public class FakeStandardOutput extends PrintStream {

    /**
     * This is an instance variable that will get returned.
     */
    private StringOutputStream innerStream;
    
    private static final Logger log = LogManager.getRootLogger();

    /**
     * Summary: This is a constructor to create a FakeStandardOutput.
     * 
     * @throws UnsupportedEncodingException
     *             if the machine somehow doesn't support UTF8
     */
    public FakeStandardOutput() throws UnsupportedEncodingException {
        super(new StringOutputStream(), true, "UTF8");
        innerStream = (StringOutputStream) super.out;
    } 

    /**
     * This is a method that will take in an object, and put it into an
     * instance variable it as a string.
     * 
     * @param someObj
     *            is an object that can be passed in and converted to a string.
     */
    public void print(Object someObj) {
        this.print(someObj.toString());
    } 

    /**
     * This is a method that will take in an string, and put it into an
     * instance variable it as a string.
     * 
     * @param someString
     *            is an object that can be passed in and converted to a string.
     */
    public void print(String someString) {
        try {
            innerStream.write(someString.getBytes("UTF8"));
        } catch (Exception e) {
            log.error("Exception: void print(String blah) method");
        }
    } 

    /**
     * This is a method that will take in an int, and put it into an
     * instance variable as a string.
     * 
     * @param someInt
     *            is an object that can be passed in and converted to a string.
     */
    public void print(int someInt) {
        print(someInt + "");
    } 

    /**
     * This is a method that will take in a double, and put it into an
     * instance variable as a string.
     * 
     * @param someDouble
     *            is an object that can be passed in and converted to a string.
     */
    public void print(double someDouble) {
        print(someDouble + "");
    }

    /**
     * This is a method that will take in a float, and put it into an
     * instance variable as a string.
     * 
     * @param someFloat
     *            is an object that can be passed in and converted to a string.
     */
    public void print(float someFloat) {
        print(someFloat + "");
    } 

    /**
     * This is a method that will take in a long, and put it into an
     * instance variable as a string.
     * 
     * @param someLong
     *            is an object that can be passed in and converted to a string.
     */
    public void print(long someLong) {
        print(someLong + "");
    }

    /**
     * This is a method that will take in a boolean, and put it into an
     * instance variable as a string.
     * 
     * @param someBoolean
     *            is an object that can be passed in and converted to a string.
     */
    public void print(boolean someBoolean) {
        print(someBoolean + "");
    }

    /**
     * This is a method that will take in a char, and put it into an
     * instance variable as a string.
     * 
     * @param someChar
     *            is an object that can be passed in and converted to a string.
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
