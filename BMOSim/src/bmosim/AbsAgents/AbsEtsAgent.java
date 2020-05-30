package bmosim.AbsAgents;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.util.FileManager;

import bmosim.exchange.dialog.Collaboration;
import bmosim.exchange.dialog.Queries;
import bmosim.exchange.filters.CollaborationEnumFilter;
import bmosim.exchange.filters.ConversationEnumFilter;
import bmosim.exchange.filters.QueriesEnumFilter;
import bmosim.exchange.param.ParamCusAgent;
import bmosim.hibernateDB.DBcustomer;
import bmosim.hibernateDB.DBoffer;
import bmosim.model.AGR;
import madkit.kernel.Message;
import madkit.message.EnumMessage;

public abstract class AbsEtsAgent extends AbsBMOAgent{
	String stMyRoles;
	protected List<String> myRoles = new ArrayList<String>();
	protected String role;
	
	public int nbStepsToWaitDBResponse = 5;
	public int nbStepsToWaitCustomerResponse = 10;
	public int nbStepsToWaitAgentResponse = 10;
	ConversationEnumFilter filter;
	QueriesEnumFilter dbFilter;
	CollaborationEnumFilter colFilter;
	protected boolean ontologyUse;
	int aSpeed;
	int versatility ;
	int errorRate ;

	Message m = null;
	public EnumMessage<Queries> reqDB;
	public EnumMessage<Queries> dbResp;
	public EnumMessage<Collaboration> quest;
	public EnumMessage<Collaboration> agentResp;
	
	DBoffer selectedOffer; 

	public AbsEtsAgent() {}
	public AbsEtsAgent(Object conf) {
		super(conf);
		// TODO Auto-generated constructor stub
	}
	public void doThing(){
		role = myRoles.get(selectRole());
		nextStep = null;
		doIt();
	}
	public int selectRole(){
		int y = myRoles.size();
		if (y==0) if (getLogger() != null) getLogger().info(" BMOSIM-ERR: i have no-roles  ");
//		int x =y+1;
//		while ( x==y+1 && y!=0 ) x = (int) (y*Math.random()+1);		
//		return x-1;
		return (int)(y*Math.random());
	}
	public void requestRoles(){
		int i;
		for (i = 0 ; i < myRoles.size() ; i++){ 
			String role = myRoles.get(i);
			requestRole(AGR.COMMUNITY, AGR.IN_GROUP , role.toString());
			}
	}
	public void saveToDB (Queries qr, Object obj){
		reqDB = new EnumMessage<Queries>(qr,obj);
		sendMessage(AGR.COMMUNITY,AGR.IN_GROUP,AGR.DB_IN_ROLE,reqDB);
	}
	@SuppressWarnings("unchecked")
	public EnumMessage<Queries> checkDBResponse(Queries x){
		dbFilter = new QueriesEnumFilter(x);
		EnumMessage <Queries> y = null;
		y = (EnumMessage<Queries>) nextMessage(dbFilter);
		if (y == null) decrementTimer(); else nullTimer();
		return y;
	}
	@SuppressWarnings("unchecked")
	public EnumMessage<Collaboration> checkAgentMessage(Collaboration x){
		colFilter = new CollaborationEnumFilter(x);
		EnumMessage <Collaboration> y = null;
		y = (EnumMessage<Collaboration>) nextMessage(colFilter);
		if (y == null) decrementTimer(); else nullTimer();
		return y;
	}
	public void requestDBandWait(EnumMessage<Queries> m, String x){
		timer = nbStepsToWaitDBResponse;
		sendMessage(AGR.COMMUNITY,AGR.IN_GROUP,AGR.DB_IN_ROLE,m);
		nextStep = x;
	}
	public void askManAndWait(EnumMessage<Collaboration> m, String x){
		timer = nbStepsToWaitAgentResponse;
		sendMessage(AGR.COMMUNITY,AGR.IN_GROUP,AGR.MAN_IN_ROLE,m);
		nextStep = x;
	}
	public void decrementTimer(){
		timer--;
//		if (getLogger() != null) getLogger().info(getName()+": update timer = "+timer);
	}
	public void stopTimer(){
		timer = -1 ;
//		if (getLogger() != null) getLogger().info(getName()+" stop timer "+timer + " end of role: "+role);
	}
	public void setTimer(int t){
		timer = t;
//		if (getLogger() != null) getLogger().info(getName()+": start timer = "+timer);
	}
	public void nullTimer(){
		timer = 0;
//		if (getLogger() != null) getLogger().info(getName()+": timer set to "+timer);
	}
	
	public ParamCusAgent retrieveCusInfo(DBcustomer dBcus){
		ParamCusAgent info = new ParamCusAgent();
    	info.budget = dBcus.getBudget();
    	info.age = dBcus.getAge();
    	info.quality = dBcus.getQuality();
    	info.time = dBcus.getTime();
    	for (int i=0; i< dBcus.getTypes().size();i++) info.objectTypes.add(dBcus.getTypes().get(i).getObj_type());
    	for (int i=0; i< dBcus.getChannels().size();i++) info.channels.add(dBcus.getChannels().get(i).getChannel_id());
    	return info;	
	}
	
	public String customerSegmentation(DBcustomer cusInfo){
		// � r�ecrire en se basant sur l'ontologie
		String x = "";
		if (cusInfo.getAge() <= 16){x = "1";}
		if (cusInfo.getAge() >= 17 && cusInfo.getAge() <= 29){x = "2";}
		if (cusInfo.getAge() >= 30 && cusInfo.getAge() <= 49){x = "3";}
		if (cusInfo.getAge() >= 50){x = "4";}
		
		return x;
		
	}
	
    public DBoffer selectOffer(ArrayList<DBoffer> proposition,DBcustomer cusInfo){

    	ArrayList<String> targetOffers = new ArrayList<String>();
    	
    	FileManager.get().addLocatorClassLoader(AbsEtsAgent.class.getClassLoader());
    	Model model = FileManager.get().loadModel("C:\\Users\\BRAHIM\\Downloads\\3juil2019\\demo\\BMOSim\\BMOSim\\src\\bmosim\\onto\\BusModelOWL.owl");
    	String x = String.valueOf(customerSegmentation(cusInfo));
    	String queryString = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> "
    		+ "PREFIX bmo: <http://www.owl-ontologies.com/businessmodelontology.owl#> "
    		+ "PREFIX elt: <http://www.owl-ontologies.com/> "
    		+ "SELECT ?vp "
    		+ "WHERE { "
    		+ "?vp rdf:type bmo:ValueProposition. "
    		+ "?vp bmo:targets elt:"+x
    		+ "}";
    	Query vpQuery = QueryFactory.create(queryString);
    	QueryExecution qex = QueryExecutionFactory.create(vpQuery,model);
    	try {
    	ResultSet results = qex.execSelect();
    		while (results.hasNext()){
    			QuerySolution sol = results.nextSolution();
    			String s = sol.toString().replace("( ?vp = <http://www.owl-ontologies.com/", "").replace("> )","").trim();
    			targetOffers.add(s);
    		}
    	} finally {qex.close();}
    	
		
			ArrayList<DBoffer> goodOffers = new ArrayList<DBoffer>();

			for (int i = 0; i<proposition.size(); i++){
				String vp = proposition.get(i).getProduct_id().getProduct_name();
				if (targetOffers.contains(vp)) goodOffers.add(proposition.get(i));
			}
			
			switch (goodOffers.size()){
			case 0:{
				selectedOffer = proposition.get((int)Math.random());
			};break;
			case 1:{
				selectedOffer = goodOffers.get(0);
			};break;
			default:{
				selectedOffer = goodOffers.get((int)Math.random());
			};break;
			}

    	return selectedOffer;
    }
//	public void checkForQuery(Queries x,String y){
//		dbResp= checkDBReponse(x);
//		if (dbResp != null) nextStep = y;
//		else timer--;
//	}
}
