package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * TemplateReader reads somes text files and returns them as strings. The files
 * are templates for a generic test.
 * 
 * @author Ricky
 * 
 */
public class TemplateReader {

    private static TemplateReader templateReader = new TemplateReader();

    /**
     * private constructor prevents template readers from being made.
     */
    private TemplateReader() {

    }

    /**
     * return value is "" if file not found.
     * 
     * @return The contents of Call.txt
     * @throws FileNotFoundException If Call.txt doesn't exist
     */
    public static String readCall() throws FileNotFoundException {
        return templateReader.doRead("Call.txt");
    }

    /**
     * return value is "" if file not found.
     * 
     * @return The contents of Input.txt
     * @throws FileNotFoundException  if Input.txt doesn't exist
     */
    public static String readInput() throws FileNotFoundException {
        return templateReader.doRead("Input.txt");
    }

    /**
     * return value is "" if file not found.
     * 
     * @return The contents of Output.txt
     * @throws FileNotFoundException If Output.txt doesn't exist
     */
    public static String readOutput() throws FileNotFoundException {
        return templateReader.doRead("Output.txt");
    }

    /**
     * return value is "" if file not found.
     * 
     * @return The contents of Return.txt
     * @throws FileNotFoundException If Return.txt doesn't exist
     */
    public static String readReturn() throws FileNotFoundException {
        return templateReader.doRead("Return.txt");
    }
    
    /**
     * return value is "" if file not found.
     * 
     * @return The contents of Test.txt
     * @throws FileNotFoundException If Test.txt doesn't exist
     */
    public static String readTest() throws FileNotFoundException {
        return templateReader.doRead("Test.txt");
    }

    /**
     * It is preferable to use the non-parameterized methods, but this is here
     * just in case.
     * 
     * @param inputFile
     *            The file to read.
     * @return The file as a string. If file is not found, returns "".
     * @throws FileNotFoundException If the inputFile doesn't exist
     */
    public static String readTemplate(String inputFile)
            throws FileNotFoundException {
        return templateReader.doRead(inputFile);
    }

    /**
     * @param inputFile
     *            The file to read.
     * @return The file as a string. If file is not found, returns "".
     * @throws FileNotFoundException 
     */
    private String doRead(String inputFile) throws FileNotFoundException {
        // add directory to path
        inputFile = Paths.get("Templates", inputFile).toString();
        Scanner scanner = new Scanner(new File(inputFile), "UTF8");

        String str = "";
        while (scanner.hasNextLine()) {
            str = str.concat(scanner.nextLine() + "\n");
        }
        str = str.trim();
        scanner.close();
        return str;
    }
}
