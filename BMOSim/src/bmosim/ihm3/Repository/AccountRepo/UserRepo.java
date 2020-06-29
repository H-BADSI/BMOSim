package bmosim.ihm3.Repository.AccountRepo;

import bmosim.ihm3.Hibernate.hibernateAccount.DBUser;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.Query;

public class UserRepo {

    SessionFactory sessionFactory = new Configuration().configure("bmosim/ihm3/Hibernate/hibernateAccount/hiberAccount.cfg.xml").buildSessionFactory();

    private String username;
    private String password;
    private Boolean admin;

    public UserRepo() {
    }

    public UserRepo(String username) {
        this.username = username;
    }

    public UserRepo(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public UserRepo(String username, String password, Boolean admin) {
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
        Query query = session.createQuery("from DBUser where username='"+this.username+"'");
        DBUser user= (DBUser) query.getSingleResult();
        session.close();
        return user!=null;
    }
    public boolean userExistPass(){
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from DBUser where username='"+this.username+"' and password='"+this.password+"'");
        DBUser user= (DBUser) query.getSingleResult();
        session.close();
        return user!=null;
    }
    public boolean isAdmin(){
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from DBUser where username='"+this.username+"' and password='"+this.password+"'");
        DBUser user= (DBUser) query.getSingleResult();
        session.close();
        return user.isAdmin();
    }
}
