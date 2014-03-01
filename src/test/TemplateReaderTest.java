package test;

import static org.junit.Assert.assertTrue;
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
     * @throws FileNotFoundException 
     */
    @Test
    public void testCall() throws FileNotFoundException {
        String str = TemplateReader.readCall();
        String expected = 
                    "Thread timeOut = new Thread() {\n" 
                  + "    public void run() {\n"
                  + "        Object returnValue = ClassInstance.METHOD(ARGS);\n"
                  + "    }\n"
                  + "};\n"
                  + "timeOut.start();\n"
                  + "try {Thread.sleep(TimeoutTime);}\n" 
                  + "catch (Exception e) {}\n"
                  + "if(timeOut.isAlive()) {\n"
                  + "    timeOut.stop();\n"
                  + "    fail();\n"
                  + "}";

        System.out.println(str);
        assertTrue(str.equals(expected));
    }

    /**
     * reads the Input.txt file and ensures it was read correctly, prints it out
     * for user verification too.
     * @throws FileNotFoundException 
     */
    @Test
    public void testInput() throws FileNotFoundException {
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
     * @throws FileNotFoundException 
     */
    @Test
    public void testOutput() throws FileNotFoundException {
        String str = TemplateReader.readOutput();
        String expected = "assertEquals(fso.getOutput(), EXPECTED);";
        System.out.println(str);
        assertTrue(str.equals(expected));
    }
    
    /**
     * reads the Return.txt file and ensures it was read correctly, prints it
     * out for user verification too.
     * @throws FileNotFoundException 
     */
    @Test
    public void testReturn() throws FileNotFoundException {
        String str = TemplateReader.readReturn();
        String expected = "assertEquals(returnValue, EXPECTED);";
        System.out.println(str);
        assertTrue(str.equals(expected));
    }

    /**
     * reads the Test.txt file and ensures it was read correctly, prints it out
     * for user verification too.
     * @throws FileNotFoundException 
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
                + "   CLASS ClassInstance = new CLASS();\n"
                + "   CALL_LINE\n"
                + "   RETURN_LINE\n"
                + "   OUTPUT_LINE\n"
                + "}";
        System.out.println(str);
        assertTrue(str.equals(expected));
    }    
    
    
    
    /**
     * reads the Return.txt file and ensures it was read correctly, prints it
     * out for user verification too.
     * @throws FileNotFoundException 
     */
    @Test
    public void useMethodDoReadForCodeCoverage() throws FileNotFoundException {
        String str = TemplateReader.readTemplate("return.txt");
        String expected = "assertEquals(returnValue, EXPECTED);";
        System.out.println(str);
        assertTrue(str.equals(expected));
    }
}
