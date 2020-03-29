package bmosim.ihm2;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class ManagerController implements  Initializable{
	String a,b,c,d,e,f,g,n,m;
	@FXML
    private AnchorPane root2;
	 @FXML
	    private JFXHamburger hamburger;

	    @FXML
	    private JFXDrawer drawer;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		try {
            VBox vbox = FXMLLoader.load(getClass().getResource("/application/Drawer.fxml"));
            drawer.setSidePane(vbox);
        } catch (IOException ex) {
            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        HamburgerBackArrowBasicTransition transition = new HamburgerBackArrowBasicTransition(hamburger);
        transition.setRate(-1);
        hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED,(e)->{
            transition.setRate(transition.getRate()*-1);
            transition.play();
            
            if(drawer.isShown())
            {
                drawer.close();
            }else
                drawer.open();
        });
	}
        @FXML
        public Label text1;
    public void generateRandom1 (ActionEvent event){
    	Random rand1= new Random();
    	int myrand1 =rand1.nextInt(3) +1;
    	text1.setText(Integer.toString(myrand1));
    	}
    @FXML
    private Label text2;
    public void generateRandom2 (ActionEvent event){
    	Random rand2= new Random();
    	int myrand2 =rand2.nextInt(20) +1;
    	text2.setText(Integer.toString(myrand2));}
    @FXML
    private Label text3;
    public void generateRandom3 (ActionEvent event){
    	Random rand3= new Random();
    	int myrand3 =rand3.nextInt(5) +1;
    	text3.setText(Integer.toString(myrand3));}
    @FXML
    private CheckBox rd16;
    @FXML
    private CheckBox rd17;
    @FXML
    private CheckBox rd18;
    @FXML
    private CheckBox rd22;
    @FXML
    private CheckBox rd21;
    @FXML
    private CheckBox rd20;
   
    @FXML
    private CheckBox rd30;
    @FXML
    private CheckBox rd31;
    @FXML
    private CheckBox rd32;
    @FXML
    private CheckBox rd13;
    @FXML
    private CheckBox rd14;
    @FXML
    private CheckBox rd15;
    

    @FXML
    public void vider(ActionEvent event){
    	 text1.setText(" ");
    	 text2.setText(" ");
    	 text3.setText(" ");
    	 rd16.setSelected(false);
    	 rd17.setSelected(false);
    	 rd18.setSelected(false);
    	 rd22.setSelected(false);
    	 rd21.setSelected(false);
    	 rd20.setSelected(false);
    	 rd30.setSelected(false);
    	 rd31.setSelected(false);
         rd32.setSelected(false);
         id4.setText(" ");
         rd13.setSelected(false);
    	 rd14.setSelected(false);
    	 rd15.setSelected(false);
    	
    }
    @FXML
    void role(ActionEvent event) {
       	if(rd13.isSelected()){
       		rd14.setSelected(false);
      		rd15.setSelected(false);
    	}
    	{m="1";}}
    	@FXML
        void role1(ActionEvent event) {
    	if(rd14.isSelected()){
    		rd13.setSelected(false);
    		rd15.setSelected(false);
    	}
    	{m="2";}}
    	@FXML
        void role2(ActionEvent event) {
    	if(rd15.isSelected()){
    		rd13.setSelected(false);
    		rd14.setSelected(false);
    	}
    	{m="3";}}

    @FXML
    void taux(ActionEvent event) {
       	if(rd16.isSelected()){
       		rd17.setSelected(false);
      		rd18.setSelected(false);
    	}
    	{c="faible";}}
    	@FXML
        void taux1(ActionEvent event) {
    	if(rd17.isSelected()){
    		rd16.setSelected(false);
    		rd18.setSelected(false);
    	}
    	{c="moyenne";}}
    	@FXML
        void taux2(ActionEvent event) {
    	if(rd18.isSelected()){
    		rd16.setSelected(false);
    		rd17.setSelected(false);
    	}
    	{c="haut";}}

    	@FXML
    	void vitesse(ActionEvent event) {
    		if(rd20.isSelected()){
    			rd21.setSelected(false);
        		rd22.setSelected(false);
    		}
    		{b="faible";}}
    	@FXML
    	void vitesse1(ActionEvent event) {
    		if(rd21.isSelected()){
    			rd20.setSelected(false);
        		rd22.setSelected(false);
    		}
    		{b="moyenne";}}
    	@FXML
    	void vitesse2(ActionEvent event) {
    		if(rd22.isSelected()){
    			rd20.setSelected(false);
        		rd21.setSelected(false);
    		}
    		{b="haut";}
    }
    	
    	@FXML
    	void polyvalance(ActionEvent event) {
    		if(rd30.isSelected()){
    			rd31.setSelected(false);
        		rd32.setSelected(false);
    		}
    		{e="faible";}}
    	@FXML
    	void polyvalance1(ActionEvent event) {
    		if(rd31.isSelected()){
    			rd30.setSelected(false);
        		rd32.setSelected(false);
    		}
    		{e="moyenne";}}
    	@FXML
    	void polyvalance2(ActionEvent event) {
    		if(rd32.isSelected()){
    			rd30.setSelected(false);
        		rd31.setSelected(false);
    		}
    		{e="haut";}
    }
    	@FXML
    	private TextField id4;
    	@FXML
        private TextField saisie2;

    	@FXML
    	  void save2(ActionEvent event) throws ParserConfigurationException, TransformerException, SAXException, IOException{
    		// création du fichier avec "simparam" comme racine
    			  
    			  
    			 DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
    		      DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
    		      Document document = documentBuilder.newDocument();
    		        
    		       Element racine = document.createElement("simparam");
    		       document.appendChild(racine);
    		       Element c6 = document.createElement("client");
    	           c6.appendChild(document.createTextNode(""));
    	           racine.appendChild(c6);
    		     
    		      TransformerFactory transformerFactory = TransformerFactory.newInstance();
    		      Transformer transformer = transformerFactory.newTransformer();
    		      DOMSource source = new DOMSource(document);
    		      StreamResult streamResult = new StreamResult( new File(saisie2.getText()));
    		      transformer.transform(source, streamResult);}	
    	
    	@FXML
    	void save(ActionEvent event) throws ParserConfigurationException, TransformerException , SAXException, IOException {
    		
    		
    		
    		
    		DocumentBuilderFactory dBF3 = DocumentBuilderFactory.newInstance();
    	     DocumentBuilder dBuilder3 = dBF3.newDocumentBuilder();
    	     Document document3 = dBuilder3.parse(saisie2.getText());
    	     Element racine3 = document3.getDocumentElement();
    	     
    	     // ajout d'un element client sous la racine
    	     
    	     Element client2 = document3.createElement("Manager");
    	     racine3.appendChild(client2);
    	     Element budget2 = document3.createElement("role");
    	     budget2.appendChild(document3.createTextNode(text1.getText()));
    	     client2.appendChild(budget2);

    	    Element budget1 = document3.createElement("nombredesubordonnée");
    	     budget1.appendChild(document3.createTextNode(text2.getText()));
    	     client2.appendChild(budget1);
    	    
    	     Element budget = document3.createElement("typedevaluation");
    	     budget.appendChild(document3.createTextNode(text3.getText()));
    	     client2.appendChild(budget);

    	     
    	     Element qualite2 = document3.createElement("tauxderreur");
    	     qualite2.appendChild(document3.createTextNode(c));
    	     client2.appendChild(qualite2);
    	     
    	     Element age2 = document3.createElement("vitesse");
    	     age2.appendChild(document3.createTextNode(b));
    	     client2.appendChild(age2);
    	     
    	     
    	     Element c5 = document3.createElement("polyvalance");
    	     c5.appendChild(document3.createTextNode(e));
    	     client2.appendChild(c5);
    	     
    	     
    	     Element c6 = document3.createElement("n3");
    	     c6.appendChild(document3.createTextNode(id4.getText()));
    	     client2.appendChild(c6);
    	    
    	     
    	     
    	     TransformerFactory transformerFactory3 = TransformerFactory.newInstance();
    	     Transformer transformer3 = transformerFactory3.newTransformer();
    	     DOMSource source3 = new DOMSource(document3);
    	     StreamResult streamResult3 = new StreamResult( new File(saisie2.getText()));
    	     transformer3.transform(source3, streamResult3);
    	     
    	}
}
