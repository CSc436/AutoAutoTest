package model;

import org.apache.commons.lang3.StringEscapeUtils;

/**
 * Contains the input string that should be placed in standard in for a given
 * test case.
 * 
 * @author Cody
 */
public class StockedStandardInput {

    private String inputString;

    /**
     * Defaults to nothing in stdin.
     */
    public StockedStandardInput() {
        inputString = "";
    }

    /**
     * @param input
     *            The new string for stdin.
     */
    public void setInput(String input) {
        inputString = input;
    }

    /**
     * @return The string used for stdin.
     */
    public String getInputString() {
        return inputString;
    }

    /**
     * @return The Java code to set the standard in to the desired value.
     */
    @Override
    public String toString() {
        String template = TemplateReader.readInput();
        String escapedInput = StringEscapeUtils.escapeJava(inputString);
        escapedInput = "\"" + escapedInput + "\"";
        template = template.replace("INPUT", escapedInput);
        return template;
    }
}
