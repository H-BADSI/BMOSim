package bmosim.ihm3.Hibernate.hibernateAgent;


import javax.persistence.*;

@Entity
@Table( name = "agent",uniqueConstraints = {
        @UniqueConstraint(name = "type",
                columnNames = {"type"})
})
public class DBAgent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idAgent")
    private int idAgent;

    @Column(name = "type")
    private String type;

    @Column(name = "agClass")
    private String agClass;

    public int getIdAgent() {
        return idAgent;
    }

    public void setIdAgent(int idAgent) {
        this.idAgent = idAgent;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAgClass() {
        return agClass;
    }

    public void setAgClass(String agClass) {
        this.agClass = agClass;
    }
}
