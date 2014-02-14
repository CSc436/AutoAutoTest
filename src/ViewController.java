

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
 
public class ViewController {
	private int testcounter = 0;
    @FXML private Text actiontarget;
    
    @FXML protected void handleSubmitButtonAction(ActionEvent event) {
        actiontarget.setText("Test " + testcounter + " Generated.");
        testcounter++;
    }

}