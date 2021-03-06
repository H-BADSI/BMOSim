package bmosim.ihm3.controller;

import bmosim.ihm3.Main;
import bmosim.ihm3.Repository.FeedRepo.StatRepo;
import com.jfoenix.controls.JFXComboBox;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;


public class stat3 implements Initializable{

    @FXML
    private CheckComboBox vars;

    @FXML
    private JFXComboBox comp;

    @FXML
    private CheckComboBox sim;

    @FXML
    private BarChart chart;

//    SimulationRepo simrepo= new SimulationRepo();
//    FeedRepo feedrepo = new FeedRepo();
//    InstanceRepo instanceRepo = new InstanceRepo();
    StatRepo statRepo = Main.statRepo;


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

        ArrayList<String> cmp = new ArrayList(Arrays.asList("Mean","Min","Max"));
        comp.getItems().addAll(cmp);


    }

    void getVals(String sim){
        XYChart.Series series = new XYChart.Series();
        int i=0;
        String cmp = String.valueOf(comp.getValue());
        for (Object var:vars.getCheckModel().getCheckedItems()) {
            String is=String.valueOf(i);
            Double d=statRepo.getFeedsByVar( sim,cmp, String.valueOf(var));
            series.getData().add(new XYChart.Data(var,d));
            i+=10;
        }
        series.setName(sim);
        chart.getData().addAll(series);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            fillCheckCombo();

            ArrayList<String> sims = statRepo.getSimulationsByName("");
            sim.getItems().addAll(sims);

            sim.getCheckModel().getCheckedItems().addListener(new ListChangeListener() {
                @Override
                public void onChanged(Change c) {
                    chart.getData().clear();
                    for (Object s:sim.getCheckModel().getCheckedItems()) {
                        getVals(String.valueOf(s));
                    }

                }
            });

            vars.getCheckModel().getCheckedItems().addListener(new ListChangeListener() {
                @Override
                public void onChanged(Change c) {
                    chart.getData().clear();
                    for (Object s:sim.getCheckModel().getCheckedItems()) {
                        getVals(String.valueOf(s));
                    }
                }
            });

            comp.valueProperty().addListener(new ChangeListener<String>() {
                @Override public void changed(ObservableValue ov, String t, String t1) {
                    chart.getData().clear();
                    for (Object s:sim.getCheckModel().getCheckedItems()) {
                        getVals(String.valueOf(s));
                    }
                }
            });


        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }



}
