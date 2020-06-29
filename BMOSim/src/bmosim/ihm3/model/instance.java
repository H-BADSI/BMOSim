package bmosim.ihm3.model;

public class instance {

//    public static ArrayList<Integer> getIdInstBySim(String name){
//        ArrayList<Integer> insts = new ArrayList<>();
//
//        Statement stmt = null;
//        try {
//            ArrayList<Integer> idsim = new SimulationRepo().getIdSimByName(name);
//            stmt = Main.conStat.createStatement();
//            String query = "SELECT * FROM inst where idsimulation="+idsim.get(0);//*****Review
//            ResultSet rs = stmt.executeQuery(query);
//            while (rs.next()) {
//                insts.add(rs.getInt("idInstance"));
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return insts;
//    }


//    public static ArrayList<String> getInstance(String name) {
//        ArrayList<String> insts = new ArrayList<>();
//
//        Statement stmt = null;
//        try {
//            ArrayList<Integer> idsim = new SimulationRepo().getIdSimByName(name);
//            stmt = Main.conStat.createStatement();
//            String query = "SELECT * FROM inst where idsimulation="+idsim.get(0);//*****Review
//            ResultSet rs = stmt.executeQuery(query);
//            while (rs.next()) {
//                insts.add(String.valueOf(rs.getString("ord")));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return insts;
//    }
}
