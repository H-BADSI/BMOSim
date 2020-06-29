package bmosim.ihm3.Repository.AgentRepo;

import bmosim.ihm3.Hibernate.hibernateAgent.DBAgent;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.Query;
import java.util.ArrayList;

public class AgentRepo {

    SessionFactory sessionFactory = new Configuration().configure("bmosim/ihm3/Hibernate/hibernateAgent/hiberAgent.cfg.xml").buildSessionFactory();

    public String getAgClass(String type) {

        Session session = sessionFactory.openSession();
        Query query = session.createQuery("select agClass from DBAgent where type='"+type+"'");
        ArrayList<String> agClass = (ArrayList<String>) query.getResultList();
        session.close();
        return agClass.get(0);

    }

    public ArrayList<String> getAgentTypes() {

        Session session = sessionFactory.openSession();
        Query query = session.createQuery("select type from DBAgent");
        ArrayList<String> agClass = (ArrayList<String>) query.getResultList();
        session.close();
        return agClass;

    }

    public Integer getIdAgent(String type) {

        Session session = sessionFactory.openSession();
        Query query = session.createQuery("select idAgent from DBAgent where type='"+type+"'");
        Integer id= (Integer) query.getSingleResult();
        session.close();
        return id;

    }

    public void insertAgent(String type,String agClass){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        DBAgent agent = new DBAgent();
        agent.setType(type);
        agent.setAgClass(agClass);
        session.save(agent);
        session.getTransaction().commit();

    }
}
