package bmosim.ihm3.controller;

import bmosim.ihm3.Main;
import com.sun.xml.xsom.impl.scd.Iterators;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

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

    static String getXmlDirectory() throws ParserConfigurationException, IOException, SAXException {
        Document doc = getConfDoc();
        Node xmldest = doc.getElementsByTagName("xmlDest").item(0);
        return xmldest.getTextContent();
    }

    static String getClassDirectory() throws ParserConfigurationException, IOException, SAXException {
        Document doc = getConfDoc();
        Node p1 = doc.getElementsByTagName("package1").item(0);
        Node p2 = doc.getElementsByTagName("package2").item(0);
        return "src/"+p1.getTextContent()+"/"+p2.getTextContent();
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

    static ArrayList<String> getXmlFilesNamesFilter(String s) throws IOException, SAXException, ParserConfigurationException {

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
    static ArrayList<String> getClassFilesNamesFilter(String s) throws IOException, SAXException, ParserConfigurationException {

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
    public static ArrayList<Integer> getSimSet() throws ParserConfigurationException, IOException, SAXException {
        Document doc = getConfDoc();
        ArrayList<Integer> simSet= new ArrayList<>();
        if(doc.getElementsByTagName("SimulationSet")!=null){
            simSet.add(Integer.valueOf(doc.getElementsByTagName("Duration").item(0).getTextContent()));
            simSet.add(Integer.valueOf(doc.getElementsByTagName("PayCycle").item(0).getTextContent()));
            simSet.add(Integer.valueOf(doc.getElementsByTagName("StepDelay").item(0).getTextContent()));
        }
        return simSet;

    }

    public static ArrayList<String> getClassPackages() throws ParserConfigurationException, IOException, SAXException {
        ArrayList<String> ps = new ArrayList<>();
        Document doc = getConfDoc();
        if(doc.getElementsByTagName("Package1")!=null){
            ps.add(doc.getElementsByTagName("Package1").item(0).getTextContent());
            ps.add(doc.getElementsByTagName("Package2").item(0).getTextContent());
        }
        return ps;

    }

    public static void setDarkLight(Boolean b){
        Main.dark_light=b;
        Document doc = getConfDoc();
        Node DarkLight;
        if(doc.getElementsByTagName("DarkLight").item(0)==null){
            System.out.println("NULL");
            DarkLight = doc.createElement("DarkLight");
        }else {
            System.out.println("not NULL");
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


}
