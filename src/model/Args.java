package model;

/**
 * wrapper class for Args to be passed into a method
 * 
 * @author Ricky
 * 
 */
public class Args {

    private String args;

    /**
     * default constructor, sets args to "".
     */
    public Args() {
        args = "";
    }

    /**
     * @param val
     *            sets args to val.
     */
    public Args(String val) {
        args = val;
    }

    /**
     * @return returns args.
     */
    public String getArgsValue() {
        return args;
    }

    /**
     * @param val
     *            set args to value
     */
    public void setArgsValue(String val) {
        args = val;
    }

    /**
     * Just returns the value of args.
     */
    public String toString() {
        return args;
    }
}
