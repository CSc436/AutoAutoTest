package model;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

//Carlos Gallego
//Feb 7, 2014

/**
 * 
 * @author Carlos G This class creates a "FakeStandardOutput", as a string so
 *         testing can be easily done between student code correct answer.
 * 
 */
public class FakeStandardOutput extends PrintStream {

    /**
     * This is an instance variable that will get returned.
     */
    private StringOutputStream newS;

    // public static void main(String [] args){
    // FakeStandardOutput newClass = new FakeStandardOutput();
    // System.setOut(newClass);
    // System.out.println("something");
    // }//end of main method

    /**
     * Summary: This is a constructor to create a FakeStandardOutput.
     * @throws UnsupportedEncodingException 
     */
    public FakeStandardOutput() throws UnsupportedEncodingException {
        super(new StringOutputStream(), true, "UTF8");
        newS = (StringOutputStream) super.out;
    } // end of FakeStandardOutput constructor

    /**
     * Summary: This is a print method that will take in an object, and return
     * it as a string.
     * 
     * @param blah
     *            is an object that can be passed in and converted to a string.
     */
    public void print(Object blah) {
        this.print(blah.toString());
    } // end of print with Object method

    /**
     * Summary: This is a print method that will take in an string, and return
     * it as a string.
     * 
     * @param blah
     *            is an object that can be passed in and converted to a string.
     */
    public void print(String blah) {
        try {
            newS.write(blah.getBytes("UTF8"));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    } // end of print with String method

    /**
     * Summary: This is a print method that will take in an int, and return it
     * as a string.
     * 
     * @param blah
     *            is an object that can be passed in and converted to a string.
     */
    public void print(int blah) {
        print(blah + "");
    } // end of print with Int method

    /**
     * Summary: This is a print method that will take in a double, and return it
     * as a string.
     * 
     * @param blah
     *            is an object that can be passed in and converted to a string.
     */
    public void print(double blah) {
        print(blah + "");
    } // end of print with double method

    /**
     * Summary: This is a print method that will take in a float, and return it
     * as a string.
     * 
     * @param blah
     *            is an object that can be passed in and converted to a string.
     */
    public void print(float blah) {
        print(blah + "");
    } // end of print with float method

    /**
     * Summary: This is a print method that will take in a long, and return it
     * as a string.
     * 
     * @param blah
     *            is an object that can be passed in and converted to a string.
     */
    public void print(long blah) {
        print(blah + "");
    } // end of print with long method

    /**
     * Summary: This is a print method that will take in a boolean, and return
     * it as a string.
     * 
     * @param blah
     *            is an object that can be passed in and converted to a string.
     */
    public void print(boolean blah) {
        print(blah + "");
    } // end of print with boolean method

    /**
     * Summary: This is a print method that will take in a char, and return it.
     * as a string.
     * 
     * @param blah
     *            is an object that can be passed in and converted to a string.
     */
    public void print(char blah) {
        print(blah + "");
    } // end of print with char method

    /**
     * Summary: This is a getter method that returns the private string instance
     * variable.
     * 
     * @return String
     */
    public String getOutput() {
        return newS.getString();
    } // end of getOutput method

} // end of FakeStandardOutput
