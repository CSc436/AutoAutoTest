package view;

import java.io.File;

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
        myTestCollection = TestCollection.getInstance();
    }
    
    /**
     * Handles the clicking of the return void menu checkbox.
     * @param event Clicking on the menu item.
     */
    @FXML
    public void handleVoidMenuCheckBox(ActionEvent event) {
        isVoid = !isVoid;
    }
    
    /**
     * Handles the clicking of the ignore casing menu checkbox.
     * @param event Clicking on the menu item.
     */
    @FXML
    public void handleCasingMenuCheckBox(ActionEvent event) {
        ignoreCasing = !ignoreCasing;
    }
    
    /**
     * Handles the clicking of the ignore whitespace menu checkbox.
     * @param event Clicking on the menu item. 
     */
    @FXML
    public void handleWhiteSpaceMenuCheckBox(ActionEvent event) {
        ignoreWhitespace = !ignoreWhitespace;
    }
    
    /**
     * Handles the clicking of the ignore punctuation menu checkbox.
     * @param event Clicking on the menu item.
     */
    @FXML
    public void handlePunctuationMenuCheckBox(ActionEvent event) {
        ignorePunctuation = !ignorePunctuation;
    }
    
    /**
     * This method handles the action of exiting the GUI via the menu.
     * @param event Clicking on the menu item.
     */
    @FXML
    public void handleExitMenuOption(ActionEvent event) {
        Platform.exit();
    }
    
    /**
     * Loads the tests into the program via the menu.
     * @param event Clicking on the menu item.
     * @throws Exception File loading.
     */
    @FXML
    public void handleLoadTestsMenuOption(ActionEvent event) throws Exception {
        FileChooser myFileChooser = new FileChooser();
        myFileChooser.setTitle("Load Tests");
        File file = myFileChooser.showOpenDialog(new Stage());
        if (file != null) {
            String fileName = file.getAbsolutePath();
            myTestCollection.load(fileName);
            for (int i = 0; i < myTestCollection.testCount(); i++) {
                listView.getItems().add(
                        getTestForView(myTestCollection.getTest(i)));
            }
        }
    }
    
    /**
     * Exports the tests to a .java file via the menu.
     * @param event Clicking on the Export Tests menu item.
     * @throws Exception Saving Exception.
     */
    @FXML
    public void handleExportMenuOption(ActionEvent event) throws Exception {
        FileChooser myFileChooser = new FileChooser();
        myFileChooser.setTitle("Export Tests");
        File file = myFileChooser.showSaveDialog(new Stage());
        if (file != null) {
            String filePath = file.getAbsolutePath();
            myTestCollection.export(filePath);
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
            TestCase newTest = myTestCollection.newTest();
            sendAllDataToTestCase(newTest);
            String anotherTest = getTestForView(newTest);
            listView.getItems().add(anotherTest);
        }
    }

    /**
     * This method is the response to the user clicking the 'Delete Button'. The
     * selected test in the listview is deleted from the listview, and from the
     * test collection.
     * 
     * @param event
     *            The event that triggers this method. In this case, pressing
     *            the 'Delete' button.
     */
    @FXML
    public void handleDeleteButtonAction(ActionEvent event) {
        int testIndex = listView.getSelectionModel().getSelectedIndex();
        if (testIndex >= 0) {
            listView.getItems().remove(testIndex);
            myTestCollection.removeTest(testIndex);
            int newSelected = listView.getSelectionModel().getSelectedIndex();
            listView.getSelectionModel().select(newSelected);
        }
    }

    /**
     * This method saves all of tests in the TestCollection. It calls the save
     * method in the test collection, and the test collection will save the 
     * tests in an xml format to be loaded later.
     * 
     * @param event
     *            The event that triggers this method. In this case, clicking
     *            on the save menu option.
     * @throws Exception Due to file errors.
     */
    @FXML
    public void handleSaveTestsMenuOption(ActionEvent event) throws Exception {
        FileChooser myFileChooser = new FileChooser();
        myFileChooser.setTitle("Save Tests");
        File file = myFileChooser.showSaveDialog(new Stage());
        if (file != null) {
            String filePath = file.getAbsolutePath();
            myTestCollection.save(filePath);
        }
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
