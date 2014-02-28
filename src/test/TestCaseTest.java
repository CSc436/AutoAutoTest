package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
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
        emptyCase.setTestName("empty");
        assertEquals("empty", emptyCase.getTestName());
        String javaString = emptyCase.toString();
        assertTrue(javaString.startsWith("@Test"));
        assertTrue(javaString.contains("public void empty() {"));
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
        returnCase.setExpectedReturn("12345678");
        assertEquals("12345678", returnCase.getExpectedReturn());
        String javaString = returnCase.toString();
        assertTrue(javaString.contains("assertEquals(returnValue, 12345678"));
    }

    /**
     * Ensure that stocked standard input is set inside of the test method.
     */
    @Test
    public void testSettingInput() {
        TestCase inputCase = new TestCase();
        inputCase.setStockedInput("Hello");
        assertEquals("Hello", inputCase.getStockedInput());
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
        outputCase.setExpectedStandardOutput("\"Hello\"");
        assertEquals("\"Hello\"", outputCase.getExpectedStandardOutput());
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
        argsCase.setArgs("1,2,3");
        assertEquals("1,2,3", argsCase.getArgs());
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
        assertEquals("Byte", classCase.getClassName());
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
        assertEquals("jump", methodCase.getMethodName());
        String javaString = methodCase.toString();
        assertTrue(javaString.contains("jump("));
    }

    /**
     * Ensure that the method name is used to call the method in the Java
     * output.
     */
    @Test
    public void testSettingTimeoutTime() {
        TestCase timeoutCase = new TestCase();
        timeoutCase.setTimeoutTime(2000);
        assertEquals(2000, timeoutCase.getTimeoutTime());
    }

}
