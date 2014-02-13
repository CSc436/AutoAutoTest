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
     *            Constructor sets standardOutValue to the expected output of a student's method.
     */
    public ExpectedStandardOut(String value) {
        standardOutValue = value;
    }

    /**
     * @return The expected output of a student's method.
     */
    public String getStandardOutValue() {
        return standardOutValue;
    }

    /**
     * @param value
     *            The expected output of a student's method.
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
