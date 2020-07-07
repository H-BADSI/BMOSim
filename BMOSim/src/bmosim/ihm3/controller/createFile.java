package bmosim.ihm3.controller;



import bmosim.ihm3.Main;
import bmosim.ihm3.Repository.AgentRepo.AgentRepo;
import bmosim.ihm3.Repository.AgentRepo.AttributeRepo;
import bmosim.ihm3.model.Agent;
import bmosim.ihm3.model.Attribute;
import bmosim.ihm3.model.type;
import com.jfoenix.controls.*;
import javafx.animation.FadeTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.AccessibleRole;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.apache.jena.base.Sys;
import org.controlsfx.control.CheckComboBox;
import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.accessibility.AccessibleSelection;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class createFile implements Initializable{

    @FXML
    private StackPane root;

    @FXML
    private AnchorPane anchor;
    @FXML
    private ComboBox agenttype;
    @FXML
    private ComboBox fileName;
    @FXML
    private JFXTextField agCat;
    @FXML
    private JFXTextField nbInst;
    @FXML
    private VBox box11;
    @FXML
    private VBox box12;
    @FXML
    private VBox box21;
    @FXML
    private VBox box22;

    @FXML
    private HBox hbox1;

    @FXML
    private HBox hbox2;

    @FXML
    private HBox hbox3  ;

    @FXML
    private Pane catPane;

    @FXML
    private Label err2;

    @FXML
    private Label err1;

    @FXML
    private Button add;

    double hboxw1,hboxw2;

    AttributeRepo attrepo = new AttributeRepo();

    ArrayList<type> at = new ArrayList<>();
    ArrayList<type> newAt = new ArrayList<>();

    Document document;
    Node agents;

    FadeTransition ft = new FadeTransition();
    FadeTransition ft1 = new FadeTransition();
    FadeTransition ft2 = new FadeTransition();

    void fillCombobox() {
        agenttype.getItems().addAll(new AgentRepo().getAgentTypes(""));
    }

    void getFile(String fn) throws ParserConfigurationException, IOException, SAXException {
        if(funct.getXmlFilesNamesFilter(fn).isEmpty()){

            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            document = documentBuilder.newDocument();

            Element MDK = document.createElement("MDK");
            agents = document.createElement("agents");
            MDK.appendChild(agents);
            document.appendChild(MDK);
        }else {
            File file = new File(funct.getXmlDirectory() +"/"+ fn);

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();

            document = db.parse(file);
            agents = document.getElementsByTagName("agents").item(0);
        }
    }

    public void fillVbox(type t,VBox vb1,VBox vb2){
        Label tabText = new Label(t.name);
        Pane p1 = new Pane();
        p1.getChildren().add(tabText);
        p1.setMaxHeight(25);
        p1.setMinHeight(25);
        Pane p2 = new Pane();
        p2.setMaxHeight(25);
        p2.setMinHeight(25);
        vb1.getChildren().add(p1);



        switch (t.type){
            case "boolean": {
                            JFXCheckBox cb = new JFXCheckBox(); cb.setId(t.name);
                            cb.setMaxHeight(25);cb.setMaxHeight(25);p2.getChildren().add(cb);
                            newAt.add(new type(cb.getId(),"false"));
                            cb.selectedProperty().addListener(new ChangeListener<Boolean>() {
                                  @Override
                                  public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                                      newAt.add(new type(cb.getId(),String.valueOf(newValue)));
                                  }
                              }
                            );
                            break;
            }
            case "scale":{
                            JFXSlider s = new JFXSlider(); s.setId(t.name);
                            s.setMaxHeight(25);s.setMaxHeight(25);
                            p2.getChildren().add(s);
                            s.valueProperty().addListener(new ChangeListener<Number>() {
                                @Override
                                public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                                    newAt.add(new type(s.getId(),String.valueOf(newValue)));
                                }
                            });
                            break;
            }
            case "table":{CheckComboBox ccb = new CheckComboBox();
                            ccb.setMaxHeight(25);ccb.setMaxHeight(25);
                            ArrayList<String> opt = attrepo.getAttributeValues(t.name);
                            ObservableList<String> options= FXCollections.observableArrayList(opt);
                            ccb.getItems().addAll(options);
                            ccb.getCheckModel().getCheckedItems().addListener(new ListChangeListener() {
                                @Override
                                public void onChanged(Change c) {
                                    String vals="";
                                    for (Object o:ccb.getCheckModel().getCheckedItems()) {
                                        vals+=" "+o;
                                    }
                                    newAt.add(new type(ccb.getId(),vals));
                                }
                            });
                             ccb.setId(t.name); p2.getChildren().add(ccb);
                             break;
            }
            case "text":{JFXTextField tf = new JFXTextField(); tf.setId(t.name);
                            tf.setMaxHeight(25);tf.setMaxHeight(25);
                            tf.setFocusColor(Color.rgb(22,215,212));
                            tf.setUnFocusColor(Color.rgb(46,161,161));
                            p2.getChildren().add(tf);
                            tf.textProperty().addListener(new ChangeListener<String>() {
                                @Override
                                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                                    newAt.add(new type(tf.getId(),newValue));
                                }
                            });
                            break;
            }
            case "number":{JFXTextField tf = new JFXTextField(); tf.setId(t.name);
                            tf.setMaxHeight(25);tf.setMaxHeight(25);
                            tf.setFocusColor(Color.rgb(22,215,212));
                            tf.setUnFocusColor(Color.rgb(46,161,161));
                            p2.getChildren().add(tf);
                            tf.textProperty().addListener(new ChangeListener<String>() {
                                @Override
                                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                                    newAt.add(new type(tf.getId(),newValue));
                                }
                            });
                            break;
            }
            default:break;
        }
        vb2.getChildren().add(p2);
    }

    public void addLabel(String s){
        JFXButton l = new JFXButton();
        l.setStyle("-fx-background-color: #83cca0");
        if(s==""){
            l.setText(agCat.getText()+" X");
            l.setId(agCat.getText());
        }else{
            l.setText(s+" X");
            l.setId(s);
        }

        hboxw1=0.0;
        hboxw2=0.0;
        for (javafx.scene.Node n:hbox1.getChildren()) {
            hboxw1+=(((JFXButton)n).getText().toCharArray().length*6);
        }
        for (javafx.scene.Node n:hbox2.getChildren()) {
            hboxw2+=(((JFXButton)n).getText().toCharArray().length*6);
        }

        if((hboxw1+l.getWidth())<390){
            hbox1.getChildren().add(l);
            l.setAccessibleText("1");
        }else if((hboxw2+l.getWidth())<800){
            hbox2.getChildren().add(l);
            l.setAccessibleText("2");
        }else{
            hbox3.getChildren().add(l);
            l.setAccessibleText("3");
        }

        l.setOnAction(event -> {
                Scene scene = anchor.getScene();
                JFXButton x = (JFXButton) scene.lookup("#"+((JFXButton)event.getSource()).getId());
                funct.deleteAgentCategory(String.valueOf(fileName.getValue()).replace(".xml",""),x.getId());
                if(x.getAccessibleText().equals("1")){
                    hbox1.getChildren().remove(x);
                }else if(x.getAccessibleText().equals("2")){
                    hbox2.getChildren().remove(x);
                }else{
                    hbox3.getChildren().remove(x);
                }

        });
    }

    public void fillAgCat(){
        ArrayList<String> agcats = funct.getAgCats(String.valueOf(fileName.getValue()).replace(".xml",""));
        for (String cat:agcats) {
            addLabel(cat);
        }
    }

    @FXML
    void Add(ActionEvent event) {
        if(agCat.getText().isEmpty() || nbInst.getText().isEmpty() || fileName.getValue()==null || agenttype.getValue()==null){
            Main.Alert(root,"Warning","fill All the fields !","");

        }else {
            addLabel("");
            String fn=String.valueOf(fileName.getValue());
            if(!funct.containsIgnoreCase(fn,".xml")) fn+=".xml";
            try {
                getFile(fn);
//                String agc=agCat.getText();
                Element agent = document.createElement("Agent");
                agent.setAttribute("name",agCat.getText());
                String path =funct.getClassDirectory().replace("\\",".");
                agent.setAttribute("class",path+"."+new AgentRepo().getAgClass(String.valueOf(agenttype.getValue())));
                agent.setAttribute("nbOfInstances",nbInst.getText());
                Element attributes = document.createElement("Attributes");

                for (type t:newAt) {
                    System.out.println(t.name+" "+t.type);
                    attributes.setAttribute(t.name,t.type);
                }

                agent.appendChild(attributes);
                agents.appendChild(agent);

                funct.writeXML(document,funct.getXmlDirectory()+"/"+fn);
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SAXException e) {
                e.printStackTrace();
            }
        }
    }

    void initFade(){
        ft.setDuration(Duration.seconds(2));
        ft.setFromValue(0);
        ft.setToValue(10);
        ft.setAutoReverse(true);
        ft.setNode(catPane);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initFade();

        fillCombobox();

        box11.setSpacing(20);box12.setSpacing(20);
            box21.setSpacing(20);box22.setSpacing(20);
            hbox1.setSpacing(10);hbox2.setSpacing(10);hbox3.setSpacing(10);
            agenttype.valueProperty().addListener(new ChangeListener<String>() {
                @Override public void changed(ObservableValue ov, String t, String t1) {

                    box11.getChildren().clear();box12.getChildren().clear();
                    box21.getChildren().clear();box22.getChildren().clear();

                    agenttype.show();

                    at= attrepo.getAttributesByAgent(t1);

                    for (type ty:at) {
                        if(at.indexOf(ty)%2==0){
                            fillVbox(ty,box11,box12);
                        }else{
                            fillVbox(ty,box21,box22);
                        }
                    }

                }
            });

            fileName.getItems().addAll(funct.getXmlFilesNamesFilter(""));
            fileName.getEditor().textProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observable,
                                    String oldValue, String newValue) {

                        fileName.show();
                        hbox1.getChildren().clear();hbox2.getChildren().clear();hbox3.getChildren().clear();

                        fillAgCat();
                        ft.play();
//                        fileName.getItems().setAll(funct.getXmlFilesNamesFilter(newValue));
                }
            });

        nbInst.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    nbInst.setText(newValue.replaceAll("[^\\d]", ""));
                }
                if (nbInst.getText().length() > 3) {
                    String s = nbInst.getText().substring(0, 3);
                    nbInst.setText(s);
                }
            }
        });
    }

}
