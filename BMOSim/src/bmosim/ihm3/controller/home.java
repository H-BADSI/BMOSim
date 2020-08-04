package bmosim.ihm3.controller;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import java.net.URL;
import java.util.ResourceBundle;


public class home implements Initializable{

    @FXML
    public AnchorPane an;

    @FXML
    public ImageView image;

    @FXML
    private Pane pane;

    TranslateTransition ttimage = new TranslateTransition();
    TranslateTransition ttpane = new TranslateTransition();

    public void animate(ImageView image,Pane pane){

        ttimage.setDuration(Duration.millis(1000));
        ttimage.setNode(image);
        ttimage.setByX(-650);

        ttpane.setDuration(Duration.millis(1000));
        ttpane.setNode(pane);
        ttpane.setByY(-200);

        ttpane.play();
        ttimage.play();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        animate(image,pane);
    }
}
