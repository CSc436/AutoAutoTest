package model;


/**
 * Wrapper class for return values. Holds them as strings.
 * 
 * @author Ricky
 */
public class ExpectedReturn {
    private String returnValue;

    /**
     * Default return value of "".
     */
    public ExpectedReturn() {
        returnValue = "";
    }

    /**
     * Sets return value to whatever is supplied.
     * 
     * @param value
     *            the first value.
     */
    public ExpectedReturn(String value) {
        returnValue = value;
    }

    /**
     * @return The return value.
     */
    public String getReturnValue() {
        return returnValue;
    }

    /**
     * @param value
     *            the new value.
     */
    public void setReturnValue(String value) {
        returnValue = value;
    }

    /**
     * @return a string that verifies the return value is the expected value.
     */
    public String toString() {
        String result = TemplateReader.readReturn();
        result = result.replace("EXPECTED", returnValue);
        return result;
    }
}
