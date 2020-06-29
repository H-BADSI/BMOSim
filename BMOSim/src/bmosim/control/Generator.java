package bmosim.control;
import java.io.File;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;

import bmosim.ihm3.bdd;
import madkit.kernel.Scheduler;
import madkit.kernel.Scheduler.SimulationState;
import madkit.simulation.SimulationException;
import org.xml.sax.SAXException;
import javafx.fxml.FXML;
import madkit.kernel.Agent;
import bmosim.agents.DB;
//import bmosim.exchange.param.ParamAnaAgent;
//import bmosim.exchange.param.ParamComAgent;
//import bmosim.exchange.param.ParamCusAgent;
//import bmosim.exchange.param.ParamManAgent;
//import bmosim.exchange.param.ParamSupAgent;
import bmosim.ihm.Controller.Ent;
import bmosim.model.AGR;

public class Generator extends Agent{
	
	ReturnCode code =null;
	static Ent enterprise ;
	public static boolean alive = true;

	static int idSim;
	static int ord=1;
	static int nbInst;
	static int idInst;

	//brahim
	Scheduler scheduler;



	public void main() {
		executeThisAgent(1,false);

	}

	public static void main(Ent ent) {
		enterprise = ent;
		executeThisAgent(1,true
//				Madkit.LevelOption.agentLogLevel.toString(),"OFF"
				);
	}
	

	public void activate () {	
		createGroupIfAbsent(AGR.COMMUNITY, AGR.GROUP);
		createGroupIfAbsent(AGR.COMMUNITY, AGR.CONTROL_GROUP);
		createGroupIfAbsent(AGR.COMMUNITY, AGR.IN_GROUP);
		createGroupIfAbsent(AGR.COMMUNITY, AGR.EX_GROUP);
		
		requestRole(AGR.COMMUNITY, AGR.CONTROL_GROUP, AGR.LAUNCHER_ROLE);


	}

	@FXML
	public void live(){
		scheduler = new Schedul();
		launchAgent(new DB(false),false);
		launchAgent(scheduler,false);
		try {
			launchXmlAgents(Main.fileDirect);
		} catch (SAXException e) {
			System.out.println(1);
		} catch (ParserConfigurationException e) {
			System.out.println(3);
		}catch (SimulationException e){
			System.out.println(4444444);
		} catch (IOException e) {
			System.out.println(555555);
		}

		launchAgent(new Observer(),false);
		while (alive) pause (500);



//		int nb[] = new int[8];
//		nb=enterprise.nb();
//		for (int i = 0; i < nb[0]; i++){
//			launchAgent(new CUS(getCusSettings()),true);
//		}	
//		for (int i = 0; i < nb[1]; i++){	
//			launchAgent(new COM(getComSettings()),true);
//		}
//		for (int i = 0; i < nb[2]; i++){
//			launchAgent(new SUP(getSupSettings()),true);
//		}
//		for (int i = 0; i < nb[3]; i++){
//			launchAgent(new ANA(getAnaSettings()),true);
//		}
//		for (int i = 0; i < nb[4]; i++){
//			launchAgent(new MAN(getManSettings()),true);
//		}
		
		//SchedulingMessage m = new SchedulingMessage(SchedulingAction.RUN);
		//sendMessage("BMOSimulator","Control","scheduler",m);
	}

	public void end(){
		//sendMessage(AGR.COMMUNITY,AGR.CONTROL_GROUP,AGR.SCHEDULER_ROLE,new SchedulingMessage(SchedulingAction.SHUTDOWN));
		//System.out.println("END Generator");
	}


//	public ParamCusAgent getCusSettings(){
//		
//		// devra donner N fois chaque config
//		
//		ParamCusAgent confcus = new ParamCusAgent();
//		confcus.budget = 1000;
//		confcus.age = 30;
//		confcus.quality = 2;
//		confcus.time = 6;
//		confcus.objectTypes.add(1);
//		confcus.objectTypes.add(2);
//		confcus.channels.add(1);
//		confcus.channels.add(2);
//		confcus.channels.add(3);
//		return confcus;
//	} 
//	public ParamComAgent getComSettings(){
//		
//		// devra donner N fois chaque config
//		
//		ParamComAgent confcom = new ParamComAgent();
//		
//		confcom.ontologyUse = false;
//		confcom.speed = 500;
//		confcom.versatility = 4;
//		confcom.errorRate = 6;
//		confcom.roles.add(Role.Com.ADV);
//		confcom.roles.add(Role.Com.REC);
//		confcom.roles.add(Role.Com.PRE);
//		confcom.roles.add(Role.Com.TRE);
////		confcom.roles.add(ComR.BIL);
////		confcom.roles.add(ComR.DEL);
////		confcom.roles.add(ComR.MCS);
////		confcom.roles.add(ComR.REF);
//		confcom.channels.add(1);
//		confcom.channels.add(2);
//		confcom.channels.add(3);
//		return confcom;
//	}
//	public ParamAnaAgent getAnaSettings(){
//		
//		// devra donner N fois chaque config
//		
//		ParamAnaAgent confana = new ParamAnaAgent();
//		
//		confana.ontologyUse = false;
//		confana.speed = 500;
//		confana.versatility = 4;
//		confana.errorRate = 6;
//		confana.roles.add(Role.Ana.LEAD);
//		confana.roles.add(Role.Ana.PROF);
//		confana.roles.add(Role.Ana.FB);
//		confana.analysisType.add(1);
//		confana.analysisType.add(2);
//		confana.analysisType.add(3);
//		return confana;
//	}
//	public ParamSupAgent getSupSettings(){
//		
//		// devra donner N fois chaque config
//		
//		ParamSupAgent confsup = new ParamSupAgent();
//		
//		confsup.ontologyUse = false;
//		confsup.speed = 500;
//		confsup.versatility = 4;
//		confsup.errorRate = 6;
//		confsup.roles.add(Role.Sup.SURV);
//		confsup.roles.add(Role.Sup.ANAREP);
//		confsup.roles.add(Role.Sup.LOYMAN);
//		confsup.channels.add(1);
//		confsup.channels.add(2);
//		confsup.channels.add(3);
//		confsup.analysisType.add(1);
//		confsup.analysisType.add(2);
//		confsup.analysisType.add(3);
//		confsup.requestType.add(1);
//		confsup.requestType.add(2);
//		return confsup;
//	}
//	public ParamManAgent getManSettings(){
//		
//		// devra donner N fois chaque config
//		
//		ParamManAgent confman = new ParamManAgent();
//		
//		confman.ontologyUse = false;
//		confman.speed = 500;
//		confman.versatility = 4;
//		confman.errorRate = 6;
//		confman.roles.add(Role.Man.PERF);
//		confman.roles.add(Role.Man.STRAT);
//		confman.roles.add(Role.Man.ENAB);
//		confman.evaluationTypes.add(1);
//		confman.evaluationTypes.add(2);
//		confman.evaluationTypes.add(3);
//		confman.strategytypes.add(1);
//		confman.strategytypes.add(2);
//		confman.strategytypes.add(3);
//		confman.leadership = 3;
//		return confman;
//	}

}
