package bmosim.ihm3.model;

import bmosim.ihm3.Main;
import bmosim.ihm3.bdd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Agent {


//    public static void addAgent(String type,String agClass) throws SQLException {
//        String query = "INSERT INTO agent (type,agClass) SELECT '"+type+"','"+agClass+"' " +
//                "FROM DUAL WHERE NOT EXISTS (SELECT * FROM agent WHERE type='"+type+"' and agClass='"+agClass+"' LIMIT 1)";
//        Statement stmt = bdd.getAgStatement();
//        stmt.executeUpdate(query);
//    }



//    public static ArrayList<String> getAgentType() throws SQLException {
//        ArrayList<String> types=new ArrayList<>();
//        Statement stmt = bdd.getAgStatement();
//        String query = "SELECT * FROM agent";
//        ResultSet rs = stmt.executeQuery(query);
//        while (rs.next()) {
//            types.add(String.valueOf(rs.getString("type")));
//        }
//        return types;
//    }

//    public static int getIdAgent(String agent) throws SQLException{
//        int id=0;
//        String q = "SELECT idAgent FROM agent where type='"+agent+"'";
//        Statement stmt1=bdd.getAgStatement();
//        ResultSet rs1=stmt1.executeQuery(q);
//        if(rs1.next()) {
//            id=rs1.getInt("idAgent");
//        }
//        return id;
//    }
//    public static String getAgClass(String type)throws SQLException{
//        String q= "select * from agent where type='"+type+"'";
//        Statement stmt = bdd.getAgStatement();
//        ResultSet rs= stmt.executeQuery(q);
//        if(rs.next()){
//            return rs.getString("agClass");
//        }
//        return null;
//
//    }
//    public static String getAgentClass(String ag)  {
//        try {
//            String q = "SELECT agClass FROM agent where type='"+ag+"'";
//
//            Statement stmt1 = bdd.getAgStatement();
//            ResultSet rs1=stmt1.executeQuery(q);
//            if(rs1.next()) {
//                return rs1.getString("agClass");
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return " ";
//
//    }
}
