package bmosim.ihm2;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import com.jfoenix.controls.JFXButton;
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

public class ClientController implements  Initializable{
	String a,b,c,d,e,f,g,n;
	 @FXML
	    private AnchorPane root1;
	 @FXML
	    private JFXHamburger hamburger;

	    @FXML
	    private JFXDrawer drawer;

	    @FXML
	    private JFXButton but1;

	    @FXML
	    private JFXButton but2;

	    @FXML
	    private JFXButton but3;

	    @FXML
	    private JFXButton but89;

	    @FXML
	    private TextField saisie2;


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
      public Label txt_temps;
  public void generateRandom1 (ActionEvent event){
  	Random rand1= new Random();
  	int myrand1 =rand1.nextInt(3) +1;
  	txt_temps.setText(Integer.toString(myrand1));
  	}
  @FXML
  private CheckBox btn_a1;
  @FXML
  private CheckBox btn_a2;
  @FXML
  private CheckBox btn_a3;
  @FXML
  private CheckBox btn_b1;
  @FXML
  private CheckBox btn_b2;
  @FXML
  private CheckBox btn_b3;
  @FXML
  private CheckBox btn_c1;
  @FXML
  private CheckBox btn_c2;
  @FXML
  private CheckBox btn_c3;
  @FXML
  private CheckBox btn_c4;
  @FXML
  private CheckBox btn_c6;
  @FXML
  private CheckBox btn_c5;
  @FXML
  private CheckBox btn_o1;
  @FXML
  private CheckBox btn_o2;
  @FXML
  private CheckBox btn_o3;
  @FXML
  private CheckBox btn_q1;
  @FXML
  private CheckBox btn_q2;
  @FXML
  private CheckBox btn_q3;
  @FXML
  private TextField saisie;
  @FXML
  public void vider(ActionEvent event){
	  txt_temps.setText(" ");
  	btn_a1.setSelected(false);
  	btn_a2.setSelected(false);
  	btn_a3.setSelected(false);
  	btn_b1.setSelected(false);
  	btn_b2.setSelected(false);
  	btn_b3.setSelected(false);
  	btn_c1.setSelected(false);
  	btn_c2.setSelected(false);
  	btn_c3.setSelected(false);
  	btn_c4.setSelected(false);
  	btn_c6.setSelected(false);
  	btn_c5.setSelected(false);
  	btn_o1.setSelected(false);
  	btn_o2.setSelected(false);
  	btn_o3.setSelected(false);
  	btn_q1.setSelected(false);
  	btn_q2.setSelected(false);
  	btn_q3.setSelected(false);
  	saisie.setText(" ");
  	 
  	
  }
  @FXML
  void age(ActionEvent event) {
  	
	if(btn_a1.isSelected()){
		btn_a2.setSelected(false);
  		btn_a3.setSelected(false);
	}
	{b="enfant";}
  }
	@FXML
	  void age1(ActionEvent event) {
  	if(btn_a2.isSelected()){
  		btn_a1.setSelected(false);
  		btn_a3.setSelected(false);
  	}
  	{b="jeune";}
	}
	@FXML
	  void age2(ActionEvent event) {
  	if(btn_a3.isSelected()){
  		btn_a1.setSelected(false);
  		btn_a2.setSelected(false);
  	}
  	{b="agé";}
  }
  @FXML
  void budjet(ActionEvent event) {
  	
	if(btn_b1.isSelected()){
		btn_b2.setSelected(false);
  		btn_b3.setSelected(false);
	}
	{a="faible";}}
  @FXML
  void budjet1(ActionEvent event) {
  	if(btn_b2.isSelected()){
  		btn_b1.setSelected(false);
  		btn_b3.setSelected(false);
  	}
  	{a="moyenne";}}
  @FXML
  void budjet2(ActionEvent event) {
  	if(btn_b3.isSelected()){
  		btn_b1.setSelected(false);
  		btn_b2.setSelected(false);
  	}
  	{a="haute";}

  }
  @FXML
  void sms(ActionEvent event) {
  	if(btn_c1.isSelected()){g="sms";}
  	}

  @FXML
  void phisique(ActionEvent event) {
  	if(btn_c2.isSelected()){g+="+physique ";}
  }
  @FXML
  void email(ActionEvent event) {
  	if(btn_c3.isSelected()){g+="+E-mail ";}
  }
  @FXML
  void application(ActionEvent event) {
  	if(btn_c4.isSelected()){g+="+application ";}}
  
  	@FXML
      void site(ActionEvent event) {
  		if(btn_c6.isSelected()){g+="+site-web ";}
      }
  
  @FXML
  void mobile(ActionEvent event) {
  	if(btn_c5.isSelected()){g+="+mobile  ";}
  }



  @FXML
  void nombre(ActionEvent event) {
  	n=saisie.getText();

  }

  @FXML
  void objet(ActionEvent event) {
  	if(btn_o1.isSelected()){ 
  		btn_o2.setSelected(false);
  		btn_o3.setSelected(false);
  	}{e="*";}}
  @FXML
  	 void objet1(ActionEvent event) {
  	 if(btn_o2.isSelected()){
		btn_o1.setSelected(false);
  		btn_o3.setSelected(false);
	}{e="**";}}
  @FXML
  	 void objet2(ActionEvent event) {
	if(btn_o3.isSelected()){
		btn_o1.setSelected(false);
  		btn_o2.setSelected(false);
	}
		{e="***";}}

  @FXML
  void qualite(ActionEvent event) {
  	if(btn_q1.isSelected()){
  		btn_q2.setSelected(false);
  		btn_q3.setSelected(false);
  	}
  	{c="faible";}}
  	@FXML
    void qualite1(ActionEvent event) {
  	if(btn_q2.isSelected()){
  		btn_q1.setSelected(false);
  		btn_q3.setSelected(false);
  	}
  	{c="moyenne";}}
  	@FXML
    void qualite2(ActionEvent event) {
  	if(btn_q3.isSelected()){
  		btn_q1.setSelected(false);
  		btn_q2.setSelected(false);
  	}
  	{c="haut";}
  }
  @FXML
  void temp(ActionEvent event){
 	d= txt_temps.getText();
  }
  
  
  
 
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
	      transformer.transform(source, streamResult);
	  
  }
  @FXML
  void save(ActionEvent event) throws ParserConfigurationException, TransformerException, SAXException, IOException {
	     
      //  nouvelle ouverture du fichier, on essai de faire une nouvelle inscription 
      // sur le même fichier,  
      
      
	  DocumentBuilderFactory dBF3 = DocumentBuilderFactory.newInstance();
      DocumentBuilder dBuilder3 = dBF3.newDocumentBuilder();
      Document document3 = dBuilder3.parse(saisie2.getText());
      Element racine3 = document3.getDocumentElement();
      
      // ajout d'un element client sous la racine
      
      Element client2 = document3.createElement("client");
      racine3.appendChild(client2);

      Element budget2 = document3.createElement("budget");
      budget2.appendChild(document3.createTextNode(a));
      client2.appendChild(budget2);
      Element temps2 = document3.createElement("temps");
      temps2.appendChild(document3.createTextNode(txt_temps.getText()));
      client2.appendChild(temps2);

      Element age2 = document3.createElement("age");
      age2.appendChild(document3.createTextNode(b));
      client2.appendChild(age2);
      
      Element qualite2 = document3.createElement("qualite");
      qualite2.appendChild(document3.createTextNode(c));
      client2.appendChild(qualite2);
      
      Element c6 = document3.createElement("caneaux");
      c6.appendChild(document3.createTextNode(g));
      client2.appendChild(c6);
      Element c = document3.createElement("n1");
      c.appendChild(document3.createTextNode(saisie.getText()));
      client2.appendChild(c);
      
      
      TransformerFactory transformerFactory3 = TransformerFactory.newInstance();
      Transformer transformer3 = transformerFactory3.newTransformer();
      DOMSource source3 = new DOMSource(document3);
      StreamResult streamResult3 = new StreamResult( new File(saisie2.getText()));
      transformer3.transform(source3, streamResult3);
      
  

  }


      
	
  
}
