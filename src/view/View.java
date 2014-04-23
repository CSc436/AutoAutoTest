package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * This class starts the GUI and the whole program.
 * 
 * @author dillon
 */
public class View extends Application {
    
    /**
     * This is the generic main method, it launches the gui.
     * 
     * @param args
     *            Standard input arguments.
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("View.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("AutoAuto Tester");
        stage.setScene(scene);
        stage.show();
    }
 
}
