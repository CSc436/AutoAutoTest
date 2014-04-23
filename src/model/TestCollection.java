package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * A Class that stores all of the tests. This class is responsible for reading
 * and writing files associated with the tests.
 * 
 * @author Cody
 */
public class TestCollection {

    private static TestCollection instance = new TestCollection();
    private ArrayList<TestCase> tests;

    private final String saveArgsName = "Args";
    private final String saveClassName = "ClassName";
    private final String saveExpectedReturnName = "ExpectedReturn";
    private final String saveExpectedStdOutName = "ExpectedStandardOut";
    private final String saveMethodName = "MethodName";
    private final String saveFloatPrecisionName = "FloatPrecision";
    private final String saveStockedInputName = "StockedInput";
    private final String saveTimeoutTimeName = "TimeoutTime";
    private final String saveIsIgnoreCasingName = "IsIgnoreCasing";
    private final String saveIsIgnorePunctuationName = "IsIgnorePunctuation";
    private final String saveIsIgnoreWhitespaceName = "IsIgnoreWhitespace";
    private final String saveIsVoidName = "IsVoid";

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
        String content = getFileContentString(className);
        writeToFile(content, filePath);
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

    /**
     * Saves all the information in the TestCollection in xml format.
     * 
     * @param fileName
     *            the name of the file to save
     * @throws Exception
     *             if anything goes wrong
     */
    public void save(String fileName) throws Exception {

        DocumentBuilderFactory docFactory = DocumentBuilderFactory
                .newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

        // root elements
        Document doc = docBuilder.newDocument();
        Element rootElement = doc.createElement("TestCollection");
        doc.appendChild(rootElement);

        for (TestCase test : tests) {
            Element oneTest = doc.createElement("Test");
            oneTest.setAttribute("name", test.getTestName());

            appendElement(doc, saveArgsName, test.getArgs(), oneTest);
            appendElement(doc, saveClassName, test.getClassName(), oneTest);
            appendElement(doc, saveExpectedReturnName,
                    test.getExpectedReturn(), oneTest);
            appendElement(doc, saveExpectedStdOutName,
                    test.getExpectedStandardOutput(), oneTest);
            appendElement(doc, saveMethodName,
                    test.getMethodName(), oneTest);
            appendElement(doc, saveFloatPrecisionName,
                    "" + test.getFloatPrecision(), oneTest);
            appendElement(doc, saveStockedInputName, test.getStockedInput(),
                    oneTest);
            appendElement(doc, saveTimeoutTimeName, "" + test.getTimeoutTime(),
                    oneTest);
            appendElement(doc, saveIsIgnoreCasingName,
                    "" + test.isIgnoreCasing(), oneTest);
            appendElement(doc, saveIsIgnorePunctuationName,
                    "" + test.isIgnorePunctuation(), oneTest);
            appendElement(doc, saveIsIgnoreWhitespaceName,
                    "" + test.isIgnoreWhitespace(), oneTest);
            appendElement(doc, saveIsVoidName, "" + test.isVoid(), oneTest);

            rootElement.appendChild(oneTest);
        }

        TransformerFactory transformerFactory = TransformerFactory
                .newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File(fileName));

        transformer.transform(source, result);

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
            String elementValue, Element root) {
        Element tempElement = doc.createElement(elementName);
        tempElement.appendChild(doc.createTextNode(elementValue));
        root.appendChild(tempElement);
    }
}
