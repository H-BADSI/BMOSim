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
        type=t;
    }

    @Override
    public String toString() {
        return name+" "+type+" "+val;
    }
}
