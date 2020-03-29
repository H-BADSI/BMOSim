package bmosim.ihm2;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MenuController {
	@FXML
	void menu(ActionEvent event) throws IOException {
     Parent root2 = FXMLLoader.load(getClass().getResource("/application/Client.fxml"));
     Scene scene = new Scene (root2);
     Stage primaryStage = (Stage)((Node) event.getSource()).getScene().getWindow();
     primaryStage.setScene(scene);
     primaryStage.show();
     
}
	
	
	@FXML
	void stat1(ActionEvent event) throws IOException {
     Parent root2 = FXMLLoader.load(getClass().getResource("/application/graphe2.fxml"));
     Scene scene = new Scene (root2);
     Stage primaryStage = (Stage)((Node) event.getSource()).getScene().getWindow();
     primaryStage.setScene(scene);
     primaryStage.show();
	}
    
}
