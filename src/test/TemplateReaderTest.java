package test;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;

import model.TemplateReader;

import org.apache.logging.log4j.LogManager;
import org.junit.Test;

/**
 * Tests the TemplateReader class.
 */
public class TemplateReaderTest {

    /**
     * reads the DummyTemplate.txt file and ensures it was read correctly, 
     * prints it out for user verification too.
     * 
     * @throws FileNotFoundException
     *             (This shouldn't happen)
     */
    @Test
    public void testReading() throws FileNotFoundException {
        String str = TemplateReader.readTemplate("DummyTemplate.txt");
        String expected = 
                    "final Object[] returnValue = new Object[1];\n"
                  + "Thread studentMethodRunner = new Thread() {\n" 
                  + "    public void run() {\n"
                  + "        RUN\n"
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
        LogManager.getRootLogger().info(str);
        assertEquals(expected, str);
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
