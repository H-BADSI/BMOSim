package bmosim.ihm3.controller;

public class DBconfStruct {

    String add;
    String port;
    String name;
    String user;
    String pass;

    public DBconfStruct(){

    }

    public DBconfStruct(String add, String port, String name, String user, String pass) {
        this.add = add;
        this.port = port;
        this.name = name;
        this.user = user;
        this.pass = pass;
    }

    public String getAdd() {
        return add;
    }

    public void setAdd(String add) {
        this.add = add;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
