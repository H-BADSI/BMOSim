package bmosim.ihm3.controller;

import bmosim.ihm3.Main;
import com.sun.xml.xsom.impl.scd.Iterators;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.imageio.ImageIO;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.awt.image.BufferedImage;
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

    public static void deleteAgentCategory(String path, String agCat) {
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
                if(ag.getAttributes().item(1).getTextContent().equals(agCat)){
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
                agCats.add(ag.getAttributes().item(1).getTextContent());
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

    public static void setDBSet(String n1,String p1,String n2,String p2,String a1,String a2,
                                String pr1,String pr2,String u1,String u2){
        Document doc =getConfDoc();
        Node root=doc.getElementsByTagName("root").item(0);
        Node db;
        Node db1,db2;
        Node name1,name2,pass1,pass2,address1,address2,port1,port2,user1,user2;

        if(doc.getElementsByTagName("db").item(0)==null){
            db = doc.createElement("db");
            root.appendChild(db);
        }else {
            db=doc.getElementsByTagName("db").item(0);
        }
        if(doc.getElementsByTagName("db1").item(0)==null){
            db1 = doc.createElement("db1");
            db.appendChild(db1);
        }else{
            db1=doc.getElementsByTagName("db1").item(0);
        }
        if(doc.getElementsByTagName("db2").item(0)==null){
            db2 = doc.createElement("db2");
            db.appendChild(db2);
        }else{
            db2=doc.getElementsByTagName("db2").item(0);
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
        name1.setTextContent(n1);
        pass1 = doc.createElement("pass");
        pass1.setTextContent(p1);
        address1 = doc.createElement("address");
        address1.setTextContent(a1);
        port1 = doc.createElement("port");
        port1.setTextContent(pr1);
        user1 = doc.createElement("username");
        user1.setTextContent(u1);
        db1.appendChild(address1);
        db1.appendChild(port1);
        db1.appendChild(name1);
        db1.appendChild(user1);
        db1.appendChild(pass1);

        e1 = doc.getElementsByTagName("name").item(1);
        if (e1 != null) {
            e1.getParentNode().removeChild(e1);
        }
        e2 = doc.getElementsByTagName("pass").item(1);
        if (e2 != null) {
            e2.getParentNode().removeChild(e2);
        }
        e3 = doc.getElementsByTagName("address").item(1);
        if (e3 != null) {
            e3.getParentNode().removeChild(e3);
        }
        e4 = doc.getElementsByTagName("port").item(1);
        if (e4 != null) {
            e4.getParentNode().removeChild(e4);
        }
        e5 = doc.getElementsByTagName("username").item(1);
        if (e5 != null) {
            e5.getParentNode().removeChild(e5);
        }
        name2 = doc.createElement("name");
        name2.setTextContent(n2);
        pass2 = doc.createElement("pass");
        pass2.setTextContent(p2);
        address2 = doc.createElement("address");
        address2.setTextContent(a2);
        port2 = doc.createElement("port");
        port2.setTextContent(pr2);
        user2 = doc.createElement("username");
        user2.setTextContent(u2);
        db2.appendChild(address2);
        db2.appendChild(port2);
        db2.appendChild(name2);
        db2.appendChild(user2);
        db2.appendChild(pass2);

        funct.writeXML(doc,"src/bmosim/ihm3/conf/conf.xml");
    }

    public static ArrayList<String> getDBSet(){
        ArrayList<String> as = new ArrayList<>();
        Document doc = getConfDoc();
        Node n= doc.getElementsByTagName("name").item(0);
        as.add(n==null?"":n.getTextContent());
        n= doc.getElementsByTagName("pass").item(0);
        as.add(n==null?"":n.getTextContent());
        n= doc.getElementsByTagName("address").item(0);
        as.add(n==null?"":n.getTextContent());
        n= doc.getElementsByTagName("port").item(0);
        as.add(n==null?"":n.getTextContent());
        n= doc.getElementsByTagName("username").item(0);
        as.add(n==null?"":n.getTextContent());

        n= doc.getElementsByTagName("name").item(1);
        as.add(n==null?"":n.getTextContent());
        n= doc.getElementsByTagName("pass").item(1);
        as.add(n==null?"":n.getTextContent());
        n= doc.getElementsByTagName("address").item(1);
        as.add(n==null?"":n.getTextContent());
        n= doc.getElementsByTagName("port").item(1);
        as.add(n==null?"":n.getTextContent());
        n= doc.getElementsByTagName("username").item(1);
        as.add(n==null?"":n.getTextContent());

        return as;

    }

}
