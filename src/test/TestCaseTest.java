package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotEquals;
import model.TestCase;

import org.apache.logging.log4j.LogManager;
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
        LogManager.getRootLogger().info(javaString);
        assertTrue(javaString.startsWith("@Test"));
        assertTrue(javaString.contains("public void empty() {"));
        assertTrue(javaString.contains(
                "FakeStandardOutput fso = new FakeStandardOutput();"
        ));
        assertTrue(javaString.contains("System.setOut(fso);"));
        assertTrue(javaString
                .contains("FakeStandardIn fsi = new FakeStandardIn("));
        assertTrue(javaString.contains("System.setIn(fsi);"));
        assertTrue(javaString.contains("classInstance = "));
        assertTrue(javaString.contains("returnValue = "));
        assertTrue(javaString.contains("relaxedAssertEquals(, returnValue"));
        assertTrue(javaString.contains("relaxedAssertEquals("));
        assertTrue(javaString.endsWith(");\n}"));
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
        assertTrue(javaString
                .contains("relaxedAssertEquals(12345678, returnValue"));
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
        s = "fsi.setString(\"Hello\")";
        assertTrue(javaString.contains(s));
    }

    /**
     * Ensure that expected output is checked.
     */
    @Test
    public void testSettingOutput() {
        TestCase outputCase = new TestCase();
        outputCase.setExpectedStandardOutput("Hello");
        assertEquals("Hello", outputCase.getExpectedStandardOutput());
        String javaString = outputCase.toString();
        LogManager.getRootLogger().info(javaString);
        assertTrue(javaString
                .contains("relaxedAssertEquals(\"Hello\", fso.getOutput()"));
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
        assertTrue(javaString,
                javaString.contains("Byte classInstance = new Byte();"));
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
     * Ensure that timeout can be set and fetched.
     */
    @Test
    public void testSettingTimeoutTime() {
        TestCase timeoutCase = new TestCase();
        timeoutCase.setTimeoutTime(2000);
        assertEquals(2000, timeoutCase.getTimeoutTime());
    }

    /**
     * Ensure that ignore casing flag can be set and fetched.
     */
    @Test
    public void testGettingAndSettingCasingFlag() {
        TestCase casingCase = new TestCase();
        casingCase.setIgnoreCasing(true);
        assertTrue(casingCase.isIgnoreCasing());
        casingCase.setIgnoreCasing(false);
        assertFalse(casingCase.isIgnoreCasing());
    }

    /**
     * Ensure that ignore whitespace flag can be set and fetched.
     */
    @Test
    public void testGettingAndSettingWhitespaceFlag() {
        TestCase whitespaceCase = new TestCase();
        whitespaceCase.setIgnoreWhitespace(true);
        assertTrue(whitespaceCase.isIgnoreWhitespace());
        whitespaceCase.setIgnoreWhitespace(false);
        assertFalse(whitespaceCase.isIgnoreWhitespace());
    }

    /**
     * Ensure that ignore punctuation flag can be set and fetched.
     */
    @Test
    public void testGettingAndSettingPunctuationFlag() {
        TestCase punctuationCase = new TestCase();
        punctuationCase.setIgnorePunctuation(true);
        assertTrue(punctuationCase.isIgnorePunctuation());
        punctuationCase.setIgnorePunctuation(false);
        assertFalse(punctuationCase.isIgnorePunctuation());
    }
    
    /**
     * Ensure that the void return flag can be set and fetched.
     */
    @Test
    public void testGettingAndSettingVoidFlag() {
        TestCase voidCase = new TestCase();
        voidCase.setIsVoid(true);
        assertTrue(voidCase.isVoid());
        voidCase.setIsVoid(false);
        assertFalse(voidCase.isVoid());
    }
    
    /**
     * Ensure the floating point precision can be set and fetched.
     */
    @Test
    public void testGettingAndSettingFloatPrecision() {
        TestCase precisionCase = new TestCase();
        precisionCase.setFloatPrecision(5);
        assertEquals(5, precisionCase.getFloatPrecision());
        precisionCase.setFloatPrecision(3);
        assertEquals(3, precisionCase.getFloatPrecision());
    }
    
    /**
     * Tests the toString method in the Test Case
     * for a method that returns void.
     */
    @Test
    public void testVoidtoString() {
        TestCase voidCase = new TestCase();
        voidCase.setIsVoid(true);
        String voidString = voidCase.toString();
        voidCase.setIsVoid(false);
        String notVoidString = voidCase.toString();
        assertNotEquals(voidString, notVoidString);
    }

}
