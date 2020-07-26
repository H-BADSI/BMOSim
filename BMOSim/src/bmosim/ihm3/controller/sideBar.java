package bmosim.ihm3.controller;

import bmosim.ihm3.Main;
import com.jfoenix.controls.JFXDrawer;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

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
    private JFXDrawer drawer2;

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

    double xOffset = 0;
    double yOffset = 0;

    BoxBlur bb1 = new BoxBlur(10.0f,0.0f,1);
    BoxBlur bb2 = new BoxBlur(0.0f,0.0f,0);

    void go(String path,MouseEvent event) throws IOException {
        Parent p =FXMLLoader.load(getClass().getResource(path));
        Scene scene = new Scene (p);
        Stage appStage = (Stage) ((BorderPane) event.getSource()).getScene().getWindow();
//         appStage.hide();
        String dark = getClass().getResource("../css/dark.css").toExternalForm();
        String light = getClass().getResource("../css/light.css").toExternalForm();
        if (Main.dark_light) {
            scene.getStylesheets().clear();
            scene.getStylesheets().add(dark);
        } else {
            scene.getStylesheets().clear();
            scene.getStylesheets().add(light);
        }
        p.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
        appStage.setX(Main.x);
        appStage.setY(Main.y);
        appStage.setScene(scene);
//        appStage.sizeToScene();
        p.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Main.x=event.getScreenX() - xOffset;
                Main.y=event.getScreenY() - yOffset;
                appStage.setX(event.getScreenX() - xOffset);
                appStage.setY(event.getScreenY() - yOffset);
            }
        });

        appStage.show();
    }

    @FXML
    void goHome(MouseEvent event) throws Exception {
        go("../view/home.fxml",event);
    }

    @FXML
    void goConf(MouseEvent event) throws IOException {
//        go("../view/configuration.fxml",event);
        if(drawer2.isHidden()){
            drawer2.open();
            drawer.close();
            drawer2.setLayoutX(240);
        }else {
            drawer2.close();
            drawer2.setLayoutX(-161);
        }
    }

    @FXML
    void goMetaModel(MouseEvent event) throws IOException {
        go("../view/createAgent.fxml",event);
    }

    @FXML
    void goModel(MouseEvent event) throws IOException {
        go("../view/createFile.fxml",event);
    }

    @FXML
    void goSimulate(MouseEvent event) throws IOException {
        go("../view/simulate.fxml",event);
    }

    @FXML
    void goStat(MouseEvent event) {
        if(drawer.isHidden()){
            drawer.open();
            drawer2.close();
            drawer.setLayoutX(240);
        }else {
            drawer.close();
            drawer.setLayoutX(-282);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        funct.setImage(m1,"src/bmosim/ihm3/images/home1.png");
        funct.setImage(m2,"src/bmosim/ihm3/images/home.png");
        funct.setImage(m3,"src/bmosim/ihm3/images/setting.png");
        funct.setImage(m4,"src/bmosim/ihm3/images/code.png");
        funct.setImage(m5,"src/bmosim/ihm3/images/connection.png");
        funct.setImage(m6,"src/bmosim/ihm3/images/gloves.png");
        try {
            HBox box = FXMLLoader.load(getClass().getResource("../view/stat.fxml"));
            drawer.setSidePane(box);
            HBox box2 = FXMLLoader.load(getClass().getResource("../view/conf.fxml"));
            drawer2.setSidePane(box2);
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
