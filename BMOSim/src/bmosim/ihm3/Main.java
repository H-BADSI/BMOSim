package bmosim.ihm3;

import bmosim.ihm3.Hibernate.hibernateAccount.DBUser;
import bmosim.ihm3.Repository.AccountRepo.UserRepo;
import bmosim.ihm3.Repository.AgentRepo.AgRepo;
import bmosim.ihm3.Repository.FeedRepo.StatRepo;
import bmosim.ihm3.controller.DBconfStruct;
import bmosim.ihm3.controller.funct;
import com.jfoenix.controls.*;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    public static boolean dark_light;

    public Scene scene;

    public static String path="";

    private double xOffset = 0;
    private double yOffset = 0;

    public static double x=170;
    public static double y=70;

    public static StatRepo statRepo = new StatRepo();
    public static AgRepo agRepo =  new AgRepo();
    public static UserRepo userRepo = new UserRepo();

    public static DBUser loginUser=new DBUser("admin","admin","admin");

    @Override
    public void start(Stage primaryStage) throws Exception{

        primaryStage.initStyle(StageStyle.TRANSPARENT);
        dark_light= funct.getDarkLight();

        Parent root;
        if(funct.firstTime()){
            root= FXMLLoader.load(getClass().getResource("view/install1.fxml"));
            path="login";
        }else{
            if(funct.getAppType()){
                root= FXMLLoader.load(getClass().getResource("view/login.fxml"));
                path="login";
            }else {
                root= FXMLLoader.load(getClass().getResource("view/home.fxml"));
                path="home";
            }
        }

        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                x=event.getScreenX() - xOffset;
                y=event.getScreenY() - yOffset;
                primaryStage.setX(x);
                primaryStage.setY(y);
            }
        });


        scene =new Scene(root);

        String dark = getClass().getResource("../ihm3/css/dark.css").toExternalForm();
        String light = getClass().getResource("../ihm3/css/light.css").toExternalForm();
        if(dark_light){
            scene.getStylesheets().clear();
            scene.getStylesheets().add(dark);
        }else{
            scene.getStylesheets().clear();
            scene.getStylesheets().add(light);
        }

        primaryStage.setX(570);
        primaryStage.setY(130);


        primaryStage.setScene(scene);
        primaryStage.show();


    }

    public static void Alert(StackPane root, String s1, String s2, String s3){
        JFXButton confirm = new JFXButton("Got it");
        confirm.setStyle("-fx-background-color: linear-gradient(from 300px 70px to 280px 500px,#43cea2, #185a9d) ");
        JFXDialogLayout dialogLayout = new JFXDialogLayout();
        dialogLayout.setHeading(new Text(s1));
        dialogLayout.setBody(new Text(s2+". "+s3));

        JFXDialog dialog = new JFXDialog(root,dialogLayout,JFXDialog.DialogTransition.TOP);

        dialog.setStyle("-fx-background-color:  linear-gradient(from 300px 70px to 280px 500px,#43cea2, #185a9d)");

        confirm.setOnAction(event -> {
            dialog.close();
        });
        dialogLayout.setActions(confirm);
        dialog.show();
    }
    public static void main(String[] args){
        launch(args);
    }
}
