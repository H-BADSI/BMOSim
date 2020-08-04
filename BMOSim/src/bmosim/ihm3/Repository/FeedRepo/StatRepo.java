package bmosim.ihm3.Repository.FeedRepo;

import bmosim.ihm3.Hibernate.hibernateFeed.DBFeed;
import bmosim.ihm3.Hibernate.hibernateFeed.DBInstance;
import bmosim.ihm3.Hibernate.hibernateFeed.DBSimulation;
import bmosim.ihm3.Repository.Repo;
import bmosim.ihm3.controller.DBconfStruct;
import bmosim.ihm3.controller.funct;
import bmosim.ihm3.model.vals;
import bmosim.ihm3.model.vars;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.ArrayList;

public class StatRepo extends Repo {
    public StatRepo() {
        super("bmosim/ihm3/Hibernate/hibernateFeed/hiberFeed.cfg.xml");
        ArrayList<DBconfStruct> dbconf = funct.getDBSet();
        c.setProperty("hibernate.connection.url","jdbc:mysql://"+dbconf.get(1).getAdd()+":"+dbconf.get(1).getPort()+"/"+dbconf.get(1).getName());
        c.setProperty("hibernate.connection.username",dbconf.get(1).getUser());
        c.setProperty("hibernate.connection.password",dbconf.get(1).getPass());
        sessionFactory= ((Configuration) o).buildSessionFactory();
    }

    //FEED
    public Double getFeedsByVar(String sim,String comp,String var){
        ArrayList<Integer> insts = getIdInstBySim(sim);
        Double d=0.0;

        ArrayList<Double> v = new ArrayList<>();
        for (Integer id:insts) {
            Session session = sessionFactory.openSession();
            Query query = session.createQuery("select avg("+var+") as "+var+" from DBFeed where idInstance="+id);
            Double feed = (Double) query.getSingleResult();
            session.close();

            if(feed!=null){
                v.add(feed);
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
        return d;
    }

    //    public ArrayList<Double> getFeed(int id){
//        Session session = sessionFactory.openSession();
//        Query query = session.createQuery("select avgSat from DBFeed where idInstance="+id);
//        ArrayList<Double> sims = (ArrayList<Double>) query.getResultList();
//        session.close();
//        return sims;
//    }
    public ArrayList<vals> getFeeds(String sim, String o){
        int ord=Integer.parseInt(o);
        int idIn=getIdInstBySimAndOrd(sim,ord);
        ArrayList<vals> vals = new ArrayList<>();
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("FROM DBFeed where idInstance="+idIn);
        ArrayList<DBFeed> feeds = (ArrayList<DBFeed>) query.getResultList();
        session.close();


        for (DBFeed fd:feeds) {
            vals v = new vals();
            v.addVar(new vars("avgSat",fd.getAvgSat()));
            v.addVar(new vars("nbSat",fd.getNbSat()));
            v.addVar(new vars("nbAcc",fd.getNbAcc()));
            v.addVar(new vars("nbunAcc",fd.getNbunAcc()));
            v.addVar(new vars("waitAvgTime",fd.getWaitAvgTime()));
            v.addVar(new vars("ordersTotalNb",Double.valueOf(fd.getOrdersTotalNb())));
            v.addVar(new vars("purchasesTotalNb",Double.valueOf(fd.getPurchasesTotalNb())));
            v.addVar(new vars("turnoverProbe",fd.getTurnoverProbe()));
            v.addVar(new vars("refundProbe",fd.getRefundProbe()));
            vals.add(v);
        }

        return vals;
    }

    public void insertFeeds(int idInst,double avgSat,double nbSat,double nbAcc,double nbunAcc,double waitAvgTime,
                            Integer ordersTotalNb,Integer purchasesTotalNb,
                            double turnoverProbe,double refundProbe){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        DBFeed feed = new DBFeed();
        feed.setWaitAvgTime(waitAvgTime);
        feed.setTurnoverProbe(turnoverProbe);
        feed.setRefundProbe(refundProbe);
        feed.setNbSat(nbSat);
        feed.setNbunAcc(nbunAcc);
        feed.setNbAcc(nbAcc);
        feed.setAvgSat(avgSat);
        feed.setOrdersTotalNb(ordersTotalNb);
        feed.setPurchasesTotalNb(purchasesTotalNb);
        DBInstance instance = new DBInstance();
        instance.setIdInstance(idInst);
        feed.setIdInstance(instance);
        session.save(feed);
        session.getTransaction().commit();
        session.close();
    }

    //INSTANCE
    public ArrayList<Integer> getIdInstBySim(String name){

        ArrayList<Integer> idsim = getIdSimByName(name);
        Session session = sessionFactory.openSession();
        Query query2 = session.createQuery("select idInstance from DBInstance where idSimulation="+idsim.get(0));//REVIEW SING RESULT ***********************
        ArrayList<Integer> inst= (ArrayList<Integer>) query2.getResultList();
        session.close();
        return inst;
    }

    public ArrayList<Integer> getInstance(String name){
        ArrayList<Integer> idsim = getIdSimByName(name);
        Session session = sessionFactory.openSession();
        Query query2 = session.createQuery("select ord from DBInstance where idSimulation="+idsim.get(0));//REVIEW SING RESULT ***********************
        ArrayList<Integer> inst= (ArrayList<Integer>) query2.getResultList();
        session.close();
        return inst;
    }

    public Integer getIdInstBySimAndOrd(String sim,int ord){

        int idSim=getIdSimByName(sim).get(0);
        Session session = sessionFactory.openSession();
        Query query2 = session.createQuery("select idInstance from DBInstance where idSimulation="+idSim+" and ord="+ord);
        Integer inst= (Integer) query2.getSingleResult();
        session.close();
        return inst;
    }

    public Integer getIdInstByIdSimAndOrd(int idSim,int ord){

        Session session = sessionFactory.openSession();
        Query query2 = session.createQuery("from DBInstance where idSimulation="+idSim+" and ord="+ord);
        DBInstance inst;
        try {
            inst= (DBInstance) query2.getSingleResult();
        }catch (NoResultException e){
            session.close();
            return 0;
        }
        session.close();
        return inst.getIdInstance();
    }

    public void insertInst(int idSim, int ord){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        DBInstance inst = new DBInstance();
        DBSimulation sim = new DBSimulation();
        sim.setIdSimulation(idSim);
        inst.setIdSimulation(sim);
        inst.setOrd(ord);
        session.save(inst);
        session.getTransaction().commit();

    }

    //SIMULATION
    public ArrayList<String> getSimulationsByName(String name){
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("select name from DBSimulation where name like '%"+name+"%'");
        ArrayList<String> sims = (ArrayList<String>) query.getResultList();
        session.close();
        return sims;
    }

    public int getLastId(){
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from DBSimulation order by idSimulation DESC");
        query.setMaxResults(1);
        DBSimulation last = (DBSimulation) ((org.hibernate.query.Query) query).uniqueResult();
        session.close();
        return last.getIdSimulation();
    }

    public ArrayList<Integer> getIdSimByName(String name){
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("select idSimulation from DBSimulation where name like '%"+name+"%'");
        ArrayList<Integer> sims = (ArrayList<Integer>) query.getResultList();
        session.close();
        return sims;
    }

    public void insertSim(String s){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        DBSimulation sim = new DBSimulation();
        sim.setName(s);
        session.save(sim);
        session.getTransaction().commit();

    }
}
