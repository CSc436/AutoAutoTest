package model;

/**
 * Class representing one test case.
 * 
 * One method of one class will be tested based on its output and return value.
 * The method will be given input (arguments, and standard input) to produce
 * deterministic and testable values.
 * 
 * @author Cody
 * 
 */
public class TestCase {

    private Args arguments;
    private ExpectedReturn expectedReturn;
    private ExpectedStandardOut expectedOutput;
    private StockedStandardInput stockedInput;
    private String className;
    private String methodName;
    private String testName;
    private boolean ignoreCasing;
    private boolean ignorePunctuation;
    private boolean ignoreWhitespace;
    private boolean isVoid;
    private int timeoutTime;
    private int floatPrecision;

    /**
     * Create a new empty test.
     * 
     * Tests should have expected values set before exporting!
     */
    public TestCase() {
        arguments = new Args();
        expectedOutput = new ExpectedStandardOut();
        expectedReturn = new ExpectedReturn();
        stockedInput = new StockedStandardInput();
        setClassName("Class");
        setMethodName("method");
        setTestName("test");
        setTimeoutTime(1000);
        setIgnoreCasing(false);
        setIgnorePunctuation(false);
        setIgnoreWhitespace(false);
        setIsVoid(false);
    }

    /**
     * @return The arguments for this TestCase
     */
    public String getArgs() {
        return arguments.getArgsValue();
    }

    /**
     * @param newValue
     *            The new arguments for the current test
     */
    public void setArgs(String newValue) {
        arguments.setArgsValue(newValue);
    }

    /**
     * @return The expected return value for this TestCase
     */
    public String getExpectedReturn() {
        return expectedReturn.getReturnValue();
    }

    /**
     * @param newValue
     *            the expected return value for this TestCase
     */
    public void setExpectedReturn(String newValue) {
        expectedReturn.setReturnValue(newValue);
    }

    /**
     * @return The expected standard out for this TestCase
     */
    public String getExpectedStandardOutput() {
        return expectedOutput.getStandardOutValue();
    }

    /**
     * @param newValue
     *            The expected standard out for this TestCase
     */
    public void setExpectedStandardOutput(String newValue) {
        expectedOutput.setStandardOutValue(newValue);
    }

    /**
     * @return Whether to use timeout or not
     */
    public int getTimeoutTime() {
        return timeoutTime;
    }

    /**
     * @param newValue
     *            Whether to use timeout or not
     */
    public void setTimeoutTime(int newValue) {
        this.timeoutTime = newValue;
    }

    /**
     * @return The current number of decimal points to check
     */
    public int getFloatPrecision() {
        return floatPrecision;
    }

    /**
     * @param newValue The new number of decimal points to check
     */
    public void setFloatPrecision(int newValue) {
        floatPrecision = newValue;
    }

    /**
     * @return True if this TestCase will ignore casing in output
     */
    public boolean isIgnoreCasing() {
        return ignoreCasing;
    }

    /**
     * @param newValue
     *            whether or not this TestCase should ignore casing in output
     */
    public void setIgnoreCasing(boolean newValue) {
        this.ignoreCasing = newValue;
    }

    /**
     * @return true if this TestCase will ignore whitespace in strings
     */
    public boolean isIgnoreWhitespace() {
        return ignoreWhitespace;
    }

    /**
     * @param newValue
     *            true to have this TestCase ignore whitespace False otherwise.
     */
    public void setIgnoreWhitespace(boolean newValue) {
        ignoreWhitespace = newValue;
    }

    /**
     * @return the true if punctuation in strings will be ignored
     */
    public boolean isIgnorePunctuation() {
        return ignorePunctuation;
    }

    /**
     * @param newValue
     *            true if punctuation in output strings should be ignored
     */
    public void setIgnorePunctuation(boolean newValue) {
        ignorePunctuation = newValue;
    }

    /**
     * @return The standard input for this TestCase
     */
    public String getStockedInput() {
        return stockedInput.getInputString();
    }

    /**
     * @param newValue
     *            the standard input for this TestCase
     */
    public void setStockedInput(String newValue) {
        stockedInput.setInput(newValue);
    }

    /**
     * @return the class name
     */
    public String getClassName() {
        return className;
    }

    /**
     * @param newName
     *            the new name of the class
     */
    public void setClassName(String newName) {
        this.className = newName;
    }

    /**
     * @return the test name
     */
    public String getTestName() {
        return testName;
    }

    /**
     * @param newName
     *            the new test name
     */
    public void setTestName(String newName) {
        this.testName = newName;
    }

    /**
     * @return the method name
     */
    public String getMethodName() {
        return methodName;
    }

    /**
     * @param newName
     *            the new method name
     */
    public void setMethodName(String newName) {
        this.methodName = newName;
    }
    
    /**
     * 
     * @param newValue the new value
     */
    public void setIsVoid(boolean newValue) {
        this.isVoid = newValue;
    }
    
    /**
     * 
     * @return If the current test returns void.
     */
    public boolean isVoid() {
        return isVoid;
    }
    
    

    /**
     * @return The code that will test the method based on the parameters
     *         specified.
     */
    @Override
    public String toString() {
        String template = TemplateReader.readTest();
        template = template.replace("CALL_LINE", TemplateReader.readCall());
        if (isVoid) {
            template = template.replace("RUN",
                    TemplateReader.readCallRunVoid());
        } else {
            template = template.replace("RUN", TemplateReader.readCallRun());
            template = template.replace("RETURN_LINE",
                    expectedReturn.toString());
        }
        template = template.replace("OUTPUT_LINE", expectedOutput.toString());
        template = template.replace("INPUT_LINE", stockedInput.toString());
        template = template.replace("ARGS", arguments.toString());
        template = template.replace("CLASS", className);
        template = template.replace("METHOD", methodName);
        template = template.replace("NAME", testName);
        template = template.replace("TIMEOUT_TIME", timeoutTime + "");
        template = template.replace("FLOAT_PRECISION", floatPrecision + "");
        template = template.replace("IS_IGNORE_CASING", ignoreCasing + "");
        template = template.replace("IS_IGNORE_WHITESPACE", 
                ignoreWhitespace + "");
        template = template.replace("IS_IGNORE_PUNCTUATION", 
                ignorePunctuation + "");
        template = template.replace("IS_VOID", isVoid + "");
        return template;
    }
}
