package model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * A Class that stores all of the tests. This class is responsible for reading
 * and writing files associated with the tests.
 * 
 * @author Cody
 */
public class TestCollection {

    private static TestCollection instance = new TestCollection();
    private ArrayList<TestCase> tests;

    /**
     * Create a new test collection with no tests.
     */
    private TestCollection() {
        tests = new ArrayList<>();
    }

    /**
     * @param index
     *            The location of the desired test
     * @return The test at the specified index
     */
    public TestCase getTest(int index) {
        return tests.get(index);
    }

    /**
     * Create a new test, add it to the end of the list, then return it.
     * 
     * @return A newly created TestCase
     */
    public TestCase newTest() {
        TestCase freshTest = new TestCase();
        tests.add(freshTest);
        return freshTest;
    }

    /**
     * @param index
     *            The location of the TestCase to delete
     */
    public void removeTest(int index) {
        tests.remove(index);
    }

    /**
     * @return The number of tests in the collection
     */
    public int testCount() {
        return tests.size();
    }

    /**
     * Create a .java file containing all of the code for the defined tests.
     * 
     * @param filePath
     *            A full path to the destination file. The destination file must
     *            end with .java and be writable to by the current user.
     * @throws Exception
     *             If the filePath isn't a .java file or can't be written to.
     * @throws IOException
     *             If unable to open the file for any reason.
     */
    public void save(String filePath) throws Exception {
        String className = getClassName(filePath);
        String content = getFileContentString(className);
        writeToFile(content, filePath);
    }

    /**
     * Verify that the given file path is valid. A valid file path ends with a
     * .java file extension and is writable.
     * 
     * @param filePath
     *            The place to write the Java code to
     * @return The name of the file in the path, without the .java extension
     * @throws Exception
     *             If the path is not writable or doesn't end with a .java
     *             extension.
     */
    private String getClassName(String filePath) throws Exception {
        File destination = new File(filePath);
        if (!destination.canWrite()) {
            throw new IOException("Permission denied. Cannot write to "
                    + filePath);
        }
        String filename = destination.getName();
        if (!filename.endsWith(".java")) {
            throw new RuntimeException("Destination must be a .java file");
        }
        String className = filename.substring(0, filename.length() - 5);
        return className;
    }

    /**
     * Get the Java code for each TestCase and place that inside of a unit test
     * class. The entire test class file is returned as a string.
     * 
     * @param className
     *            The name of the test class
     * @return The test class as a string
     */
    private String getFileContentString(String className) {
        String template = "";
        template = template.replace("CLASSNAME", className);
        StringBuilder testCaseCode = new StringBuilder();
        for (TestCase test : tests) {
            testCaseCode.append(test.toString());
            testCaseCode.append("\n");
        }
        template = template.replace("TESTS", testCaseCode.toString());
        return template;
    }

    /**
     * Write the given content string to a file at the specified path. It is
     * assumed that the path already been checked beforehand and we'll be able
     * to write to it just fine.
     * 
     * @param content
     *            The contents of the file as a string
     * @param filePath
     *            The path to the file that will be written to
     * @throws IOException
     *             If the file cannot be written to for some reason
     */
    private void writeToFile(String content, String filePath)
            throws IOException {
        FileWriter writer = new FileWriter(filePath);
        writer.write(content);
        writer.close();
    }

    /**
     * @return A TestCollection Object
     */
    public static TestCollection getInstance() {
        return instance;
    }

}
