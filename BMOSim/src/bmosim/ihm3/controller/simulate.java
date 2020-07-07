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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class simulate implements Initializable{


    @FXML
    StackPane root;

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
    private RotateTransition rt1,rt2,rt3,rt4,rt5;

    @FXML
    private JFXButton pause,stop,simulate;

    @FXML
    private ImageView err1;

    @FXML
    private ImageView err2;


    ArrayList<Integer> simSet = new ArrayList<>();

    public boolean running=false;

    void go(String path,ActionEvent event) throws IOException {
        Parent p = FXMLLoader.load(getClass().getResource(path));
        Scene scene = new Scene (p);
        Stage appStage = (Stage) ((JFXButton) event.getSource()).getScene().getWindow();
//        appStage.hide();
        String dark = getClass().getResource("../css/dark.css").toExternalForm();
        String light = getClass().getResource("../css/light.css").toExternalForm();
        if (bmosim.ihm3.Main.dark_light) {
            scene.getStylesheets().clear();
            scene.getStylesheets().add(dark);
        } else {
            scene.getStylesheets().clear();
            scene.getStylesheets().add(light);
        }
        appStage.setScene(scene);
        appStage.sizeToScene();
        appStage.show();
    }

    public void activate() {
        rt1= new RotateTransition(Duration.seconds(5),ph1);
        rt2= new RotateTransition(Duration.seconds(8),ph2);
        rt3= new RotateTransition(Duration.seconds(simSet.get(0)/21),ph3);
        rt4= new RotateTransition(Duration.seconds(5),ph4);
        RotateTransition r[] = {rt1,rt2,rt3,rt4};
        for (RotateTransition rr:r) {
            rr.setCycleCount(1);
            rr.setAutoReverse(false);
            rr.setFromAngle(0);
        }
        rt1.setToAngle(360*5);
        rt2.setToAngle(360*8);
        rt3.setToAngle(360*simSet.get(0)/50);
        rt4.setToAngle(360*5);
        funct.setImage(ph1,"src/bmosim/ihm3/images/fan.png");

        rt1.play();
        rt1.setOnFinished(new EventHandler<ActionEvent>() {
              @Override
              public void handle(ActionEvent event) {
                  funct.setImage(ph1,"src/bmosim/ihm3/images/success.png");
                  funct.setImage(ph2,"src/bmosim/ihm3/images/fan.png");
                  rt2.play();
              }
          }
        );
        rt2.setOnFinished(new EventHandler<ActionEvent>() {
              @Override
              public void handle(ActionEvent event) {
                  pause.setDisable(false);
                  stop.setDisable(false);
                  funct.setImage(ph2,"src/bmosim/ihm3/images/success.png");
                  a1.setVisible(true);
                  funct.setImage(ph3,"src/bmosim/ihm3/images/fan.png");
                  rt3.play();
              }
          }
        );
        rt3.setOnFinished(new EventHandler<ActionEvent>() {
              @Override
              public void handle(ActionEvent event) {
                  funct.setImage(ph3,"src/bmosim/ihm3/images/success.png");
                  a2.setVisible(true);
                  funct.setImage(ph4,"src/bmosim/ihm3/images/fan.png");
                  rt4.play();
              }
          }
        );
        rt4.setOnFinished(new EventHandler<ActionEvent>() {
              @Override
              public void handle(ActionEvent event) {
                  funct.setImage(ph4,"src/bmosim/ihm3/images/success.png");
                  a3.setVisible(true);
                  simulate.setDisable(false);
                  pause.setDisable(true);
                  stop.setDisable(true);
                  running=false;

              }
          }
        );
    }

    @FXML
    void pause(ActionEvent event) {
        simulate.setDisable(false);
        pause.setDisable(true);
        Main.simState="PAUSED";
        RotateTransition r[] = {rt1,rt2,rt3,rt4};
        for (RotateTransition rr:r) {
            if(rr.getStatus().toString()=="RUNNING"){
                rt5=rr;
                rt5.pause();
            }
        }
    }

    @FXML
    void stop(ActionEvent event) throws IOException {
        Main.simState="SHUTDOWN";
        go("../view/simulate.fxml",event);
    }

    @FXML
    void simulate(ActionEvent event) throws IOException {

//        Main.execute(Integer.valueOf(nbSim.getText()),simName.getText(), funct.getXmlDirectory()+"\\"+simFile.getValue(),simSet);


        File f = new File(funct.getXmlDirectory()+"\\"+simFile.getValue());
        f.exists();
        if(!f.exists()){
            bmosim.ihm3.Main.Alert(root,"Oups !","File doesn't exist","Try another file");
        }else if(simName.getText().isEmpty()){
            err1.setVisible(true);
        }else{

            if(!running){
                err1.setVisible(false);
                err2.setVisible(false);

                simulate.setDisable(true);
                pause.setDisable(true);
                stop.setDisable(true);

                a1.setVisible(false);
                a2.setVisible(false);
                a3.setVisible(false);
                funct.setImage(ph1,"src/bmosim/ihm3/images/stopwatch.png");
                funct.setImage(ph2,"src/bmosim/ihm3/images/stopwatch.png");
                funct.setImage(ph3,"src/bmosim/ihm3/images/stopwatch.png");
                funct.setImage(ph4,"src/bmosim/ihm3/images/stopwatch.png");
                simSet.addAll(funct.getSimSet());
                activate();
                running=true;
                Main.simState="RUNNING";

                try {
//                    Main.execute(1,"aasa","src/bmosim/model/MDK.xml",simSet);
                    Main.execute(Integer.valueOf(nbSim.getText()),simName.getText(), funct.getXmlDirectory()+"\\"+simFile.getValue(),simSet);
                } catch (Exception e) {
                    e.printStackTrace();
                    stop(event);
                }
            }else{
                simulate.setDisable(true);
                pause.setDisable(false);
                Main.simState="RUNNING";
                rt5.play();
            }
        }


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        simFile.getItems().addAll(funct.getXmlFilesNamesFilter(""));

        simFile.getEditor().textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                                String oldValue, String newValue) {
                    simFile.show();
                    simFile.getItems().setAll(funct.getXmlFilesNamesFilter(newValue));

            }
        });

        nbSim.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    nbSim.setText(newValue.replaceAll("[^\\d]", ""));
                }
                if (nbSim.getText().length() > 2) {
                    String s = nbSim.getText().substring(0, 2);
                    nbSim.setText(s);
                }
            }
        });

    }



}
