/** @dillon: this class is the graphical view of our system.
 *  the user will use this gui to interact with the model.
**/

// package view;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
 
public class View extends Application {
    private int testcounter;

    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        testcounter = 0;
        primaryStage.setTitle("AutoAuto Tester");
        Button testbutton = new Button();
        testbutton.setText("Generate a Test");
        testbutton.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Test" + testcounter + " generated.");
            }
        });
        
        StackPane root = new StackPane();
        root.getChildren().add(testbutton);
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();
    }
}
