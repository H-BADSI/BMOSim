package bmosim.ihm3.controller;

import bmosim.ihm3.Main;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class sideBar implements Initializable{
    //#16d7d4
    //#2ea1a1
    @FXML
    private BorderPane home;

    @FXML
    private BorderPane stat;

    @FXML
    private BorderPane model;

    @FXML
    private BorderPane metaModel;

    @FXML
    private BorderPane conf;

    @FXML
    private BorderPane simulate;

    @FXML
    private JFXDrawer drawer;

    @FXML
    private ImageView m1;
    @FXML
    private ImageView m2;
    @FXML
    private ImageView m3;
    @FXML
    private ImageView m4;
    @FXML
    private ImageView m5;
    @FXML
    private ImageView m6;
    @FXML
    private ImageView logo;

    BoxBlur bb1 = new BoxBlur(10.0f,0.0f,1);
    BoxBlur bb2 = new BoxBlur(0.0f,0.0f,0);

    void setImage(ImageView im,String path){
        File file = new File(path);
        im.setImage(new Image(file.toURI().toString()));
    }

    void go(String path,MouseEvent event) throws IOException {
        Parent p =FXMLLoader.load(getClass().getResource(path));
        Scene scene = new Scene (p);
        Stage appStage = (Stage) ((ImageView) event.getSource()).getScene().getWindow();
//        appStage.hide();
        String dark = getClass().getResource("../css/dark.css").toExternalForm();
        String light = getClass().getResource("../css/light.css").toExternalForm();
        if (Main.dark_light) {
            scene.getStylesheets().clear();
            scene.getStylesheets().add(dark);
        } else {
            scene.getStylesheets().clear();
            scene.getStylesheets().add(light);
        }
        appStage.setScene(scene);
        appStage.sizeToScene();
        appStage.show();
    }

    @FXML
    void goHome(MouseEvent event) throws Exception {
        go("../view/simulate.fxml",event);
    }

    @FXML
    void goConf(MouseEvent event) throws IOException {
        go("../view/configuration.fxml",event);
    }

    @FXML
    void goMetaModel(MouseEvent event) throws IOException {
        go("../view/createAgent.fxml",event);
    }

    @FXML
    void goModel(MouseEvent event) throws IOException {
        go("../view/sample1.fxml",event);
    }

    @FXML
    void goSimulate(MouseEvent event) throws IOException {
        go("../view/simulate.fxml",event);
    }

    @FXML
    void goStat(MouseEvent event) throws IOException {
        if(drawer.isHidden()){
            drawer.open();
        }else {
            drawer.close();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        setImage(logo,"C:\\Users\\BRAHIM\\Downloads\\3juil2019\\demo\\BMOSim\\BMOSim\\src\\bmosim\\ihm3\\images\\logo4.png");
        setImage(m1,"C:\\Users\\BRAHIM\\Downloads\\3juil2019\\demo\\BMOSim\\BMOSim\\src\\bmosim\\ihm3\\images\\home1.png");
        setImage(m2,"C:\\Users\\BRAHIM\\Downloads\\3juil2019\\demo\\BMOSim\\BMOSim\\src\\bmosim\\ihm3\\images\\home.png");
        setImage(m3,"C:\\Users\\BRAHIM\\Downloads\\3juil2019\\demo\\BMOSim\\BMOSim\\src\\bmosim\\ihm3\\images\\setting.png");
        setImage(m4,"C:\\Users\\BRAHIM\\Downloads\\3juil2019\\demo\\BMOSim\\BMOSim\\src\\bmosim\\ihm3\\images\\code.png");
        setImage(m5,"C:\\Users\\BRAHIM\\Downloads\\3juil2019\\demo\\BMOSim\\BMOSim\\src\\bmosim\\ihm3\\images\\connection.png");
        setImage(m6,"C:\\Users\\BRAHIM\\Downloads\\3juil2019\\demo\\BMOSim\\BMOSim\\src\\bmosim\\ihm3\\images\\gloves.png");
        try {
            HBox box = FXMLLoader.load(getClass().getResource("../view/stat.fxml"));
            drawer.setSidePane(box);
        } catch (IOException e) {
            e.printStackTrace();
        }

        home.hoverProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue) {
                    m1.setEffect(bb1);
                } else {
                    m1.setEffect(bb2);
                }
            }
        });

        stat.hoverProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue) {
                    m2.setEffect(bb1);
                } else {
                    m2.setEffect(bb2);
                }
            }
        });

        conf.hoverProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue) {
                    m3.setEffect(bb1);
                } else {
                    m3.setEffect(bb2);
                }
            }
        });

        model.hoverProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue) {
                    m4.setEffect(bb1);
                } else {
                    m4.setEffect(bb2);
                }
            }
        });

        metaModel.hoverProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue) {
                    m5.setEffect(bb1);

                } else {
                    m5.setEffect(bb2);
                }
            }
        });

        simulate.hoverProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue) {
                    m6.setEffect(bb1);
                } else {
                    m6.setEffect(bb2);
                }
            }
        });

    }
}
