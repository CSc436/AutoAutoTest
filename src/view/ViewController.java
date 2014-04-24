package view;

import java.io.File;

import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckMenuItem;
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
    private CheckMenuItem ignoreCasingBox;
    @FXML
    private CheckMenuItem ignoreWhitespaceBox;
    @FXML
    private CheckMenuItem ignorePunctuationBox;
    @FXML
    private CheckMenuItem returnVoidBox;
    
    //TODO: make a linked list of test cases that we might save, but are not added to the collection yet
    
    /**
     * Generic constructor used for tests.
     */
    public ViewController() {
        myTestCollection = TestCollection.getInstance();
    }
    
    /**
     * This method handles the action of exiting the GUI via the menu.
     */
    @FXML
    public void handleExitMenuOption() {
        //TODO: exit
    }
    
    /**
     * Loads the tests into the program via the menu.
     */
    @FXML
    public void handleLoadTestsMenuOption() {
        //TODO: load tests
    }
    
    /**
     * Exports the tests to a .java file via the menu.
     */
    @FXML
    public void handleExportTestsMenuOption() {
        //TODO: export tests to a file 'old save'
    }
    
    /**
     * @param event
     *            This method generates a new test when the button is pressed.
     */
    @FXML
    public void handleGenerateButtonAction(ActionEvent event) {
        getAllData();
        if (dataIsAcceptable()) {
            generateTest(); //TODO: this puts the test into the collection, only do this if the user saves
            String anotherTest = getTestForView(myTestCollection
                .getTest(myTestCollection.testCount() - 1));
            listView.getItems().add(anotherTest);
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
        int testIndex = listView.getSelectionModel().getSelectedIndex();
        if (testIndex >= 0) {
            deleteTest(testIndex); // TODO: only need to delete from the listview now
            listView.getItems().remove(testIndex);
            int newSelected = listView.getSelectionModel().getSelectedIndex();
            listView.getSelectionModel().select(newSelected);
        }
    }

    //TODO: put this code into the export method
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
    public void handleSaveMenuOption(ActionEvent event) throws Exception {
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

    //TODO: probably don't need this anymore, or turn it into a delete all
    // along with the add test to test collection method (add all from list)
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

}
