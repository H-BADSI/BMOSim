package bmosim.ihm3.controller;

import java.util.ArrayList;

public class agXmlStruct {
    String classe;
    String name;
    String nbInst;
    String gui;
    String logLevel;
    ArrayList<atXmlStruct> Attributes = new ArrayList<>();

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNbInst() {
        return nbInst;
    }

    public void setNbInst(String nbInst) {
        this.nbInst = nbInst;
    }

    public String getGui() {
        return gui;
    }

    public void setGui(String gui) {
        this.gui = gui;
    }

    public String getLogLevel() {
        return logLevel;
    }

    public void setLogLevel(String logLevel) {
        this.logLevel = logLevel;
    }

    public ArrayList<atXmlStruct> getAttributes() {
        return Attributes;
    }

    public void setAttributes(ArrayList<atXmlStruct> attributes) {
        Attributes = attributes;
    }
}
