package test;

import static org.junit.Assert.assertTrue;
import model.TemplateReader;
import org.junit.Test;


/**
 * Tests the TemplateReader class.
 * 
 * @author Ricky
 * 
 */
public class TemplateReaderTest {

    /**
     * reads the Call.txt file and ensures it was read correctly, prints it out
     * for user verification too.
     */
    @Test
    public void testCall() {
        String str = TemplateReader.readCall();
        System.out.println(str);
        assertTrue(str
                .equals("Object returnValue = CLASS_INSTANCE.METHOD(ARGS);"));
    }

    /**
     * reads the ClassInstance.txt file and ensures it was read correctly,
     * prints it out for user verification too.
     */
    @Test
    public void testClassInstance() {
        String str = TemplateReader.readClassInstance();
        System.out.println(str);
        assertTrue(str.equals("CLASS CLASS_INSTANCE = new CLASS();"));
    }

    /**
     * reads the Input.txt file and ensures it was read correctly, prints it out
     * for user verification too.
     */
    @Test
    public void testInput() {
        String str = TemplateReader.readInput();
        System.out.println(str);
        assertTrue(str
                .equals("FakeStandardInput fsi = new FakeStandardInput();\n"
                        + "fsi.setText(INPUT);\n" + "System.setOut(fis);"));
    }

    /**
     * reads the Output.txt file and ensures it was read correctly, prints it
     * out for user verification too.
     */
    @Test
    public void testOutput() {
        String str = TemplateReader.readOutput();
        System.out.println(str);
        assertTrue(str
                .equals("FakeStandardOutput fso = new FakeStandardOutput();\n"
                        + "System.setOut(fso);"));
    }

    /**
     * reads the OutputAssertion.txt file and ensures it was read correctly,
     * prints it out for user verification too.
     */
    @Test
    public void testOutputAssertion() {
        String str = TemplateReader.readOutputAssertion();
        System.out.println(str);
        assertTrue(str.equals("assertEquals(fso.getOutput(), EXPECTED);"));
    }

    /**
     * reads the Return.txt file and ensures it was read correctly, prints it
     * out for user verification too.
     */
    @Test
    public void testReturnAssertion() {
        String str = TemplateReader.readReturnAssertion();
        System.out.println(str);
        assertTrue(str.equals("assertEquals(returnValue, EXPECTED);"));
    }

    /**
     * reads the Test.txt file and ensures it was read correctly, prints it out
     * for user verification too.
     */
    @Test
    public void testTest() {
        String str = TemplateReader.readTest();
        System.out.println(str);
        assertTrue(str.equals(
                "@Test\n" + "public void NAME() {\n"
              + "   Input.txt\n"
              + "   Output.txt\n"
              + "   ClassInstance.txt\n"
              + "   Call.txt\n"
              + "   ReturnAssertion.txt\n"
              + "   OutputAssertion.txt\n"
              + "}"
           ));
    }

    /**
     * Asks for the contents of a non-existent file. Should receive a blank.
     */
    @Test
    public void testFileNotFound() {
        String str = TemplateReader.readTemplate("FileNotFound.txt");
        System.out.println(str);
        assertTrue(str.equals(""));
    }
}
