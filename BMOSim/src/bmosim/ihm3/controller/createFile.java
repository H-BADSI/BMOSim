package bmosim.ihm3.controller;

import bmosim.ihm3.Main;
import bmosim.ihm3.Repository.AgentRepo.AgRepo;
import bmosim.ihm3.model.type;
import com.jfoenix.controls.*;
import javafx.animation.FadeTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import org.controlsfx.control.CheckComboBox;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
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
    private JFXCheckBox gui;
    @FXML
    private JFXComboBox loglevel;
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
    private Pane catPane;

    @FXML
    private Button add;

    @FXML
    private JFXButton update;

    @FXML
    private JFXButton delete;

    @FXML
    private JFXButton clear;

    int i=1;

    String cat;

     AgRepo agRepo = Main.agRepo;

    ArrayList<type> at = new ArrayList<>();
    ArrayList<type> newAt = new ArrayList<>();

    Document document;
    Node agents;

    FadeTransition ft = new FadeTransition();

    void initFade(){
        ft.setDuration(Duration.seconds(2));
        ft.setFromValue(0);
        ft.setToValue(10);
        ft.setAutoReverse(true);
        ft.setNode(catPane);
    }

    void fillCombobox() {
        fileName.getItems().addAll(funct.getXmlFilesNamesFilter(""));
        agenttype.getItems().addAll(agRepo.getAgentTypes(""));
        loglevel.getItems().addAll(new ArrayList<String>(Arrays.asList(
                "OFF","FINE","SEVERE","FINEST","ALL","WARNING","CONFIG","FINER","INFO")));
    }

    public void fillAgCat(){
        ArrayList<String> agcats = funct.getAgCats(String.valueOf(fileName.getValue()).replace(".xml",""));
        for (String cat:agcats) {
            addLabel(cat);
        }
    }

    void getFile(String fn) throws ParserConfigurationException, IOException, SAXException {
        if(funct.getXmlFilesNamesFilter(fn).isEmpty()){

            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            document = documentBuilder.newDocument();

            Element MDK = document.createElement("MDK");
            agents = document.createElement("Agents");
            MDK.appendChild(agents);
            document.appendChild(MDK);
        }else {
            File file = new File(funct.getXmlDirectory() +"/"+ fn);

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();

            document = db.parse(file);
            agents = document.getElementsByTagName("Agents").item(0);
        }
    }

    public void fillVbox(type t,VBox vb1,VBox vb2,boolean withval){
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
                            if(withval){
                                if(t.getVal().equalsIgnoreCase("true")){
                                    cb.setSelected(true);
                                    newAt.add(new type(cb.getId(),t.getVal()));
                                }
                            }

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
                            if(withval){
                                double d = Double.valueOf(t.getVal());
                                s.setValue(d);
                                newAt.add(new type(s.getId(),t.getVal()));
                            }
                            s.valueProperty().addListener(new ChangeListener<Number>() {
                                @Override
                                public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                                    newAt.add(new type(s.getId(),String.valueOf(newValue)));
                                }
                            });
                            break;
            }
            case "text":{JFXTextField tf = new JFXTextField(); tf.setId(t.name);
                            tf.setMaxHeight(25);tf.setMaxHeight(25);
                            tf.setFocusColor(Color.rgb(22,215,212));
                            tf.setUnFocusColor(Color.rgb(46,161,161));
                            p2.getChildren().add(tf);
                            if(withval){
                                tf.setText(t.getVal());
                                newAt.add(new type(tf.getId(),t.getVal()));
                            }
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
                            if(withval){
                                tf.setText(t.getVal());
                                newAt.add(new type(tf.getId(),t.getVal()));
                            }
                            tf.textProperty().addListener(new ChangeListener<String>() {
                                @Override
                                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                                    newAt.add(new type(tf.getId(),newValue));
                                }
                            });
                            break;
            }
            case "table":{CheckComboBox ccb = new CheckComboBox();
                ccb.setId(t.name);
                ccb.setMaxHeight(25);ccb.setMaxHeight(25);
                ArrayList<String> opt = agRepo.getAttributeValues(t.name);
                ObservableList<String> options= FXCollections.observableArrayList(opt);
                ccb.getItems().addAll(options);
                if(withval){
                    String str[] = t.getVal().replaceAll("\"","").split(" ");
                    ArrayList<String> vals = new ArrayList<>(Arrays.asList(str));
                    for (String val:vals) {
                        ccb.getCheckModel().check(Integer.valueOf(val)-1);
                    }
                    newAt.add(new type(ccb.getId(),t.getVal()));
                }
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
            default:break;
        }
        vb2.getChildren().add(p2);
    }

    public void addLabel(String s){
        JFXButton l = new JFXButton();
        l.setStyle("-fx-background-color: #83cca0");
        l.setText(s);
        l.setMinWidth(Region.USE_PREF_SIZE);
        l.setId(s);

        hbox1.getChildren().add(l);

        l.setOnAction(event -> {
            newAt.clear();
            delete.setVisible(true);
            update.setVisible(true);
            add.setVisible(false);
            cat=l.getId();

            agXmlStruct ag=funct.getAgentByCategory(String.valueOf(fileName.getValue()),l.getId());

            int indx1 = ag.classe.lastIndexOf(".");
            int indx2 = ag.classe.length();
            String classe = ag.classe.substring((indx1+1),indx2);
            agenttype.getSelectionModel().select(classe);
            agCat.setText(ag.name);
            nbInst.setText(ag.nbInst);
            loglevel.getSelectionModel().select(ag.logLevel);
            if(ag.gui.equalsIgnoreCase("true"))gui.setSelected(true);
            ArrayList<type> atts=new ArrayList<>();
            for (atXmlStruct at:ag.getAttributes()) {
                atts.add(new type(at.name,agRepo.getTypeByAtt(at.name),at.value));
            }
            box11.getChildren().clear();box12.getChildren().clear();
            box21.getChildren().clear();box22.getChildren().clear();

            for (type ty:atts) {
                if(atts.indexOf(ty)%2==0){
                    fillVbox(ty,box11,box12,true);
                }else{
                    fillVbox(ty,box21,box22,true);
                }
            }
        });
    }

    @FXML
    void Add(ActionEvent event) {

        if(agCat.getText().isEmpty() || nbInst.getText().isEmpty() || fileName.getValue()==null || agenttype.getValue()==null){
            Main.Alert(root,"Warning","fill All the fields !","");

        }else if(funct.categoryExists(agCat.getText(),String.valueOf(fileName.getValue()).replace(".xml",""))){
            Main.Alert(root,"Warning","this category already exists","try another one");
        }else {
            addLabel(agCat.getText());
            String fn=String.valueOf(fileName.getValue());
            if(!funct.containsIgnoreCase(fn,".xml")) fn+=".xml";
            try {
                getFile(fn);
//                String agc=agCat.getText();
                Element agent = document.createElement("Agent");
                agent.setAttribute("GUI",gui.isSelected()?"true":"false");
                String path =funct.getClassDirectory().replace("\\",".");
                agent.setAttribute("class",path+"."+agRepo.getAgClass(String.valueOf(agenttype.getValue())));
                agent.setAttribute("logLevel",String.valueOf(loglevel.getValue()));
                agent.setAttribute("name",agCat.getText());
                agent.setAttribute("nbOfInstances",nbInst.getText());

                Element attributes = document.createElement("Attributes");

                for (type t:newAt) {
                    attributes.setAttribute(t.name,t.val);
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
        agCat.setText("agentCat"+(i++));
    }

    @FXML
    void clear(ActionEvent event) {
        box11.getChildren().clear();box12.getChildren().clear();
        box21.getChildren().clear();box22.getChildren().clear();
        update.setVisible(false);
        delete.setVisible(false);
        add.setVisible(true);
    }

    @FXML
    void delete(ActionEvent event) {
        Scene scene = anchor.getScene();
        JFXButton x = (JFXButton) scene.lookup("#"+cat);
        funct.deleteAgentByCategory(String.valueOf(fileName.getValue()).replace(".xml",""),cat);
        hbox1.getChildren().remove(x);

    }

    @FXML
    void update(ActionEvent event) {
        delete(event);
        Add(event);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initFade();

        fillCombobox();

        agCat.setText("agentCat"+(i++));
        loglevel.getSelectionModel().select(0);

        box11.setSpacing(20);box12.setSpacing(20);
        box21.setSpacing(20);box22.setSpacing(20);
//            hbox1.setSpacing(10);hbox2.setSpacing(10);hbox3.setSpacing(10);
        hbox1.setSpacing(10);
        agenttype.valueProperty().addListener(new ChangeListener<String>() {
            @Override public void changed(ObservableValue ov, String t, String t1) {
                box11.getChildren().clear();box12.getChildren().clear();
                box21.getChildren().clear();box22.getChildren().clear();

                at= agRepo.getAttributesByAgent(t1);

                for (type ty:at) {
                    if(at.indexOf(ty)%2==0){
                        fillVbox(ty,box11,box12,false);
                    }else{
                        fillVbox(ty,box21,box22,false);
                    }
                }

            }
        });


        fileName.getEditor().textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                                String oldValue, String newValue) {

                    fileName.show();
                    hbox1.getChildren().clear();
                    update.setVisible(false);
                    delete.setVisible(false);
                    add.setVisible(true);

                    fillAgCat();
                    ft.play();
                    fileName.getItems().setAll(funct.getXmlFilesNamesFilter(newValue));
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
