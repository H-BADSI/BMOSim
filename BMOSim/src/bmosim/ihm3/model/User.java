package bmosim.ihm3.model;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class User {

    private String username;
    private String password;
    private Boolean admin;

    public User() {
    }

    public User(String username) {
        this.username = username;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String username, String password, Boolean admin) {
        this.username = username;
        this.password = password;
        this.admin = admin;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

//    public void insertUser(){
//        Statement stmt = bdd.getAccountStatement();
//        int b=this.admin?1:0;
//        String query = "insert into appUser(username,password,adm) values('"+this.username+"','"+this.username+"',"+b+")";
//        try {
//            stmt.executeUpdate(query);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

//    public boolean userExist(){
//        Statement stmt = bdd.getAccountStatement();
//        String query = "Select * from appUser where username='"+this.username+"'";
//        try {
//            ResultSet rs = stmt.executeQuery(query);
//            if(rs.next()){
//                return true;
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return false;
//    }
//    public boolean userExistPass(){
//        Statement stmt = bdd.getAccountStatement();
//        String query = "Select * from appUser where username='"+this.username+"' and password='"+this.password+"'";
//        try {
//            ResultSet rs = stmt.executeQuery(query);
//            if(rs.next()){
//                return true;
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return false;
//    }
//    public boolean isAdmin(){
//        Statement stmt = bdd.getAccountStatement();
//        String query = "Select adm from appUser where username='"+this.username+"' and password='"+this.password+"'";
//        try {
//            ResultSet rs = stmt.executeQuery(query);
//            if(rs.next()){
//               if(rs.getBoolean("adm")==true){
//                   return true;
//               }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return false;
//    }
}
