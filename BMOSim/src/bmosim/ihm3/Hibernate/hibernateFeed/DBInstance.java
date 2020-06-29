package bmosim.ihm3.Hibernate.hibernateFeed;

import javax.persistence.*;

@Entity
@Table(name = "Instance")
public class DBInstance{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idInstance")
    private int idInstance;
    @Column(name = "ord")
    private int ord;
    @OneToOne
    @JoinColumn(name = "idSimulation")
    private DBSimulation idSimulation;

    public DBInstance clone(){
        DBInstance twin = new DBInstance();
        twin.setIdInstance(idInstance);
        twin.setOrd(ord);
        twin.setIdSimulation(idSimulation);
        return twin;
    }

    public int getIdInstance() {
        return idInstance;
    }

    public void setIdInstance(int idInstance) {
        this.idInstance = idInstance;
    }

    public int getOrd() {
        return ord;
    }

    public void setOrd(int ord) {
        this.ord = ord;
    }

    public DBSimulation getIdSimulation() {
        return idSimulation;
    }

    public void setIdSimulation(DBSimulation idSimulation) {
        this.idSimulation = idSimulation;
    }
}