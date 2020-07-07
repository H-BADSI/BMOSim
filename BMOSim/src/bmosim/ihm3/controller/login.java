package bmosim.ihm3.controller;

import bmosim.ihm3.Main;
import bmosim.ihm3.Repository.AccountRepo.UserRepo;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class login implements Initializable {


    @FXML
    private JFXTextField username;

    @FXML
    private JFXPasswordField pass;

    @FXML
    private JFXButton createUser;

    @FXML
    private Label err;

    @FXML
    private JFXDrawer drawer;
    double xOffset = 0;
    double yOffset = 0;

    void go(String path, ActionEvent event) throws IOException {

        Parent p =FXMLLoader.load(getClass().getResource(path));
        Scene scene = new Scene (p);
        Stage appStage = (Stage) ((Button) event.getSource()).getScene().getWindow();
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
        Main.x=170;
        Main.y=70;
        appStage.setX(Main.x);
        appStage.setY(Main.y);
        appStage.setScene(scene);
        appStage.sizeToScene();
        p.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                appStage.setX(event.getScreenX() - xOffset);
                appStage.setY(event.getScreenY() - yOffset);
            }
        });
        appStage.show();
    }

    @FXML
    void close(MouseEvent event) {
        System.exit(0);
    }

    public void login(ActionEvent actionEvent) throws IOException {
        UserRepo u = new UserRepo(username.getText(),pass.getText());
        if(u.userExistPass()){
            Main.username=username.getText();
            go("../view/home.fxml",actionEvent);
        }else{
            username.setUnFocusColor(Color.RED);
            pass.setUnFocusColor(Color.RED);
            err.setVisible(true);
        }
    }

    public void createUser(ActionEvent actionEvent) {
        if(drawer.isShown()){
            drawer.close();
            drawer.toBack();
            createUser.setText("Create User");

        }else {
            drawer.open();
            drawer.toFront();
            createUser.setText("Login");
        }

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        drawer.toBack();
        try {
            StackPane an = FXMLLoader.load(getClass().getResource("../view/createUser.fxml"));
            drawer.setSidePane(an);
        } catch (IOException e) {
            e.printStackTrace();
        }

        username.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                username.setUnFocusColor(Color.rgb(77,77,77));
            }
        });
        pass.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                pass.setUnFocusColor(Color.rgb(77,77,77));
            }
        });

    }


}
