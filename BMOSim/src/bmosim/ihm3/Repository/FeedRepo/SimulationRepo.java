package bmosim.ihm3.Repository.FeedRepo;

import bmosim.ihm3.Hibernate.hibernateFeed.DBSimulation;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.Query;
import java.util.ArrayList;

public class SimulationRepo {

    SessionFactory sessionFactory = new Configuration().configure("bmosim/ihm3/Hibernate/hibernateFeed/hiberFeed.cfg.xml").buildSessionFactory();


//    private static List<DBSimulation> loadAllData(Session session) {
//        CriteriaBuilder builder = session.getCriteriaBuilder();
//        CriteriaQuery<DBSimulation> criteria = builder.createQuery(DBSimulation.class);
//        Root<DBSimulation> rootEntry = criteria.from(DBSimulation.class);
//        CriteriaQuery<DBSimulation> all = criteria.select(rootEntry);
//        List<DBSimulation> data = session.createQuery(all).getResultList();
//        return data;
//    }
//    public ArrayList<DBSimulation> getAllSimulations(){
//        Session session = sessionFactory.openSession();
//        ArrayList<DBSimulation> dbs = new ArrayList<>();
//        dbs.addAll(loadAllData(session));
//        session.close();
//        return dbs;
//    }

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
