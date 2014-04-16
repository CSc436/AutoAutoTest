package test;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import org.junit.Test;

/**This class is used to test the test we have generated for the student code.
 * It uses a Process builder to run tests in another JVM.
 * @author jeffersd
 */
public class TestTest {
    private int numberOfExpectedFails = 3;
    
    /**
     *Tests the test with a ProcessBuilder.
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
            StringBuilder builder = new StringBuilder();
            String line = null;
            while ( (line = reader.readLine()) != null) {
                builder.append(line);
                if (line.contains("Failures: ")) {
                    actualFailures = Integer.valueOf(line.substring(line.length()-1));
                }
                builder.append(System.getProperty("line.separator"));
            }
            String result = builder.toString();
            System.out.println(result);
            assertEquals(numberOfExpectedFails, actualFailures);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
