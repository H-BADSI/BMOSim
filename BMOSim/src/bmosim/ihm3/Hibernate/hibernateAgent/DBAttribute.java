package bmosim.ihm3.Hibernate.hibernateAgent;


import javax.persistence.*;

@Entity
@Table( name = "attribute",uniqueConstraints = {
        @UniqueConstraint(name = "name",
                columnNames = {"name"})
})
public class DBAttribute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idAttribute")
    private int idAttribute;

    @Column(name = "name")
    private String name;

    @Column(name = "values")
    private String values;

    @Column(name="idAttType")
    private int idAttType;

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

    public String getValues() {
        return values;
    }

    public void setValues(String values) {
        this.values = values;
    }

    public int getIdAttType() {
        return idAttType;
    }

    public void setIdAttType(int idAttType) {
        this.idAttType = idAttType;
    }
//    public DBAtttype getIdAttType() {
//        return idAttType;
//    }
//
//    public void setIdAttType(DBAtttype idAttType) {
//        this.idAttType = idAttType;
//    }
}
