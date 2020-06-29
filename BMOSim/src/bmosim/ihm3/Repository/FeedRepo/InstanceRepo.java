package bmosim.ihm3.Repository.FeedRepo;

import bmosim.ihm3.Hibernate.hibernateFeed.DBInstance;
import bmosim.ihm3.Hibernate.hibernateFeed.DBSimulation;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.ArrayList;

public class InstanceRepo {

    SessionFactory sessionFactory = new Configuration().configure("bmosim/ihm3/Hibernate/hibernateFeed/hiberFeed.cfg.xml").buildSessionFactory();

    public ArrayList<Integer> getIdInstBySim(String name){

        ArrayList<Integer> idsim = new SimulationRepo().getIdSimByName(name);
        Session session = sessionFactory.openSession();
        Query query2 = session.createQuery("select idInstance from DBInstance where idSimulation="+idsim.get(0));//REVIEW SING RESULT ***********************
        ArrayList<Integer> inst= (ArrayList<Integer>) query2.getResultList();
        session.close();
        return inst;
    }

    public ArrayList<Integer> getInstance(String name){
        ArrayList<Integer> idsim = new SimulationRepo().getIdSimByName(name);
        Session session = sessionFactory.openSession();
        Query query2 = session.createQuery("select ord from DBInstance where idSimulation="+idsim.get(0));//REVIEW SING RESULT ***********************
        ArrayList<Integer> inst= (ArrayList<Integer>) query2.getResultList();
        session.close();
        return inst;
    }

    public Integer getIdInstBySimAndOrd(String sim,int ord){

        int idSim=new SimulationRepo().getIdSimByName(sim).get(0);
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
}
