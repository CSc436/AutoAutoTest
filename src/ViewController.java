

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
 
public class ViewController {
	private int testcounter = 0;
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
    
    @FXML protected void handleSubmitButtonAction(ActionEvent event) {
    	getAllData(); // get all the data from the text fields
    	actiontarget.setText(getOutputMessage());
        testcounter++;
    }
    
    
    void getAllData() {
        setTestName(namefield.getText()); // set the test name to the value from the text field
        setTestParams(paramsfield.getText());
        setTestReturn(returnfield.getText());
        setTestStdIn(stdinfield.getText());
        setTestStdOut(stdinfield.getText());
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