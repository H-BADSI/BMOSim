package bmosim.ihm3.Repository.AgentRepo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.Query;
import java.util.ArrayList;

public class TypeRepo {

    SessionFactory sessionFactory = new Configuration().configure("bmosim/ihm3/Hibernate/hibernateAgent/hiberAgent.cfg.xml").buildSessionFactory();


    public int getIdAttType(String type){
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from DBAtttype where WHERE type='"+type+"'");
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
