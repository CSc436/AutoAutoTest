package view;

import java.io.File;

import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
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
    @FXML
    private CheckBox returnVoidBox;
    
    /**
     * Generic constructor used for tests.
     */
    public ViewController() {
        myTestCollection = TestCollection.getInstance();
    }

    /**
     * 
     * @param event
     *             Repopulates the fields with the data from the test
     *              to be changed.
     */
    @FXML
    public void handleClickOnListAction(MouseEvent event) {
        int index = listView.getSelectionModel().getSelectedIndex();
        if (index >= 0) {
            // change the data in the fields to the data from the selected test
            namefield.setText(
                    myTestCollection.getTest(index).getTestName());
            paramsfield.setText(
                    myTestCollection.getTest(index).getArgs());
            returnfield.setText(
                    myTestCollection.getTest(index).getExpectedReturn());
            stdinfield.setText(
                    myTestCollection.getTest(index).getStockedInput());
            stdoutfield.setText(
                    myTestCollection.getTest(index).
                    getExpectedStandardOutput());
            methodnamefield.setText(
                    myTestCollection.getTest(index).getMethodName());
            classnamefield.setText(
                    myTestCollection.getTest(index).getClassName());
            timeoutfield.setText(
                    Integer.toString(
                            myTestCollection.getTest(index).getTimeoutTime()));
            floatprecisionfield.setText(
                    Integer.toString(
                            myTestCollection.getTest(index).
                            getFloatPrecision()));
            ignoreCasingBox.selectedProperty().set(
                    myTestCollection.getTest(index).isIgnoreCasing());
            ignorePunctuationBox.selectedProperty().set(
                    myTestCollection.getTest(index).isIgnorePunctuation());
            ignoreWhitespaceBox.selectedProperty().set(
                    myTestCollection.getTest(index).isIgnoreWhitespace());
            returnVoidBox.selectedProperty().set(
                    myTestCollection.getTest(index).isVoid());
        }
    }
    
    /**
     * @param event
     *            This method generates a new test when the button is pressed.
     */
    @FXML
    public void handleGenerateButtonAction(ActionEvent event) {
        getAllData();
        if (dataIsAcceptable()) {
            generateTest();
            String anotherTest = getTestForView(myTestCollection
                .getTest(myTestCollection.testCount() - 1));
            listView.getItems().add(anotherTest);
        }
    }
    
    /**
     * 
     * @param event
     *             This method will replace the selected test from the listview
     *             with new data.
     */
    @FXML
    public void handleReplaceButtonAction(ActionEvent event) {
        getAllData();
        replaceTest();
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
            if (newSelected == -1) {
                clearFields();
            } else {
                handleClickOnListAction(null);
            }
        }
    }

    /**
     * This method exports all of the created tests.
     * 
     * @param event
     *            The event that triggers this method. In this case, pressing
     *            the 'Export' button.
     * @throws Exception
     *             Throws an exception if the file is not found.
     */
    @FXML
    public void handleExportButtonAction(ActionEvent event) throws Exception {
        FileChooser myFileChooser = new FileChooser();
        myFileChooser.setTitle("Export Tests");
        File file = myFileChooser.showSaveDialog(new Stage());
        if (file != null) {
            String filePath = file.getAbsolutePath();
            myTestCollection.export(filePath);
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
     * Replaces the data of a test inside of the test collection.
     */
    private void replaceTest() {
        int index = listView.getSelectionModel().getSelectedIndex();
        TestCase testToReplace = myTestCollection.getTest(index);
        sendAllDataToTestCase(testToReplace);
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
        isVoid = returnVoidBox.isSelected();
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
    
    /**
     * Clears the data in the input fields.
     */
    public void clearFields() {
        namefield.setText(null);
        paramsfield.setText(null);
        returnfield.setText(null);
        stdinfield.setText(null);
        stdoutfield.setText(null);
        methodnamefield.setText(null);
        classnamefield.setText(null);
        timeoutfield.setText(null);
        floatprecisionfield.setText(null);
        ignoreCasingBox.selectedProperty().set(false);
        ignorePunctuationBox.selectedProperty().set(false);
        ignoreWhitespaceBox.selectedProperty().set(false);
        returnVoidBox.selectedProperty().set(false);
    }

}
