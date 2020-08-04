package bmosim.ihm3.controller;

import bmosim.ihm3.Main;
import bmosim.ihm3.Repository.AgentRepo.AgRepo;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import bmosim.ihm3.model.type;
import org.apache.jena.base.Sys;

public class createAgent implements Initializable{


    @FXML
    private StackPane root;
    @FXML
    private ComboBox agenttype;
    @FXML
    private JFXComboBox attName1;
    @FXML
    private ComboBox<String> attType1;
    @FXML
    private ComboBox<String> agClass;
    @FXML
    private VBox vbox1;
    @FXML
    private VBox vbox2;
    @FXML
    private VBox vbox3;
    @FXML
    private VBox vbox4;
    @FXML
    private JFXTextField attVal1;

    @FXML
    private Separator sep2;

    @FXML
    private Separator sep3;

    @FXML
    private Separator sep1;

    @FXML
    private JFXButton delete1;

    AgRepo agrepo = Main.agRepo;

    int attLine;

    void fillCombobox() {
        agenttype.getItems().addAll(agrepo.getAgentTypes(""));
        attType1.getItems().addAll(agrepo.getAttributeTypes());
        agClass.getItems().addAll(getAgentClasses(""));
        attName1.getItems().addAll(agrepo.getAttributesNames());
    }

    ArrayList<String> getAgentClasses(String s){
        ArrayList<String> classes=funct.getClassFilesNamesFilter(s);
        return classes;
    }

    @FXML
    void addOneAttribute(String name,String type,String val){
        sep1.setMinHeight(sep1.getHeight()+70);
        sep2.setMinHeight(sep2.getHeight()+70);
        sep3.setMinHeight(sep3.getHeight()+70);
        attLine++;
        int range = attLine;
        JFXComboBox<String> cb1 = new JFXComboBox<String>();
        cb1.getItems().addAll(agrepo.getAttributesNames());
        cb1.setFocusColor(Color.rgb(22,215,212));
        cb1.setUnFocusColor(Color.rgb(46,161,161));
        cb1.setPromptText("Attribute Name");
        cb1.setEditable(true);
        cb1.setId("attName"+range);
        cb1.getSelectionModel().select(name);
        System.out.println("name "+name);
        cb1.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                Scene scene = vbox1.getScene();
                JFXComboBox atttype = (JFXComboBox) scene.lookup("#attType"+range);
                JFXTextField attvals = (JFXTextField) scene.lookup("#attVal"+range);
                atttype.getSelectionModel().select(agrepo.getTypeByAtt((String)newValue));
                attvals.setText(agrepo.getValsByAtt((String)newValue));
            }
        });
        vbox1.getChildren().add(cb1);

        JFXComboBox<String> cb2 = new JFXComboBox<String>();
        cb2.setFocusColor(Color.rgb(22,215,212));
        cb2.setUnFocusColor(Color.rgb(46,161,161));
        cb2.setId("attType"+range);
        cb2.setValue(type);
        cb2.getItems().addAll(agrepo.getAttributeTypes());
        cb2.setEditable(false);
        cb2.setPromptText("Type");
        cb2.valueProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                Scene scene = vbox1.getScene();
                JFXTextField attbt = (JFXTextField) scene.lookup("#attVal"+range);
                if(newValue.equals("table")){
                    attbt.setVisible(true);
                }else{
                    attbt.setVisible(false);
                }
            }
        });
        vbox2.getChildren().add(cb2);

        JFXTextField cb3 = new JFXTextField();
        cb3.setFocusColor(Color.rgb(22,215,212));
        cb3.setUnFocusColor(Color.rgb(46,161,161));
        cb3.setId("attVal"+range);
        cb3.setText(val);
        cb3.setVisible(type.equals("table"));
        cb3.setPromptText("val1 val2 val3 ...");
        vbox3.getChildren().add(cb3);

        JFXButton bt = new JFXButton();
        bt.setText("x");
        bt.setStyle("-fx-background-color:  #d43333;-fx-text-fill: white");
        bt.setId(String.valueOf(range));

        bt.setOnAction(event -> {
            Scene scene = vbox1.getScene();
            JFXComboBox attname = (JFXComboBox) scene.lookup("#attName"+range);
            JFXComboBox atttype = (JFXComboBox) scene.lookup("#attType"+range);
            JFXTextField attval = (JFXTextField) scene.lookup("#attVal"+range);
            JFXButton attbt = (JFXButton) scene.lookup("#"+range);
            String att = (String) attname.getValue();
            if(attname.getValue()!=null && agenttype.getValue()!=null){
                agrepo.disJoinAttAgent(att,String.valueOf(agenttype.getValue()));
            }
            vbox1.getChildren().remove(attname);
            vbox2.getChildren().remove(atttype);
            vbox3.getChildren().remove(attval);
            vbox4.getChildren().remove(attbt);
            sep1.setMinHeight(sep1.getHeight()-70);
            sep2.setMinHeight(sep2.getHeight()-70);
            sep3.setMinHeight(sep3.getHeight()-70);
        });
        vbox4.getChildren().add(bt);
    }

    @FXML
    void addAttribute(){
        addOneAttribute("","","");
    }

    void addAttribute(String agent){
        for (int i=0;i<attLine;i++){
            String name=String.valueOf(((ComboBox)vbox1.getChildren().get(i)).getValue());
            String type=String.valueOf(((ComboBox)vbox2.getChildren().get(i)).getValue());
            if(vbox3.getChildren().get(i).isVisible()){
                String v = ((JFXTextField)vbox3.getChildren().get(i)).getText();
                agrepo.insertAttributeWithOptions(name,type,v);
            }else {
                agrepo.insertAttribute(name,type);
            }
            agrepo.linkAttAgent(agent,name);
        }
    }

    @FXML
    void addAgent() {
        if(agenttype.getValue()==null || agClass.getValue()==null){
            Main.Alert(root,"Warning","fill All the fields !","");
        }else {
            String agent = String.valueOf(agenttype.getValue());
            String agClass = String.valueOf(this.agClass.getValue());
            agrepo.insertAgent(agent,agClass);
            addAttribute(agent);
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fillCombobox();
        attLine=1;
        vbox1.setSpacing(30);vbox3.setSpacing(30);
        vbox2.setSpacing(32);vbox4.setSpacing(30);

        attName1.valueProperty().addListener(new ChangeListener() {
            @Override
            public void  changed(ObservableValue observable, Object oldValue, Object newValue) {
                attName1.show();
                attType1.getSelectionModel().select(agrepo.getTypeByAtt((String)newValue));
                attVal1.setText(agrepo.getValsByAtt((String)newValue));
            }
        });

        attType1.valueProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                if(newValue.equals("table")){
                    vbox3.getChildren().get(0).setVisible(true);
                }else{
                    vbox3.getChildren().get(0).setVisible(false);
                }
            }
        });


        agenttype.setOnAction(event -> {

            agClass.getSelectionModel().select(agrepo.getAgClass((String) agenttype.getValue()));
            ArrayList<type> atts = agrepo.getAttributesByAgent((String) agenttype.getValue());

            vbox1.getChildren().clear();vbox3.getChildren().clear();
            vbox2.getChildren().clear();vbox4.getChildren().clear();
            if(atts!=null){
                for (type t:atts) {
                    addOneAttribute(t.name,t.type,t.val);
                }
            }

        });

        delete1.setOnAction(event -> {
            String att = (String) ((JFXComboBox)vbox1.getChildren().get(0)).getValue();
            if(((JFXComboBox)vbox1.getChildren().get(0)).getValue()!=null && agenttype.getValue()!=null){
                agrepo.disJoinAttAgent(att,String.valueOf(agenttype.getValue()));
            }
            vbox1.getChildren().remove(0);
            vbox2.getChildren().remove(0);
            vbox3.getChildren().remove(0);
            vbox4.getChildren().remove(0);
        });
    }
}
