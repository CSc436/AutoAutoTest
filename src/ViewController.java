

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
 
public class ViewController {
	private int testcounter = 2;
	private String name;
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
    @FXML private Label label;
    @FXML private ScrollPane list;
    @FXML private VBox box;
    
    @FXML protected void handleGenerateButtonAction(ActionEvent event) {
    	generateTest();
    	// actiontarget.setText(getOutputMessage()); no need to have text pop up
        label = new Label(name + "(" + params + ")" + testreturn + stdin + stdout + "\n");
        box.getChildren().add(testcounter, label);
        testcounter++;
    }
    
    
    // TODO get the selected item from the list, delete that item from the model
    	// maybe base it off of a given id at creation
    @FXML protected void handleDeleteButtonAction(ActionEvent event) {
        if (testcounter > 2) {
        	testcounter--;
        }
    	box.getChildren().get(testcounter).setVisible(false);
    	// actiontarget.setText("A test has been deleted."); no need to have text pop up
    }
    
    
    // TODO make the model make a new test with the given data
    void generateTest() {
    	getAllData(); // get all the data from the text fields
    	// make the model make a new test
    	sendAllDataToModel(); // give data to model to new test
    }
    
    
    void getAllData() {
        setTestName(namefield.getText()); // set the test name to the value from the text field
        setTestParams(paramsfield.getText());
        setTestReturn(returnfield.getText());
        setTestStdIn(stdinfield.getText());
        setTestStdOut(stdoutfield.getText());
    }
    
    // this method will send all of the data to the model via sub methods
    void sendAllDataToModel() {
    	sendNameToModel(name);
    	sendParamsToModel(params);
    	sendReturnToModel(testreturn);
    	sendStdInToModel(stdin);
    	sendStdOutToModel(stdout);
    }
    
    
    // TODO send the data to the new model
    private void sendNameToModel(String pname) {
    	// give the name to the model
    }
    private void sendParamsToModel(String pparams) {
    	// give the params to the model
    }
    private void sendReturnToModel(String ptestreturn) {
    	// give the testreturn to the model
    }
    private void sendStdInToModel(String pstdin) {
    	// give the stdin to the model
    }
    private void sendStdOutToModel(String pstdout) {
    	// give the stdout to the model
    }
    
    
    String getOutputMessage() {
    	String message = "Test #" + testcounter;
    	message += "\n------------------------------";
    	message += "\nName:	" + name + "(" + params + ")";
    	message += "\nStdIn:	" + stdin;
    	message += "\nStdOut:	" + stdout;
    	message += "\nReturns:	" + testreturn;
    	message += "\n------------------------------";
    	return message;
    }
    
    
    void setTestName(String pname) {
    	name = pname;
    }
    void setTestParams(String pparams) {
    	params = pparams;
    }
    void setTestReturn(String preturn) {
    	testreturn = preturn;
    }
    void setTestStdIn(String pin) {
    	stdin = pin;
    }
    void setTestStdOut(String pout) {
    	stdout = pout;
    }
    
}