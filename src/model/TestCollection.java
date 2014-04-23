package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
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
     */
    public void save(String filePath) throws Exception {
        String className = getClassName(filePath);
        String content = getFileContentString(className);
        writeToFile(content, filePath);
        copyFiles(filePath, className);
    }

    /**
     * copyFiles creates 2 folders, and then calls copyPaths to saves 4 jars and
     * 2 files, plus the build script to the place the user defines to save the
     * .java file.
     * 
     * @param path
     *            is the file path in which the test will be saved
     * @param className
     *            is the actual classname from the entire directory.
     * @throws IOException
     *             is thrown in case the copyPath does not actually copy
     */
    public void copyFiles(String path, String className) throws IOException {
        String filePath = path;
        // getting destination path
        File destination = new File(filePath);
        Path newdir = destination.toPath().getParent();

        // making src folder
        new File(Paths.get(newdir.toString(), "src").toString()).mkdir();

        // making lib folder
        new File(Paths.get(newdir.toString(), "lib").toString()).mkdir();

        // making src/model folder
        new File(Paths.get(newdir.toString(), "src", "model").toString())
                .mkdir();

        copyPath("./src/model/FakeStandardOutput.java", newdir,
                Paths.get("src", "model").toString());

        copyPath("./src/model/FakeStandardIn.java", newdir,
                Paths.get("src", "model").toString());

        copyPath("./dev/hamcrest-core-1.3.jar", newdir, "lib");
        copyPath("./dev/junit-4.11.jar", newdir, "lib");
        copyPath("./lib/log4j-api-2.0-rc1.jar", newdir, "lib");
        copyPath("./lib/log4j-core-2.0-rc1.jar", newdir, "lib");

        replaceXMLVariable(className, newdir);
    }

    /**
     * copyPath does the actual copying for copyFiles. This creates the files
     * and puts them in the locations specified
     * 
     * @param directory
     *            is the file path that is used to copy files
     * @param newdirectory
     *            is the path the parent of the directory where we are copying
     * @param folder
     *            is the directory in which the file is located
     * @throws IOException
     *             is thrown if the copy does not actually copy
     */
    private void copyPath(String directory, Path newdirectory, String folder)
            throws IOException {
        Path filePath = new File(directory).toPath();
        Path newdir = newdirectory;
        Files.copy(filePath, newdir.resolve(Paths.get(folder, filePath
                .getFileName().toString())),
                StandardCopyOption.REPLACE_EXISTING);
    }

    /**
     * replaceXMLVariable opens the buildscript, and replaces the word CLASSNAME
     * with the actual classname grabbed from the user
     * 
     * @param name
     *            is the classname that is passed in from the save method.
     * @param directory
     *            is the directory in which the new build script will be saved.
     * @throws IOException
     *             this is thrown if the build script cannot be opened, or
     *             cannot be written.
     */
    private void replaceXMLVariable(String name, Path directory)
             throws IOException {
        File source = new File("build.xml");
        Path build = source.toPath();
        Charset charset = Charset.forName("UTF-8");
        String result = "";
        BufferedReader reader = Files.newBufferedReader(build, charset);
        String line = null;
        while ((line = reader.readLine()) != null) {
            if (line.contains("classname")) {
                line = line.replace("classname", name);
            }
            result += line;
        }
        String destination = directory.resolve("build.xml").toString();
        FileWriter writer = new FileWriter(destination);
        writer.write(result);
        writer.close();
    }

    /**
     * Verify that the given file path is valid. A valid file path ends with a
     * filename that is a valid Java filename.
     * 
     * @param filePath
     *            The place to write the Java code to
     * @return The name of the file in the path, without the .java extension
     * @throws Exception
     *             If the filename isn't a valid Java filename
     */
    private String getClassName(String filePath) throws Exception {
        File destination = new File(filePath);
        String filename = destination.getName();
        if (!filename.endsWith(".java")) {
            filename += ".java";
        }
        String className = filename.substring(0, filename.length() - 5);
        if (className.contains(".")) {
            throw new RuntimeException("Java Class names may not contain '.'");
        }
        return className;
    }

    /**
     * Get the Java code for each TestCase and place that inside of a unit test
     * class. The entire test class file is returned as a string.
     * 
     * @param className
     *            The name of the test class
     * @return The test class as a string
     * @throws FileNotFoundException
     *             If Collection.txt cannot be found.
     */
    private String getFileContentString(String className)
            throws FileNotFoundException {
        String template = "";
        template = TemplateReader.readCollection();
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
        FileOutputStream outStream = new FileOutputStream(filePath);
        OutputStreamWriter writer = new OutputStreamWriter(outStream, "UTF8");
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
