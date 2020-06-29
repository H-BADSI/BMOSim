package bmosim.ihm3.Repository.FeedRepo;

import bmosim.ihm3.Hibernate.hibernateFeed.DBFeed;
import bmosim.ihm3.Hibernate.hibernateFeed.DBInstance;
import bmosim.ihm3.model.vals;
import bmosim.ihm3.model.vars;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.Query;
import java.util.ArrayList;

public class FeedRepo {


    SessionFactory sessionFactory = new Configuration().configure("bmosim/ihm3/Hibernate/hibernateFeed/hiberFeed.cfg.xml").buildSessionFactory();

    public Double getFeedsByVar(String sim,String comp,String var){
        ArrayList<Integer> insts = new InstanceRepo().getIdInstBySim(sim);
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
    public ArrayList<vals> getFeeds(String sim,String o){
        int ord=Integer.parseInt(o);
        int idIn=new InstanceRepo().getIdInstBySimAndOrd(sim,ord);
        ArrayList<vals> vals = new ArrayList<>();
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("FROM DBFeed where idInstance="+idIn);
        ArrayList<DBFeed> feeds = (ArrayList<DBFeed>) query.getResultList();
        session.clear();
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

        StackTraceElement[] st = Thread.currentThread().getStackTrace();
        System.out.println(  "******* create connection called from " + st[2] );

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


    }

}
