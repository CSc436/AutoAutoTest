package model;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;


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
     * @param someObj
     *            is an object that can be passed in and converted to a string.
     */
    public void print(String someObj) {
        try {
            innerStream.write(someObj.getBytes("UTF8"));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    } 

    /**
     * This is a method that will take in an int, and put it into an
     * instance variable as a string.
     * 
     * @param someObj
     *            is an object that can be passed in and converted to a string.
     */
    public void print(int someObj) {
        print(someObj + "");
    } 

    /**
     * This is a method that will take in a double, and put it into an
     * instance variable as a string.
     * 
     * @param someObj
     *            is an object that can be passed in and converted to a string.
     */
    public void print(double someObj) {
        print(someObj + "");
    }

    /**
     * This is a method that will take in a float, and put it into an
     * instance variable as a string.
     * 
     * @param someObj
     *            is an object that can be passed in and converted to a string.
     */
    public void print(float someObj) {
        print(someObj + "");
    } 

    /**
     * This is a method that will take in a long, and put it into an
     * instance variable as a string.
     * 
     * @param someObj
     *            is an object that can be passed in and converted to a string.
     */
    public void print(long someObj) {
        print(someObj + "");
    }

    /**
     * This is a method that will take in a boolean, and put it into an
     * instance variable as a string.
     * 
     * @param someObj
     *            is an object that can be passed in and converted to a string.
     */
    public void print(boolean someObj) {
        print(someObj + "");
    }

    /**
     * This is a method that will take in a char, and put it into an
     * instance variable as a string.
     * 
     * @param someObj
     *            is an object that can be passed in and converted to a string.
     */
    public void print(char someObj) {
        print(someObj + "");
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
