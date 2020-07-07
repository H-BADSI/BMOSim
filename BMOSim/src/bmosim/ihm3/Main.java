package bmosim.ihm3;

import bmosim.ihm3.controller.Enceyption;
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

import java.util.ArrayList;

public class Main extends Application {

    public static boolean dark_light;
    public static String username;

    public Scene scene;

//    public static Connection conAgent;
//    public static Connection conStat;
//    public static Connection conAccount;

    private double xOffset = 0;
    private double yOffset = 0;

    public static double x=170;
    public static double y=70;

    public static Controller controller ;

    @Override
    public void start(Stage primaryStage) throws Exception{

        primaryStage.initStyle(StageStyle.TRANSPARENT);
        dark_light= funct.getDarkLight();

        ArrayList<String> dbconf = funct.getDBSet();
        Enceyption e = new Enceyption();
        String p1=e.decrypt(dbconf.get(1));
        String p2=e.decrypt(dbconf.get(6));
        System.out.println(p1);
        System.out.println(p2);
//        conAgent=bdd.connect(dbconf.get(2),dbconf.get(3),dbconf.get(0),dbconf.get(4),p1);
//        conStat=bdd.connect(dbconf.get(7),dbconf.get(8),dbconf.get(5),dbconf.get(9),p2);
//        conAccount=bdd.connect(dbconf.get(7),dbconf.get(8),"acc",dbconf.get(9),p2);
//        conAgent=bdd.connect("localhost","3306","agentdb","root","emplacement44");
//        conStat=bdd.connect("localhost","3306","stat","root","emplacement44");
//        conAccount=bdd.connect("localhost","3306","acc","root","emplacement44");

        Parent root = FXMLLoader.load(getClass().getResource("view/login.fxml"));

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
    public static void main(String[] args) {
        launch(args);
//        UserRepo user = new UserRepo("KHALED","KHALED");
//        System.out.println(user.isAdmin());
//        new AttributeRepo().disJoinAttAgent("versality","ANA");

//        System.out.println(new AttributeRepo().getAttributesByAgent("COM").toString());


//        System.out.println(new AgentRepo().getAgClass("Brahim"));
//        System.out.println(new InstanceRepo().getIdInstByIdSimAndOrd(2,1));
//            new SimulationRepo().insertSim("braaaaaa");
//        SessionFactory sessionFactory = new Configuration().configure("bmosim/ihm3/hibernateFeed/hiberFeed.cfg.xml").buildSessionFactory();
//
//        Session session = sessionFactory.openSession();

//        ArrayList<Integer> dbs = new InstanceRepo().getIdInstBySim("sim1");
//        for (Integer s:dbs
//             ) {
//            System.out.println("**** "+s+" "+s);
//        }
//        ArrayList<Double> i= new FeedRepo().getFeed(3);
//        for (Double s:i
//             ) {
//            System.out.println("**** "+s+" "+s);
//        }
//        System.out.println("****"+i);

//        ArrayList<vals> d = new FeedRepo().getFeeds("3");
//        for (vals s : d
//        ) {
//            System.out.println("**** " + s.getVars().get(0).getVal() + " " + s.getVars().get(1).getVal());
//        }
    }


//    public void start(Stage stage,String a) throws Exception{
//
////        ;
////        if(dark_light){
////            System.out.println("++++++");
////            scene.getStylesheets().clear();
////            scene.getStylesheets().add(dark);
////        }else{
////            System.out.println("****");
////            scene.getStylesheets().clear();
////            scene.getStylesheets().add(light);
////        }
//
//        stage.setScene(scene);
//        stage.show();
//
//    }


}
