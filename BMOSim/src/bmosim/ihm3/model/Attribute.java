package bmosim.ihm3.model;

import bmosim.ihm3.Main;
import bmosim.ihm3.Repository.AgentRepo.AgentRepo;
import bmosim.ihm3.Repository.AgentRepo.AttributeRepo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

public class Attribute {

//    public static void addAttribute(String name,String idtype) throws SQLException {
//        int idty=getidAttType(idtype);
//        String query = "INSERT INTO attribute (name,idAttType) SELECT '"+name+"',"+idty+
//                " FROM DUAL WHERE NOT EXISTS (SELECT * FROM attribute WHERE name='"+name+"' and idAttType='"+idty+"' LIMIT 1)";
//        Statement stmt = Main.conAgent.createStatement();
//        stmt.executeUpdate(query);
//    }

//    public static void addAttributeWithValues(String name,String idtype,String value) throws SQLException {
//        int idty=getidAttType(idtype);
//        String query = "INSERT INTO attribute (name,attribute.values,idAttType) SELECT '"+name+"','"+value+"',"+idty+
//                " FROM DUAL WHERE NOT EXISTS (SELECT * FROM attribute WHERE name='"+name+"' and idAttType='"+idty+"' LIMIT 1)";
//        Statement stmt = Main.conAgent.createStatement();
//        stmt.executeUpdate(query);
//    }

//    public static void linkAttAgent(String agent,String att) throws SQLException {
//        int idAg=new AgentRepo().getIdAgent(agent);
//        int idAtt=new AttributeRepo().getIdAttribute(att);
//        String query = "INSERT INTO agentattribute (idAgent,idAttribute) SELECT '"+idAg+"','"+idAtt+"' " +
//                "FROM DUAL WHERE NOT EXISTS (SELECT * FROM agentattribute WHERE idAgent='"+idAg+"' and idAttribute='"+idAtt+"' LIMIT 1)";
//        Statement stmt = Main.conAgent.createStatement();
//        stmt.executeUpdate(query);
//    }

//    public static ArrayList<String> getAttTypes() throws SQLException {
//        ArrayList<String> types= new ArrayList<>();
//        Statement stmt = Main.conAgent.createStatement();
//        String query = "SELECT * FROM atttype";
//        ResultSet rs = stmt.executeQuery(query);
//        while (rs.next()) {
//            types.add(String.valueOf(rs.getString("type")));
//        }
//        return types;
//    }

//    public static int getIdAttribute(String att) throws SQLException{
//        int id=0;
//        String q = "SELECT idAttribute FROM attribute where name='"+att+"'";
//        Statement stmt1=Main.conAgent.createStatement();
//        ResultSet rs1=stmt1.executeQuery(q);
//        if(rs1.next()) {
//            id=rs1.getInt("idAttribute");
//        }
//        return id;
//    }

//    public static int getidAttType(String type) throws SQLException{
//        int id=0;
//        String q = "SELECT idAttType FROM atttype where type='"+type+"'";
//        Statement stmt1=Main.conAgent.createStatement();
//        ResultSet rs1=stmt1.executeQuery(q);
//        if(rs1.next()) {
//            id=rs1.getInt("idAttType");
//        }
//        return id;
//    }

//    public static ArrayList<type> getAttributesByAgent(String agent) throws SQLException {
//        ArrayList<type> at = new ArrayList<>();
//
//        String q = " select idagent from agent where type='"+agent+"'";
//        Statement stmt1=Main.conAgent.createStatement();
//        ResultSet rs1=stmt1.executeQuery(q);
//        if(rs1.next()){
//            String q2= "select * from agentattribute where idagent="+rs1.getInt("idAgent");
//            Statement stmt2=Main.conAgent.createStatement();
//            ResultSet rs2=stmt2.executeQuery(q2);
//            while (rs2.next()){
//                String q3= "select * from attribute where idattribute="+rs2.getInt("idattribute");
//                Statement stmt3=Main.conAgent.createStatement();
//                ResultSet rs3=stmt3.executeQuery(q3);
//                if(rs3.next()){
//                    String q4= "select * from atttype where idatttype="+rs3.getInt("idatttype");
//                    Statement stmt4=Main.conAgent.createStatement();
//                    ResultSet rs4=stmt4.executeQuery(q4);
//                    if(rs4.next()){
//                        at.add(new type(rs3.getString("name"),rs4.getString("type"),rs3.getString("values")));
//                    }
//                }
//            }
//        }
//        return at;
//    }

//    public static void disJoinVar(String att, String ag)  {
//
//        try{
//            int idAtt=new AttributeRepo().getIdAttribute(att);
//            int idAg=new AgentRepo().getIdAgent(ag);
//
//            String q = " delete from agentattribute where idAgent="+idAg+" and idAttribute="+idAtt;
//            Statement stmt=Main.conAgent.createStatement();
//            stmt.executeUpdate(q);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//    }
//    public static ArrayList<String> getAttributeVals(String att){
//        ArrayList<String> vals = new ArrayList<>();
//        System.out.println("ATT "+att);
//        String q = "select a.values from attribute as a where name='"+att+"'";
//        Statement stmt1= null;
//        try {
//            stmt1 = Main.conAgent.createStatement();
//            ResultSet rs1=stmt1.executeQuery(q);
//            if(rs1.next()) {
//                String str[] = rs1.getString("values").replaceAll("\"","").split(" ");
//                vals = new ArrayList<>(Arrays.asList(str));
//            }
//            return vals;
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
}
