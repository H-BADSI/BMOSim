package bmosim.ihm3.model;

import bmosim.ihm3.Main;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class simulation {

    int id;
    String name;

    public static int getLastIdSim() throws SQLException {
        Statement stmt = Main.conStat.createStatement();
        String q="SELECT * FROM Simulation ORDER BY idSimulation DESC LIMIT 1";
        ResultSet rs = stmt.executeQuery(q);
        if(rs.next()){
            return rs.getInt("idSimulation");
        }
        return 0;
    }

    public static ArrayList<String> getSimulations(String name) throws SQLException {
        ArrayList<String> sims = new ArrayList<>();
        Statement stmt = bmosim.ihm3.Main.conStat.createStatement();
        String query = "SELECT * FROM simulation where name like '%"+name+"%'";
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {
            sims.add(String.valueOf(rs.getString("name")));
        }
        return sims;
    }

    public static int getIdSimByName(String name) throws SQLException {
        Statement stmt = bmosim.ihm3.Main.conStat.createStatement();
        String query = "SELECT * FROM simulation where name='"+name+"'";
        ResultSet rs = stmt.executeQuery(query);
        if (rs.next()) {
           return rs.getInt("idsimulation");
        }
        return 0;
    }
}
