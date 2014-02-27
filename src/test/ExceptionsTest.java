package test;

import java.io.FileNotFoundException;




import model.TemplateReader;
import model.TestCollection;

import org.junit.Test;

/**
 * A class to put all the testing for exceptions so we can get full code
 * coverage and pass the build script.
 * 
 * @author Ricky
 * 
 */
public class ExceptionsTest {

    /**
     * Asks for the contents of a non-existent file. Should receive a blank.
     * @throws FileNotFoundException when file is not found
     */
    @Test(expected = FileNotFoundException.class)
    public void testFileNotFound() throws FileNotFoundException {
       TemplateReader.readTemplate("FileNotFound.txt");
    }
    
    /**
     * Ensure that an exception is thrown when trying to write to a non
     * .java file
     * @throws Exception Because we tried to save a .py file instead of .java
     */
    @Test(expected = RuntimeException.class)
    public void testSavingToNonJavaFile() throws Exception {
        TestCollection.getInstance().save("Lol.py");
    }

}
