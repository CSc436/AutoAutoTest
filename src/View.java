// convert to xml
/** @dillon: this class is the graphical view of our system.
 *  the user will use this gui to interact with the model.
**/

// package view;



import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
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
        
        // gridpane layout with gaps and padding
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER); // the default position of the grid is now the center, (default is top left)
        grid.setHgap(10); // horizontal gap (space between columns)
        grid.setVgap(10); // vertical gap (space between rows)
        grid.setPadding(new Insets(25, 25, 25, 25)); // padding (space between edges and grid)
        
        Text sceneTitle = new Text("AutoAuto Tester"); // here we create the title for the scene
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(sceneTitle, 0, 0, 2, 1);
        
        // user input goes here
        Label userName = new Label("Test Name:");
        grid.add(userName, 0, 1);
        
        TextField userTextField = new TextField();
        grid.add(userTextField, 1, 1);
        
        Label ip = new Label("Test Input:");
        grid.add(ip, 0, 2);
        
        TextField ipTextField = new TextField();
        grid.add(ipTextField, 1, 2);
//        PasswordField pwBox = new PasswordField();
//        grid.add(pwBox, 1, 2);
        
        Label op = new Label("Test Output:");
        grid.add(op, 0, 3);
        
        TextField opTextField = new TextField();
        grid.add(opTextField, 1, 3);
        
        
        
        
        // code for button
        Button button = new Button("Generate Test");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(button);
        grid.add(hbBtn, 1, 4);
        
        
        // text message
        final Text actiontarget = new Text();
        grid.add(actiontarget, 1, 6);
        
        
        
        
        button.setOnAction(new EventHandler<ActionEvent>() {
        	 
            @Override
            public void handle(ActionEvent e) {
                actiontarget.setFill(Color.FIREBRICK);
                actiontarget.setText("Test Generated.");
            }
        });
        
        
        
        
        
        
        Scene scene = new Scene(grid, 600, 400);
        primaryStage.setScene(scene);
        // end of gridpane layout
        // the grid pane is used as the root node
        
        
        
        primaryStage.show();
    }
}