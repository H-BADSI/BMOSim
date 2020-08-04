package bmosim.ihm3.controller;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class DBconf implements Initializable{

    @FXML
    private AnchorPane an;

    @FXML
    private JFXTextField user1;

    @FXML
    private JFXPasswordField pass1;

    @FXML
    private JFXTextField name1;

    @FXML
    private JFXTextField add1;

    @FXML
    private JFXTextField port1;

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
    private JFXButton update;

    @FXML
    void update(ActionEvent event) throws Exception {
        ArrayList<DBconfStruct> dbconf = new ArrayList<>();
        Enceyption e = new Enceyption();
        dbconf.add(new DBconfStruct(add1.getText(),port1.getText(),name1.getText(),user1.getText(),e.encrypt(pass1.getText())));
        dbconf.add(new DBconfStruct(add2.getText(),port2.getText(),name2.getText(),user2.getText(),e.encrypt(pass2.getText())));
        dbconf.add(new DBconfStruct(add3.getText(),port3.getText(),name3.getText(),user3.getText(),e.encrypt(pass3.getText())));
        dbconf.add(new DBconfStruct(add4.getText(),port4.getText(),name4.getText(),user4.getText(),e.encrypt(pass4.getText())));
        funct.setDBSet(dbconf);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        name1.setFocusTraversable(false);
        name2.setFocusTraversable(false);
        pass1.setFocusTraversable(false);
        pass2.setFocusTraversable(false);
        add1.setFocusTraversable(false);
        add2.setFocusTraversable(false);
        port1.setFocusTraversable(false);
        port2.setFocusTraversable(false);
        user1.setFocusTraversable(false);
        user2.setFocusTraversable(false);
        update.setFocusTraversable(false);

        ArrayList<DBconfStruct> dbconf=funct.getDBSet();

        name1.setText(dbconf.get(0).name);
        pass1.setText(dbconf.get(0).pass);
        add1.setText(dbconf.get(0).add);
        port1.setText(dbconf.get(0).port);
        user1.setText(dbconf.get(0).user);

        name2.setText(dbconf.get(1).name);
        pass2.setText(dbconf.get(1).pass);
        add2.setText(dbconf.get(1).add);
        port2.setText(dbconf.get(1).port);
        user2.setText(dbconf.get(1).user);

        name3.setText(dbconf.get(2).name);
        pass3.setText(dbconf.get(2).pass);
        add3.setText(dbconf.get(2).add);
        port3.setText(dbconf.get(2).port);
        user3.setText(dbconf.get(2).user);

        name4.setText(dbconf.get(3).name);
        pass4.setText(dbconf.get(3).pass);
        add4.setText(dbconf.get(3).add);
        port4.setText(dbconf.get(3).port);
        user4.setText(dbconf.get(3).user);

    }

}
