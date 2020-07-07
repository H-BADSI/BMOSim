package bmosim.ihm3.Repository.AgentRepo;

import bmosim.ihm3.Hibernate.hibernateAgent.DBAgAtt;
import bmosim.ihm3.Hibernate.hibernateAgent.DBAttribute;
import bmosim.ihm3.Hibernate.hibernateAgent.DBAtttype;
import bmosim.ihm3.controller.funct;
import bmosim.ihm3.model.type;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Arrays;

public class AttributeRepo {

    SessionFactory sessionFactory;

    public AttributeRepo() {
        ArrayList<String> dbconf = funct.getDBSet();
        Configuration c = new Configuration();
        Object o=c.configure("bmosim/ihm3/Hibernate/hibernateAgent/hiberAgent.cfg.xml");
        c.setProperty("hibernate.connection.url","jdbc:mysql://localhost:3306/agentdb");
        c.setProperty("hibernate.connection.username","root");
        c.setProperty("hibernate.connection.password","emplacement44");
        sessionFactory= ((Configuration) o).buildSessionFactory();
    }
    public Integer getIdAttribute(String name) {
        Session session = sessionFactory.openSession();
        DBAttribute attribute=null;
        try {
            Query query = session.createQuery("from DBAttribute where name='"+name+"'");
            attribute= (DBAttribute) query.getSingleResult();
        }catch (NoResultException e){

        }

        session.close();
        return attribute.getIdAttribute();
    }

    public ArrayList<String> getAttributeValues(String att){
        ArrayList<String> vals = new ArrayList<>();
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from DBAttribute where name='"+att+"'");
        DBAttribute attribute = (DBAttribute)query.getSingleResult();
        String str[] = attribute.getVals().replaceAll("\"","").split(" ");
        vals = new ArrayList<>(Arrays.asList(str));

        return vals;
    }

    public ArrayList<type> getAttributesByAgent(String agent){

        ArrayList<type> attributes = new ArrayList<>();

        int idAgent = new AgentRepo().getIdAgent(agent);

        Session session = sessionFactory.openSession();
        Query query = session.createQuery("select idAttribute from DBAgAtt where idAgent='"+idAgent+"'");
        ArrayList<Integer> idAtt= (ArrayList<Integer>) query.getResultList();
        session.close();
        for (Integer id:idAtt) {
            Session session1 = sessionFactory.openSession();
            Query query1 = session1.createQuery("from DBAttribute where idAttribute="+id);
            DBAttribute attribute= (DBAttribute) query1.getSingleResult();
            session1.close();

            Session session2 = sessionFactory.openSession();
            Query query2 = session2.createQuery("from DBAtttype where idAttType="+attribute.getIdAttType());
            DBAtttype type= (DBAtttype) query2.getSingleResult();
            session2.close();

            attributes.add(new type(attribute.getName(),type.getType(),attribute.getVals()));
        }

        return attributes;
    }

    public int getLastId(){
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from DBAttribute order by idAttribute DESC");
        query.setMaxResults(1);
        DBAttribute last = (DBAttribute) ((org.hibernate.query.Query) query).uniqueResult();
        session.close();
        return last.getIdAttribute();
    }

    public void linkAttAgent(String agent,String att)  {
        int idAg=new AgentRepo().getIdAgent(agent);
        int idAtt=new AttributeRepo().getIdAttribute(att);
        Session session2 = sessionFactory.openSession();

        System.out.println(idAg+" + "+idAtt);
        try {
            Query query = session2.createQuery("from DBAgAtt where idAgent='"+idAg+"' and idAttribute='"+idAtt+"'");
            DBAgAtt attribute1 = (DBAgAtt) query.getSingleResult();
            session2.close();
        }catch (NoResultException e){
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            DBAgAtt attribute = new DBAgAtt();
            attribute.setIdAgent(idAg);
            attribute.setIdAttribute(idAtt);
            session.save(attribute);
            session.getTransaction().commit();
        }

//        if(attribute1==null){

//        }
    }

    public void disJoinAttAgent(String att, String ag)  {

        int idAtt=new AttributeRepo().getIdAttribute(att);
        int idAg=new AgentRepo().getIdAgent(ag);

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        DBAgAtt agAtt = new DBAgAtt();

        agAtt.setIdAttribute(idAtt);
        agAtt.setIdAgent(idAg);
        session.delete(agAtt);
        session.getTransaction().commit();
    }

    public void insertAttribute(String name,String idtype){
        int idty=new TypeRepo().getIdAttType(idtype);
        Session session2 = sessionFactory.openSession();
        DBAttribute attribute1=null;
        try {
            Query query = session2.createQuery("from DBAttribute where name='"+name+"' and idAttType='"+idty+"'");
            attribute1 = (DBAttribute) query.getSingleResult();
            session2.close();
        }catch (NoResultException e){
            System.out.println("------------------------------");
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            DBAttribute attribute = new DBAttribute();
            attribute.setName(name);
            attribute.setIdAttType(idty);
            session.save(attribute);
            session.getTransaction().commit();
        }

//        if(attribute1==null){
//
//
//
//
//        }
    }

    public void insertAttributeWithOptions(String name,String idtype,String value){
        int idty=new TypeRepo().getIdAttType(idtype);
        Session session2 = sessionFactory.openSession();
        DBAttribute attribute1=null;
        try {
            Query query = session2.createQuery("from DBAttribute where name='"+name+"' and idAttType='"+idty+"'");
            attribute1 = (DBAttribute) query.getSingleResult();
            session2.close();

        }catch (NoResultException e){
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            DBAttribute attribute = new DBAttribute();
            attribute.setIdAttribute(getLastId()+1);
            attribute.setName(name);
            attribute.setIdAttType(idty);
            attribute.setVals(value);
            session.save(attribute);
            session.getTransaction().commit();
        }

//        if(attribute1==null){
//
//        }
    }


}
