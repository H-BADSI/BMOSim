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

public abstract class AttributeRepo extends AgRepo {

//    SessionFactory sessionFactory;

    public AttributeRepo() {
        super();
//        ArrayList<String> dbconf = funct.getDBSet();
//        Configuration c = new Configuration();
//        Object o=c.configure("bmosim/ihm3/Hibernate/hibernateAgent/hiberAgent.cfg.xml");
//        c.setProperty("hibernate.connection.url","jdbc:mysql://localhost:3306/agentdb");
//        c.setProperty("hibernate.connection.username","root");
//        c.setProperty("hibernate.connection.password","emplacement44");
//        sessionFactory= ((Configuration) o).buildSessionFactory();
    }

    @Override
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

    @Override
    public ArrayList<String> getAttributeValues(String att){
        ArrayList<String> vals = new ArrayList<>();
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from DBAttribute where name='"+att+"'");
        DBAttribute attribute = (DBAttribute)query.getSingleResult();
        String str[] = attribute.getVals().replaceAll("\"","").split(" ");
        vals = new ArrayList<>(Arrays.asList(str));

        return vals;
    }

    @Override
    public ArrayList<type> getAttributesByAgent(String agent){

        ArrayList<type> attributes = new ArrayList<>();

        int idAgent = getIdAgent(agent);

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


    @Override
    public String getTypeByAtt(String att){
        String type;
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("select idAttType from DBAttribute where name='"+att+"'");
        Integer idtype= (Integer) query.getSingleResult();
        session.close();
        Session session2 = sessionFactory.openSession();
        Query query2 = session2.createQuery("select type from DBAtttype where idAttType='"+idtype+"'");
        type = (String) query2.getSingleResult();
        session2.close();
        return type;

    }

    @Override
    public int getLastId(){
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from DBAttribute order by idAttribute DESC");
        query.setMaxResults(1);
        DBAttribute last = (DBAttribute) ((org.hibernate.query.Query) query).uniqueResult();
        session.close();
        return last.getIdAttribute();
    }

    @Override
    public void linkAttAgent(String agent,String att)  {
        int idAg=getIdAgent(agent);
        int idAtt=getIdAttribute(att);
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
    }

    @Override
    public void disJoinAttAgent(String att, String ag)  {

        int idAtt=getIdAttribute(att);
        int idAg=getIdAgent(ag);

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        DBAgAtt agAtt = new DBAgAtt();

        agAtt.setIdAttribute(idAtt);
        agAtt.setIdAgent(idAg);
        session.delete(agAtt);
        session.getTransaction().commit();
    }

    @Override
    public void insertAttribute(String name,String idtype){
        int idty=getIdAttType(idtype);
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

    }

    @Override
    public void insertAttributeWithOptions(String name,String idtype,String value){
        int idty=getIdAttType(idtype);
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
    }

}
