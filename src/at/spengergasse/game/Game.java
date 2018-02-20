package at.spengergasse.game;
import at.spengergasse.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
public class Game extends Application{
	
	 public void start(Stage primaryStage) throws Exception {
	        primaryStage.setTitle("Jump and Run");
	        Group root = new Group();
	        Scene scene = new Scene(root, 800, 800, Color.WHITE);
	        Player.move();
	        
	        Rectangle rectangle = new Blocks().newBlock(100, 200);
	  
	        // add rectangle
	        root.getChildren().add(rectangle); 
	        // add line
	        primaryStage.setScene(scene);
	        primaryStage.show();
	    }
	 
	 public static void main(String[] args) {
			launch(args);
			
	 }
}
