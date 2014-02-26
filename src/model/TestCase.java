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
    private boolean useTimeout;
    private ExpectedReturn expectedReturn;
    private ExpectedStandardOut expectedOutput;
    private StockedStandardInput stockedInput;
    private String className;
    private String methodName;
    private String testName;

    /**
     * Create a new empty test
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
        setUseTimeout(false);
    }

    /**
     * @return The arguments for this TestCase
     */
    public String getArgs() {
        return arguments.getArgsValue();
    }
    
    /**
     * @param newValue The new arguments for the current test
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
     * @param newValue the expected return value for this TestCase
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
     * @param newValue The expected standard out for this TestCase
     */
    public void setExpectedStandardOutput(String newValue) {
        expectedOutput.setStandardOutValue(newValue);
    }

    /**
     * @return The standard input for this TestCase
     */
    public String getStockedInput() {
        return stockedInput.getInputString();
    }
    
    /**
     * @param newValue the standard input for this TestCase
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
     * @param newName the new name of the class
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
     * @param newName the new test name
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
     * @param newName the new method name
     */
    public void setMethodName(String newName) {
        this.methodName = newName;
    }

    /**
     * @return Whether to use timeout or not
     */
    private boolean getUseTimeout() {
        return useTimeout;
    }
    
    /**
     * @param Whether to use timeout or not
     */
    private void setUseTimeout(boolean newValue) {
        this.useTimeout = newValue;
    }
    
    /**
     * @return The code that will test the method based on the parameters
     *         specified.
     */
    @Override
    public String toString() {
        String template = "@Test\n" + "public void TESTNAME() {\n"
                + "    FakeStandardOut fso = new FakeStandardOut();\n"
                + "    System.setOut(fso);\n" + "    INPUT_LINE\n"
                + "    CLASSNAME studentObject = new CLASSNAME();\n"
                + "    Object returnValue = studentObject.METHOD(ARGS);\n"
                + "    RETURN_LINE\n" + "    OUTPUT_LINE\n" + "}\n";
        template = template.replace("RETURN_LINE", expectedReturn.toString());
        template = template.replace("OUTPUT_LINE", expectedOutput.toString());
        template = template.replace("INPUT_LINE", stockedInput.toString());
        template = template.replace("ARGS", arguments.toString());
        template = template.replace("CLASSNAME", className);
        template = template.replace("METHOD", methodName);
        template = template.replace("TESTNAME", testName);
        return template;
    }

}
