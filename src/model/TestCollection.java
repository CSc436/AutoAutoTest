package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
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
    
    private static final String SAVE_ARGS_NAME = "Args";
    private static final String SAVE_CLASS_NAME = "ClassName";
    private static final String SAVE_EXPECTED_RETURN_NAME = "ExpectedReturn";
    private static final String SAVE_EXPECTED_STD_OUT_NAME = 
            "ExpectedStandardOut";
    private static final String SAVE_METHOD_NAME = "MethodName";
    private static final String SAVE_FLOAT_PRECISION_NAME = "FloatPrecision";
    private static final String SAVE_STOCKED_INPUT_NAME = "StockedInput";
    private static final String SAVE_TIMEOUT_TIME_NAME = "TimeoutTime";
    private static final String SAVE_IS_IGNORE_CASING_NAME = 
            "IsIgnoreCasing";
    private static final String SAVE_IS_IGNORE_PUNCTUATION_NAME = 
            "IsIgnorePunctuation";
    private static final String SAVE_IS_IGNORE_WHITESPACE_NAME = 
            "IsIgnoreWhitespace";
    private static final String SAVE_IS_VOID_NAME = "IsVoid";

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
            if (node.getNodeType() != Node.ELEMENT_NODE) 
                continue;
                
                Element el = (Element) node;
                System.out.println("name is: " + el.getAttribute("name"));
                
                // get arguments for test case
                String args = getText(el, SAVE_ARGS_NAME);
                String className = getText(el, SAVE_CLASS_NAME);
                String expectedReturn = getText(el, SAVE_EXPECTED_RETURN_NAME);
                String expectedStandardOut = getText(el, SAVE_EXPECTED_STD_OUT_NAME);
                String method = getText(el, SAVE_METHOD_NAME);
                String floatPrecision = getText(el, SAVE_FLOAT_PRECISION_NAME);
                String stockedInput = getText(el, SAVE_STOCKED_INPUT_NAME);
                String timeoutTime = getText(el, SAVE_TIMEOUT_TIME_NAME);
                String isIgnoreCasing = getText(el, SAVE_IS_IGNORE_CASING_NAME);
                String isIgnorePunctuation = getText(el, SAVE_IS_IGNORE_PUNCTUATION_NAME);
                String isIgnoreWhitespace = getText(el, SAVE_IS_IGNORE_WHITESPACE_NAME);
                String isVoid = getText(el, SAVE_IS_VOID_NAME);
                
                // set test case args
                TestCase test = newTest();
                test.setArgs(args);
                test.setClassName(className);
                test.setExpectedReturn(expectedReturn);
                test.setExpectedStandardOutput(expectedStandardOut);
                test.setMethodName(method);
                test.setFloatPrecision(Integer.parseInt(floatPrecision));
                test.setStockedInput(stockedInput);
                test.setTimeoutTime(Integer.parseInt(timeoutTime));
                test.setIgnoreCasing(Boolean.parseBoolean(isIgnoreCasing));
                test.setIgnorePunctuation(Boolean.parseBoolean(isIgnorePunctuation));
                test.setIgnoreWhitespace(Boolean.parseBoolean(isIgnoreWhitespace));
                test.setIsVoid(Boolean.parseBoolean(isVoid));
        }
    }
    
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
            oneTest.setAttribute("name", test.getTestName());

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
