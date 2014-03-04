package model;

import java.io.FileNotFoundException;

import org.apache.commons.lang3.StringEscapeUtils;

/**
 * Wrapper class holds a standard out value as a string. When toString is
 * called, converts the standardOutValue to
 * assertEquals(TestingNameConstants.STANDARD_OUT_VALUE, standardOutValue);
 * 
 * @author Ricky
 * 
 */
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
     *            Constructor sets standardOutValue to the expected output of a
     *            student's method.
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
        try {
            String template = TemplateReader.readOutput();
            String output = StringEscapeUtils.escapeJava(standardOutValue);
            output = "\"" + output + "\"";
            template = template.replace("EXPECTED", output);
            return template;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
