package model;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;


//Carlos Gallego
//Feb 7, 2014

/**
 * This class creates a "FakeStandardOutput", as a string so
 *         testing can be easily done between student code correct answer.
 * 
 * @author Carlos G 
 * 
 */
public class FakeStandardOutput extends PrintStream {

    /**
     * This is an instance variable that will get returned.
     */
    private StringOutputStream innerStream;

    /**
     * This is a constructor to create a FakeStandardOutput.
     * @throws UnsupportedEncodingException 
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
     * @param blah
     *            is an object that can be passed in and converted to a string.
     */
    public void print(String blah) {
        try {
            innerStream.write(blah.getBytes("UTF8"));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    } 

    /**
     * This is a method that will take in an int, and put it into an
     * instance variable as a string.
     * 
     * @param blah
     *            is an object that can be passed in and converted to a string.
     */
    public void print(int blah) {
        print(blah + "");
    } 

    /**
     * This is a method that will take in a double, and put it into an
     * instance variable as a string.
     * 
     * @param blah
     *            is an object that can be passed in and converted to a string.
     */
    public void print(double blah) {
        print(blah + "");
    }

    /**
     * This is a method that will take in a float, and put it into an
     * instance variable as a string.
     * 
     * @param blah
     *            is an object that can be passed in and converted to a string.
     */
    public void print(float blah) {
        print(blah + "");
    } 

    /**
     * This is a method that will take in a long, and put it into an
     * instance variable as a string.
     * 
     * @param blah
     *            is an object that can be passed in and converted to a string.
     */
    public void print(long blah) {
        print(blah + "");
    }

    /**
     * This is a method that will take in a boolean, and put it into an
     * instance variable as a string.
     * 
     * @param blah
     *            is an object that can be passed in and converted to a string.
     */
    public void print(boolean blah) {
        print(blah + "");
    }

    /**
     * This is a method that will take in a char, and put it into an
     * instance variable as a string.
     * 
     * @param blah
     *            is an object that can be passed in and converted to a string.
     */
    public void print(char blah) {
        print(blah + "");
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
