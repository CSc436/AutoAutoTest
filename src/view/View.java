package view;

/** @dillon: this class is the graphical view of our system.
 *  the user will use this gui to interact with the model.
 **/

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author dillon This is the main class, we run it to run the GUI.
 */
public class View extends Application {

    private static final int GUI_WIDTH = 750;
    private static final int GUI_HEIGHT = 500;

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
        Scene scene = new Scene(root, GUI_WIDTH, GUI_HEIGHT);
        stage.setTitle("AutoAuto Tester");
        stage.setScene(scene);
        stage.show();
    }
 
}
