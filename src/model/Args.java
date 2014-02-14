package model;

/**
 * Wrapper class for Args to be passed into a method.
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
     *            Constructor sets args to the value supplied.
     */
    public Args(String val) {
        args = val;
    }

    /**
     * @return The arguments set by the user.
     */
    public String getArgsValue() {
        return args;
    }

    /**
     * @param val
     *            Set args to val.
     */
    public void setArgsValue(String val) {
        args = val;
    }

    /**
     * @return The arguments set by the user.
     */
    public String toString() {
        return args;
    }
}
