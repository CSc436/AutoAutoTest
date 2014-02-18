package test;

import static org.junit.Assert.assertTrue;
import model.Args;
import model.ExpectedReturn;
import model.ExpectedStandardOut;
import model.StockedStandardInput;
import model.TestCase;

import org.junit.Test;

/**
 * Unit/Integration Test for the TestCase class.
 * 
 * @see TestCase
 * 
 * @author Cody
 * 
 */
public class TestCaseTest {

    /**
     * Ensure that the java output of a TestCase has the bare bones of what's
     * required to run the JUnit test.
     */
    @Test
    public void testEmptyTestCase() {
        TestCase emptyCase = new TestCase();
        String javaString = emptyCase.toString();
        assertTrue(javaString.startsWith("@Test"));
        assertTrue(javaString.contains("public void test() {"));
        assertTrue(javaString
                .contains("FakeStandardOut fso = new FakeStandardOut();"));
        assertTrue(javaString.contains("System.setOut(fso);"));
        assertTrue(javaString
                .contains("FakeStandardInput stdin = new FakeStandardInput("));
        assertTrue(javaString.contains("System.setIn(stdin);"));
        assertTrue(javaString.contains("studentObject = "));
        assertTrue(javaString.contains("Object returnValue = "));
        assertTrue(javaString.contains("assertEquals(returnValue,"));
        assertTrue(javaString.contains("assertEquals("));
        assertTrue(javaString.endsWith(");\n}\n"));
    }

    /**
     * Ensure that an expected return value makes it to the Java output.
     */
    @Test
    public void testSettingReturnValue() {
        TestCase returnCase = new TestCase();
        ExpectedReturn expectedReturn = returnCase.getExpectedReturn();
        expectedReturn.setReturnValue("12345678");
        String javaString = returnCase.toString();
        assertTrue(javaString.contains("assertEquals(returnValue, 12345678"));
    }

    /**
     * Ensure that stocked standard input is set inside of the test method.
     */
    @Test
    public void testSettingInput() {
        TestCase inputCase = new TestCase();
        StockedStandardInput input = inputCase.getStockedInput();
        input.setInput("Hello");
        String javaString = inputCase.toString();
        String s;
        s = "FakeStandardInput stdin = new FakeStandardInput(\"Hello\")";
        assertTrue(javaString.contains(s));
    }

    /**
     * Ensure that expected output is checked.
     */
    @Test
    public void testSettingOutput() {
        TestCase outputCase = new TestCase();
        ExpectedStandardOut output = outputCase.getExpectedStandardOutput();
        output.setStandardOutValue("\"Hello\"");
        String javaString = outputCase.toString();
        assertTrue(javaString
                .contains("assertEquals(fos.getString(), \"Hello\");"));
    }

    /**
     * Ensure that correct args are passed to the student function.
     */
    @Test
    public void testSettingArgs() {
        TestCase argsCase = new TestCase();
        Args args = argsCase.getArgs();
        args.setArgsValue("1,2,3");
        String javaString = argsCase.toString();
        assertTrue(javaString.contains("method(1,2,3)"));
    }

    /**
     * Ensure that the class name set is used to construct an object in the Java
     * output.
     */
    @Test
    public void testSettingClassName() {
        TestCase classCase = new TestCase();
        classCase.setClassName("Byte");
        String javaString = classCase.toString();
        assertTrue(javaString.contains("Byte studentObject = new Byte();"));
    }

    /**
     * Ensure that the method name is used to call the method in the Java
     * output.
     */
    @Test
    public void testSettingMethodName() {
        TestCase methodCase = new TestCase();
        methodCase.setMethodName("jump");
        String javaString = methodCase.toString();
        assertTrue(javaString.contains("jump("));
    }

}
