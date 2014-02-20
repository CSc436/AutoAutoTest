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

    private ExpectedReturn expectedReturn;
    private String methodName;
    private String className;
    private String testName;

    /**
     * Create a new empty test
     * 
     * Tests should have expected values set before exporting!
     */
    public TestCase() {
        expectedReturn = new ExpectedReturn();
        methodName = "method";
        className = "Class";
        testName = "test";
    }

    /**
     * @param value
     *            The expected return value for this test
     */
    public void setExpectedReturnValue(String value) {
        expectedReturn.setReturnValue(value);
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
                + "    System.setOut(fso);\n"
                + "    FakeStandardIn fsi = new FakeStandardIn(INPUT);\n"
                + "    System.setIn(fsi);\n"
                + "    CLASSNAME studentObject = new CLASSNAME();\n"
                + "    Object returnValue = studentObject.METHOD(ARGS);\n"
                + "    EXPECTED_RETURN_LINE\n"
                + "    assertEquals(EXPECTED_OUTPUT, fso.getString());\n"
                + "}\n";
        template = template.replace("EXPECTED_RETURN_LINE",
                expectedReturn.toString());
        template = template.replace("CLASSNAME", className);
        template = template.replace("METHOD", methodName);
        template = template.replace("TESTNAME", testName);
        return template;
    }

}
