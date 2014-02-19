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
     * private constructor for code coverage.
     */
    private TemplateReader() {

    }

    /**
     * @return The contents of Call.txt
     *         <p>
     *         "" if file not found.
     *         </p>
     */
    public static String readCall() {
        return doRead("Call.txt");
    }

    /**
     * @return The contents of Input.txt
     *         <p>
     *         "" if file not found.
     *         </p>
     */
    public static String readInput() {
        return doRead("Input.txt");
    }

    /**
     * @return The contents of Output.txt
     *         <p>
     *         "" if file not found.
     *         </p>
     */
    public static String readOutput() {
        return doRead("Output.txt");
    }

    /**
     * @return The contents of Return.txt
     *         <p>
     *         "" if file not found.
     *         </p>
     */
    public static String readReturn() {
        return doRead("Return.txt");
    }

    /**
     * @return The contents of Test.txt
     *         <p>
     *         "" if file not found.
     *         </p>
     */
    public static String readTest() {
        return doRead("Test.txt");
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
        return doRead(inputFile);
    }

    /**
     * @param inputFile
     *            The file to read.
     * @return The file as a string. If file is not found, returns "".
     */
    private static String doRead(String inputFile) {
        // add directory to path
        inputFile = Paths.get("Templates", inputFile).toString();
        Scanner scanner;
        try {
            scanner = new Scanner(new File(inputFile));
        } catch (FileNotFoundException e) {
            System.out.println("error, file " + inputFile + " not found!"); // TODO turn into a logging statement
            e.printStackTrace();
            return "";
        }

        String str = "";
        if (scanner.hasNextLine()) {
            str += scanner.nextLine();
        }

        while (scanner.hasNextLine()) {
            str += "\n" + scanner.nextLine();
        }

        scanner.close();
        return str;
    }
}
