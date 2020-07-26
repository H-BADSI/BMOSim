package bmosim.ihm3.model;

public class type{
    public String name;
    public String type;
    public String val;

    public type(String n,String t,String v){
        name=n;
        type=t;
        val=v;
    }
    public type(String n,String t){
        name=n;
        val=t;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return name+" "+type+" "+val;
    }
}
