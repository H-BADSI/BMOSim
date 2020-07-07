package bmosim.ihm3.Repository.AgentRepo;

import bmosim.ihm3.controller.funct;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.Query;
import java.util.ArrayList;

public class TypeRepo {

    SessionFactory sessionFactory;

    public TypeRepo() {
        ArrayList<String> dbconf = funct.getDBSet();
        Configuration c = new Configuration();
        Object o=c.configure("bmosim/ihm3/Hibernate/hibernateAgent/hiberAgent.cfg.xml");
        c.setProperty("hibernate.connection.url","jdbc:mysql://localhost:3306/agentdb");
        c.setProperty("hibernate.connection.username","root");
        c.setProperty("hibernate.connection.password","emplacement44");
        sessionFactory= ((Configuration) o).buildSessionFactory();
    }

    public int getIdAttType(String type){
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("select idAttType from DBAtttype where type='"+type+"'");
        Integer id = (Integer) query.getSingleResult();
        session.close();
        return id;
    }

    public ArrayList<String> getAttributeTypes(){
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("select type from DBAtttype ");
        ArrayList<String> types= (ArrayList<String>) query.getResultList();
        session.close();
        return types;
    }
}
