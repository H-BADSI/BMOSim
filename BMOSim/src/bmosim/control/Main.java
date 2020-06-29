package bmosim.control;

import bmosim.ihm3.Repository.FeedRepo.InstanceRepo;
import bmosim.ihm3.Repository.FeedRepo.SimulationRepo;

import java.util.ArrayList;

public class Main {

	public static String fileDirect;
	public static Generator g;
	public static String simState="RUNNING";

    public static void executeInst(){
        new InstanceRepo().insertInst(Generator.idSim,Generator.ord);
        Generator.idInst=new InstanceRepo().getIdInstByIdSimAndOrd(Generator.idSim,Generator.ord);
        g = new Generator();
        g.main();
    }

    public static void execute(Integer i, String name, String fileDir, ArrayList<Integer> simSet){
        new SimulationRepo().insertSim(name);
        Generator.idSim= new SimulationRepo().getLastId();

        Generator.nbInst=i;
        fileDirect=fileDir;
        Schedul.simulationDuration=simSet.get(0);
        Schedul.payCycle=simSet.get(1);
        Schedul.stepDelay=simSet.get(2);

        executeInst();
    }

    public static void reexecute() {
        if(Generator.ord<Generator.nbInst){
            Generator.ord++;
            executeInst();
        }
    }


//    public static int getIdInst(int ord,int idSim)throws SQLException {
//        Statement stmt = bmosim.ihm3.Main.conStat.createStatement();
//        String q ="select idinstance from instance where ord="+ord+" and idSimulation="+idSim;
//        ResultSet rs = stmt.executeQuery(q);
//        if(rs.next()){
//            return rs.getInt("idinstance");
//        }
//        return 0;
//    }

//	public static void insertValues(int id){
//        try {
//            Statement stmt2 = bmosim.ihm3.Main.conStat.createStatement();
//            String q2 = "insert into Feed (avgSat,nbSat,nbAcc,nbunAcc,waitAvgTime,ordersTotalNb,purchasesTotalNb," +
//                    "turnoverProbe,refundProbe,idInstance)" +
//                    " values ("+Observer.globalScore+","+Observer.satOffersNb+","+Observer.accOffersNb+"," +
//                    Observer.unAccOffersNb+","+Observer.avgT+","+Observer.ordersTotalNb+","+Observer.purchasesTotalNb
//                    +","+Observer.turnover+","+Observer.refund+","+id+")";
////            System.out.println(q2);
//            stmt2.executeUpdate(q2);
//        } catch (SQLException e) {
//            e.printStackTrace();
////            Alert alert = new Alert(Alert.AlertType.WARNING);
////            alert.setTitle("Warning");
////            alert.setHeaderText("Values was not inserted");
////            alert.setContentText("Check database configurations ");
////
////            alert.showAndWait();
//        }
//        catch (NullPointerException e){
//            e.printStackTrace();
////            Alert alert = new Alert(Alert.AlertType.WARNING);
////            alert.setTitle("Warning");
////            alert.setHeaderText("Simulation cannot be executed !");
////            alert.setContentText("Check database configurations ");
////
////            alert.showAndWait();
//        }
//    }




//	public static void main(String[] args) throws InterruptedException, SQLException, ClassNotFoundException {
//		//Generator.main();
//
//	}

}
