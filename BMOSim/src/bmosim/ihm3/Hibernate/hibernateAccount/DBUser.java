package bmosim.ihm3.Hibernate.hibernateAccount;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table( name = "appuser")
public class DBUser implements Serializable {
    @Id
    @Column(name = "iduser")
    private int idAgent;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "adm")
    private boolean admin;


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

    public int getIdAgent() {
        return idAgent;
    }

    public void setIdAgent(int idAgent) {
        this.idAgent = idAgent;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
}


