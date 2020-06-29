package bmosim.ihm3.controller;


import bmosim.ihm3.Main;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class stat implements Initializable{


    @FXML
    private HBox anchor;

    @FXML
    private BorderPane stat1;

    @FXML
    private BorderPane stat2;

    @FXML
    private BorderPane stat3;

    @FXML
    private ImageView ch1,ch2,ch3;

    private double xOffset=0;
    private double yOffset=0;

    DropShadow ds1 = new DropShadow(BlurType.GAUSSIAN, Color.BLACK,0,50,0,0);
    DropShadow ds2 = new DropShadow();

    void go(String path,MouseEvent event) throws IOException {
        Parent p =FXMLLoader.load(getClass().getResource(path));
        Scene scene = new Scene (p);
        Stage appStage = (Stage) ((BorderPane) event.getSource()).getScene().getWindow();
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
    void goStat1(MouseEvent event) throws IOException {
        go("../view/stat1.fxml",event);

    }

    @FXML
    void gostat2(MouseEvent event) throws IOException {
        go("../view/stat2.fxml",event);

    }

    @FXML
    void gostat3(MouseEvent event) throws IOException {
        go("../view/stat3.fxml",event);

    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        stat1.hoverProperty().addListener(new ChangeListener<Boolean>() {

            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue) {
                    ch1.setEffect(ds2);
                } else {
                    ch1.setEffect(ds1);
                }
            }
        });

        stat2.hoverProperty().addListener(new ChangeListener<Boolean>() {

            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue) {
                    ch2.setEffect(ds2);
                } else {
                    ch2.setEffect(ds1);
                }
            }
        });
        stat3.hoverProperty().addListener(new ChangeListener<Boolean>() {

            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue) {
                    ch3.setEffect(ds2);
                } else {
                    ch3.setEffect(ds1);
                }
            }
        });


    }

}
