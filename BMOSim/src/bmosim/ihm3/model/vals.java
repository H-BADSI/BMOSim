package bmosim.ihm3.model;

import bmosim.ihm3.Main;
import com.sun.xml.xsom.impl.scd.Iterators;
import org.semanticweb.owlapi.rdf.rdfxml.parser.State;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class vals {

    ArrayList<vars> vars = new ArrayList<>();
    double avgSat;
    double nbSat;
    double nbAcc;
    double nbUnAcc;
    double waitTimeAvg;
    double ordersTotalNb;
    double purchasesTotalNb;
    double turnoverProbe;
    double refundProbe;
    double nbCustomer;

    public ArrayList<bmosim.ihm3.model.vars> getVars() {
        return vars;
    }

    public void setVars(ArrayList<bmosim.ihm3.model.vars> vars) {
        this.vars = vars;
    }

    public void addVar(vars v){
       this.getVars().add(v);
    }

    public vars getValByName(String name){
        for (vars v: this.getVars()) {
            if(v.name.equals(name)){
                return v;
            }
        }
        return null;
    }

    public static Double getValuesByVar(String sim,String comp,String var){
        ArrayList<Integer> insts = instance.getIdInstBySim(sim);
        Double d=0.0;
        try {
            ArrayList<Double> v = new ArrayList<>();
            for (Integer id:insts) {
                Statement stmt = Main.conStat.createStatement();
                String query = "SELECT avg("+var+") as "+var+" FROM vals where idinstance="+id;

                ResultSet rs = stmt.executeQuery(query);
                if(rs.next()){
                    v.add(rs.getDouble(var));
                }
            }

            switch (comp){
                case "Mean": {
                    for (Double dd :v) {
                        d+=dd;
                    }
                    d/=v.size();
                    break;
                }
                case "Max": {
                    for (Double dd :v) {
                        if(d<dd){
                            d=dd;
                        }
                    }
                    break;}
                case "Min": {
                    for (Double dd :v) {
                        if(d>dd){
                            d=dd;
                        }
                    }
                    break;
                }
                default:break;
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return d;
    }


    public static ArrayList<vals> getValues(String idinst){
        int id=Integer.parseInt(idinst);
        ArrayList<vals> vals = new ArrayList<>();

        try {
            Statement stmt = Main.conStat.createStatement();
            String query = "SELECT * FROM vals where idinstance="+id;
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {

                vals v = new vals();
                v.addVar(new vars("avgSat",rs.getDouble("avgSat")));
                v.addVar(new vars("nbsat",rs.getDouble("nbsat")));
                v.addVar(new vars("nbacc",rs.getDouble("nbacc")));
                v.addVar(new vars("nbunacc",rs.getDouble("nbunacc")));
                v.addVar(new vars("waitTimeAVG",rs.getDouble("waitTimeAVG")));
                v.addVar(new vars("ordersTotalNb",rs.getDouble("ordersTotalNb")));
                v.addVar(new vars("purchasesTotalNb",rs.getDouble("purchasesTotalNb")));
                v.addVar(new vars("turnoverProbe",rs.getDouble("turnoverProbe")));
                v.addVar(new vars("refundProbe",rs.getDouble("refundProbe")));
                v.addVar(new vars("nbCustomer",rs.getDouble("nbCustomer")));
                vals.add(v);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vals;

    }

}
