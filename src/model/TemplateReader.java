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
     * @return The contents of Call.txt
     */
    public static String readCall() {
        return templateReader.doRead("Call.txt");
    }
    
    /**
     * return value is "" if file not found.
     * @return The contents of ClassInstance.txt
     */
    public static String readClassInstance() {
        return templateReader.doRead("ClassInstance.txt");
    }

    /**
     * return value is "" if file not found.
     * @return The contents of Input.txt
     */
    public static String readInput() {
        return templateReader.doRead("Input.txt");
    }

    /**
     * return value is "" if file not found.
     * @return The contents of Output.txt
     */
    public static String readOutput() {
        return templateReader.doRead("Output.txt");
    }
    
    /**
     * return value is "" if file not found.
     * @return The contents of OutputAssertion.txt
     */
    public static String readOutputAssertion() {
        return templateReader.doRead("OutputAssertion.txt");
    }

    /**
     * return value is "" if file not found.
     * @return The contents of Return.txt
     */
    public static String readReturnAssertion() {
        return templateReader.doRead("ReturnAssertion.txt");
    }

    /**
     * return value is "" if file not found.
     * @return The contents of Test.txt
     */
    public static String readTest() {
        return templateReader.doRead("Test.txt");
    }

    /**
     * It is preferable to use the non-parameterized methods, but this is here
     * just in case.
     * 
     * @param inputFile
     *            The file to read.
     * @return The file as a string. If file is not found, returns "".
     */
    public static String readTemplate(String inputFile) {
        return templateReader.doRead(inputFile);
    }

    /**
     * @param inputFile
     *            The file to read.
     * @return The file as a string. If file is not found, returns "".
     */
    private String doRead(String inputFile) {
        // add directory to path
        inputFile = Paths.get("Templates", inputFile).toString();
        Scanner scanner;
        try {
            scanner = new Scanner(new File(inputFile));
        } catch (FileNotFoundException e) {
         // TODO turn into a logging statement
            System.out.println("error, file " + inputFile + " not found!");
            e.printStackTrace();
            return "";
        }

        String str = "";
        while (scanner.hasNextLine()) {
            str += scanner.nextLine() + "\n";
        }
        str = str.trim();
        scanner.close();
        return str;
    }
}
