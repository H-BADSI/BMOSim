package bmosim.AbsAgents;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import bmosim.exchange.dialog.Collaboration;
import bmosim.exchange.dialog.Conversation;
import bmosim.exchange.filters.CollaborationEnumFilter;
import bmosim.exchange.filters.ConversationEnumFilter;
import bmosim.exchange.param.ParamComAgent;
import bmosim.model.AGR;
import bmosim.model.Role;
import madkit.message.EnumMessage;

public abstract class AbstractCOM extends AbsEtsAgent {

	String stChannelsUsed;
	List<Integer> channelsUsed = new ArrayList<Integer>();
	
	
	// Messaging
	public EnumMessage<Conversation> dialog;
	public EnumMessage<Conversation> reply;
	
	public AbstractCOM(){}
	public AbstractCOM(Object conf) {
		super(conf);
		// TODO Auto-generated constructor stub
	}
	public void activate () {
		getLogger().setLevel(Level.FINEST);
		requestCGR();
		requestRole(AGR.COMMUNITY, AGR.IN_GROUP, AGR.COM_IN_ROLE);
		requestRole(AGR.COMMUNITY, AGR.EX_GROUP, AGR.COM_EX_ROLE);
		setParameters();
		requestRoles();
		getComSubRole(Role.Com.ADV);
		getComSubRole(Role.Com.REC);
		getComSubRole(Role.Com.PRE);
		getComSubRole(Role.Com.TRE);
		getComSubRole(Role.Com.BIL);
		getComSubRole(Role.Com.DEL);
		getComSubRole(Role.Com.MCS);
		getComSubRole(Role.Com.REF);
	}
	public void setLocalSettings(){
		ParamComAgent c = (ParamComAgent)configuration;
		ontologyUse  = c.ontologyUse;
		aSpeed= c.speed;
		versatility = c.versatility;
		errorRate = c.errorRate;
		myRoles.addAll(c.roles);
		channelsUsed.addAll(c.channels);
	}
	public void completeSettings(){
		myRoles = stringToStringList(stMyRoles);
		channelsUsed = stringToIntList(stChannelsUsed);
	}
	public void doIt(){
//		if (getLogger() != null) getLogger().info(" step BEGIN "+ role+ " next step: "+ nextStep+"  timer: "+timer);
//		if (getLogger() != null) getLogger().info(role);
		switch (role){
		case Role.Com.ADV:{
//			if (getLogger() != null) getLogger().info(" advertising ");
			advertising();
		};break;
		case Role.Com.REC:{
//			if (getLogger() != null) getLogger().info(" receiving customer ");
			receivingCustomer();
		};break;
		case Role.Com.PRE:{
//			if (getLogger() != null) getLogger().info(" preparing offer ");
			preparingOffer();
		};break;
		case Role.Com.TRE:{
//			if (getLogger() != null) getLogger().info(" treating order ");
			treatingOrder();
		};break;
		case Role.Com.BIL:{
//			if (getLogger() != null) getLogger().info(" billing ");
			billing();
		};break;
		case Role.Com.DEL:{
//			if (getLogger() != null) getLogger().info(" delivering ");
			delivering();
		};break;
		case Role.Com.MCS:{
//			if (getLogger() != null) getLogger().info(" managing CS ");
			managingCS();
		};break;
		case Role.Com.REF:{
//			if (getLogger() != null) getLogger().info(" refunding ");
			refunding();
		};break;
		default:{};
		}
//	    if (getLogger() != null) getLogger().info(" step END "+ role+ " next step: "+ nextStep+"  timer: "+timer);
}
	public abstract void advertising();
	public abstract void receivingCustomer();
	public abstract void preparingOffer();
	public abstract void treatingOrder();
	public abstract void billing();
	public abstract void delivering();
	public abstract void managingCS();
	public abstract void refunding();
	
	// potentielles interfaces:
	
	@SuppressWarnings("unchecked")
	public EnumMessage<Conversation> getConversationMessage(Conversation x){
		filter = new ConversationEnumFilter(x);
		EnumMessage<Conversation> y = null;
		y = (EnumMessage<Conversation>) nextMessage(filter);
		return y;
	}
	
	@SuppressWarnings("unchecked")
	public EnumMessage<Collaboration> getCollaborationMessage(Collaboration x){
		colFilter = new CollaborationEnumFilter(x);
		EnumMessage<Collaboration> y = null;
		y = (EnumMessage<Collaboration>) nextMessage(colFilter);
		return y;
	}
	
	@SuppressWarnings("unchecked")
	public EnumMessage<Conversation> checkConversationMessage(Conversation x){
		filter = new ConversationEnumFilter(x);
		EnumMessage <Conversation> y = null;
		y = (EnumMessage<Conversation>) nextMessage(filter);
		if (y == null) decrementTimer(); else nullTimer();
		return y;
	}
	
	public void checkForConversation(Conversation x,String y){
		dialog = checkConversationMessage(x);
		if (dbResp != null) nextStep = y;
		else decrementTimer();
	}

	public int getCustomerScore(){
		// TODO à completer après introduction du score de la clientelle
		return (1);
	}
	
	public void getComSubRole(String s){
		if (myRoles.contains(s)) requestRole(AGR.COMMUNITY, AGR.EX_GROUP, s);
	}
}
