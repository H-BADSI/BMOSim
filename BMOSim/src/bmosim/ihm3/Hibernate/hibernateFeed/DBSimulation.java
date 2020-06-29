package bmosim.ihm3.Hibernate.hibernateFeed;

import javax.persistence.*;

@Entity
@Table( name = "Simulation",uniqueConstraints = {
        @UniqueConstraint(name = "name",
        columnNames = {"name"})
})
public class DBSimulation {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idSimulation")
    private int idSimulation;
    @Column(name = "name")
	private String name;

    public DBSimulation clone(){
        DBSimulation twin = new DBSimulation();
        twin.setIdSimulation(idSimulation);
        return twin;
    }

    public int getIdSimulation() {
        return idSimulation;
    }

    public void setIdSimulation(int idSimulation) {
        this.idSimulation = idSimulation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
