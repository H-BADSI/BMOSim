package bmosim.ihm3.controller;


import bmosim.ihm3.Hibernate.hibernateAccount.DBUser;
import bmosim.ihm3.Main;
import bmosim.ihm3.Repository.AccountRepo.UserRepo;
import com.jfoenix.controls.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.exception.SQLGrammarException;
import org.hibernate.service.spi.ServiceException;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class install1 implements Initializable {

    @FXML
    private StackPane root;

    @FXML
    private AnchorPane an;

    @FXML
    private JFXTextField user1;

    @FXML
    private JFXPasswordField pass1;

    @FXML
    private JFXTextField add1;

    @FXML
    private JFXTextField port1;

    @FXML
    private JFXTextField name1;

    @FXML
    private Pane accPane;

    @FXML
    private JFXTextField name3;

    @FXML
    private JFXPasswordField pass3;

    @FXML
    private JFXTextField user3;

    @FXML
    private JFXTextField add3;

    @FXML
    private JFXTextField port3;

    @FXML
    private JFXTextField name2;

    @FXML
    private JFXPasswordField pass2;

    @FXML
    private JFXTextField user2;

    @FXML
    private JFXTextField add2;

    @FXML
    private JFXTextField port2;

    @FXML
    private JFXTextField name4;

    @FXML
    private JFXPasswordField pass4;

    @FXML
    private JFXTextField user4;

    @FXML
    private JFXTextField add4;

    @FXML
    private JFXTextField port4;

    @FXML
    private JFXComboBox appType;

    public SessionFactory sessionFactory;

    double xOffset = 0;
    double yOffset = 0;

    void go(String path, Event event) {
        Parent p = null;
        try {
            p = FXMLLoader.load(getClass().getResource(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene (p);
        Stage appStage = (Stage) ((Button) event.getSource()).getScene().getWindow();
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
    void close(MouseEvent event) {
        funct.unfirstTime();
        System.exit(0);
    }

    @FXML
    void next(ActionEvent event) {
        boolean b=true;
//        Configuration c1 = new Configuration();
//        Configuration c2 = new Configuration();
//        Configuration c3 = new Configuration();
//        Object o1=c1.configure("bmosim/ihm3/Hibernate/hibernateAccount/hiberAccount.cfg.xml");
//        Object o2=c2.configure("bmosim/ihm3/Hibernate/hibernateAgent/hiberAgent.cfg.xml");
//        Object o3=c3.configure("bmosim/ihm3/Hibernate/hibernateFeed/hiberFeed.cfg.xml");
//
//        c1.setProperty("hibernate.connection.url","jdbc:mysql://"+add3.getText()+":"+port3.getText()+"/"+name3.getText());
//        c1.setProperty("hibernate.connection.username",user3.getText());
//        c1.setProperty("hibernate.connection.password",pass3.getText());
//        if(accPane.isVisible()){
//            try {
//                sessionFactory= ((Configuration) o1).buildSessionFactory();
//            }catch (SQLGrammarException e){
//                System.out.println("ERROR**************1");
//            }catch (ExceptionInInitializerError e){
//                System.out.println("ERROR**************2");
//            }catch (ServiceException e){
//                b=false;
//                Main.Alert(root," \"Accounts DataBase\"","Could not connect to database","Maybe the database server is off or the parameters are wrong.");
//            }
//        }
//
//        c2.setProperty("hibernate.connection.url","jdbc:mysql://"+add1.getText()+":"+port1.getText()+"/"+name1.getText());
//        c2.setProperty("hibernate.connection.username",user1.getText());
//        c2.setProperty("hibernate.connection.password",pass1.getText());
//        try {
//            sessionFactory= ((Configuration) o2).buildSessionFactory();
//        }catch (SQLGrammarException e){
//            System.out.println("ERROR**************1");
//        }catch (ExceptionInInitializerError e){
//            System.out.println("ERROR**************2");
//        }catch (ServiceException e){
//            b=false;
//            Main.Alert(root," \"Accounts2 DataBase\"","Could not connect to database","Maybe the database server is off or the parameters are wrong.");
//        }
//
//        c3.setProperty("hibernate.connection.url","jdbc:mysql://"+add2.getText()+":"+port2.getText()+"/"+name2.getText());
//        c3.setProperty("hibernate.connection.username",user2.getText());
//        c3.setProperty("hibernate.connection.password",pass2.getText());
//        try {
//            sessionFactory= ((Configuration) o3).buildSessionFactory();
//        }catch (SQLGrammarException e){
//            System.out.println("ERROR**************4");
//        }catch (ExceptionInInitializerError e){
//            System.out.println("ERROR**************5");
//        }catch (ServiceException e){
//            b=false;
//            Main.Alert(root," \"Accounts3 DataBase\"","Could not connect to database","Maybe the database server is off or the parameters are wrong.");
//        }
        if(b){
            if(appType.getSelectionModel().isSelected(0)){
                funct.setAppType(false);

            }else {
                funct.setAppType(true);

            }
            if(funct.getAppType()){
                go("../view/login.fxml",event);
                Main.path="login";

            }else {
                go("../view/home.fxml",event);
                Main.path="home";

            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ArrayList<String> as = new ArrayList<>(Arrays.asList("mono user (without login)","multi user (with login)"));
        appType.getItems().addAll(as);
        appType.getSelectionModel().select(0);
        accPane.setVisible(false);

        appType.valueProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                if(appType.getSelectionModel().isSelected(0)){
                    accPane.setVisible(false);
                }else{
                    accPane.setVisible(true);
                }
            }
        });

        final KeyCombination keyComb1 = new KeyCodeCombination(KeyCode.F4,
                KeyCombination.ALT_ANY);
        an.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (keyComb1.match(event)) {
                    funct.unfirstTime();
                    System.exit(0);
                }
            }
        });
    }
}
