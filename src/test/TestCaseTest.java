package test;

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
        String javaString = emptyCase.toString();
        assertTrue(javaString.startsWith("@Test"));
        assertTrue(javaString.contains("public void test() {"));
        assertTrue(javaString
                .contains("FakeStandardOut fso = new FakeStandardOut();"));
        assertTrue(javaString.contains("System.setOut(fso);"));
        assertTrue(javaString
                .contains("FakeStandardIn fsi = new FakeStandardIn("));
        assertTrue(javaString.contains("System.setIn(fsi);"));
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
        returnCase.setExpectedReturnValue("12345678");
        String javaString = returnCase.toString();
        assertTrue(javaString.contains("assertEquals(returnValue, 12345678"));
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
