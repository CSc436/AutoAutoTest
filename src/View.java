/** @dillon: this class is the graphical view of our system.
 *  the user will use this gui to interact with the model.
**/

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
 
public class View extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage stage) throws Exception {
    	Parent root = FXMLLoader.load(getClass().getResource("View.fxml"));
        Scene scene = new Scene(root, 900, 600);
        stage.setTitle("AutoAuto Tester");
        stage.setScene(scene);
        stage.show();
    }
    
}