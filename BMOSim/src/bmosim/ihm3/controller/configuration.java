package bmosim.ihm3.controller;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.sun.xml.xsom.impl.scd.Iterators;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.apache.jena.base.Sys;
import org.controlsfx.control.CheckComboBox;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import sun.plugin.javascript.navig.Array;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import static java.util.Arrays.asList;

public class configuration implements Initializable{

    @FXML
    private JFXComboBox pack1;
    @FXML
    private JFXComboBox pack2;
    @FXML
    private JFXTextField xmlDest;
    @FXML
    private JFXButton update;
    @FXML
    private Label label;
    @FXML
    private JFXTextField simVars;
    @FXML
    private CheckComboBox dvar;
    @FXML
    private JFXButton add;

    private ArrayList<String> p;

    private ArrayList<String> vars= new ArrayList<>();

    @FXML
    private JFXTextField duration;

    @FXML
    private JFXTextField stepdelay;

    @FXML
    private JFXTextField paycycle;

    @FXML
    private JFXButton updateSet;

    Document doc;
    Node root;


    @FXML
    void updateSet() throws TransformerException {
        Node SimulationSet;
        if(doc.getElementsByTagName("SimulationSet").item(0)==null){
            System.out.println("NULL");
            SimulationSet = doc.createElement("SimulationSet");
        }else {
            System.out.println("not NULL");
            SimulationSet=doc.getElementsByTagName("SimulationSet").item(0);
        }
        Element Duration = doc.createElement("Duration");
        Duration.setTextContent(String.valueOf(duration.getText()));
        Element PayCycle = doc.createElement("PayCycle");
        PayCycle.setTextContent(String.valueOf(paycycle.getText()));
        Element StepDelay = doc.createElement("StepDelay");
        StepDelay.setTextContent(String.valueOf(stepdelay.getText()));
        SimulationSet.appendChild(Duration);
        SimulationSet.appendChild(PayCycle);
        SimulationSet.appendChild(StepDelay);

        root.appendChild(SimulationSet);

        funct.writeXML(doc,"src/bmosim/ihm3/conf/conf.xml");
        duration.clear();
        paycycle.clear();
        stepdelay.clear();

    }

    @FXML
    void addVariable(ActionEvent event) throws TransformerException {
        vars.add(String.valueOf(simVars.getText()));
        Node variables;
        if(doc.getElementsByTagName("variables").item(0)==null){
            System.out.println("NULL");
            variables = doc.createElement("variables");
        }else {
            System.out.println("not NULL");
            variables=doc.getElementsByTagName("variables").item(0);
        }
        Element variable = doc.createElement("variable");
        variable.setTextContent(String.valueOf(simVars.getText()));
        variables.appendChild(variable);

        root.appendChild(variables);

        funct.writeXML(doc,"src/bmosim/ihm3/conf/conf.xml");
        simVars.clear();
        getVariables();
        add.setDisable(true);
    }
    @FXML
    void delete(ActionEvent event) throws TransformerException {
        ArrayList<String> as = new ArrayList<>();
        for (Object o:dvar.getCheckModel().getCheckedItems()) {
            as.add((String) o);
        }
        List<Node> v = XmlUtil.asList(doc.getElementsByTagName("variable"));
        for (int i=0;i<v.size();i++){
            if(as.contains(v.get(i).getTextContent())){
                Element element = (Element) doc.getElementsByTagName("variable").item(i);
                element.getParentNode().removeChild(element);
            }
        }
        funct.writeXML(doc,"src/bmosim/ihm3/conf/conf.xml");
        getVariables();
    }


    void createDoc() throws ParserConfigurationException, IOException, SAXException {
        if(!new File("src/bmosim/ihm3/conf/conf.xml").exists()){
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            doc = documentBuilder.newDocument();
            root = doc.createElement("root");
            doc.appendChild(root);

        }else {
            File file = new File("src/bmosim/ihm3/conf/conf.xml");
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            doc = db.parse(file);
            root=doc.getElementsByTagName("root").item(0);
        }
    }

    @FXML
    void update() throws TransformerException {
        root.removeChild(doc.getElementsByTagName("sroot").item(0));
        Node p1=doc.createElement("package1");
        p1.setTextContent(String.valueOf(pack1.getValue()));
        Element p2=doc.createElement("package2");
        p2.setTextContent(String.valueOf(pack2.getValue()));
        Element xmlDest=doc.createElement("xmlDest");
        xmlDest.setTextContent(this.xmlDest.getText());
        Element sroot=doc.createElement("sroot");
        sroot.appendChild(p1);
        sroot.appendChild(p2);
        sroot.appendChild(xmlDest);
        root.appendChild(sroot);

        funct.writeXML(doc,"src/bmosim/ihm3/conf/conf.xml");
        getVariables();

    }

    void getPack1(){
        pack1.getItems().addAll(funct.getFilesNames("src"));
    }

    void getPack2(){
        pack2.getItems().addAll(funct.getFilesNames("src/"+pack1.getValue()));
    }

    void getVariables(){
        ArrayList<String> vars= new ArrayList<>();
        if(doc.getElementsByTagName("variable")!=null){
            for (Node n: XmlUtil.asList(doc.getElementsByTagName("variable"))) {
                vars.add(n.getTextContent());
            }
            dvar.getItems().clear();
            dvar.getItems().addAll(vars);
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        getPack1();
        pack1.valueProperty().addListener(new ChangeListener<String>() {
            @Override public void changed(ObservableValue ov, String t, String t1) {
                pack2.setDisable(false);
                getPack2();
            }
        });
        xmlDest.textProperty().addListener(new ChangeListener<String>() {
            @Override public void changed(ObservableValue ov, String t, String t1) {
                File f = new File(t1);
                if(f.exists()){
                    label.setText("directory exists");
                    label.setTextFill(Color.GREEN);
                    update.setDisable(false);
                }else {
                    label.setText("directory does not exist");
                    label.setTextFill(Color.RED);
                    update.setDisable(true);
                }

            }
        });
        simVars.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                add.setDisable(false);
            }
        });

        try {
            createDoc();
            getVariables();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }

}
