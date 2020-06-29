package bmosim.ihm3.controller;


import bmosim.ihm3.Controller;
import bmosim.ihm3.Main;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class topBar implements Initializable{

    @FXML
    private AnchorPane anchor;

    @FXML
    private Label user;

    @FXML
    void changeLight(MouseEvent event) {

        String dark1 = getClass().getResource("../css/dark.css").toExternalForm();
        String light1 = getClass().getResource("../css/light.css").toExternalForm();
        if(Main.dark_light){

            anchor.getScene().getStylesheets().clear();
            anchor.getScene().getStylesheets().add(light1);
            funct.setDarkLight(false);
        }else{
            anchor.getScene().getStylesheets().clear();
            anchor.getScene().getStylesheets().add(dark1);
            funct.setDarkLight(true);
        }
    }

    @FXML
    void close(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    void reduce(MouseEvent event) {
        Stage s = (Stage) anchor.getScene().getWindow();
        s.setIconified(true);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        user.setText(Main.username);

    }

}
