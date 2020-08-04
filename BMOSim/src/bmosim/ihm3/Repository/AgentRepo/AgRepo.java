package bmosim.ihm3.Repository.AgentRepo;

import bmosim.ihm3.Hibernate.hibernateAgent.DBAgAtt;
import bmosim.ihm3.Hibernate.hibernateAgent.DBAgent;
import bmosim.ihm3.Hibernate.hibernateAgent.DBAttribute;
import bmosim.ihm3.Hibernate.hibernateAgent.DBAtttype;
import bmosim.ihm3.Repository.Repo;
import bmosim.ihm3.controller.DBconfStruct;
import bmosim.ihm3.controller.funct;
import bmosim.ihm3.model.type;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Arrays;

public class AgRepo extends Repo {
    public AgRepo() {
        super("bmosim/ihm3/Hibernate/hibernateAgent/hiberAgent.cfg.xml");
        ArrayList<DBconfStruct> dbconf = funct.getDBSet();
        c.setProperty("hibernate.connection.url","jdbc:mysql://"+dbconf.get(0).getAdd()+":"+dbconf.get(0).getPort()+"/"+dbconf.get(0).getName());
        c.setProperty("hibernate.connection.username",dbconf.get(0).getUser());
        c.setProperty("hibernate.connection.password",dbconf.get(0).getPass());
        sessionFactory= ((Configuration) o).buildSessionFactory();
    }

    //ATTRIBUTE

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

    public ArrayList<String> getAttributesNames(){
        ArrayList<String> atts = new ArrayList<>();
        Session session = sessionFactory.openSession();
        try {
            Query query = session.createQuery("select name from DBAttribute");
            atts= (ArrayList<String>) query.getResultList();
        }catch (NoResultException e){
            e.getStackTrace();
        }
        session.close();
        return atts;
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

        Integer idAgent = getIdAgent(agent);
        if(idAgent==null)return null;

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


    public String getTypeByAtt(String att){
        String type;
        Session session = sessionFactory.openSession();
        Integer idtype;
        try {
            Query query = session.createQuery("select idAttType from DBAttribute where name='"+att+"'");
            idtype= (Integer) query.getSingleResult();
            session.close();
        }catch (NoResultException e){
            return null;
        }
        Session session2 = sessionFactory.openSession();
        Query query2 = session2.createQuery("select type from DBAtttype where idAttType='"+idtype+"'");
        type = (String) query2.getSingleResult();
        session2.close();
        return type;

    }

    public String getValsByAtt(String att){
        String vals;
        Session session = sessionFactory.openSession();
        try {
            Query query = session.createQuery("select vals from DBAttribute where name='"+att+"'");
            vals= (String) query.getSingleResult();
            session.close();
        }catch (NoResultException e){
            return null;
        }
        return vals;
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
        int idAg=getIdAgent(agent);
        int idAtt=getIdAttribute(att);
        Session session2 = sessionFactory.openSession();
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

    public void insertAttribute(String name,String idtype){
        int idty=getIdAttType(idtype);
        Session session2 = sessionFactory.openSession();
        try {
            Query query = session2.createQuery("from DBAttribute where name='"+name+"' and idAttType='"+idty+"'");
            DBAttribute attribute1 = (DBAttribute) query.getSingleResult();
            session2.close();
        }catch (NoResultException e){
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            DBAttribute attribute = new DBAttribute();
            attribute.setName(name);
            attribute.setIdAttType(idty);
            session.save(attribute);
            session.getTransaction().commit();
        }
    }

    public void insertAttributeWithOptions(String name,String idtype,String value){
        int idty=getIdAttType(idtype);
        Session session2 = sessionFactory.openSession();
        try {
            Query query = session2.createQuery("from DBAttribute where name='"+name+"' and idAttType='"+idty+"'");
            DBAttribute attribute1 = (DBAttribute) query.getSingleResult();
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

    //AGENT
    public String getAgClass(String type) {
        String agClass;
        Session session = sessionFactory.openSession();
        try{
            Query query = session.createQuery("select agClass from DBAgent where type='"+type+"'");
            agClass = (String) query.getSingleResult();
            session.close();
        }catch (NoResultException e){
            return null;
        }
        return agClass;
    }

    public ArrayList<String> getAgentTypes(String ag) {
        ArrayList<String> agClass;
        Session session = sessionFactory.openSession();
        try{
            Query query = session.createQuery("select type from DBAgent where type like '%"+ag+"%'");
            agClass = (ArrayList<String>) query.getResultList();
            session.close();

        }catch (NoResultException e){
            return null;
        }

        return agClass;
    }

    public Integer getIdAgent(String type) {
        Integer id;
        Session session = sessionFactory.openSession();
        try {
            Query query = session.createQuery("select idAgent from DBAgent where type='"+type+"'");
            id= (Integer) query.getSingleResult();
            session.close();
        }catch (NoResultException e){
            return null;
        }
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

    //TYPE

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
