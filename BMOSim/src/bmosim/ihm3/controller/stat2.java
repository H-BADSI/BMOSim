package bmosim.ihm3.controller;

import bmosim.ihm3.model.instance;
import bmosim.ihm3.model.simulation;
import bmosim.ihm3.model.vals;
import com.jfoenix.controls.JFXComboBox;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import org.controlsfx.control.CheckComboBox;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class stat2 implements Initializable{

    @FXML
    private JFXComboBox sim;

    @FXML
    private CheckComboBox inst;

    @FXML
    private JFXComboBox vars;

    @FXML
    private LineChart<?, ?> chart;

//    @FXML
//    private CategoryAxis x;
//
//    @FXML
//    private NumberAxis y;

    void fillCheckCombo() throws ParserConfigurationException, IOException, SAXException {
        if(new File("src/bmosim/ihm3/conf/conf.xml").exists()) {
            File file = new File("src/bmosim/ihm3/conf/conf.xml");
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);

            ArrayList<String> var= new ArrayList<>();
            if(doc.getElementsByTagName("variable")!=null){
                for (Node n: XmlUtil.asList(doc.getElementsByTagName("variable"))) {
                    var.add(n.getTextContent());
                }
                vars.getItems().clear();
                vars.getItems().addAll(var);
            }
        }


    }

    void getVals(ArrayList<vals> vs,String name,String inst){
        XYChart.Series series = new XYChart.Series();
        int i=0;
        for (vals v: vs){
            String is=String.valueOf(i);
            series.getData().add(new XYChart.Data(is,v.getValByName(name).getVal()));
            i+=10;
        }

        series.setName("Instance: "+inst);
        chart.getData().addAll(series);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            fillCheckCombo();

            ArrayList<String> sims = simulation.getSimulations("");
            sim.getItems().addAll(sims);

            sim.valueProperty().addListener(new ChangeListener<String>() {
                @Override public void changed(ObservableValue ov, String t, String t1) {
                    ArrayList<String> ins = instance.getInstance(t1);
                    inst.setDisable(false);
                    inst.getItems().clear();
                    inst.getItems().addAll(ins);

                }
            });

            inst.getCheckModel().getCheckedItems().addListener(new ListChangeListener() {
                @Override
                public void onChanged(Change c) {
                    chart.getData().clear();
                    for (Object s:inst.getCheckModel().getCheckedItems()) {
                        getVals(vals.getValues(String.valueOf(s)),String.valueOf(vars.getValue()), (String) s);
                    }
                }
            });

            vars.valueProperty().addListener(new ChangeListener<String>() {
                @Override public void changed(ObservableValue ov, String t, String t1) {
                    vars.setDisable(false);
                    chart.getData().clear();
                    for (Object s:inst.getCheckModel().getCheckedItems()) {
                        getVals(vals.getValues(String.valueOf(s)),t1,(String) s);
                    }
                }
            });

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }



}
