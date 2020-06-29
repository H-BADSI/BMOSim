package bmosim.ihm3.Hibernate.hibernateAgent;


import javax.persistence.*;

@Entity
@Table( name = "atttype",uniqueConstraints = {
        @UniqueConstraint(name = "type",
                columnNames = {"type"})
})
public class DBAtttype {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idAttType")
    private int idAttType;

    @Column(name = "type")
    private String type;

    public int getIdAttType() {
        return idAttType;
    }

    public void setIdAttType(int idAttType) {
        this.idAttType = idAttType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
