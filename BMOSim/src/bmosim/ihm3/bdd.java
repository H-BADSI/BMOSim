//package bmosim.ihm3;
//
//
//import javafx.scene.control.Alert;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//import java.sql.Statement;
//
//public class bdd{
//    public static Connection connect(String address, String port, String db, String user, String pass) {
//        Connection con=null;
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            con = DriverManager.getConnection(
//                    "jdbc:mysql://" + address + ":" + port + "/" + db, user, pass);
//
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (SQLException e) {
//            System.out.println("WRONG");
//        }
//
//        return con;
//    }
//    public static Statement getAgStatement(){
//        Statement stmt=null;
//        try {
//            stmt = Main.conAgent.createStatement();
//        }  catch (SQLException e) {
//            Alert alert = new Alert(Alert.AlertType.WARNING);
//            alert.setTitle("Warning");
//            alert.setHeaderText("There is no database connected !");
//            alert.setContentText("Check again database configurations ");
//
//            alert.showAndWait();
//        }
//        catch (NullPointerException e){
//            Alert alert = new Alert(Alert.AlertType.WARNING);
//            alert.setTitle("Warning");
//            alert.setHeaderText("There is no database connected !");
//            alert.setContentText("Check again database configurations ");
//
//            alert.showAndWait();
//        }
//        return stmt;
//    }
//    public static Statement getStatStatement(){
//        Statement stmt=null;
//        try {
//            stmt = Main.conStat.createStatement();
//        }  catch (SQLException e) {
//            Alert alert = new Alert(Alert.AlertType.WARNING);
//            alert.setTitle("Warning");
//            alert.setHeaderText("There is no database connected !");
//            alert.setContentText("Check again database configurations ");
//
//            alert.showAndWait();
//        }
//        catch (NullPointerException e){
//            Alert alert = new Alert(Alert.AlertType.WARNING);
//            alert.setTitle("Warning");
//            alert.setHeaderText("There is no database connected !");
//            alert.setContentText("Check again database configurations ");
//
//            alert.showAndWait();
//        }
//        return stmt;
//    }
//    public static Statement getAccountStatement(){
//        Statement stmt=null;
//        try {
//            stmt = Main.conAccount.createStatement();
//        }  catch (SQLException e) {
//            Alert alert = new Alert(Alert.AlertType.WARNING);
//            alert.setTitle("Warning");
//            alert.setHeaderText("There is no database connected !");
//            alert.setContentText("Check again database configurations ");
//
//            alert.showAndWait();
//        }
//        catch (NullPointerException e){
//            Alert alert = new Alert(Alert.AlertType.WARNING);
//            alert.setTitle("Warning");
//            alert.setHeaderText("There is no database connected !");
//            alert.setContentText("Check again database configurations ");
//
//            alert.showAndWait();
//        }
//        return stmt;
//    }
//}