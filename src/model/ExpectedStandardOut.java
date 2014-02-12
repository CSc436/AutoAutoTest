package model;

public class ExpectedStandardOut {

    private String standardOutValue;

    /**
     * Default standard out value of "".
     */
    public ExpectedStandardOut() {
        standardOutValue = "";
    }

    /**
     * @param value
     *            constructor sets standardOutValue to the value supplied.
     */
    public ExpectedStandardOut(String value) {
        standardOutValue = value;
    }

    /**
     * @return The standard out value.
     */
    public String getStandardOutValue() {
        return standardOutValue;
    }

    /**
     * @param value
     *            sets standardOutValue to the value supplied.
     */
    public void setStandardOutValue(String value) {
        standardOutValue = value;
    }

    /**
     * @return assertEquals(TestingNameConstants.STANDARD_OUT_VALUE,
     *         standardOutValue);
     */
    public String toString() {
        return "assertEquals(" + TestingNameConstants.STANDARD_OUT_VALUE + ", "
                + standardOutValue + ");";
    }
}
