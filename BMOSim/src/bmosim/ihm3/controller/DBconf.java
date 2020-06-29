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
    private JFXTextField name2;

    @FXML
    private JFXPasswordField pass2;

    @FXML
    private JFXButton update;

    @FXML
    private JFXTextField add1;

    @FXML
    private JFXTextField port1;

    @FXML
    private JFXTextField name1;

    @FXML
    private JFXTextField user2;

    @FXML
    private JFXTextField add2;

    @FXML
    private JFXTextField port2;

    @FXML
    void update(ActionEvent event) throws Exception {
        Enceyption e = new Enceyption();
        String p1 = e.encrypt(pass1.getText());
        String p2 = e.encrypt(pass2.getText());
        funct.setDBSet(name1.getText(),p1,name2.getText(),p2,
                add1.getText(),add2.getText(),port1.getText(),port2.getText(),user1.getText(),user2.getText());
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

        ArrayList<String> dbconf=funct.getDBSet();

        name1.setText(dbconf.get(0));
        pass1.setText(dbconf.get(1));
        add1.setText(dbconf.get(2));
        port1.setText(dbconf.get(3));
        user1.setText(dbconf.get(4));

        name2.setText(dbconf.get(5));
        pass2.setText(dbconf.get(6));
        add2.setText(dbconf.get(7));
        port2.setText(dbconf.get(8));
        user2.setText(dbconf.get(9));

    }

}
