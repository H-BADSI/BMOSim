package bmosim.ihm3.model;

import bmosim.ihm3.Main;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class instance {

    public static ArrayList<Integer> getIdInstBySim(String name){
        ArrayList<Integer> insts = new ArrayList<>();

        Statement stmt = null;
        try {
            int idsim = simulation.getIdSimByName(name);
            stmt = Main.conStat.createStatement();
            String query = "SELECT * FROM inst where idsimulation="+idsim;
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                insts.add(rs.getInt("idInstance"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return insts;
    }


    public static ArrayList<String> getInstance(String name) {
        ArrayList<String> insts = new ArrayList<>();

        Statement stmt = null;
        try {
            int idsim = simulation.getIdSimByName(name);
            stmt = Main.conStat.createStatement();
            String query = "SELECT * FROM inst where idsimulation="+idsim;
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                insts.add(String.valueOf(rs.getString("ord")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return insts;
    }
}
