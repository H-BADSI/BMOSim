package bmosim.ihm;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class NewEnt extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {

		primaryStage.setTitle("BMO Simulator");
		FXMLLoader loader= new FXMLLoader();
		loader.setLocation(NewEnt.class.getResource("newEnt.fxml"));
		AnchorPane settingsView = (AnchorPane) loader.load();
		Scene scene = new Scene(settingsView);
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
 
	public static void main (String[] args){
		launch(args);
	}
	
}
