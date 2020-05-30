package bmosim.ihm3.controller;

import bmosim.control.Main;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.RotateTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
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
import java.util.Arrays;
import java.util.ResourceBundle;


public class simulate implements Initializable{

    @FXML
    JFXTextField nbSim;

    @FXML
    JFXTextField simName;

    @FXML
    JFXComboBox simFile;

    @FXML
    private ImageView ph1,ph2,ph3,ph4;

    @FXML
    private Label a1,a2,a3;

    @FXML
    private RotateTransition rt1,rt2,rt3,rt4;

    @FXML
    private JFXButton stop,simulate;

    @FXML
    public  JFXTextField tf1,tf2,tf3,tf4;

    @FXML
    public static String t1,t2,t3,t4;

    ArrayList<Integer> simSet = new ArrayList<>();


    public void stop(javafx.scene.input.MouseEvent mouseEvent) {
        System.out.println("+++++++++++++++");
        System.out.println("+++++++++++++++");
        System.out.println("+++++++++++++++");
        System.out.println("+++++++++++++++");
        System.out.println("+++++++++++++++");
//        Main.stop();
    }
    public void pause(javafx.scene.input.MouseEvent mouseEvent) {
        System.out.println("+++++++++++++++");
        System.out.println("+++++++++++++++");
        System.out.println("+++++++++++++++");
        System.out.println("+++++++++++++++");
        System.out.println("+++++++++++++++");
//        Main.stop();
    }

    void setImage(ImageView im,String path){
        File file = new File(path);
        im.setImage(new Image(file.toURI().toString()));
    }



    public void activate() {
        rt1= new RotateTransition(Duration.seconds(5),ph1);
        rt2= new RotateTransition(Duration.seconds(5),ph2);
        rt3= new RotateTransition(Duration.seconds(simSet.get(0)/50),ph3);
        rt4= new RotateTransition(Duration.seconds(5),ph4);
        RotateTransition r[] = {rt1,rt2,rt3,rt4};
        for (RotateTransition rr:r) {
            rr.setCycleCount(1);
            rr.setAutoReverse(false);
            rr.setFromAngle(0);
        }
        rt1.setToAngle(1080);
        rt2.setToAngle(1080);
        rt3.setToAngle(1080);
        rt4.setToAngle(1080);
        simulate.setDisable(true);
        setImage(ph1,"C:\\Users\\BRAHIM\\Downloads\\3juil2019\\demo\\BMOSim\\BMOSim\\src\\bmosim\\ihm3\\images\\fan.png");

        rt1.play();
        rt1.setOnFinished(new EventHandler<ActionEvent>() {
                              @Override
                              public void handle(ActionEvent event) {
                                  setImage(ph1,"C:\\Users\\BRAHIM\\Downloads\\3juil2019\\demo\\BMOSim\\BMOSim\\src\\bmosim\\ihm3\\images\\success.png");

                                  setImage(ph2,"C:\\Users\\BRAHIM\\Downloads\\3juil2019\\demo\\BMOSim\\BMOSim\\src\\bmosim\\ihm3\\images\\fan.png");
                                  rt2.play();
                              }
                          }

        );
        rt2.setOnFinished(new EventHandler<ActionEvent>() {
                              @Override
                              public void handle(ActionEvent event) {

                                  setImage(ph2,"C:\\Users\\BRAHIM\\Downloads\\3juil2019\\demo\\BMOSim\\BMOSim\\src\\bmosim\\ihm3\\images\\success.png");

                                  a1.setVisible(true);
                                  setImage(ph3,"C:\\Users\\BRAHIM\\Downloads\\3juil2019\\demo\\BMOSim\\BMOSim\\src\\bmosim\\ihm3\\images\\fan.png");

                                  ph3.setStyle("-fx-fill: #3b0843");
                                  rt3.play();
                              }
                          }

        );
        rt3.setOnFinished(new EventHandler<ActionEvent>() {
                              @Override
                              public void handle(ActionEvent event) {

                                  setImage(ph3,"C:\\Users\\BRAHIM\\Downloads\\3juil2019\\demo\\BMOSim\\BMOSim\\src\\bmosim\\ihm3\\images\\success.png");

                                  a2.setVisible(true);
                                  setImage(ph4,"C:\\Users\\BRAHIM\\Downloads\\3juil2019\\demo\\BMOSim\\BMOSim\\src\\bmosim\\ihm3\\images\\fan.png");

                                  rt4.play();
                              }
                          }

        );
        rt4.setOnFinished(new EventHandler<ActionEvent>() {
                              @Override
                              public void handle(ActionEvent event) {

                                  setImage(ph4,"C:\\Users\\BRAHIM\\Downloads\\3juil2019\\demo\\BMOSim\\BMOSim\\src\\bmosim\\ihm3\\images\\success.png");

                                  simulate.setDisable(false);
                                  a3.setVisible(true);
                              }
                          }

        );
    }

    @FXML
    void simulate() throws SQLException, ClassNotFoundException, IOException, SAXException, ParserConfigurationException, InterruptedException {
        a1.setVisible(false);
        a2.setVisible(false);
        a3.setVisible(false);
        setImage(ph1,"C:\\Users\\BRAHIM\\Downloads\\3juil2019\\demo\\BMOSim\\BMOSim\\src\\bmosim\\ihm3\\images\\timer.png");
        setImage(ph2,"C:\\Users\\BRAHIM\\Downloads\\3juil2019\\demo\\BMOSim\\BMOSim\\src\\bmosim\\ihm3\\images\\timer.png");
        setImage(ph3,"C:\\Users\\BRAHIM\\Downloads\\3juil2019\\demo\\BMOSim\\BMOSim\\src\\bmosim\\ihm3\\images\\timer.png");
        setImage(ph4,"C:\\Users\\BRAHIM\\Downloads\\3juil2019\\demo\\BMOSim\\BMOSim\\src\\bmosim\\ihm3\\images\\timer.png");
//
        simSet.addAll(funct.getSimSet());
        activate();
//        Main.execute(Integer.valueOf(nbSim.getText()),simName.getText(), funct.getXmlDirectory()+"\\"+simFile.getValue(),simSet);
//        ArrayList<Integer> a = new ArrayList<>(Arrays.asList(500,50,50));
//        Main.execute(1,"","C:\\Users\\BRAHIM\\Downloads\\3juil2019\\demo\\BMOSim\\BMOSim\\src\\bmosim\\model\\MDK.xml"
//                ,a);
    }

    String getXmlDirectory() throws ParserConfigurationException, IOException, SAXException {
        File file = new File("src/bmosim/ihm3/conf/conf.xml");

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();

        Document doc = db.parse(file);
        Node xmldest = doc.getElementsByTagName("xmlDest").item(0);
        return xmldest.getTextContent();
    }

    public boolean containsIgnoreCase(String str, String subString) {
        return str.toLowerCase().contains(subString.toLowerCase());
    }


    ArrayList<String> getXmlFiles(String s) throws IOException, SAXException, ParserConfigurationException {
        ArrayList<String> as = new ArrayList<>();

        File folder = new File(getXmlDirectory());
        File[] listOfFiles = folder.listFiles();

        if(s==null){
            for (int i = 0; i <listOfFiles.length; i++) {
                if(containsIgnoreCase(listOfFiles[i].getName(),".xml"))
                    as.add(listOfFiles[i].getName());
            }
        }else{
            for (int i = 0; i <listOfFiles.length; i++) {
                if(containsIgnoreCase(listOfFiles[i].getName(),s) && containsIgnoreCase(listOfFiles[i].getName(),".xml")){
                    as.add(listOfFiles[i].getName());
                }
            }
        }
        return as;
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {


        try {
            simFile.getItems().addAll(getXmlFiles(""));

            simFile.getEditor().textProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observable,
                                    String oldValue, String newValue) {
                    try {
                        simFile.getItems().setAll(getXmlFiles(newValue));
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (SAXException e) {
                        e.printStackTrace();
                    } catch (ParserConfigurationException e) {
                        e.printStackTrace();
                    }
                }
            });
//            tf1.textProperty().addListener(new ChangeListener<String>() {
//                @Override
//                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
//                    rt1.stop();
//                }
//            });
//            tf2.textProperty().addListener(new ChangeListener<String>() {
//                @Override
//                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
//                    rt2.stop();
//                }
//            });
//            tf3.textProperty().addListener(new ChangeListener<String>() {
//                @Override
//                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
//                    rt3.stop();
//                }
//            });
//            tf4.textProperty().addListener(new ChangeListener<String>() {
//                @Override
//                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
//
//                }
//            });
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }


    }



}
