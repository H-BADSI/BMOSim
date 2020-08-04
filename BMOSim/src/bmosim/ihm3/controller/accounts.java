package bmosim.ihm3.controller;


import bmosim.ihm3.Hibernate.hibernateAccount.DBUser;
import bmosim.ihm3.Main;
import bmosim.ihm3.Repository.AccountRepo.UserRepo;
import com.jfoenix.controls.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class accounts implements Initializable {

    @FXML
    private StackPane root;

    @FXML
    private AnchorPane an;

    @FXML
    private TableView<DBUser> table;

    @FXML
    private TableColumn<DBUser,Integer> id;

    @FXML
    private TableColumn<DBUser,String> username;

    @FXML
    private TableColumn<DBUser,Boolean> admin;

    @FXML
    private TableColumn<DBUser,String> login;

    @FXML
    private TableColumn<DBUser,String> logout;

    @FXML
    private JFXButton delete;

    @FXML
    private JFXButton adm;

    @FXML
    private JFXButton sadm;

    @FXML
    private JFXButton ordianry;

    double xOffset = 0;
    double yOffset = 0;

    UserRepo userRepo = Main.userRepo;

    void go(String path) {

        Parent p = null;
        try {
            p = FXMLLoader.load(getClass().getResource(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene (p);
        Stage appStage = (Stage) an.getScene().getWindow();
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

    private void alertRemove(StackPane root,DBUser user){
        JFXButton confirm = new JFXButton("confirm");
        JFXButton cancel = new JFXButton("cancel");
        confirm.setStyle("-fx-background-color: linear-gradient(from 300px 70px to 280px 500px,#43cea2, #185a9d) ");
        cancel.setStyle("-fx-background-color: #dddddd");
        JFXDialogLayout dialogLayout = new JFXDialogLayout();
        dialogLayout.setHeading(new Text("Are you sure that you want to remove "+user.getUsername()+"?"));

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 0, 10, 10));
        dialogLayout.setBody(grid);

        JFXDialog dialog = new JFXDialog(root,dialogLayout,JFXDialog.DialogTransition.TOP);
        dialog.setStyle("-fx-background-color:  linear-gradient(from 300px 70px to 280px 500px,#43cea2, #185a9d)");

        confirm.setOnAction(event -> {
            userRepo.deleteUser(user);
            Main.path="accounts";
            go("../view/accounts.fxml");
            dialog.close();
        });
        cancel.setOnAction(event -> {
            dialog.close();
        });
        dialogLayout.setActions(confirm,new Text("   "),cancel);
        dialog.show();
    }

    private void alertUpdate(StackPane root,DBUser user,String admin){
        JFXButton confirm = new JFXButton("confirm");
        JFXButton cancel = new JFXButton("cancel");
        confirm.setStyle("-fx-background-color: linear-gradient(from 300px 70px to 280px 500px,#43cea2, #185a9d) ");
        cancel.setStyle("-fx-background-color: #dddddd");
        JFXDialogLayout dialogLayout = new JFXDialogLayout();
        dialogLayout.setHeading(new Text("Are you sure that you want to make "+user.getUsername()+" a "+admin));

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 0, 10, 10));
        Label l = new Label("*Note that if you make this user a super admin \n you will not be super admin anymore !");
        l.setStyle("-fx-text-fill: red");
        if(admin.equals("superAdmin")) grid.getChildren().add(l);

        dialogLayout.setBody(grid);

        JFXDialog dialog = new JFXDialog(root,dialogLayout,JFXDialog.DialogTransition.TOP);


        dialog.setStyle("-fx-background-color:  linear-gradient(from 300px 70px to 280px 500px,#43cea2, #185a9d)");

        confirm.setOnAction(event -> {
            user.setAdmin(admin);
            userRepo.updateAdminUser(user);
            Main.loginUser=userRepo.getUserByName(Main.loginUser.getUsername());
            dialog.close();
            Main.path="accounts";
            go("../view/accounts.fxml");
        });
        cancel.setOnAction(event -> {
            dialog.close();
        });
        dialogLayout.setActions(confirm,new Text("   "),cancel);
        dialog.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(Main.loginUser.getAdmin().equals("admin"))sadm.setVisible(false);
        if(Main.loginUser.getAdmin().equals("admin"))ordianry.setVisible(false);

        ObservableList observableList = FXCollections.observableArrayList(userRepo.getAllUsers(Main.loginUser));
        id.setCellValueFactory(new PropertyValueFactory<>("iduser"));
        username.setCellValueFactory(new PropertyValueFactory<>("username"));
        admin.setCellValueFactory(new PropertyValueFactory<>("admin"));
        login.setCellValueFactory(new PropertyValueFactory<>("lastLogin"));
        logout.setCellValueFactory(new PropertyValueFactory<>("lastLogout"));
        table.setItems(observableList);

    }

    public void delete(javafx.event.ActionEvent actionEvent) {
        DBUser user;
        user=table.getSelectionModel().getSelectedItem();
        if(Main.loginUser.getAdmin().equals("superAdmin")){
            if(user.getAdmin().equals("superAdmin")){
                Main.Alert(root,"You cant remove your own account !","","");
            }else{
                alertRemove(root,user);
            }
        }else{
            if(user.getAdmin().equals("superAdmin")){
                Main.Alert(root,"You cant remove a superAdmin !","","");
            }else if(user.getAdmin().equals("admin")){
                Main.Alert(root,"You cant remove an Admin !","","");
            }else{
                alertRemove(root,user);
            }
        }

    }

    public void makeAdm(javafx.event.ActionEvent actionEvent) {
        DBUser user;
        user=table.getSelectionModel().getSelectedItem();
        if(user.getAdmin().equals("ordinary")){
            alertUpdate(root,user,"admin");
        }else if(user.getAdmin().equals("superAdmin")){
            if(Main.loginUser.getAdmin().equals("superAdmin")){
                Main.Alert(root,"To degrade yourself to admin you should make another user a super Admin.","","");
            }else{
                Main.Alert(root,"you do not have the authority to make a super admin as an admin.","","");
            }
        }else{
            Main.Alert(root,"this user is already an admin","","");
        }

    }

    public void makeSAdm(javafx.event.ActionEvent actionEvent) {
        DBUser user;
        user=table.getSelectionModel().getSelectedItem();
        if(!user.getAdmin().equals("superAdmin")){
            alertUpdate(root,user,"superAdmin");
        }else{
            Main.Alert(root,"this user is already a super admin.","","");
        }
    }

    public void makeOrd(javafx.event.ActionEvent actionEvent) {
        DBUser user;
        user=table.getSelectionModel().getSelectedItem();
        if(user.getAdmin().equals("admin")){
            alertUpdate(root,user,"ordinary");
        }else if(user.getAdmin().equals("superAdmin")){
            Main.Alert(root,"you cannot make a super admin as an ordinary user.",
                    "*Changing the super admin will allow you to make this user an admin then an ordinary user","");
        }else{
            Main.Alert(root,"this user is already an ordinary user.","","");
        }
    }
}
