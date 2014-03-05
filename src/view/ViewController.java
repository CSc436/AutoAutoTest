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
import javafx.scene.control.ListView;
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
	private TestCollection TC;
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
    // @FXML private Label label;
    @FXML private ListView<String> listView;// maybe of strings?
    
    // add more inputs for timeouts etc.
    
    
    /**
     * Generic constructor used for tests.
     */
    public ViewController() {
    	TC = TestCollection.getInstance();
    }
    
    
    /**
     * @param event
     * This method generates a new test when the button is pressed.
     */
    @FXML protected void handleGenerateButtonAction(ActionEvent event) {
    	System.out.println("A");
    	if (listView != null) {
        	System.out.println("B");
        	generateTest(); // add a test to the model
        	//String newTestString = getTestForView();
        	//listView.getChildren().add(testcounter, label); // insert it to the list
        	//listView.getItems().add(newTestString);
        	updateListView();
        	testcounter++; //not needed?
        }
    }
    
    
    // TODO get the selected item from the list, delete that item from the model
    	// maybe base it off of a given id at creation
    /**This method is the response to the user clicking the 'Delete Button'. The selected
     * test in the listview is deleted from the TestCollection data structure, and is then removed from the list.
     * @param event
     */
    @FXML protected void handleDeleteButtonAction(ActionEvent event) {
    	if (listView != null) {
    		if (testcounter > 0) {
    			testcounter--;
    			deleteTest(testcounter);
    			//listView.getChildren().get(testcounter).setVisible(false);
    			//listView.getItems().remove(testcounter); // only removes the most recent one, we need to remove the selected one, and remove it from the TC
    			updateListView();
    		}

    		// get selected test from the list
    		// delete that test from the test collection
    	
    	}
    }
    
    
    /** This method is a sub method to make the model generate a new test.
     * 
     */
    public void generateTest() {
    	getAllData(); // get all the data from the text fields
    	TestCase T = TC.newTest(); // add the test case to the test collection
    	sendAllDataToModel(T); // give data to model to new test
    }
    
    /** Deletes the test from the TestCollection at the specified index.
     * 
     * @param
     * The integer index of the desired TestCase
     */
    public void deleteTest(int pindex) {
    	TC.removeTest(pindex);
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
    
    /**This method is a sub method that sends all of the
     * collected data to the model.
     * @param
     * An individual test case.
     */
    private void sendAllDataToModel(TestCase T) {
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
    
    /**Updates the listview to match the TestCollection data structure.
     * Can be used after deleting or adding a test instead of seperate methods to
     * do both.
     * 
     */
    public void updateListView() {
    	int length = TC.testCount();
    	listView.getItems().clear(); // clear the list
    	for (int i = 0; i < length; i++) { // for all the tests
    		String anotherTest = TC.getTest(i).toString(); // get the string representation
    		listView.getItems().add(anotherTest); // add it to the listview
    	}
    }
    
    
    
    
    
    /**
     * @return message
     * This method creates a string that is used for the list view.
     * It calls the toString() method of the last TestCase in the TestCollection.
     */
    String getTestForView() {
    	//String message = "------------------------------";
    		//String message = "\nTest #" + testcounter;
    		//message += "Name:	" + testname + "(" + params + ")";
    	//message += "\nStdIn:	" + stdin;
    	//message += "\nStdOut:	" + stdout;
    	//message += "\nReturns:	" + testreturn;
    	//message += "\nMethod Name:     " + methodname;
    	//message += "\nClass Name:     " + classname;
    	//message += "\n------------------------------";
    	String message = TC.getTest(getNumberOfTests()-1).toString(); // gets the tostring of the last test in the list
    	return message;
    }
    
    
    /**
     * @param pname
     * Sets the name.
     */
    public void setTestName(String pname) {
    	testname = pname;
    }
    
    /**
     * @param pparams
     * Sets the parameters.
     */
    public void setTestParams(String pparams) {
    	params = pparams;
    }
    /**
     * @param preturn
     * Sets the return value.
     */
    public void setTestReturn(String preturn) {
    	testreturn = preturn;
    }
    /**
     * @param pin
     * Sets the standard in value.
     */
    public void setTestStdIn(String pin) {
    	stdin = pin;
    }
    /**
     * @param pout
     * Sets the standard out value.
     */
    public void setTestStdOut(String pout) {
    	stdout = pout;
    }
    
    /**
     * set the class name of the test
     * @param pcname
     * Sets the name of the class.
     */
    public void setTestClassName(String pcname) {
    	classname = pcname;
    }
    
    /**
     * set the name of the method that the test will call
     * @param pmname
     * Sets the name of the method.
     */
    public void setTestMethodName(String pmname) {
    	methodname = pmname;
    }
    
    /**
     * This method was created strictly for testing purposes
     * @param ps
     * A generic string used for all fields of a test.
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
     * @return
     * The number of tests in the collection.
     */
    public int getNumberOfTests() {
    	return TC.testCount();
    }
    
    /**
     * Returns the string value of a single test case when
     * given an integer index.
     * @param i
     * The integer index of the desired test.
     * @return
     * The string representation of the test.
     */
    public String getTestAsString(int i) {
    	return TC.getTest(i).toString();
    }
    
}