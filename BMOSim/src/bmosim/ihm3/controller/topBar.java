package bmosim.ihm3.controller;


import bmosim.ihm3.Main;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
    private ImageView cls;

    @FXML
    private Pane dl;

    @FXML
    private ImageView light;

    @FXML
    private ImageView rdc;

    void setImage(ImageView im,String path){
        File file = new File(path);
        im.setImage(new Image(file.toURI().toString()));
    }

    @FXML
    void changeLight(MouseEvent event) {
        String dark1 = getClass().getResource("../css/dark.css").toExternalForm();
        String light1 = getClass().getResource("../css/light.css").toExternalForm();
        if(Main.dark_light==true){
            funct.setDarkLight(false);
            anchor.getScene().getStylesheets().clear();
            anchor.getScene().getStylesheets().add(light1);
            setImage(light,"C:\\Users\\BRAHIM\\Downloads\\3juil2019\\demo\\BMOSim\\BMOSim\\src\\bmosim\\ihm3\\images\\idea.png");
        }else{
            anchor.getScene().getStylesheets().clear();
            anchor.getScene().getStylesheets().add(dark1);
            funct.setDarkLight(true);
            setImage(light,"C:\\Users\\BRAHIM\\Downloads\\3juil2019\\demo\\BMOSim\\BMOSim\\src\\bmosim\\ihm3\\images\\bulb.png");
        }
    }

    @FXML
    void close(MouseEvent event) {
        System.exit(0);

    }

    @FXML
    void reduce(MouseEvent event) {

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        if(Main.dark_light==false){
            setImage(light,"C:\\Users\\BRAHIM\\Downloads\\3juil2019\\demo\\BMOSim\\BMOSim\\src\\bmosim\\ihm3\\images\\idea.png");
        }else{
            setImage(light,"C:\\Users\\BRAHIM\\Downloads\\3juil2019\\demo\\BMOSim\\BMOSim\\src\\bmosim\\ihm3\\images\\bulb.png");
        }

        cls.hoverProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue) {
                    setImage(cls,"C:\\Users\\BRAHIM\\Downloads\\3juil2019\\demo\\BMOSim\\BMOSim\\src\\bmosim\\ihm3\\images\\power-red.png");
                } else {
                    setImage(cls,"C:\\Users\\BRAHIM\\Downloads\\3juil2019\\demo\\BMOSim\\BMOSim\\src\\bmosim\\ihm3\\images\\power.png");
                }
            }
        });
        rdc.hoverProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue) {
                    setImage(rdc,"C:\\Users\\BRAHIM\\Downloads\\3juil2019\\demo\\BMOSim\\BMOSim\\src\\bmosim\\ihm3\\images\\minus-red.png");
                } else {
                    setImage(rdc,"C:\\Users\\BRAHIM\\Downloads\\3juil2019\\demo\\BMOSim\\BMOSim\\src\\bmosim\\ihm3\\images\\minus.png");
                }
            }
        });
        light.hoverProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue) {
                    light.setOpacity(0.5);
                } else {
                    light.setOpacity(1);
                }
            }
        });
    }

}
