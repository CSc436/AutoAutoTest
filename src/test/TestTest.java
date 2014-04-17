package test;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.junit.BeforeClass;
import org.junit.Test;

/**This class is used to test the test we have generated for the student code.
 * It uses a Process builder to run tests in another JVM.
 * @author jeffersd
 */
public class TestTest {
    private static int numberOfExpectedFails;
    
    /**
     * Loads the number of expected failures before the tests 
     * are run.
     */
    @BeforeClass
    public static void loadNumExpectedFails() {
        String pathToRes = System.getProperty("user.dir") 
                + System.getProperty("file.separator") 
                + "res" + System.getProperty("file.separator") + "numBadTests.txt";
        BufferedReader reader;
            try {
                reader = new BufferedReader(new FileReader(pathToRes));
                String line = reader.readLine();
                numberOfExpectedFails = Integer.valueOf(line);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }
    
    /**
     *Tests the test by running ant in the
     *res folder with a ProcessBuilder. Then checks
     *the number of failed tests against the number of 
     *expected failures.
     */
    @Test
    public void testOurTest() {
        try {
            int actualFailures = 0;
            ProcessBuilder pb = new ProcessBuilder("ant");
            File pathToRes = new File(System.getProperty("user.dir") + System.getProperty("file.separator") + "res");
            pb.directory(pathToRes);
            Process p = pb.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line = null;
            while ((line = reader.readLine()) != null) {
                if (line.contains("Failures: ")) {
                    String end = line.substring(line.indexOf("Failures: "));
                    end = end.substring(10);
                    actualFailures = Integer.valueOf(end);
                }
            }
            assertEquals(numberOfExpectedFails, actualFailures);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
