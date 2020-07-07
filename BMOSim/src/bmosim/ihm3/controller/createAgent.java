package bmosim.ihm3.controller;

import bmosim.ihm3.Main;
import bmosim.ihm3.Repository.AgentRepo.AgentRepo;
import bmosim.ihm3.Repository.AgentRepo.AttributeRepo;
import bmosim.ihm3.Repository.AgentRepo.TypeRepo;
import bmosim.ihm3.model.Agent;
import bmosim.ihm3.model.Attribute;
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
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import bmosim.ihm3.model.type;

public class createAgent implements Initializable{


    @FXML
    private StackPane root;
    @FXML
    private ComboBox agenttype;
    @FXML
    private JFXComboBox<?> attName1;
    @FXML
    private ComboBox attType1;
    @FXML
    private ComboBox agClass;
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
    private JFXButton delete1;

    AttributeRepo attrepo = new AttributeRepo();

    int attLine;

    void fillCombobox() throws SQLException {
        agenttype.getItems().addAll(new AgentRepo().getAgentTypes(""));
        attType1.getItems().addAll(new TypeRepo().getAttributeTypes());
    }

    ArrayList<String> getAgentClasses(String s){
        ArrayList<String> classes=funct.getClassFilesNamesFilter(s);
        return classes;
    }

    @FXML
    void addOneAttribute(String name,String type,String val) throws SQLException {
        attLine++;
        int range = attLine;
        JFXComboBox cb1 = new JFXComboBox();
        cb1.setFocusColor(Color.rgb(22,215,212));
        cb1.setUnFocusColor(Color.rgb(46,161,161));
        cb1.setId("attName"+range);
        cb1.setValue(name);
        cb1.setEditable(true);
        cb1.setPromptText("Attribute Name");
        vbox1.getChildren().add(cb1);

        JFXComboBox cb2 = new JFXComboBox();
        cb2.setFocusColor(Color.rgb(22,215,212));
        cb2.setUnFocusColor(Color.rgb(46,161,161));
        cb2.setId("attType"+range);
        cb2.setValue(type);
        cb2.getItems().addAll(new TypeRepo().getAttributeTypes());
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
            attrepo.disJoinAttAgent(att,String.valueOf(agenttype.getValue()));
            vbox1.getChildren().remove(attname);
            vbox2.getChildren().remove(atttype);
            vbox3.getChildren().remove(attval);
            vbox4.getChildren().remove(attbt);
        });
        vbox4.getChildren().add(bt);
    }

    @FXML
    void addAttribute() throws SQLException {
        addOneAttribute("","","");
    }

    void addAttribute(String agent){
        for (int i=0;i<attLine;i++){
            String name=String.valueOf(((ComboBox)vbox1.getChildren().get(i)).getValue());
            String type=String.valueOf(((ComboBox)vbox2.getChildren().get(i)).getValue());
            if(vbox3.getChildren().get(i).isVisible()){
                String v = ((JFXTextField)vbox3.getChildren().get(i)).getText();
                attrepo.insertAttributeWithOptions(name,type,v);
            }else {
                attrepo.insertAttribute(name,type);
            }
            attrepo.linkAttAgent(agent,name);
        }
    }

    @FXML
    void addAgent() throws SQLException {
        if(agenttype.getValue()==null || agClass.getValue()==null){
            Main.Alert(root,"Warning","fill All the fields !","");
        }else {
            String agent = String.valueOf(agenttype.getValue());
            String agClass = String.valueOf(this.agClass.getValue());
            new AgentRepo().insertAgent(agent,agClass);
            addAttribute(agent);
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            agClass.getItems().addAll(getAgentClasses(""));
            fillCombobox();
            attLine=1;
            vbox1.setSpacing(30);
            vbox2.setSpacing(32);
            vbox3.setSpacing(30);
            vbox4.setSpacing(30);

            attName1.valueProperty().addListener(new ChangeListener() {
                @Override
                public void  changed(ObservableValue observable, Object oldValue, Object newValue) {
                    attName1.show();
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
            agClass.valueProperty().addListener(new ChangeListener() {
                @Override
                public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                    agClass.show();
                    agClass.getItems().clear();
                    agClass.getItems().addAll(getAgentClasses((String) newValue));
                }
            });
            agenttype.valueProperty().addListener(new ChangeListener() {
                @Override
                public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                    try {
                        agenttype.show();
                        agenttype.getItems().clear();
                        agenttype.getItems().addAll(new AgentRepo().getAgentTypes((String) newValue));
                        agClass.setValue(new AgentRepo().getAgClass((String) newValue).toLowerCase());
                        ArrayList<type> atts = attrepo.getAttributesByAgent((String) newValue);
                        vbox1.getChildren().clear();
                        vbox2.getChildren().clear();
                        vbox3.getChildren().clear();
                        vbox4.getChildren().clear();
                        for (type t:atts) {
                            addOneAttribute(t.name,t.type,t.val);
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                }
            });

        } catch (SQLException e) {
            e.printStackTrace();
        }

        delete1.setOnAction(event -> {
            String att = (String) ((JFXComboBox)vbox1.getChildren().get(0)).getValue();
            attrepo.disJoinAttAgent(att,String.valueOf(agenttype.getValue()));
            vbox1.getChildren().remove(0);
            vbox2.getChildren().remove(0);
            vbox3.getChildren().remove(0);
            vbox4.getChildren().remove(0);
        });




    }



}
