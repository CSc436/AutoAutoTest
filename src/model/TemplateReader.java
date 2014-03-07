package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;


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
     * @return The contents of Collection.txt
     */
    public static String readCollection() {
        return templateReader.doRead("Collection.txt");
    }

    /**
     * @return The contents of Call.txt
     */
    public static String readCall() {
        return templateReader.doRead("Call.txt");
    }

    /**
     * @return The contents of Input.txt
     */
    public static String readInput() {
        return templateReader.doRead("Input.txt");
    }

    /**
     * @return The contents of Output.txt
     */
    public static String readOutput() {
        return templateReader.doRead("Output.txt");
    }

    /**
     * @return The contents of Return.txt
     */
    public static String readReturn() {
        return templateReader.doRead("Return.txt");
    }
    
    /**
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
     * @return The contents of the file as a String
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
        try {
            inputFile = Paths.get("Templates", inputFile).toString();
            Scanner scanner = new Scanner(new File(inputFile), "UTF8");
            scanner.useDelimiter("\\Z");
            String str = scanner.next();
            str = str.replace("\r\n", "\n").replace("\r", "\n");
            scanner.close();
            return str;
        } catch (FileNotFoundException e) {
            LogManager.getRootLogger().error("FileNotFoundException in the " 
        + "private doRead method");
        }
        return "";
    }
}

