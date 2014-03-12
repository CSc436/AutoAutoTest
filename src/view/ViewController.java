/**
 * dillon: need the code for TestCase and
 * TestCollection to advance controller code.
 */

package view;

import java.io.File;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.TestCase;
import model.TestCollection;

/**
 * @author dillon This is the main class for the controller code. It contains
 *         all of the methods and data for interacting between the model and the
 *         view.
 */
public class ViewController {
    /**
     * The total collection of tests.
     * 
     */
    private TestCollection myTestCollection;
    private String testname;
    private String methodname;
    private String classname;
    private String params;
    private String testreturn;
    private String stdin;
    private String stdout;

    @FXML
    private Text actiontarget;
    /**
     * Field for entering the name.
     * 
     */
    @FXML
    private TextField namefield;
    /**
     * Field for entering the name.
     * 
     */
    @FXML
    private TextField paramsfield;
    /**
     * Field for entering the parameters.
     * 
     */
    @FXML
    private TextField returnfield;
    /**
     * Field for entering the return.
     * 
     */
    @FXML
    private TextField stdinfield;
    /**
     * Field for entering the standard in.
     * 
     */
    @FXML
    private TextField stdoutfield;
    /**
     * Field for entering the standard out.
     * 
     */
    @FXML
    private TextField methodnamefield;
    /**
     * Field for entering the class name.
     * 
     */
    @FXML
    private TextField classnamefield;
    /**
     * A view of our tests in a list.
     * 
     */
    @FXML
    private ListView<String> listView;

    
    
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
        if (listView != null) {
            getAllData(); // get all the data from the text fields
            generateTest();
            updateListView();
        }
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
        if (listView != null) {
            int testIndex = listView.getSelectionModel().getSelectedIndex();
            if (testIndex >= 0) { // if selected test is not null

                deleteTest(testIndex);
                updateListView();
            }
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
        File file = myFileChooser.showOpenDialog(new Stage());
        String filePath = file.getAbsolutePath();
        myTestCollection.save(filePath);
    }

    /**
     * This method is a sub method to make the model generate a new test. A new
     * test is created and all of the required data is sent it.
     * 
     * @return Returns the newly created testcase. This is mostly for testing
     *         purposes.
     */
    public TestCase generateTest() {
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
    public void deleteTest(int pindex) {
        myTestCollection.removeTest(pindex);
    }

    /**
     * This method is a sub method that is used to pull all of the data from the
     * text fields in the gui.
     * 
     */
    void getAllData() {
        setTestName(namefield.getText());
        setTestParams(paramsfield.getText());
        setTestReturn(returnfield.getText());
        setTestStdIn(stdinfield.getText());
        setTestStdOut(stdoutfield.getText());
        setTestMethodName(methodnamefield.getText());
        setTestClassName(classnamefield.getText());
    }

    /**
     * This method is a sub method that sends all of the collected data to the
     * model.
     * 
     * @param ptestCase
     * An individual test case.
     */
    private void sendAllDataToTestCase(TestCase ptestCase) {
        ptestCase.setArgs(params);
        ptestCase.setClassName(classname);
        ptestCase.setExpectedReturn(testreturn);
        ptestCase.setExpectedStandardOutput(stdout);
        ptestCase.setMethodName(methodname);
        ptestCase.setStockedInput(stdin);
        ptestCase.setTestName(testname);
    }

    /**
     * Updates the listview to match the TestCollection data structure. Can be
     * used after deleting or adding a test instead of seperate methods to do
     * both.
     */
    public void updateListView() {
        int length = myTestCollection.testCount();
        listView.getItems().clear(); // clear the list
        for (int i = 0; i < length; i++) { // for all the tests
            String anotherTest = getTestForView(myTestCollection.getTest(i));
            listView.getItems().add(anotherTest); // add it to the listview
        }
    }

    /**
     * Gets a string representation of the given test case for the listview.
     * 
     * @param pnewTestCase
     *            The test to get info for.
     * @return message
     * This method creates a string that is used for the list view.
     */
    String getTestForView(TestCase pnewTestCase) {
        String lname = pnewTestCase.getTestName();
        String message = "Test Name: " + lname;
        return message;
    }

    /**
     * @param pname
     *            Sets the name.
     */
    public void setTestName(String pname) {
        testname = pname;
    }

    /**
     * @param pparams
     *            Sets the parameters.
     */
    public void setTestParams(String pparams) {
        params = pparams;
    }

    /**
     * @param preturn
     *            Sets the return value.
     */
    public void setTestReturn(String preturn) {
        testreturn = preturn;
    }

    /**
     * @param pin
     *            Sets the standard in value.
     */
    public void setTestStdIn(String pin) {
        stdin = pin;
    }

    /**
     * @param pout
     *            Sets the standard out value.
     */
    public void setTestStdOut(String pout) {
        stdout = pout;
    }

    /**
     * set the class name of the test
     * 
     * @param pcname
     *            Sets the name of the class.
     */
    public void setTestClassName(String pcname) {
        classname = pcname;
    }

    /**
     * set the name of the method that the test will call
     * 
     * @param pmname
     *            Sets the name of the method.
     */
    public void setTestMethodName(String pmname) {
        methodname = pmname;
    }

    /**
     * This method was created strictly for testing purposes
     * 
     * @param ps
     *            A generic string used for all fields of a test.
     */
    public void setAllFields(String ps) {
        setTestName(ps);
        setTestParams(ps);
        setTestStdIn(ps);
        setTestStdOut(ps);
        setTestClassName(ps);
        setTestMethodName(ps);
    }

    /**
     * Returns the number of tests that are stored in the test collection.
     * 
     * @return The number of tests in the collection.
     */
    public int getNumberOfTests() {
        return myTestCollection.testCount();
    }

}
