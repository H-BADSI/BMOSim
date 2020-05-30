package bmosim.AbsAgents;

import java.util.ArrayList;
import java.util.List;
import bmosim.exchange.param.ParamAnaAgent;
import bmosim.model.AGR;
import bmosim.model.Role;

public abstract class AbstractANA extends AbsEtsAgent{
	
	String stAnalysisType;
	List<Integer> analysisType = new ArrayList<Integer>();
	
	public AbstractANA(){}
	public AbstractANA(Object conf) {
		super(conf);
		// TODO Auto-generated constructor stub
	}
	public void activate () {
//		setLogLevel(Level.FINEST);
		requestCGR();
		requestRole(AGR.COMMUNITY, AGR.IN_GROUP,AGR.ANA_IN_ROLE);
		requestRoles();
		setParameters();
	}
	public void setLocalSettings(){
		ParamAnaAgent c = (ParamAnaAgent)configuration;
		ontologyUse  = c.ontologyUse;
		aSpeed= c.speed;
		versatility = c.versatility;
		errorRate = c.errorRate;
		myRoles.addAll(c.roles);
		analysisType.addAll(c.analysisType);
	}
	public void completeSettings(){
		myRoles = stringToStringList(stMyRoles);
		analysisType = stringToIntList(stAnalysisType);
	}
	public void doIt(){
		
//			String nextRole = myRoles.get(selectRole()-1);
		    switch (role){
			case Role.Ana.LEAD:{
				System.out.println("lead analysis");
			};break;
			case Role.Ana.PROF:{
				profiling();
			};break;
			case Role.Ana.FB:{
				System.out.println("feedback");
			};break;
			default:{};
			}

	}
	public abstract void profiling();
}
