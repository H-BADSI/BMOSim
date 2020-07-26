package bmosim.ihm3.Hibernate.hibernateAgent;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table( name = "attribute",uniqueConstraints = {
        @UniqueConstraint(name = "name",
                columnNames = {"name"})
})
public class DBAttribute implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idAttribute")
    private int idAttribute;

    @Column(name = "name")
    private String name;

    @Column(name = "vals")
    private String vals;

    @Column(name="idAttType")
    private int idAttType;

    public int getIdAttType() {
        return idAttType;
    }

    public void setIdAttType(int idAttType) {
        this.idAttType = idAttType;
    }

    public int getIdAttribute() {
        return idAttribute;
    }

    public void setIdAttribute(int idAttribute) {
        this.idAttribute = idAttribute;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVals() {
        return vals;
    }

    public void setVals(String values) {
        this.vals = values;
    }

//    public DBAtttype getIdAttType() {
//        return idAttType;
//    }
//
//    public void setIdAttType(DBAtttype idAttType) {
//        this.idAttType = idAttType;
//    }
}
