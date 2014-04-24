package view;

import java.io.File;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.TestCase;
import model.TestCollection;

/**
 * This is the main class for the controller code. It contains all of the
 * methods and data for interacting between the model and the view.
 * 
 * @author: Dillon Jeffers
 */
public class ViewController {

    private TestCollection myTestCollection;
    private String testname;
    private String methodname;
    private String classname;
    private String params;
    private String testreturn;
    private String stdin;
    private String stdout;
    private String timeoutlimit;
    private String floatprecision;
    private boolean ignoreCasing;
    private boolean ignoreWhitespace;
    private boolean ignorePunctuation;
    private boolean isVoid;
    private LinkedList<TestCase> currentTests;
    private static final int LIST_VIEW_TITLE_LENGTH = 11;
    
    @FXML
    private TextField namefield;
    @FXML
    private TextField paramsfield;
    @FXML
    private TextField returnfield;
    @FXML
    private TextField stdinfield;
    @FXML
    private TextField stdoutfield;
    @FXML
    private TextField methodnamefield;
    @FXML
    private TextField classnamefield;
    @FXML
    private TextField timeoutfield;
    @FXML
    private TextField floatprecisionfield;
    @FXML
    private ListView<String> listView;
    
    /**
     * Generic constructor used for tests.
     */
    public ViewController() {
        isVoid = false;
        ignoreCasing = false;
        ignorePunctuation = false;
        ignoreWhitespace = false;
        currentTests = new LinkedList<TestCase>();
        myTestCollection = TestCollection.getInstance();
    }
    
    /**
     * Handles the clicking of the return void menu checkbox.
     * @param event Clicking on the menu item.
     */
    @FXML
    protected void handleVoidMenuCheckBox(ActionEvent event) {
        isVoid = !isVoid;
    }
    
    /**
     * Handles the clicking of the ignore casing menu checkbox.
     * @param event Clicking on the menu item.
     */
    @FXML
    protected void handleCasingMenuCheckBox(ActionEvent event) {
        ignoreCasing = !ignoreCasing;
    }
    
    /**
     * Handles the clicking of the ignore whitespace menu checkbox.
     * @param event Clicking on the menu item. 
     */
    @FXML
    protected void handleWhiteSpaceMenuCheckBox(ActionEvent event) {
        ignoreWhitespace = !ignoreWhitespace;
    }
    
    /**
     * Handles the clicking of the ignore punctuation menu checkbox.
     * @param event Clicking on the menu item.
     */
    @FXML
    protected void handlePunctuationMenuCheckbox(ActionEvent event) {
        ignorePunctuation = !ignorePunctuation;
    }
    
    /**
     * This method handles the action of exiting the GUI via the menu.
     * @param event Clicking on the menu item.
     */
    @FXML
    protected void handleExitMenuOption(ActionEvent event) {
        Platform.exit();
    }
    
    /**
     * Loads the tests into the program via the menu.
     * @param event Clicking on the menu item.
     */
    @FXML
    protected void handleLoadTestsMenuOption(ActionEvent event) {
        FileChooser myFileChooser = new FileChooser();
        myFileChooser.setTitle("Load Tests");
        File file = myFileChooser.showOpenDialog(new Stage());
        if (file != null) {
            myTestCollection.newTest();
        }
    }
    
    /**
     * Exports the tests to a .java file via the menu.
     * @param event Clicking on the Export Tests menu item.
     * @throws Exception Saving Exception.
     */
    @FXML
    protected void handleExportTestsMenuOption(ActionEvent event) throws Exception {
        FileChooser myFileChooser = new FileChooser();
        myFileChooser.setTitle("Save Tests");
        File file = myFileChooser.showSaveDialog(new Stage());
        if (file != null) {
            String filePath = file.getAbsolutePath();
            myTestCollection.save(filePath);
        }
    }
    
    /**
     * @param event
     *            This method generates a new test when the button is pressed.
     */
    @FXML
    protected void handleGenerateButtonAction(ActionEvent event) {
        getAllData();
        if (dataIsAcceptable()) {
            TestCase newTest = new TestCase();
            sendAllDataToTestCase(newTest);
            currentTests.add(newTest);
            String anotherTest = getTestForView(newTest);
            listView.getItems().add(anotherTest);
        }
    }

    /**
     * This method is the response to the user clicking the 'Delete Button'. The
     * selected test in the listview is deleted from the listview.
     * 
     * @param event
     *            The event that triggers this method. In this case, pressing
     *            the 'Delete' button.
     */
    @FXML
    protected void handleDeleteButtonAction(ActionEvent event) {
        int testIndex = listView.getSelectionModel().getSelectedIndex();
        if (testIndex >= 0) {
            String testNameToRemove = listView.getItems().remove(testIndex);
            testNameToRemove = testNameToRemove.substring(
                    LIST_VIEW_TITLE_LENGTH, testNameToRemove.length());
            for (TestCase test : currentTests) {
                if (test.getTestName().equals(testNameToRemove)) {
                    currentTests.remove(test);
                    break;
                }
            }
            int newSelected = listView.getSelectionModel().getSelectedIndex();
            listView.getSelectionModel().select(newSelected);
        }
    }

    /**
     * This method saves all of the created tests to the TestCollection Class. 
     * For every testcase in our current list, it creates a new test case 
     * inside the test collection, and then sets all of the required data for 
     * the new test case inside the collection.
     * 
     * @param event
     *            The event that triggers this method. In this case, clicking
     *            on the save menu option.
     */
    @FXML
    protected void handleSaveTestsMenuOption(ActionEvent event) {
        for (TestCase testCase : currentTests) {
            TestCase anotherTestCase = myTestCollection.newTest();
            anotherTestCase.setArgs(testCase.getArgs());
            anotherTestCase.setClassName(testCase.getClassName());
            anotherTestCase.setExpectedReturn(testCase.getExpectedReturn());
            anotherTestCase.setExpectedStandardOutput(
                   testCase.getExpectedStandardOutput());
            anotherTestCase.setFloatPrecision(testCase.getFloatPrecision());
            anotherTestCase.setIgnoreCasing(testCase.isIgnoreCasing());
            anotherTestCase.setIgnorePunctuation(
                    testCase.isIgnorePunctuation());
            anotherTestCase.setIgnoreWhitespace(testCase.isIgnoreWhitespace());
            anotherTestCase.setIsVoid(testCase.isVoid());
            anotherTestCase.setMethodName(testCase.getMethodName());
            anotherTestCase.setStockedInput(testCase.getStockedInput());
            anotherTestCase.setTestName(testCase.getTestName());
            anotherTestCase.setTimeoutTime(testCase.getTimeoutTime());
        }
        currentTests.clear();
        listView.getItems().removeAll();
    }

    /**
     * This method is a sub method that is used to pull all of the data from the
     * text fields in the gui.
     * 
     */
    private void getAllData() {
        testname = namefield.getText();
        params = paramsfield.getText();
        testreturn = returnfield.getText();
        stdin = stdinfield.getText();
        stdout = stdoutfield.getText();
        classname = classnamefield.getText();
        methodname = methodnamefield.getText();
        timeoutlimit = timeoutfield.getText();
        floatprecision = floatprecisionfield.getText();
    }

    /**
     * This method is a sub method that sends all of the collected data to the
     * model.
     * 
     * @param ptestCase
     *            An individual test case.
     */
    private void sendAllDataToTestCase(TestCase ptestCase) {
        ptestCase.setArgs(params);
        ptestCase.setClassName(classname);
        ptestCase.setExpectedReturn(testreturn);
        ptestCase.setExpectedStandardOutput(stdout);
        ptestCase.setMethodName(methodname);
        ptestCase.setStockedInput(stdin);
        ptestCase.setTestName(testname);
        ptestCase.setIgnoreCasing(ignoreCasing);
        ptestCase.setIgnoreWhitespace(ignoreWhitespace);
        ptestCase.setIgnorePunctuation(ignorePunctuation);
        ptestCase.setIsVoid(isVoid);
        try {
            int time = Integer.parseInt(timeoutlimit);
            ptestCase.setTimeoutTime(time);
        } catch (NumberFormatException e) {
            LogManager.getRootLogger().error(e);
        }
        try {
            int limit = Integer.parseInt(floatprecision);
            ptestCase.setFloatPrecision(limit);
        } catch (NumberFormatException e) {
            LogManager.getRootLogger().error(e);
        }
    }

    /**
     * Gets a string representation of the given test case for the listview.
     * 
     * @param pnewTestCase
     *            The test to get info for.
     * @return message This method creates a string that is used for the list
     *         view.
     */
    private String getTestForView(TestCase pnewTestCase) {
        String lname = pnewTestCase.getTestName();
        String message = "Test Name: " + lname;
        return message;
    }
    
    /**
     * 
     * @return The number of currently created tests.
     */
    public int getNumberOfCurrentTests() {
        return currentTests.size();
    }
    
    /**
     * Checks for bad input, will display messages accordingly
     * @return true if there is acceptable input
     */
    private boolean dataIsAcceptable() {
        if (testname.equals("")) {
            JOptionPane.showMessageDialog(null, "Please specify a test name");
            return false;
        }
        for (int i = 0; i < myTestCollection.testCount(); i++) {
            if (myTestCollection.getTest(i).getTestName().equals(testname)) {
                JOptionPane.showMessageDialog(null, "Test name exists, "
                        + "please choose a different name");
                return false;
            }
        }
        if (testreturn.equals("") && !isVoid) {
            JOptionPane.showMessageDialog(null, 
                    "Return value can not be blank when method is non-void");
            return false;
        }
        if (!testreturn.equals("") && isVoid) {
            JOptionPane.showMessageDialog(null, 
                    "Void methods can not have a return value");
            return false;
        }
        if (classname.equals("")) {
            JOptionPane.showMessageDialog(null, 
                    "Please specify the class name");
            return false;
        }
        if (methodname.equals("")) {
            JOptionPane.showMessageDialog(null, 
                    "Please specify the method name");
            return false;
        }
        if (timeoutlimit.equals("")) {
            timeoutlimit = "1000";
        }
        if (floatprecision.equals("")) {
            floatprecision = "12";
        }
        
        return true;
    }

}
