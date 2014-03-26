package view;

import java.io.File;

import org.apache.logging.log4j.LogManager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
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
 * 
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
    @FXML
    private CheckBox ignoreCasingBox;
    @FXML
    private CheckBox ignoreWhitespaceBox;
    @FXML
    private CheckBox ignorePunctuationBox;
    
    /**
     * Generic constructor used for tests.
     */
    public ViewController() {
        myTestCollection = TestCollection.getInstance();
    }

    /**
     * @param event
     *            This method generates a new test when the button is pressed.
     */
    @FXML
    public void handleGenerateButtonAction(ActionEvent event) {
        getAllData();
        generateTest();
        String anotherTest = getTestForView(myTestCollection
                .getTest(myTestCollection.testCount() - 1));
        listView.getItems().add(anotherTest);
    }

    /**
     * This method is the response to the user clicking the 'Delete Button'. The
     * selected test in the listview is deleted from the TestCollection data
     * structure, and is then removed from the list.
     * 
     * @param event
     *            The event that triggers this method. In this case, pressing
     *            the 'Delete' button.
     */
    @FXML
    public void handleDeleteButtonAction(ActionEvent event) {
        int testIndex = listView.getSelectionModel().getSelectedIndex();
        if (testIndex >= 0) {
            deleteTest(testIndex);
            listView.getItems().remove(testIndex);
            int newSelected = listView.getSelectionModel().getSelectedIndex();
            listView.getSelectionModel().select(newSelected);
        }
    }

    /**
     * This method saves all of the created tests.
     * 
     * @param event
     *            The event that triggers this method. In this case, pressing
     *            the 'Save' button.
     * @throws Exception
     *             Throws an exception if the file is not found.
     */
    @FXML
    public void handleSaveButtonAction(ActionEvent event) throws Exception {
        FileChooser myFileChooser = new FileChooser();
        myFileChooser.setTitle("Save Tests");
        File file = myFileChooser.showSaveDialog(new Stage());
        if (file != null) {
            String filePath = file.getAbsolutePath();
            myTestCollection.save(filePath);
        }
    }

    /**
     * This method is a sub method to make the model generate a new test. A new
     * test is created and all of the required data is sent it.
     * 
     * @return Returns the newly created testcase. This is mostly for testing
     *         purposes.
     */
    private TestCase generateTest() {
        TestCase oneTestCase = myTestCollection.newTest();
        sendAllDataToTestCase(oneTestCase);
        return oneTestCase;
    }

    /**
     * Deletes the test from the TestCollection at the specified index.
     * 
     * @param pindex
     *            The integer index of the desired TestCase.
     */
    private void deleteTest(int pindex) {
        myTestCollection.removeTest(pindex);
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
        ignoreCasing = ignoreCasingBox.isSelected();
        ignoreWhitespace = ignoreWhitespaceBox.isSelected();
        ignorePunctuation = ignorePunctuationBox.isSelected();
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

}
