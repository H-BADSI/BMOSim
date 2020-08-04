package bmosim.ihm3.controller;

import bmosim.ihm3.Hibernate.hibernateAccount.DBUser;
import bmosim.ihm3.Main;
import bmosim.ihm3.Repository.AccountRepo.UserRepo;
//import bmosim.ihm3.Repository.AccountRepo.appUser;
import com.jfoenix.controls.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class createUser implements Initializable {

    @FXML
    private AnchorPane an;

    @FXML
    private StackPane root;

    @FXML
    private JFXTextField username;

    @FXML
    private JFXPasswordField pass;

    @FXML
    private JFXPasswordField passConf;

    @FXML
    private JFXComboBox admin;

    UserRepo userRepo=Main.userRepo;

    @FXML
    private void alert(DBUser u, String label, boolean showLabel){
        JFXButton confirm = new JFXButton("confirm");
        JFXButton cancel = new JFXButton("cancel");
        confirm.setStyle("-fx-background-color: linear-gradient(from 300px 70px to 280px 500px,#43cea2, #185a9d) ");
        cancel.setStyle("-fx-background-color: #dddddd");
        JFXDialogLayout dialogLayout = new JFXDialogLayout();
        dialogLayout.setHeading(new Text("enter an admin's credentials , please"));

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 0, 10, 10));

        JFXTextField username = new JFXTextField();
        username.setFocusColor(Color.rgb(22,215,212));
        username.setStyle("-fx-text-fill: black");
        JFXPasswordField password = new JFXPasswordField();
        password.setFocusColor(Color.rgb(22,215,212));
        password.setStyle("-fx-text-fill: black");

        grid.add(new Text("Username:"), 0, 0);
        grid.add(username, 1, 0);
        grid.add(new Text("Password:"), 0, 1);
        grid.add(password, 1, 1);
        Label l = new Label("*Wrong credentials");
        l.setStyle("-fx-text-fill: red");
        l.setVisible(showLabel);
        l.setText(label);
        grid.add(l,1,2);
        dialogLayout.setBody(grid);

        JFXDialog dialog = new JFXDialog(root,dialogLayout,JFXDialog.DialogTransition.TOP);

        username.textProperty().addListener((observable, oldValue, newValue) -> {
            confirm.setDisable(newValue.trim().isEmpty() || password.getText().isEmpty());
        });
        password.textProperty().addListener((observable, oldValue, newValue) -> {
            confirm.setDisable(newValue.trim().isEmpty() || username.getText().isEmpty());
        });
        dialog.setStyle("-fx-background-color:  linear-gradient(from 300px 70px to 280px 500px,#43cea2, #185a9d)");

        confirm.setOnAction(event -> {
            DBUser us = new DBUser(username.getText(),password.getText());
            String adm = userRepo.isAdmin(us);
            if(adm!=null){
                if(adm.equals("admin") || adm.equals("superAdmin")){
                    userRepo.insertUser(u);
                    an.setEffect(null);
                    dialog.close();
                }else{
                    dialog.close();
                    alert(u,"This user isn't an admin !",true);

                }
            }else{
                dialog.close();
                alert(u,"Wrong credentials !",true);
            }
        }
        );
        cancel.setOnAction(event -> {
            an.setEffect(null);
            dialog.close();
        });
        confirm.setDisable(true);
        dialogLayout.setActions(confirm,new Text("   "),cancel);
        dialog.show();
    }

    @FXML
    void createUser(ActionEvent event) {
        DBUser u = new DBUser(username.getText(),pass.getText(),admin.getValue().toString());
        if(username.getText().length()<6){
            username.setUnFocusColor(Color.RED);
            Main.Alert(root,"A username should be 6 characters at least","","");
        }else if(pass.getText().length()<8){
            username.setUnFocusColor(Color.rgb(77,77,77));
            pass.setUnFocusColor(Color.RED);
            Main.Alert(root,"A password should be 8 characters at least.","","");
        }else if(!pass.getText().equals(passConf.getText()) || passConf.getText().isEmpty()){
            pass.setUnFocusColor(Color.rgb(77,77,77));
            passConf.setUnFocusColor(Color.RED);
            Main.Alert(root,"Passwords don't match !","Check again","");
        }else if(userRepo.userExist(u)){
            pass.setUnFocusColor(Color.rgb(77,77,77));
            passConf.setUnFocusColor(Color.rgb(77,77,77));
            username.setUnFocusColor(Color.RED);
            Main.Alert(root,"Username already exists !","try another one, please","");
        }else{
            alert(u,"",false);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        admin.getItems().addAll(new ArrayList(Arrays.asList("ordinary","admin")));
        admin.getSelectionModel().select(0);

    }


}
