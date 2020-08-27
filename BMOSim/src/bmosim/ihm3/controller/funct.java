package bmosim.ihm3.controller;

import bmosim.ihm3.Main;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.print.Doc;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class funct {

    public static Document getConfDoc(){
        File file = new File("src/bmosim/ihm3/conf/conf.xml");

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = null;
        try {
            db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            return doc;
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    static String getXmlDirectory() {
        Document doc = getConfDoc();
        Node xmldest = doc.getElementsByTagName("xmlDest").item(0);
        return xmldest.getTextContent();
    }

    static String getClassDirectory()  {
        Document doc = getConfDoc();
        Node agDest= doc.getElementsByTagName("agDest").item(0);
        return agDest.getTextContent();
    }

    static void writeXML(Document doc,String dir)  {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = null;
        try {
            transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(dir));
            transformer.transform(source, result);
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }

    }

    static ArrayList<String> getFilesNames(String s){
        ArrayList<String> as = new ArrayList<>();
        File folder = new File(s);
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i <listOfFiles.length; i++) {
            as.add(listOfFiles[i].getName());
        }
        return as;
    }

    public static boolean containsIgnoreCase(String str, String subString) {
        return str.toLowerCase().contains(subString.toLowerCase());
    }

    static ArrayList<String> getXmlFilesNamesFilter(String s) {

        ArrayList<String> as = new ArrayList<>();

        File folder = new File(funct.getXmlDirectory());
        File[] listOfFiles = folder.listFiles();

        if(s==null){
            for (int i = 0; i <listOfFiles.length; i++) {
                if(containsIgnoreCase(listOfFiles[i].getName(),".xml"))
                    as.add(listOfFiles[i].getName());
            }
        }else{
            for (int i = 0; i <listOfFiles.length; i++) {
                if(containsIgnoreCase(listOfFiles[i].getName(),s) && containsIgnoreCase(listOfFiles[i].getName(),".xml")){
                    as.add(listOfFiles[i].getName());
                }
            }
        }
        return as;
    }

    static ArrayList<String> getClassFilesNamesFilter(String s) {

        ArrayList<String> as = new ArrayList<>();

        File folder = new File(funct.getClassDirectory());
        File[] listOfFiles = folder.listFiles();

        if(s==""){
            for (int i = 0; i <listOfFiles.length; i++) {
                    as.add(listOfFiles[i].getName().replace(".java",""));
            }
        }else{
            for (int i = 0; i <listOfFiles.length; i++) {
                if(containsIgnoreCase(listOfFiles[i].getName(),s)){
                    as.add(listOfFiles[i].getName().replace(".java",""));
                }
            }
        }
        return as;
    }

    public static void unfirstTime(){
        Document doc = getConfDoc();
        Node n = doc.getElementsByTagName("firstTime").item(0);
        if(n!=null){
            n.getParentNode().removeChild(n);
            writeXML(doc,"src/bmosim/ihm3/conf/conf.xml");
        }
    }

    public static boolean firstTime(){
        Document doc = getConfDoc();
        if(doc.getElementsByTagName("firstTime").item(0)==null){
            Element e = doc.createElement("firstTime");

            doc.getFirstChild().appendChild(e);
            writeXML(doc,"src/bmosim/ihm3/conf/conf.xml");
            return true;
        }
        return false;
    }

    public static void setAppType(boolean b){
        Document doc = getConfDoc();
        Node e =doc.getElementsByTagName("firstTime").item(0);
        if(doc.getElementsByTagName("firstTime").item(0)!=null){
            e.setTextContent(b?"true":"false");
            writeXML(doc,"src/bmosim/ihm3/conf/conf.xml");
        }
    }

    public static boolean getAppType(){
        Document doc = getConfDoc();
        Node e =doc.getElementsByTagName("firstTime").item(0);
        if(doc.getElementsByTagName("firstTime").item(0)!=null){
            switch (e.getTextContent()){
                case "true":return true;
                case "false":return false;
                default:return false;
            }
        }
        return true;
    }

    public static ArrayList<Integer> getSimSet(){
        Document doc = getConfDoc();
        ArrayList<Integer> simSet= new ArrayList<>();
        if(doc.getElementsByTagName("SimulationSet")!=null){
            simSet.add(Integer.valueOf(doc.getElementsByTagName("Duration").item(0).getTextContent()));
            simSet.add(Integer.valueOf(doc.getElementsByTagName("PayCycle").item(0).getTextContent()));
            simSet.add(Integer.valueOf(doc.getElementsByTagName("StepDelay").item(0).getTextContent()));
        }
        return simSet;

    }

    public static void setDarkLight(Boolean b){
        Main.dark_light=b;
        Document doc = getConfDoc();
        Node DarkLight;
        if(doc.getElementsByTagName("DarkLight").item(0)==null){
            DarkLight = doc.createElement("DarkLight");
        }else {
            DarkLight=doc.getElementsByTagName("DarkLight").item(0);
        }
        DarkLight.setTextContent(b.toString());

        Node root = doc.getElementsByTagName("root").item(0);

        root.appendChild(DarkLight);

        funct.writeXML(doc,"src/bmosim/ihm3/conf/conf.xml");
    }

    public static boolean getDarkLight(){
        boolean b;
        Document doc = getConfDoc();
        if(doc.getElementsByTagName("DarkLight").item(0)==null){
            setDarkLight(true);
        }
        b=Boolean.valueOf(doc.getElementsByTagName("DarkLight").item(0).getTextContent());

        return b;
    }

    public static void setImage(ImageView im, String path) {
        File file = new File(path);
        im.setImage(new Image(file.toURI().toString()));
    }

    public static void deleteAgentByCategory(String path, String agCat) {
        String newPath = getXmlDirectory().replace("\\","/")+"/"+path+".xml";
        File file = new File(newPath);

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = null;
        try {
            db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            NodeList list = doc.getElementsByTagName("Agent");
            for (int i = 0; i < list.getLength(); i++) {
                Node ag = doc.getElementsByTagName("Agent").item(i);
                if(ag.getAttributes().getNamedItem("name").getTextContent().equals(agCat)){
                   ag.getParentNode().removeChild(ag);
                }
            }
            writeXML(doc,newPath);

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static ArrayList<String> getAgCats(String path)  {

        ArrayList<String> agCats = new ArrayList<>();
        try {
            String newPath = getXmlDirectory().replace("\\","/")+"/"+path+".xml";
            File file = new File(newPath);

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = null;

            db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            NodeList list = doc.getElementsByTagName("Agent");
            for (int i = 0; i < list.getLength(); i++) {
                Node ag = doc.getElementsByTagName("Agent").item(i);
                agCats.add(ag.getAttributes().getNamedItem("name").getTextContent());
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return agCats;

    }

    public static boolean categoryExists(String cat,String path){
        boolean b = false;
        try {
            String newPath = getXmlDirectory().replace("\\","/")+"/"+path+".xml";
            File file = new File(newPath);

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = null;

            db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            NodeList list = doc.getElementsByTagName("Agent");
            for (int i = 0; i < list.getLength(); i++) {
                Node ag = doc.getElementsByTagName("Agent").item(i);
                if(ag.getAttributes().getNamedItem("name").getTextContent().equalsIgnoreCase(cat)) b=true;
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return b;
    }

    public static void setDBSet(ArrayList<DBconfStruct> array){
        Document doc =getConfDoc();
        Node root=doc.getElementsByTagName("root").item(0);
        Node db;
        Node db1,db2,db3,db4;
        Node name1,name2,pass1,pass2,address1,address2,port1,port2,user1,user2;
        Node name3,name4,pass3,pass4,address3,address4,port3,port4,user3,user4;

        {
            if (doc.getElementsByTagName("db").item(0) == null) {
                db = doc.createElement("db");
                root.appendChild(db);
            } else {
                db = doc.getElementsByTagName("db").item(0);
            }

            if (doc.getElementsByTagName("db1").item(0) == null) {
                db1 = doc.createElement("db1");
                db.appendChild(db1);
            } else {
                db1 = doc.getElementsByTagName("db1").item(0);
            }

            if (doc.getElementsByTagName("db2").item(0) == null) {
                db2 = doc.createElement("db2");
                db.appendChild(db2);
            } else {
                db2 = doc.getElementsByTagName("db2").item(0);
            }

            if (doc.getElementsByTagName("db3").item(0) == null) {
                db3 = doc.createElement("db3");
                db.appendChild(db3);
            } else {
                db3 = doc.getElementsByTagName("db3").item(0);
            }

            if (doc.getElementsByTagName("db4").item(0) == null) {
                db4 = doc.createElement("db4");
                db.appendChild(db4);
            } else {
                db4 = doc.getElementsByTagName("db4").item(0);
            }
        }

        Node e1 = doc.getElementsByTagName("name").item(0);
        if (e1 != null) {
            e1.getParentNode().removeChild(e1);
        }
        Node e2 = doc.getElementsByTagName("pass").item(0);
        if (e2 != null) {
            e2.getParentNode().removeChild(e2);
        }
        Node e3 = doc.getElementsByTagName("address").item(0);
        if (e3 != null) {
            e3.getParentNode().removeChild(e3);
        }
        Node e4 = doc.getElementsByTagName("port").item(0);
        if (e4 != null) {
            e4.getParentNode().removeChild(e4);
        }
        Node e5 = doc.getElementsByTagName("username").item(0);
        if (e5 != null) {
            e5.getParentNode().removeChild(e5);
        }

        name1 = doc.createElement("name");
        name1.setTextContent(array.get(0).name);
        pass1 = doc.createElement("pass");
        pass1.setTextContent(array.get(0).pass);
        address1 = doc.createElement("address");
        address1.setTextContent(array.get(0).add);
        port1 = doc.createElement("port");
        port1.setTextContent(array.get(0).port);
        user1 = doc.createElement("username");
        user1.setTextContent(array.get(0).user);
        db1.appendChild(address1);
        db1.appendChild(port1);
        db1.appendChild(name1);
        db1.appendChild(user1);
        db1.appendChild(pass1);

        Node e6 = doc.getElementsByTagName("name").item(1);
        if (e6 != null) {
            e6.getParentNode().removeChild(e6);
        }
        Node e7 = doc.getElementsByTagName("pass").item(1);
        if (e7 != null) {
            e7.getParentNode().removeChild(e7);
        }
        Node e8 = doc.getElementsByTagName("address").item(1);
        if (e8 != null) {
            e8.getParentNode().removeChild(e8);
        }
        Node e9 = doc.getElementsByTagName("port").item(1);
        if (e9 != null) {
            e9.getParentNode().removeChild(e9);
        }
        Node e10 = doc.getElementsByTagName("username").item(1);
        if (e10 != null) {
            e10.getParentNode().removeChild(e10);
        }

        name2 = doc.createElement("name");
        name2.setTextContent(array.get(1).name);
        pass2 = doc.createElement("pass");
        pass2.setTextContent(array.get(1).pass);
        address2 = doc.createElement("address");
        address2.setTextContent(array.get(1).add);
        port2 = doc.createElement("port");
        port2.setTextContent(array.get(1).port);
        user2 = doc.createElement("username");
        user2.setTextContent(array.get(1).user);
        db2.appendChild(address2);
        db2.appendChild(port2);
        db2.appendChild(name2);
        db2.appendChild(user2);
        db2.appendChild(pass2);

        Node e11 = doc.getElementsByTagName("name").item(2);
        if (e11 != null) {
            e11.getParentNode().removeChild(e11);
        }
        Node e12 = doc.getElementsByTagName("pass").item(2);
        if (e12 != null) {
            e12.getParentNode().removeChild(e12);
        }
        Node e13 = doc.getElementsByTagName("address").item(2);
        if (e13 != null) {
            e13.getParentNode().removeChild(e13);
        }
        Node e14 = doc.getElementsByTagName("port").item(2);
        if (e14 != null) {
            e14.getParentNode().removeChild(e14);
        }
        Node e15 = doc.getElementsByTagName("username").item(2);
        if (e15 != null) {
            e15.getParentNode().removeChild(e15);
        }

        name3 = doc.createElement("name");
        name3.setTextContent(array.get(2).name);
        pass3 = doc.createElement("pass");
        pass3.setTextContent(array.get(2).pass);
        address3 = doc.createElement("address");
        address3.setTextContent(array.get(2).add);
        port3 = doc.createElement("port");
        port3.setTextContent(array.get(2).port);
        user3 = doc.createElement("username");
        user3.setTextContent(array.get(2).user);
        db3.appendChild(address3);
        db3.appendChild(port3);
        db3.appendChild(name3);
        db3.appendChild(user3);
        db3.appendChild(pass3);

        Node e16 = doc.getElementsByTagName("name").item(3);
        if (e16 != null) {
            e16.getParentNode().removeChild(e16);
        }
        Node e17 = doc.getElementsByTagName("pass").item(3);
        if (e17 != null) {
            e17.getParentNode().removeChild(e17);
        }
        Node e18 = doc.getElementsByTagName("address").item(3);
        if (e18 != null) {
            e18.getParentNode().removeChild(e18);
        }
        Node e19 = doc.getElementsByTagName("port").item(3);
        if (e19 != null) {
            e19.getParentNode().removeChild(e19);
        }
        Node e20 = doc.getElementsByTagName("username").item(3);
        if (e20 != null) {
            e20.getParentNode().removeChild(e20);
        }

        name4 = doc.createElement("name");
        name4.setTextContent(array.get(3).name);
        pass4 = doc.createElement("pass");
        pass4.setTextContent(array.get(3).pass);
        address4 = doc.createElement("address");
        address4.setTextContent(array.get(3).add);
        port4 = doc.createElement("port");
        port4.setTextContent(array.get(3).port);
        user4 = doc.createElement("username");
        user4.setTextContent(array.get(3).user);
        db4.appendChild(address4);
        db4.appendChild(port4);
        db4.appendChild(name4);
        db4.appendChild(user4);
        db4.appendChild(pass4);

        funct.writeXML(doc,"src/bmosim/ihm3/conf/conf.xml");
    }

    public static ArrayList<DBconfStruct> getDBSet(){
        ArrayList<DBconfStruct> as = new ArrayList<>();
        Document doc = getConfDoc();
        DBconfStruct conf1=new DBconfStruct(),conf2=new DBconfStruct(),conf3=new DBconfStruct(),conf4=new DBconfStruct();
        Node n= doc.getElementsByTagName("name").item(0);
        if (n == null) {
            conf1.setName("");
        } else {
            conf1.setName(n.getTextContent());
        }
        n= doc.getElementsByTagName("pass").item(0);
        try {
            if (n == null) {
                conf1.setPass("");
            } else {
                conf1.setPass(new Enceyption().decrypt(n.getTextContent()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        n= doc.getElementsByTagName("address").item(0);
        if (n == null) {
            conf1.setAdd("");
        } else {
            conf1.setAdd(n.getTextContent());
        }
        n= doc.getElementsByTagName("port").item(0);
        if (n == null) {
            conf1.setPort("");
        } else {
            conf1.setPort(n.getTextContent());
        }
        n= doc.getElementsByTagName("username").item(0);
        if (n == null) {
            conf1.setUser("");
        } else {
            conf1.setUser(n.getTextContent());
        }
        as.add(conf1);

        n= doc.getElementsByTagName("name").item(1);
        if (n == null) {
            conf2.setName("");
        } else {
            conf2.setName(n.getTextContent());
        }
        n= doc.getElementsByTagName("pass").item(1);
        try {
            if (n == null) {
                conf2.setPass("");
            } else {
                conf2.setPass(new Enceyption().decrypt(n.getTextContent()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        n= doc.getElementsByTagName("address").item(1);
        if (n == null) {
            conf2.setAdd("");
        } else {
            conf2.setAdd(n.getTextContent());
        }
        n= doc.getElementsByTagName("port").item(1);
        if (n == null) {
            conf2.setPort("");
        } else {
            conf2.setPort(n.getTextContent());
        }
        n= doc.getElementsByTagName("username").item(1);
        if (n == null) {
            conf2.setUser("");
        } else {
            conf2.setUser(n.getTextContent());
        }
        as.add(conf2);

        n= doc.getElementsByTagName("name").item(2);
        if (n == null) {
            conf3.setName("");
        } else {
            conf3.setName(n.getTextContent());
        }
        n= doc.getElementsByTagName("pass").item(2);
        try {
            if (n == null) {
                conf3.setPass("");
            } else {
                conf3.setPass(new Enceyption().decrypt(n.getTextContent()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        n= doc.getElementsByTagName("address").item(2);
        if (n == null) {
            conf3.setAdd("");
        } else {
            conf3.setAdd(n.getTextContent());
        }
        n= doc.getElementsByTagName("port").item(2);
        if (n == null) {
            conf3.setPort("");
        } else {
            conf3.setPort(n.getTextContent());
        }
        n= doc.getElementsByTagName("username").item(2);
        if (n == null) {
            conf3.setUser("");
        } else {
            conf3.setUser(n.getTextContent());
        }
        as.add(conf3);

        n= doc.getElementsByTagName("name").item(3);
        if (n == null) {
            conf4.setName("");
        } else {
            conf4.setName(n.getTextContent());
        }
        n= doc.getElementsByTagName("pass").item(3);
        try {
            if (n == null) {
                conf4.setPass("");
            } else {
                conf4.setPass(new Enceyption().decrypt(n.getTextContent()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        n= doc.getElementsByTagName("address").item(3);
        if (n == null) {
            conf4.setAdd("");
        } else {
            conf4.setAdd(n.getTextContent());
        }
        n= doc.getElementsByTagName("port").item(3);
        if (n == null) {
            conf4.setPort("");
        } else {
            conf4.setPort(n.getTextContent());
        }
        n= doc.getElementsByTagName("username").item(3);
        if (n == null) {
            conf4.setUser("");
        } else {
            conf4.setUser(n.getTextContent());
        }
        as.add(conf4);

        return as;
    }

    public static agXmlStruct getAgentByCategory(String path,String cat){
        agXmlStruct agXmlStruct = new agXmlStruct();

        if(!containsIgnoreCase(path,".xml")) path+=".xml";

        try {
            String newPath = getXmlDirectory().replace("\\","/")+"/"+path;
            File file = new File(newPath);

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = null;

            db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            NodeList list = doc.getElementsByTagName("Agent");
            for (int i = 0; i < list.getLength(); i++) {
                Node ag = doc.getElementsByTagName("Agent").item(i);
                if(ag.getAttributes().getNamedItem("name").getTextContent().equals(cat)){
                    agXmlStruct.classe=ag.getAttributes().getNamedItem("class").getTextContent();
                    agXmlStruct.name=ag.getAttributes().getNamedItem("name").getTextContent();
                    agXmlStruct.gui=ag.getAttributes().getNamedItem("GUI").getTextContent();
                    agXmlStruct.nbInst=ag.getAttributes().getNamedItem("nbOfInstances").getTextContent();
                    agXmlStruct.logLevel=ag.getAttributes().getNamedItem("logLevel").getTextContent();
                    Node atts =ag.getLastChild();
                    for (Node node : NamedNodeMapIterable.of(atts.getAttributes())) {
                        agXmlStruct.getAttributes().add(new atXmlStruct(node.getNodeName(),node.getNodeValue()));
                    }
                    return agXmlStruct;
                }
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return agXmlStruct;

    }




}
