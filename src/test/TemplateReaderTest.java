package test;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;

import model.TemplateReader;

import org.apache.logging.log4j.LogManager;
import org.junit.Test;

/**
 * Tests the TemplateReader class.
 * 
 * @author Ricky Gorowsky
 * 
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
                    "dummy1\n"
                  + "dummy line 2\n"
                  + "some special characters\n"
                  + "!@#$%^&*()";
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
