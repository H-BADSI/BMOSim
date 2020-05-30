package bmosim.control;


import bmosim.ihm3.bdd;
import bmosim.ihm3.controller.simulate;
import bmosim.ihm3.model.simulation;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;


public class Main {

	public static Connection con;

	public static String fileDirect;

	public static void connect(String db) throws SQLException, ClassNotFoundException, InterruptedException {
		con= bdd.connect(db);

	}


    public static int getIdInst(int ord,int idSim)throws SQLException {
        Statement stmt = Main.con.createStatement();
        String q ="select idinstance from inst where ord="+ord+" and idSimulation="+idSim;
        ResultSet rs = stmt.executeQuery(q);
        if(rs.next()){
            return rs.getInt("idinstance");
        }
        return 0;
    }

	public static void insertValues(int id){
        try {
            Statement stmt2 = Main.con.createStatement();
            String q2 = "insert into vals (avgSat,nbsat,nbacc,nbunacc,waitTimeAVG,ordersTotalNb,purchasesTotalNb," +
                    "turnoverProbe,refundProbe,nbCustomer,idInstance)" +
                    " values ("+Observer.globalScore+","+Observer.satOffersNb+","+Observer.accOffersNb+"," +
                    Observer.unAccOffersNb+","+Observer.avgT+","+Observer.ordersTotalNb+","+Observer.purchasesTotalNb
                    +","+Observer.turnover+","+Observer.refund+","+Observer.customers.size()+","+id+")";
            stmt2.executeUpdate(q2);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void executeInst() throws SQLException {
        Statement stmt1 = Main.con.createStatement();
        String q1 = "insert into inst (ord,idSimulation) values ("+Generator.ord+","+Generator.idSim+")";
        stmt1.executeUpdate(q1);
        Generator.idInst=getIdInst(Generator.ord,Generator.idSim);
        Generator g = new Generator();
        g.main();
    }

    public static void execute(Integer i, String name, String fileDir, ArrayList<Integer> simSet) throws SQLException, ClassNotFoundException, InterruptedException {
        connect("stat");
        Statement stmt = con.createStatement();
        String q = "insert into simulation (name) values ('"+name+"')";
        stmt.executeUpdate(q);
        Generator.idSim= simulation.getLastIdSim();
        Generator.nbInst=i;
        fileDirect=fileDir;
        Schedul.simulationDuration=simSet.get(0);
        Schedul.payCycle=simSet.get(1);
        Schedul.stepDelay=simSet.get(2);

        executeInst();
    }

	public static void reexecute() throws SQLException {
			if(Generator.ord<Generator.nbInst){
				Generator.ord++;
                executeInst();
			}

//			simulate.tf3.setText("activate");
	}

	public static void stop(){
	    Schedul.stop();
    }

	public static void main(String[] args) throws InterruptedException, SQLException, ClassNotFoundException {
		//Generator.main();

	}

}
