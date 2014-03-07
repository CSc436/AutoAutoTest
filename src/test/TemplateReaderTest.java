package test;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;

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
     * @throws FileNotFoundException (This shouldn't happen)
     */
    @Test
    public void testCall() throws FileNotFoundException {
        String str = TemplateReader.readCall();
        String expected = 
                    "Object returnValue;\n"
                  + "Thread studentMethodRunner = new Thread() {\n" 
                  + "    public void run() {\n"
                  + "        returnValue = ClassInstance.METHOD(ARGS);\n"
                  + "    }\n"
                  + "};\n"
                  + "int timeout = TIMEOUT_TIME;\n"
                  + "studentMethodRunner.start();\n"
                  + "try {Thread.sleep(timeout);}\n" 
                  + "catch (InterruptedException e) {fail();}\n"
                  + "if(studentMethodRunner.isAlive()) {\n"
                  + "    studentMethodRunner.stop();\n"
                  + "    fail();\n"
                  + "}";

        System.out.println(str);
        assertEquals(str, expected);
    }

    /**
     * reads the Input.txt file and ensures it was read correctly, prints it out
     * for user verification too.
     * @throws FileNotFoundException (This shouldn't happen)
     */
    @Test
    public void testInput() throws FileNotFoundException {
        String str = TemplateReader.readInput();
        String expected = 
                "FakeStandardInput fsi = new FakeStandardInput();\n"
                + "fsi.setString(INPUT);\n"
                + "System.setIn(fsi);";
        System.out.println(str);
        assertEquals(expected, str);
    }

    /**
     * reads the Output.txt file and ensures it was read correctly, prints it
     * out for user verification too.
     * @throws FileNotFoundException (This shouldn't happen)
     */
    @Test
    public void testOutput() throws FileNotFoundException {
        String str = TemplateReader.readOutput();
        String expected = "relaxedAssertEquals(fso.getOutput(), EXPECTED);";
        System.out.println(str);
        assertEquals(str, expected);
    }
    
    /**
     * reads the Return.txt file and ensures it was read correctly, prints it
     * out for user verification too.
     * @throws FileNotFoundException (This shouldn't happen)
     */
    @Test
    public void testReturn() throws FileNotFoundException {
        String str = TemplateReader.readReturn();
        String expected = "relaxedAssertEquals(returnValue, EXPECTED);";
        System.out.println(str);
        assertEquals(str, expected);
    }

    /**
     * reads the Test.txt file and ensures it was read correctly, prints it out
     * for user verification too.
     * @throws FileNotFoundException (This shouldn't happen)
     */
    @Test
    public void testTest() throws FileNotFoundException {
        String str = TemplateReader.readTest();
        String expected =                 
                "@Test\n" 
                + "public void NAME() {\n"
                + "   INPUT_LINE\n"
                + "   FakeStandardOutput fso = new FakeStandardOutput();\n"
                + "   System.setOut(fso);\n"
                + "   CLASS classInstance = new CLASS();\n"
                + "   CALL_LINE\n"
                + "   RETURN_LINE\n"
                + "   OUTPUT_LINE\n"
                + "}";
        System.out.println(str);
        assertEquals(str, expected);
    }    
    
    
    
    /**
     * reads the Return.txt file and ensures it was read correctly, prints it
     * out for user verification too.
     * @throws FileNotFoundException (This shouldn't happen)
     */
    @Test
    public void useMethodDoReadForCodeCoverage() throws FileNotFoundException {
        String str = TemplateReader.readTemplate("Return.txt");
        String expected = "relaxedAssertEquals(returnValue, EXPECTED);";
        System.out.println(str);
        assertEquals(str, expected);
    }
    
    /**
     * Asks for the contents of a non-existent file. Should receive a blank.
     */
    @Test
    public void testFileNotFound() {
        String actual = TemplateReader.readTemplate("FileNotFound.txt");
        assertEquals("", actual);
    }
}
