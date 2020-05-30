package bmosim.agents;

import bmosim.AbsAgents.AbstractANA;
import bmosim.exchange.dialog.Queries;
import bmosim.hibernateDB.DBcustomer;
import bmosim.model.Role;
import madkit.message.EnumMessage;

import java.util.ArrayList;

public class ANA extends AbstractANA{
	
	public ANA(){}
	public ANA(Object conf) {
		super(conf);
		// TODO Auto-generated constructor stub
	}

	ArrayList<DBcustomer> cusinfo = new ArrayList<>();
	DBcustomer cusinfos;

//	private void customersInfo(){
//		requestDBandWait(new EnumMessage<Queries>(Queries.GET_CUSTOMERS,cusinfos), null);
//	}

	public void profiling ()
	{

//		requestDBandWait(new EnumMessage<Queries>(Queries.GET_CUSTOMER,cusinfos), Role.Ana.PROF);
		requestDBandWait(new EnumMessage<Queries>(Queries.CUSTOMER_DATA_REQUEST),Role.Ana.PROF);
		dbResp = checkDBResponse (Queries.CUSTOMER_DATA);
			System.out.println("********++"+cusinfos);

	}

/*	private void selectAnalysisType ()
	{
		// select one from the following: leadAnalysis , profilingAnalysis, feedbackAnalysis
	}
	
	private void leadAnalysis ()
	{
		//
	}
	

	
	private void feedbackAnalysis ()
	{
		//
	}
	
	private void makeReport ()
	{
		//
	}
	
	private void saveReport ()
	{
		//
	}*/
}
