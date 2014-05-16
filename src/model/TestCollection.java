package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

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
    public void export(String filePath) throws Exception {
        String className = getClassName(filePath);
        String root = new File(filePath).getParentFile().toString();
        String content = getFileContentString(className);
        copyFiles(filePath, className);
        writeToFile(content, root, className);
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
     * @throws Exception is thrown if the file could not be created.
     */
    public void copyFiles(String path, String className) throws Exception {
        String filePath = path;
        File destination = new File(filePath);
        Path newdir = destination.toPath().getParent();
        String dirString = newdir.toString();
        
        String [][] args = {{"src"}, {"lib"}, {"src", "model"}};
        for (String[] dirs : args) {
            String dirPath = Paths.get(dirString, dirs).toString();
            File newDir = new File(dirPath);
            if (!newDir.exists()) {
                Files.createDirectory(newDir.toPath());
            }
        }

        copyPath("./src/model/FakeStandardOutput.java", newdir, "src", "model");
        copyPath("./src/model/StringOutputStream.java", newdir, "src", "model");
        copyPath(
                "./src/model/RelaxedStringFloatCheck.java",
                newdir,
                "src",
                "model"
        );
        copyPath("./src/model/FakeStandardIn.java", newdir, "src", "model");
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
     * @param paths is a list of paths.
     * @throws IOException
     *             is thrown if the copy does not actually copy
     */
    private void copyPath(String directory, Path newdirectory, String... paths)
            throws IOException {
        Path filePath = new File(directory).toPath();
        Path newdir = newdirectory;
        Path dst = newdir.resolve(Paths.get("", paths));
        dst = Paths.get(dst.toString(), filePath.getFileName().toString());
        StandardCopyOption options = StandardCopyOption.REPLACE_EXISTING;
        Files.copy(filePath, dst, options);
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
        File source = new File("./res/build.xml");
        Path build = source.toPath();
        Charset charset = Charset.forName("UTF-8");
        StringBuffer result = new StringBuffer();
        BufferedReader reader = Files.newBufferedReader(build, charset);
        String line = null;
        while ((line = reader.readLine()) != null) {
            line = line.replace("CLASSNAME", name);
            result.append(line);
        }
        String destination = directory.resolve("build.xml").toString();
        FileOutputStream outStream = new FileOutputStream(destination);
        OutputStreamWriter writer = new OutputStreamWriter(outStream, "UTF8");
        writer.write(result.toString());
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
     * @param dir
     *            The path to the directory that will be written to
     * @param className
     *            The name of the Java file being written
     * @throws IOException
     *             If the file cannot be written to for some reason
     */
    private void writeToFile(String content, String dir, String className)
            throws IOException {
        Path p = Paths.get(dir, "src", className + ".java");
        String filePath = p.toString();
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

    /**
     * Saves all the information in the TestCollection in xml format.
     * 
     * @param fileName
     *            the name of the file to save
     * @throws Exception if anything goes wrong
     */
    public void load(String fileName) throws Exception {
        
        // reset the tests
        tests = new ArrayList<TestCase>();

        DocumentBuilderFactory docFactory = DocumentBuilderFactory
                .newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

        // root elements
        Document doc = docBuilder.parse(new File(fileName));
        Element rootElement = doc.getDocumentElement();
        NodeList list = rootElement.getElementsByTagName("Test");
        for (int i = 0; i < list.getLength(); i++) {
     
            Node node = list.item(i);
            Element el = (Element) node;

            // get arguments for test case
            String testName = el.getAttribute(XmlNames.SAVE_TEST_NAME_NAME);
            String args = getText(el, XmlNames.SAVE_ARGS_NAME);
            String className = getText(el, XmlNames.SAVE_CLASS_NAME);
            String expectedReturn = getText(el,
                    XmlNames.SAVE_EXPECTED_RETURN_NAME);
            String expectedStandardOut = getText(el,
                    XmlNames.SAVE_EXPECTED_STD_OUT_NAME);
            String method = getText(el, XmlNames.SAVE_METHOD_NAME);
            String floatPrecision = getText(el,
                    XmlNames.SAVE_FLOAT_PRECISION_NAME);
            String stockedInput = getText(el, XmlNames.SAVE_STOCKED_INPUT_NAME);
            String timeoutTime = getText(el, XmlNames.SAVE_TIMEOUT_TIME_NAME);
            String isIgnoreCasing = getText(el,
                    XmlNames.SAVE_IS_IGNORE_CASING_NAME);
            String isIgnorePunctuation = getText(el,
                    XmlNames.SAVE_IS_IGNORE_PUNCTUATION_NAME);
            String isIgnoreWhitespace = getText(el,
                    XmlNames.SAVE_IS_IGNORE_WHITESPACE_NAME);
            String isVoid = getText(el, XmlNames.SAVE_IS_VOID_NAME);

            // set test case args
            TestCase test = newTest();
            test.setTestName(testName);
            test.setArgs(args);
            test.setClassName(className);
            test.setExpectedReturn(expectedReturn);
            test.setExpectedStandardOutput(expectedStandardOut);
            test.setMethodName(method);
            test.setFloatPrecision(Integer.parseInt(floatPrecision));
            test.setStockedInput(stockedInput);
            test.setTimeoutTime(Integer.parseInt(timeoutTime));
            test.setIgnoreCasing(Boolean.parseBoolean(isIgnoreCasing));
            test.setIgnorePunctuation(
                    Boolean.parseBoolean(isIgnorePunctuation));
            test.setIgnoreWhitespace(Boolean.parseBoolean(isIgnoreWhitespace));
            test.setIsVoid(Boolean.parseBoolean(isVoid));
        }
    }
    
    /**
     * @param el the element to check
     * @param name the name the element holds
     * @return the string associated with el's name variable
     */
    private String getText(Element el, String name) {
        return el.getElementsByTagName(name).item(0).getTextContent();
    }

    /**
     * Saves all the information in the TestCollection in xml format.
     * 
     * @param fileName
     *            the name of the file to save
     * @throws Exception
     *             if anything goes wrong
     */
    public void save(String fileName) throws Exception {
        if (!fileName.endsWith(".xml")) {
            fileName += ".xml";
        }

        DocumentBuilderFactory docFactory = DocumentBuilderFactory
                .newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Document doc = docBuilder.newDocument();
        Element rootElement = doc.createElement("TestCollection");
        doc.appendChild(rootElement);

        writeData(doc, rootElement);

        TransformerFactory transformerFactory = TransformerFactory
                .newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File(fileName));

        transformer.transform(source, result);
    }

    /**
     * iterates through the tests list, writing data to the doc
     * 
     * @param doc
     *            The document to be written through
     * @param rootElement
     *            the root xml element to add a test too.
     */
    private void writeData(Document doc, Element rootElement) {
        for (TestCase test : tests) {
            Element oneTest = doc.createElement("Test");
            oneTest.setAttribute(XmlNames.SAVE_TEST_NAME_NAME, 
                    test.getTestName());

            appendElement(doc, XmlNames.SAVE_ARGS_NAME, 
                    test.getArgs(), oneTest);
            appendElement(doc, XmlNames.SAVE_CLASS_NAME, test.getClassName(),
                    oneTest);
            appendElement(doc, XmlNames.SAVE_EXPECTED_RETURN_NAME,
                    test.getExpectedReturn(), oneTest);
            appendElement(doc, XmlNames.SAVE_EXPECTED_STD_OUT_NAME,
                    test.getExpectedStandardOutput(), oneTest);
            appendElement(doc, XmlNames.SAVE_METHOD_NAME, test.getMethodName(),
                    oneTest);
            appendElement(doc, XmlNames.SAVE_FLOAT_PRECISION_NAME,
                    test.getFloatPrecision(), oneTest);
            appendElement(doc, XmlNames.SAVE_STOCKED_INPUT_NAME,
                    test.getStockedInput(), oneTest);
            appendElement(doc, XmlNames.SAVE_TIMEOUT_TIME_NAME,
                    test.getTimeoutTime(), oneTest);
            appendElement(doc, XmlNames.SAVE_IS_IGNORE_CASING_NAME,
                    test.isIgnoreCasing(), oneTest);
            appendElement(doc, XmlNames.SAVE_IS_IGNORE_PUNCTUATION_NAME,
                    test.isIgnorePunctuation(), oneTest);
            appendElement(doc, XmlNames.SAVE_IS_IGNORE_WHITESPACE_NAME,
                    test.isIgnoreWhitespace(), oneTest);
            appendElement(doc, XmlNames.SAVE_IS_VOID_NAME, test.isVoid(),
                    oneTest);

            rootElement.appendChild(oneTest);
        }
    }

    /**
     * Appends an element to the save file
     * 
     * @param doc
     *            the document we are working on
     * @param elementName
     *            the name to append
     * @param elementValue
     *            the value to associate with the name
     * @param root
     *            the Element on which to append the new element
     */
    private void appendElement(Document doc, String elementName,
            Object elementValue, Element root) {
        Element tempElement = doc.createElement(elementName);
        tempElement.appendChild(doc.createTextNode(elementValue.toString()));
        root.appendChild(tempElement);
    }
}
