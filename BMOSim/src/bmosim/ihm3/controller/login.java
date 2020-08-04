package bmosim.ihm3.controller;

import bmosim.ihm3.Hibernate.hibernateAccount.DBUser;
import bmosim.ihm3.Main;
import bmosim.ihm3.Repository.AccountRepo.UserRepo;
//import bmosim.ihm3.Repository.AccountRepo.appUser;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class login implements Initializable {


    @FXML
    private AnchorPane anchor;

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

    UserRepo userRepo = Main.userRepo;

    void go(String path, Event event) {

        Parent p = null;
        try {
            p = FXMLLoader.load(getClass().getResource(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene (p);
        Stage appStage = (Stage) anchor.getScene().getWindow();
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

    public void login(Event Event){
        DBUser u = new DBUser(username.getText(),pass.getText());
        if(userRepo.userExistPass(u)){
            DBUser dbu=userRepo.getUserByName(username.getText());
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            dbu.setLastLogin(dtf.format(now));
            Main.loginUser=dbu;
            userRepo.updateLoginUser(Main.loginUser);
            Main.path="home";
            go("../view/home.fxml",Event);
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
        username.setFocusTraversable(true);
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

//        Scene s = anchor.getScene();
//        System.out.println("ss"+s.getWidth());

        anchor.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                login(e);
            }
        });

//        s.setOnKeyPressed(new EventHandler<KeyEvent>() {
//            @Override
//            public void handle(KeyEvent event) {
//                if (event.getCode() == KeyCode.ENTER) {
//                    login(event);
//                }
//            }
//
//        });
    }
}
