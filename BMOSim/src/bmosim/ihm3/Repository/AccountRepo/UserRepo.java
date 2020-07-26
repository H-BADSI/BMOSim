package bmosim.ihm3.Repository.AccountRepo;

import bmosim.ihm3.Hibernate.hibernateAccount.DBUser;
import bmosim.ihm3.Repository.Repo;
import bmosim.ihm3.controller.Enceyption;
import bmosim.ihm3.controller.funct;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.ArrayList;

public class UserRepo extends Repo {

    Enceyption enc;

    {
        try {
            enc = new Enceyption();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public UserRepo(){
        super("bmosim/ihm3/Hibernate/hibernateAccount/hiberAccount.cfg.xml");
        c.setProperty("hibernate.connection.url","jdbc:mysql://localhost:3306/acc");
        c.setProperty("hibernate.connection.username","root");
        c.setProperty("hibernate.connection.password","emplacement44");
        sessionFactory= ((Configuration) o).buildSessionFactory();
    }

    public void insertUser(DBUser u){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        u.setPassword(enc.encrypt(u.getPassword()));
        session.save(u);
        session.getTransaction().commit();
    }

    public boolean userExist(DBUser u){
        Session session = sessionFactory.openSession();
        DBUser user=null;
        try{
            Query query = session.createQuery("from DBUser where username='"+u.getUsername()+"'");
            user= (DBUser) query.getSingleResult();
        }catch (NoResultException e){
            session.close();
            return false;
        }
        session.close();
        return user!=null;
    }
    public boolean userExistPass(DBUser u){
        Session session = sessionFactory.openSession();
        DBUser user;
        u.setPassword(enc.encrypt(u.getPassword()));
        try{
            Query query = session.createQuery("from DBUser where username='"+u.getUsername()+"' and password='"+u.getPassword()+"'");
            user= (DBUser) query.getSingleResult();
        }catch (NoResultException e){
            session.close();
            return false;
        }
        session.close();
        return user!=null;
    }
    public String isAdmin(DBUser u){
        Session session = sessionFactory.openSession();
        DBUser user;
        u.setPassword(enc.encrypt(u.getPassword()));
        try{
            Query query = session.createQuery("from DBUser where username='"+u.getUsername()+"' and password='"+u.getPassword()+"'");
            user= (DBUser) query.getSingleResult();
        }catch (NoResultException e){
            session.close();
            return null;
        }
        return user.getAdmin();

    }

    public ArrayList<DBUser> getAllUsers(DBUser u){
        ArrayList<DBUser> users;
        Session session = sessionFactory.openSession();
        Query query;
        try {
            query = session.createQuery("from DBUser");

            users= (ArrayList<DBUser>) query.getResultList();
            session.close();
        }catch (NoResultException e){
            return null;
        }
        return users;
    }

    public DBUser getUserByName(String username) {
        Session session = sessionFactory.openSession();
        DBUser user;
        Query query;
        try {
            query = session.createQuery("from DBUser where username='"+username+"'");

            user= (DBUser)query.getSingleResult();
            session.close();
        }catch (NoResultException e){
            return null;
        }
        user.setPassword(enc.decrypt(user.getPassword()));
        return user;
    }

    public void updateLoginUser(DBUser u){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String s = "update DBUser  set lastLogin='"+u.getLastLogin()+"' where iduser="+u.getIduser();
        Query query =session.createQuery(s);
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
    }
    public void updateLogoutUser(DBUser u){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String s = "update DBUser  set lastLogout='"+u.getLastLogout()+"' where iduser="+u.getIduser();
        Query query =session.createQuery(s);
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    public void updateAdminUser(DBUser u){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String s = "update DBUser  set adm='"+u.getAdmin()+"' where iduser="+u.getIduser();
        Query query =session.createQuery(s);
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    public void updatePassUser(DBUser u){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        u.setPassword(enc.encrypt(u.getPassword()));
        String s = "update DBUser  set password='"+u.getPassword()+"' where iduser="+u.getIduser();
        Query query =session.createQuery(s);
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    public void deleteUser(DBUser user){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(user);
        session.getTransaction().commit();
        session.close();
    }
}
