package bmosim.AbsAgents;

import java.util.ArrayList;
import java.util.List;
import bmosim.exchange.param.ParamSupAgent;
import bmosim.model.AGR;
import bmosim.model.Role;

public abstract class AbstractSUP extends AbsEtsAgent {
	
	String stChannelsUsed;
	List<Integer> channelsUsed = new ArrayList<Integer>();
	String stAnalysisType;
	String stRequestType;
	List<Integer> analysisType = new ArrayList<Integer>();
	List<Integer> requestType = new ArrayList<Integer>();
	
	public AbstractSUP(){}
	public AbstractSUP(Object conf) {
		super(conf);
		// TODO Auto-generated constructor stub
	}
	
	public void activate () {
//		setLogLevel(Level.FINEST);
		requestCGR();
		requestRole(AGR.COMMUNITY, AGR.IN_GROUP,AGR.SUP_IN_ROLE);
		requestRole(AGR.COMMUNITY, AGR.EX_GROUP, AGR.SUP_EX_ROLE);
		requestRoles();
		setParameters();
	}
	
	public void setLocalSettings(){
		ParamSupAgent c = (ParamSupAgent)configuration;
		ontologyUse = c.ontologyUse;
		aSpeed= c.speed;
		versatility = c.versatility;
		errorRate = c.errorRate;
		myRoles.addAll(c.roles);
		channelsUsed.addAll(c.channels) ;
		analysisType.addAll(c.analysisType);
		requestType.addAll(c.requestType);
	}
	public void completeSettings(){
		myRoles = stringToStringList(stMyRoles);
		channelsUsed = stringToIntList(stChannelsUsed);
		analysisType = stringToIntList(stAnalysisType);
		requestType = stringToIntList(stRequestType);
	}
	public void doIt(){

	    switch (role){
		case Role.Sup.SURV:{
			System.out.println("survey");
		};break;
		case Role.Sup.ANAREP:{
			System.out.println("Analysis&reporting");
		};break;
		case Role.Sup.LOYMAN:{
			System.out.println("LoyaltyManagement");
		};break;
		default:{};
		}
}
}
