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
        String expected = "Object returnValue = ClassInstance.METHOD(ARGS);";
        System.out.println(str);
        assertTrue(str.equals(expected));
    }

    /**
     * reads the Input.txt file and ensures it was read correctly, prints it out
     * for user verification too.
     */
    @Test
    public void testInput() {
        String str = TemplateReader.readInput();
        String expected = 
                "FakeStandardInput fsi = new FakeStandardInput();\n"
                + "fsi.setString(INPUT);\n" 
                + "System.setIn(fis);";
        System.out.println(str);
        assertTrue(str.equals(expected));
    }

    /**
     * reads the Output.txt file and ensures it was read correctly, prints it
     * out for user verification too.
     */
    @Test
    public void testOutput() {
        String str = TemplateReader.readOutput();
        String expected = "assertEquals(fso.getOutput(), EXPECTED);";
        System.out.println(str);
        assertTrue(str.equals(expected));
    }
    
    /**
     * reads the Return.txt file and ensures it was read correctly, prints it
     * out for user verification too.
     */
    @Test
    public void testReturn() {
        String str = TemplateReader.readReturn();
        String expected = "assertEquals(returnValue, EXPECTED);";
        System.out.println(str);
        assertTrue(str.equals(expected));
    }

    /**
     * reads the Test.txt file and ensures it was read correctly, prints it out
     * for user verification too.
     */
    @Test
    public void testTest() {
        String str = TemplateReader.readTest();
        String expected =                 
                "@Test\n" 
                + "public void NAME() {\n"
                + "   INPUT_LINE\n"
                + "   FakeStandardOutput fso = new FakeStandardOutput();\n"
                + "   System.setOut(fso);\n"
                + "   CLASS ClassInstance = new CLASS();\n"
                + "   CALL_LINE\n"
                + "   RETURN_LINE\n"
                + "   OUTPUT_LINE\n"
                + "}";
        System.out.println(str);
        assertTrue(str.equals(expected));
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
