package bmosim.ihm3.Hibernate.hibernateAccount;

//import bmosim.ihm3.Repository.AccountRepo.appUser;

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
    private int iduser;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "adm")
    private String admin;

    @Column(name = "lastLogin")
    private String lastLogin;

    @Column(name = "lastLogout")
    private String lastLogout;

    public DBUser() {
    }

    public DBUser(String username) {
        this.username = username;
    }

    public DBUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public DBUser(String username, String password, String admin) {
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

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public String getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(String lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getLastLogout() {
        return lastLogout;
    }

    public void setLastLogout(String lastLogout) {
        this.lastLogout = lastLogout;
    }
}


