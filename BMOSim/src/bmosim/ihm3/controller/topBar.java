package bmosim.ihm3.controller;


import bmosim.ihm3.Hibernate.hibernateAccount.DBUser;
import bmosim.ihm3.Main;
//import bmosim.ihm3.Repository.AccountRepo.appUser;
import bmosim.ihm3.Repository.AccountRepo.UserRepo;
import com.jfoenix.controls.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class topBar implements Initializable{

    @FXML
    private StackPane root;

    @FXML
    private AnchorPane anchor;

    @FXML
    private Label user;

    @FXML
    private Pane logout;

    @FXML
    private Pane appusers;

    @FXML
    private Pane changepass;


    double xOffset = 0;
    double yOffset = 0;

    UserRepo userRepo = Main.userRepo;



    void go(String path, MouseEvent event) throws IOException {

        Parent p = FXMLLoader.load(getClass().getResource(path));
        Scene scene = new Scene (p);
        Stage appStage = (Stage) ((Pane) event.getSource()).getScene().getWindow();
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
        if(path.equals("../view/login.fxml")){
            Main.x=570;
            Main.y=130;
        }
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

    @FXML
    void logout(MouseEvent event) throws IOException {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        Main.loginUser.setLastLogout(dtf.format(now));
        userRepo.updateLogoutUser(Main.loginUser);
        go("../view/login.fxml",event);
    }

    @FXML
    void appusers(MouseEvent event) throws IOException {
        go("../view/accounts.fxml",event);
    }

    @FXML
    void changePass(MouseEvent event) {
        Scene s = (Scene) anchor.getScene();
        StackPane root = (StackPane) s.getRoot();
        alert(root,new DBUser(),"pass",false);
    }

    private void alert(StackPane root,DBUser u, String label, boolean showLabel){
        JFXButton confirm = new JFXButton("confirm");
        JFXButton cancel = new JFXButton("cancel");
        confirm.setStyle("-fx-background-color: linear-gradient(from 300px 70px to 280px 500px,#43cea2, #185a9d) ");
        cancel.setStyle("-fx-background-color: #dddddd");
        JFXDialogLayout dialogLayout = new JFXDialogLayout();
        dialogLayout.setHeading(new Text("Change your password"));

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 0, 10, 10));

        JFXPasswordField oldPass = new JFXPasswordField();
        oldPass.setFocusColor(Color.rgb(22,215,212));
        oldPass.setStyle("-fx-text-fill: black");
        JFXPasswordField newPass = new JFXPasswordField();
        newPass.setFocusColor(Color.rgb(22,215,212));
        newPass.setStyle("-fx-text-fill: black");
        JFXPasswordField confPass = new JFXPasswordField();
        confPass.setFocusColor(Color.rgb(22,215,212));
        confPass.setStyle("-fx-text-fill: black");

        grid.add(new Text("old password:"), 0, 0);
        grid.add(oldPass, 1, 0);
        grid.add(new Text("new password:"), 0, 1);
        grid.add(newPass, 1, 1);
        grid.add(new Text("confirm new password:"), 0, 2);
        grid.add(confPass, 1, 2);
        Label l = new Label("*Wrong credentials");
        l.setStyle("-fx-text-fill: red");
        l.setVisible(showLabel);
        l.setText(label);
        grid.add(l,1,3);
        dialogLayout.setBody(grid);

        JFXDialog dialog = new JFXDialog(root,dialogLayout,JFXDialog.DialogTransition.TOP);

        oldPass.textProperty().addListener((observable, oldValue, newValue) -> {
            confirm.setDisable(oldPass.getText().isEmpty() || newPass.getText().isEmpty() || confPass.getText().isEmpty());
        });
        newPass.textProperty().addListener((observable, oldValue, newValue) -> {
            confirm.setDisable(oldPass.getText().isEmpty() || newPass.getText().isEmpty() || confPass.getText().isEmpty() || (!newPass.getText().equals(confPass.getText())));
        });
        confPass.textProperty().addListener((observable, oldValue, newValue) -> {
            confirm.setDisable(oldPass.getText().isEmpty() || newPass.getText().isEmpty() || confPass.getText().isEmpty() || (!newPass.getText().equals(confPass.getText())));
        });
        dialog.setStyle("-fx-background-color:  linear-gradient(from 300px 70px to 280px 500px,#43cea2, #185a9d)");

        confirm.setOnAction(event -> {
            System.out.println(oldPass);
            System.out.println(Main.loginUser.getPassword());
            if(oldPass.getText().equals(Main.loginUser.getPassword())){
                Main.loginUser.setPassword(newPass.getText());
                userRepo.updatePassUser(Main.loginUser);
                dialog.close();
            }else{
                dialog.close();
                alert(root,u,"Wrong password !",true);
            }
        });
        cancel.setOnAction(event -> {
//            an.setEffect(null);
            dialog.close();
        });
        confirm.setDisable(true);
        dialogLayout.setActions(confirm,new Text("   "),cancel);
        dialog.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        user.setText(Main.loginUser.getUsername());
        if(Main.loginUser.getAdmin().equals("ordinary")) appusers.setVisible(false);
    }

}
