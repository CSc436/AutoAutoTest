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
     * reads the Input.txt file and ensures it was read correctly, prints it out
     * for user verification too.
     */
    @Test
    public void testInput() {
        String str = TemplateReader.readInput();
        System.out.println(str);
        assertTrue(str
                .equals("FakeStandardInput fsi = new FakeStandardInput();\n"
                        + "fsi.setText(INPUT);\n" + "System.setIn(fis);"));
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
                .equals("assertEquals(fso.getOutput(), EXPECTED);"));
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
                "@Test\n" 
              + "public void NAME() {\n"
              + "   INPUT_LINE\n"
              + "   FakeStandardOutput fso = new FakeStandardOutput();\n"
              + "   System.setOut(fso);\n"
              + "   CLASS ClassInstance = new CLASS();\n"
              + "   CALL_LINE\n"
              + "   RETURN_LINE\n"
              + "   OUTPUT_LINE\n"
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
