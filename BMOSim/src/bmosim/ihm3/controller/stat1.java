package bmosim.ihm3.controller;

import bmosim.ihm3.Repository.FeedRepo.FeedRepo;
import bmosim.ihm3.Repository.FeedRepo.InstanceRepo;
import bmosim.ihm3.Repository.FeedRepo.SimulationRepo;
import bmosim.ihm3.model.vals;
import com.jfoenix.controls.JFXComboBox;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.AnchorPane;
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
import java.util.ResourceBundle;


public class stat1 implements Initializable{

    @FXML
    public static AnchorPane an;

    @FXML
    private JFXComboBox sim;

    @FXML
    private CheckComboBox vars;

    @FXML
    private JFXComboBox inst;

    @FXML
    private LineChart<?, ?> chart;

//    @FXML
//    private CategoryAxis x;
//
//    @FXML
//    private NumberAxis y;

    SimulationRepo simrepo= new SimulationRepo();
    InstanceRepo instrepo = new InstanceRepo();
    FeedRepo feedrepo = new FeedRepo();

    void getVals(ArrayList<vals> vs,String name){
        XYChart.Series series = new XYChart.Series();
        int i=0;
        for (vals v: vs){
            String is=String.valueOf(i);
            series.getData().add(new XYChart.Data(is,v.getValByName(name).getVal()));
            i+=10;
        }
        series.setName(name);
        chart.getData().addAll(series);
    }

    void fillCheckCombo() throws ParserConfigurationException, IOException, SAXException {
        if(new File("src/bmosim/ihm3/conf/conf.xml").exists()) {
            File file = new File("src/bmosim/ihm3/conf/conf.xml");
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
//            Node root=doc.getElementsByTagName("root").item(0);

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {

            fillCheckCombo();

            ArrayList<String> sims = simrepo.getSimulationsByName("");
            sim.getItems().addAll(sims);

            sim.valueProperty().addListener(new ChangeListener<String>() {
                @Override public void changed(ObservableValue ov, String t, String t1) {
                    chart.getData().clear();
                    sim.show();
                    ArrayList<Integer> ins = instrepo.getInstance(t1);
                    inst.setDisable(false);
                    inst.getItems().clear();
                    inst.getItems().addAll(ins);

                }
            });

            inst.valueProperty().addListener(observable -> {
                inst.show();
                vars.setDisable(false);
                chart.getData().clear();
                for (Object s:vars.getCheckModel().getCheckedItems()) {
                    getVals(feedrepo.getFeeds(String.valueOf(sim.getValue()),String.valueOf(inst.getValue())),String.valueOf(s));
                }
            });


        vars.getCheckModel().getCheckedItems().addListener(new ListChangeListener() {
            @Override
            public void onChanged(Change c) {
                chart.getData().clear();
                for (Object s:vars.getCheckModel().getCheckedItems()) {
                    getVals(feedrepo.getFeeds(String.valueOf(sim.getValue()),String.valueOf(inst.getValue())), String.valueOf(s));
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
