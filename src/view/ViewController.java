/**
 * dillon: need the code for TestCase and
 * TestCollection to advance controller code.
 */


package view;

import model.TestCase;
import model.TestCollection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
 
/**
 * @author dillon
 * This is the main class for the controller code. It
 * contains all of the methods and data for
 * interacting between the model and the view.
 */
public class ViewController {
	TestCollection TC;
	
	private int testcounter = 0;
	private String testname;
	private String methodname;
	private String classname;
	private String params;
	private String testreturn;
	private String stdin;
	private String stdout;
	
    @FXML private Text actiontarget;
    @FXML private TextField namefield;
    @FXML private TextField paramsfield;
    @FXML private TextField returnfield;
    @FXML private TextField stdinfield;
    @FXML private TextField stdoutfield;
    @FXML private TextField methodnamefield;
    @FXML private TextField classnamefield;
    @FXML private Label label;
    @FXML private ScrollPane list;
    @FXML private VBox box;
    
    
    public ViewController() {
    	TC = TestCollection.getInstance();
    }
    
    
    /**
     * @param event
     * This method generates a new test when the button is pressed.
     */
    @FXML protected void handleGenerateButtonAction(ActionEvent event) {
    	generateTest(); // add a test to the model
        // label = new Label(testname + "(" + params + ")" + testreturn + stdin + stdout + "\n"); // add it to the model
        label = new Label(getOutputMessage());
        box.getChildren().add(testcounter, label); // insert it to the list
        testcounter++;
    }
    
    
    // TODO get the selected item from the list, delete that item from the model
    	// maybe base it off of a given id at creation
    /**
     * @param event
     * This method deletes a test from the collection when
     * the delete button in the view is pressed.
     */
    @FXML protected void handleDeleteButtonAction(ActionEvent event) {
        if (testcounter > 0) {
        	testcounter--;
        	box.getChildren().get(testcounter).setVisible(false);
        }

    	// get selected test from the list
    	// delete that test from the test collection
    	
    }
    
    
    /** This method is a sub method to make the model generate a new test.
     * 
     */
    public void generateTest() {
    	getAllData(); // get all the data from the text fields
    	TestCase T = TC.newTest(); // add the test case to the test collection
    	sendAllDataToModel(T); // give data to model to new test
    }
    
    
    /** This method is a sub method that is used to pull
     * all of the data from the text fields in the gui.
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
    
    
    // this method will send all of the data to the model via sub methods
    /**This method is a sub method that sends all of the
     * collected data to the model.
     * 
     */
    void sendAllDataToModel(TestCase T) {
    	T.setArgs(params);
    	T.setClassName(classname);
    	T.setExpectedReturn(testreturn);
    	T.setExpectedStandardOutput(stdout);
    	T.setMethodName(methodname);
    	T.setStockedInput(stdin);
    	T.setTestName(testname);
    }
    
    
//    /**
//     * 
//     * @param pname
//     * Gives the name value to the model.
//     */
//    private void sendNameToModel(String pname) {
//    	// give the name to the model
//    }
//    
//    
//    /**
//     * 
//     * @param pparams
//     * Gives the parameter value to the model.
//     */
//    private void sendParamsToModel(String pparams) {
//    	// give the params to the model
//    }
//    
//    
//    /**
//     * @param ptestreturn
//     * Gives the return value to the model.
//     */
//    private void sendReturnToModel(String ptestreturn) {
//    	// give the testreturn to the model
//    }
//    
//    
//    /**
//     * @param pstdin
//     * Gives the standard in value to the model.
//     */
//    private void sendStdInToModel(String pstdin) {
//    	// give the stdin to the model
//    }
//    
//    
//    /**
//     * @param pstdout
//     * Gives the standard out value to the model.
//     */
//    private void sendStdOutToModel(String pstdout) {
//    	// give the stdout to the model
//    }
//    
//    
    /**
     * @return message
     * This method creates a string that is a formatted message
     * to the user regarding a new test.
     */
    String getOutputMessage() {
    	String message = "------------------------------";
    	message += "\nTest #" + testcounter;
    	message += "\nName:	" + testname + "(" + params + ")";
    	message += "\nStdIn:	" + stdin;
    	message += "\nStdOut:	" + stdout;
    	message += "\nReturns:	" + testreturn;
    	message += "\nMethod Name:     " + methodname;
    	message += "\nClass Name:     " + classname;
    	message += "\n------------------------------";
    	return message;
    }
    
    
    /**
     * @param pname
     * Sets the name.
     */
    void setTestName(String pname) {
    	testname = pname;
    }
    
    /**
     * @param pparams
     * Sets the parameters.
     */
    void setTestParams(String pparams) {
    	params = pparams;
    }
    /**
     * @param preturn
     * Sets the return value.
     */
    void setTestReturn(String preturn) {
    	testreturn = preturn;
    }
    /**
     * @param pin
     * Sets the standard in value.
     */
    void setTestStdIn(String pin) {
    	stdin = pin;
    }
    /**
     * @param pout
     * Sets the standard out value.
     */
    void setTestStdOut(String pout) {
    	stdout = pout;
    }
    
    /**
     * set the class name of the test
     * @param pcname
     * Sets the name of the class.
     */
    void setTestClassName(String pcname) {
    	classname = pcname;
    }
    
    /**
     * set the name of the method that the test will call
     * @param pmname
     * Sets the name of the method.
     */
    void setTestMethodName(String pmname) {
    	methodname = pmname;
    }
    
}