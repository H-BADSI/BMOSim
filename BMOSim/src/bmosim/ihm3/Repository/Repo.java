package bmosim.ihm3.Repository;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Repo {

    public Object o;
    public Configuration c;
    public SessionFactory sessionFactory;

    public Repo(String path){
        c = new Configuration();
        o=c.configure(path);
    }
}
