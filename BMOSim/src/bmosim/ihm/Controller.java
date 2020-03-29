package bmosim.ihm;

import bmosim.control.Generator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

public class Controller {
	
	@FXML TextField nbcus;
	@FXML TextField nbcom;
	@FXML TextField nbsup;
	@FXML TextField nbana;
	@FXML TextField nbman;
	@FXML CheckBox randcusb;

	
	public class Ent{
		public int[] nb(){
			int v[]= new int[8];
			
			// à enlever
			nbcus.setText("3");
			nbcom.setText("1");
//			nbsup.setText("1");
//			nbana.setText("1");
			nbman.setText("1");
			
			if (nbcus.getText().trim().isEmpty()){v[0]=0;} else	{v[0] = Integer.parseInt(nbcus.getText());}
			if (nbcom.getText().trim().isEmpty()){v[1]=0;} else {v[1] = Integer.parseInt(nbcom.getText());}
			if (nbsup.getText().trim().isEmpty()){v[2]=0;} else {v[2] = Integer.parseInt(nbsup.getText());}
			if (nbana.getText().trim().isEmpty()){v[3]=0;} else {v[3] = Integer.parseInt(nbana.getText());}
			if (nbman.getText().trim().isEmpty()){v[4]=0;} else {v[4] = Integer.parseInt(nbman.getText());}
			
			return v;
		}
	}
	
/*	@FXML
	private void handleRandomBudget (ActionEvent event){
		String nbcustomer = nbcus.getText();	
		boolean isSelected = this.randcusb.isSelected();
		
		if (isSelected) {
			int n= Integer.parseInt(nbcustomer);
			int x = (int) (n*Math.random());
			int y = (int) ((n-x)*Math.random());
			int z = n-x-y;
			bl.setText(Integer.toString(x));
			bm.setText(Integer.toString(y));
			bh.setText(Integer.toString(z));
			
		}
		else {
			// verifier 
			System.out.println("false");
		}	
	}
	
	@FXML
	private void handleGenerateXML (ActionEvent event){
		int z;
		if (nbcus.getText().trim().isEmpty()){z=0;} else {z = Integer.parseInt(bl.getText());}
		System.out.println("test"+z);
		
	}
*/	
	@FXML
	private void handleGenerate (ActionEvent event){
		Ent ent = new Ent();
		Generator.main(ent);
	}
	
	@FXML
	private void handleKillAllAgents (ActionEvent event){
		Generator.alive = false;
	}

}
