package bmosim.ihm3.Hibernate.hibernateAccount;

import bmosim.ihm3.Hibernate.hibernateAccount.DBUser;
import bmosim.ihm3.controller.funct;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.ArrayList;

public class UserRepo {

    SessionFactory sessionFactory;

    public UserRepo() {
        Configuration c = new Configuration().addResource("bmosim/ihm3/Hibernate/hibernateAccount/hiberAccount.cfg.xml");
        c.setProperty("connection.url","jdbc:mysql://localhost:3306/acc");
        c.setProperty("connection.username","root");
        c.setProperty("connection.password","emplacement44");
        sessionFactory=c.configure().buildSessionFactory();
    }

    private String username;
    private String password;
    private Boolean admin;

    public UserRepo(String username) {
        Configuration c = new Configuration().addResource("bmosim/ihm3/Hibernate/hibernateAccount/hiberAccount.cfg.xml");
        c.setProperty("connection.url","jdbc:mysql://localhost:3306/acc");
        c.setProperty("connection.username","root");
        c.setProperty("connection.password","emplacement44");
        System.out.println(c.getProperties());
        sessionFactory=c.configure("bmosim/ihm3/Hibernate/hibernateAccount/hiberAccount.cfg.xml").buildSessionFactory();
        this.username = username;
    }

    public UserRepo(String username, String password) {
        Configuration c = new Configuration().addResource("bmosim/ihm3/Hibernate/hibernateAccount/hiberAccount.cfg.xml");
        c.setProperty("connection.url","jdbc:mysql://localhost:3306/acc");
        c.setProperty("connection.username","root");
        c.setProperty("connection.password","emplacement44");
        System.out.println("+++"+c.getProperties());

        sessionFactory=c.configure("bmosim/ihm3/Hibernate/hibernateAccount/hiberAccount.cfg.xml").buildSessionFactory();
        this.username = username;
        this.password = password;
    }

    public UserRepo(String username, String password, Boolean admin) {
        Configuration c = new Configuration().addResource("bmosim/ihm3/Hibernate/hibernateAccount/hiberAccount.cfg.xml");
        c.setProperty("connection.url","jdbc:mysql://localhost:3306/acc");
        c.setProperty("connection.username","root");
        c.setProperty("connection.password","emplacement44");
        sessionFactory=c.configure("bmosim/ihm3/Hibernate/hibernateAccount/hiberAccount.cfg.xml").buildSessionFactory();
        this.username = username;
        this.password = password;
        this.admin = admin;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void insertUser(){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        DBUser user = new DBUser();
        user.setUsername(this.username);
        user.setPassword(this.password);
        user.setAdmin(this.admin);
        session.save(user);
        session.getTransaction().commit();
    }

    public boolean userExist(){
        Session session = sessionFactory.openSession();
        DBUser user=null;
        try{
            Query query = session.createQuery("from DBUser where username='"+this.username+"'");
            user= (DBUser) query.getSingleResult();
        }catch (NoResultException e){
            session.close();
            return false;
        }
        session.close();
        return user!=null;
    }
    public boolean userExistPass(){
        Session session = sessionFactory.openSession();
        DBUser user;
        try{
            Query query = session.createQuery("from DBUser where username='"+this.username+"' and password='"+this.password+"'");
            user= (DBUser) query.getSingleResult();
        }catch (NoResultException e){
            session.close();
            return false;
        }
        session.close();
        return user!=null;
    }
    public boolean isAdmin(){
        Session session = sessionFactory.openSession();
        DBUser user;
        try{
            Query query = session.createQuery("from DBUser where username='"+this.username+"' and password='"+this.password+"'");
            user= (DBUser) query.getSingleResult();
        }catch (NoResultException e){
            session.close();
            return false;
        }
        return user.isAdmin();

    }
}
