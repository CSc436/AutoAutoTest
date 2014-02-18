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
        className = "Class";
        methodName = "method";
        testName = "test";
    }

    /**
     * @return The Args Object for this TestCase
     */
    public Args getArgs() {
        return arguments;
    }

    /**
     * @return The ExpectedReturn Object for this TestCase
     */
    public ExpectedReturn getExpectedReturn() {
        return expectedReturn;
    }

    /**
     * @return The ExpectedStandardOut Object for this TestCase
     */
    public ExpectedStandardOut getExpectedStandardOutput() {
        return expectedOutput;
    }

    /**
     * @return The StockedStandardInput Object for this TestCase
     */
    public StockedStandardInput getStockedInput() {
        return stockedInput;
    }

    /**
     * @param name
     *            The method to test.
     */
    public void setMethodName(String name) {
        methodName = name;
    }

    /**
     * @param name
     *            The class that contains the method to test.
     */
    public void setClassName(String name) {
        className = name;
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
