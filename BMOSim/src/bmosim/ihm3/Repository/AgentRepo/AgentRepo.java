package bmosim.ihm3.Repository.AgentRepo;

import bmosim.ihm3.Hibernate.hibernateAgent.DBAgent;
import bmosim.ihm3.controller.funct;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.ArrayList;

public class AgentRepo {

    SessionFactory sessionFactory;

    public AgentRepo() {
        ArrayList<String> dbconf = funct.getDBSet();
        Configuration c = new Configuration();
        Object o=c.configure("bmosim/ihm3/Hibernate/hibernateAgent/hiberAgent.cfg.xml");
        c.setProperty("hibernate.connection.url","jdbc:mysql://localhost:3306/agentdb");
        c.setProperty("hibernate.connection.username","root");
        c.setProperty("hibernate.connection.password","emplacement44");
        sessionFactory= ((Configuration) o).buildSessionFactory();
    }


    public String getAgClass(String type) {

        Session session = sessionFactory.openSession();
        Query query = session.createQuery("select agClass from DBAgent where type='"+type+"'");
        ArrayList<String> agClass = (ArrayList<String>) query.getResultList();
        session.close();
        return agClass.get(0);

    }

    public ArrayList<String> getAgentTypes(String ag) {

        Session session = sessionFactory.openSession();
        Query query = session.createQuery("select type from DBAgent where type like '%"+ag+"%'");
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

        try {
            Session session2 = sessionFactory.openSession();
            Query query = session2.createQuery("from DBAgent where type='"+type+"'");
            DBAgent ag= (DBAgent) query.getSingleResult();
            session2.close();
        }catch (NoResultException e){
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            DBAgent agent = new DBAgent();
            agent.setType(type);
            agent.setAgClass(agClass);
            session.save(agent);
            session.getTransaction().commit();
        }


    }
}
