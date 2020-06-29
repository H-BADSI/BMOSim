package bmosim.agents;

import java.util.ArrayList;
import java.util.List;

import bmosim.AbsAgents.AbstractCOM;
import bmosim.exchange.dialog.Collaboration;
import bmosim.exchange.dialog.Conversation;
import bmosim.exchange.dialog.Queries;
import bmosim.exchange.objects.ObjectOfValue;
import bmosim.exchange.objects.Offer;
import bmosim.exchange.param.ParamCusAgent;
import bmosim.hibernateDB.DBcustomer;
import bmosim.hibernateDB.DBoffer;
import bmosim.hibernateDB.DBorder;
import bmosim.model.AGR;
import bmosim.model.MCS;
import bmosim.model.OrderState;
import bmosim.model.Role;
import madkit.kernel.*;
import madkit.message.EnumMessage;
import org.apache.jena.base.Sys;

public class COM extends AbstractCOM{
	
	// current customer
	ObjectOfValue cusneed;
	Integer needid;
	ParamCusAgent cusinfo;
	AgentAddress cusID;
	DBoffer offre;
	ArrayList <DBoffer> offerList = new ArrayList<DBoffer>();
	DBcustomer customerData;
	DBcustomer customerInfo;
	Offer contract;
	boolean isAltContract;
	int fund = 0;
	MCS problem;
	Offer problematicOffer;
	
	public COM(){}
	public COM(Object conf) {
		super(conf);
		// TODO Auto-generated constructor stub
	}

							// **** Advertising Methods **** //

	public void sendAdvertising(){
		if (dbResp != null) {
	    	DBoffer rawOffer = (DBoffer) dbResp.getContent()[0];
	    	// 		prepare the offer message
	    	Offer offre = new Offer();
	    	offre.number = rawOffer.getOffer_id();
	    	offre.object.price = rawOffer.getPrice();
	    	offre.object.quality = rawOffer.getProduct_id().getQuality();
	    	offre.object.time = rawOffer.getTime(); 
			offre.object.type = rawOffer.getProduct_id().getType().getObj_type();
			offre.seller = getAgentAddressIn(AGR.COMMUNITY, AGR.EX_GROUP, AGR.COM_EX_ROLE);
			dialog = new EnumMessage<Conversation>(Conversation.offer,offre);		
			//		sending the offer to any customer
			sendMessage (AGR.COMMUNITY, AGR.EX_GROUP, AGR.CUS_EX_ROLE, dialog);
			}
	}
	public void advertising()
	{
		if (timer<0) {
			reqDB = new EnumMessage<Queries>(Queries.OFFER_DATA_REQUEST);
			requestDBandWait(reqDB,Role.Com.Adv.CHECK);
		}
		else switch (nextStep){
					case Role.Com.Adv.CHECK:{
						dbResp = checkDBResponse(Queries.OFFER_DATA);
						if (dbResp != null) nextStep = Role.Com.Adv.SEND;
					}; break;	// put the offer in dbResp
					case Role.Com.Adv.SEND:{
						sendAdvertising();
						stopTimer();;
						}; break; 		// send message to the customer
					default:{};
					}
		
	}
	
							// **** Receiving Customer Methods **** //
	
	public void sendInfoReq(){
		setTimer(nbStepsToWaitCustomerResponse);
		ParamCusAgent req = new ParamCusAgent();
		reply = new EnumMessage<Conversation>(Conversation.info_request,req);
		sendReply (dialog,reply);	
	}
	public void saveCustomerData(){
		cusinfo = (ParamCusAgent) dialog.getContent()[0];
		cusID = dialog.getSender(); // ********** � supprimer ??
		reqDB = new EnumMessage<Queries>(Queries.SAVE_CUSTOMER_DATA,cusID,cusneed,cusinfo);
		code = sendMessage (AGR.COMMUNITY,AGR.IN_GROUP, AGR.DB_IN_ROLE,reqDB);		
		
	}	
	public void receivingCustomer(){
		if (timer<0) {
			dialog = null;
			dialog = getConversationMessage(Conversation.inquiry);	//receiving customer inquiry
			if (dialog != null)	{
				cusneed =  (ObjectOfValue) dialog.getContent()[0];
				sendInfoReq();
				nextStep = Role.Com.Rec.CHECK;	
			}
		}
		else switch (nextStep){
		case Role.Com.Rec.CHECK:{ 
			dialog = checkConversationMessage(Conversation.customer_info);
			if (dialog != null) nextStep = Role.Com.Rec.REQDB;
			}; break;	// put the offer in dbResp
		case Role.Com.Rec.REQDB:{ 
			saveCustomerData();
			nextStep = Role.Com.Rec.TEST;
			}; break;
		case Role.Com.Rec.TEST:{
			if (myRoles.contains(Role.Com.PRE)){
				needid = -1;
				role = Role.Com.PRE;
				nullTimer();
				nextStep = Role.Com.Pre.GET_OFFER;
				}else stopTimer();;
		};break;
		default:{};
		}
	}

							// **** Preparing offer Methods **** //
	
	public void retrieveCusDataMessage(EnumMessage<Queries> cusdata){
		
			needid = (Integer) cusdata.getContent()[1];
			cusneed = (ObjectOfValue) cusdata.getContent()[2];
			customerInfo = (DBcustomer) cusdata.getContent()[3];
			 
	    	cusID = customerInfo.getCustomer_address();
	    	cusinfo = retrieveCusInfo(customerInfo);

	}
	public Offer prepareOffer(AgentAddress cusID,
								ObjectOfValue cusneed,
								ParamCusAgent cusinfo,
								Integer needid,
								DBoffer commonOffer)
	{
		Offer offre = new Offer();	
		/*
		 * TODO: pr�parer une offre personnalis�e pour le client
		 * utiliser cusneed et cusinfo
		*/

		offre.number = commonOffer.getOffer_id();
		offre.seller = getAgentAddressIn(AGR.COMMUNITY, AGR.EX_GROUP, AGR.COM_EX_ROLE);
		offre.object.price = commonOffer.getPrice();
		offre.object.quality = commonOffer.getProduct_id().getQuality();
		offre.object.time = commonOffer.getTime(); 
		offre.object.type = commonOffer.getProduct_id().getType().getObj_type();
		offre.object.damage = commonOffer.getDamage();
		offre.object.failureRate = commonOffer.getFailureRate();
			
		/*
		 * TODO: save offre in DB with reserved indice
		 * */

		return (offre);
	}
	@SuppressWarnings("unchecked")
	public void preparingOffer()
	{
		if (getLogger() != null) getLogger().info("nextstep: "+ nextStep);
		if (timer<0) {
			requestDBandWait(new EnumMessage<Queries>(Queries.CUSTOMER_DATA_REQUEST),Role.Com.Pre.CHECK_CUS);
		}
		else switch (nextStep){
		case Role.Com.Pre.CHECK_CUS:{
			dbResp = checkDBResponse (Queries.CUSTOMER_DATA);
			if (dbResp != null) {
				boolean customerExist = (boolean)dbResp.getContent()[0];
				if (customerExist) {
					retrieveCusDataMessage(dbResp);
					nextStep = Role.Com.Pre.GET_OFFER;
				}
				else {
					nextStep = null;
					stopTimer();
				}
			}
		}; break;
		case Role.Com.Pre.GET_OFFER:{
			requestDBandWait(new EnumMessage<Queries>(Queries.OFFERS_REQUEST,cusneed),Role.Com.Pre.CHECK_OFFER);
		};break;
		case Role.Com.Pre.CHECK_OFFER:{
			dbResp = checkDBResponse (Queries.AVAILABLE_OFFERS);
			if (getLogger() != null) getLogger().info("dbResp = null");
			if (dbResp != null){
				offerList =(ArrayList<DBoffer>) dbResp.getContent()[0];
//				if (logger != null) logger.info("offerslistsize =" + offerList.size());
				if (offerList.size()==1) {
					offre = offerList.get(0);
					nextStep = Role.Com.Pre.PRE_SEND;
				}else {
					if (ontologyUse == true) {
						offre = selectOffer(offerList,customerInfo);
						nextStep = Role.Com.Pre.PRE_SEND;
					}
					else {
						quest = new EnumMessage<Collaboration>(Collaboration.SELECT_BEST_OFFER,offerList,customerInfo);
						askManAndWait(quest,Role.Com.Pre.CHECK_MAN_SELECT);
					}
				}
			}
		};break;
		case Role.Com.Pre.CHECK_MAN_SELECT:{
			agentResp = checkAgentMessage (Collaboration.BEST_OFFER);
			if (agentResp != null){
				offre = (DBoffer) agentResp.getContent()[0];
			}else if (timer == 0) {
				offre = offerList.get((int)(offerList.size()*Math.random()));
				nextStep = Role.Com.Pre.PRE_SEND;
			}
		};break;
		
		case Role.Com.Pre.PRE_SEND:{
			Offer x = prepareOffer(cusID,cusneed,cusinfo,needid,offre);
			dialog = new EnumMessage<Conversation>(Conversation.updated_offer,x);
			sendMessage(cusID, dialog);
			timer = -1;
		}; break; 		// gettingCustomerData
		default:{if (getLogger() != null) getLogger().info("ERROR : default sub-role");};
		}

	}
	
							// **** Treating Order Methods **** //
	
	public Offer getAltContract (	Offer requestedOffer,	// l'offre command�e par le client
									List<DBoffer> altOffers,// les offres alternatives
									DBcustomer cusData,		// informations sur le client
									double AvgCusScore){	// score moyen des clients
		//TODO: � completer en tenant compte des informations client pour une offre plus efficace
		if (!altOffers.isEmpty()){
			Offer contract = new Offer();
			int x = (int) (altOffers.size()*Math.random());
			contract.seller = getAgentAddressIn(AGR.COMMUNITY, AGR.EX_GROUP, AGR.COM_EX_ROLE);
			contract.object.price = altOffers.get(x).getPrice();
			contract.object.quality = altOffers.get(x).getProduct_id().getQuality();
			contract.object.time = altOffers.get(x).getTime();
			contract.object.type = altOffers.get(x).getProduct_id().getType().getObj_type();
			contract.object.damage = altOffers.get(x).getDamage();
			contract.object.failureRate = altOffers.get(x).getFailureRate();
			return contract;
		} else return null;
		
		
	}
	public boolean checkOrder(EnumMessage<Conversation> cusOrder, EnumMessage<Queries> objData){
		
		Offer requestedOffer = (Offer) cusOrder.getContent()[0];
		DBoffer offerData = (DBoffer) objData.getContent()[0];
		if (offerData.getQuantity()==0 ||
				offerData.getPrice() != requestedOffer.object.price ||
					offerData.getProduct_id().getQuality() != requestedOffer.object.quality ||
					offerData.getTime() != requestedOffer.object.time ||
					offerData.getProduct_id().getType().getObj_type() != requestedOffer.object.type
					) return false;
		else {
//			if (getLogger() != null) getLogger().info("-----------------------------------------> order is correct");
			return true;		
		}
	}
	public EnumMessage<Queries> prepapreOrderInformationRequest(EnumMessage<Conversation> customerOrder){
		Offer orderedOffer = (Offer) customerOrder.getContent()[0];
		EnumMessage<Queries> reqDB = new EnumMessage<Queries>(Queries.OBJECT_DATA_REQUEST,
													orderedOffer,
													customerOrder.getSender());
		return reqDB;
	}
	public void treatingOrder()
	{
		if (timer <0){
			dialog = null;
			dialog = getConversationMessage(Conversation.order);
			if (dialog != null) requestDBandWait(prepapreOrderInformationRequest(dialog),Role.Com.Tre.CHECK);
			}
		else switch (nextStep){
		case Role.Com.Tre.CHECK:{
			dbResp = checkDBResponse(Queries.OBJECT_DATA);
			if (dbResp != null) nextStep = Role.Com.Tre.CHECK_ORD; 
			};break;
		case Role.Com.Tre.CHECK_ORD:{
				cusID = dialog.getSender();
				contract = null;
				if (checkOrder(dialog, dbResp)){
					contract = (Offer) dialog.getContent()[0];
					isAltContract = false;
					nextStep = Role.Com.Tre.SAVE_CONT;
				}
				else{
					customerData = (DBcustomer) dbResp.getContent()[1];
					reqDB = new EnumMessage<Queries>(Queries.ALTERNATIVE_OFFER_DATA_REQUEST,dialog.getContent()[0]);
					requestDBandWait(reqDB,Role.Com.Tre.CHECK_ALT);
				}
			
		};break;
		case Role.Com.Tre.CHECK_ALT:{
			dbResp = checkDBResponse(Queries.ALTERNATIVE_OFFER_DATA);
			if (dbResp != null){
				Offer requestedOffer = (Offer) dialog.getContent()[0];
				double AvgCusScore = (double) dialog.getContent()[1];
				@SuppressWarnings("unchecked")
				List<DBoffer> altOffers = (List<DBoffer>) dbResp.getContent()[0];
				contract = getAltContract(requestedOffer,altOffers,customerData,AvgCusScore);
				isAltContract = true;
				nextStep = Role.Com.Tre.SAVE_CONT;
			}else if (timer >= 0 ) decrementTimer();
			
		};break;
		case Role.Com.Tre.SAVE_CONT:{
			EnumMessage<Queries> req = new EnumMessage<Queries>(Queries.SAVE_ORDER,dialog.getSender(),contract);
			requestDBandWait(req,Role.Com.Tre.CHECK_NUM);
			};break;
		case Role.Com.Tre.CHECK_NUM:{
			dbResp = checkDBResponse(Queries.ORDER_DATA);
			if (dbResp != null){
				contract.number = (Integer) dbResp.getContent()[0];
				nextStep = Role.Com.Tre.SEND;
			}
		};break;
		case Role.Com.Tre.SEND:{
			if (contract != null && isAltContract) // if the contract is alternative
				reply = new EnumMessage<Conversation>(Conversation.contract,null,contract);
			// if the contract is the original requested or is rejected (contract is null)
			else reply = new EnumMessage<Conversation>(Conversation.contract,contract,null);
			sendMessage(cusID,reply);
			stopTimer();;
		};break;
		default:{};
		}
	}
	
								// **** Billing Methods **** //
	public void checkOrderState(OrderState os){
		switch (os){
		
			case booked:{
		
				};break;
			case cancelled:{
			
				};break;		
			case awaiting_delivery:{
				fund += ((Offer) dialog.getContent()[0]).object.price;
				};break;
			case delivered :{
			
				};break;
			default : {
				if (os != null) if (getLogger() != null) getLogger().info("error : unknown OrderState");
				};break;
		}
	}
	public void billing()
	{
		if (timer <0){
			dialog = null;
			dialog = getConversationMessage(Conversation.payment);
			if (dialog != null) {
				int orderID = ((Offer)dialog.getContent()[0]).number;
				EnumMessage<Queries> query;
				query = new EnumMessage<Queries>(Queries.UPDATE_ORDER_DELIVERY,orderID);
				requestDBandWait(query,Role.Com.Bil.CHECK_ORD);
			}
		}
		else switch (nextStep){
		case Role.Com.Bil.CHECK_ORD:{
			OrderState newState = null;
			dbResp = checkDBResponse(Queries.ORDER_STATE_AWAITING_DELIVERY);
			if (dbResp != null) {
				newState = (OrderState)dbResp.getContent()[0];
				sendReply(dialog,new EnumMessage<Conversation>(Conversation.payment_state,newState));
				checkOrderState(newState);
				stopTimer();
			} else if (timer >= 0 ) decrementTimer();else {
				sendReply(dialog,new EnumMessage<Conversation>(Conversation.payment_state,newState));
				checkOrderState(newState);
				} 
			};break;
		default:{};
		}
	}
	
								// **** Delivering Methods **** //
	public ObjectOfValue prepareOOV(DBorder orderToPrepare){
		ObjectOfValue good = new ObjectOfValue();
		good.price = orderToPrepare.getOffer_id().getPrice();
		good.quality = orderToPrepare.getOffer_id().getProduct_id().getQuality();
		good.time = orderToPrepare.getOffer_id().getTime();
		good.type = orderToPrepare.getOffer_id().getProduct_id().getType().getObj_type();
		good.damage = orderToPrepare.getOffer_id().getDamage();
		good.failureRate = orderToPrepare.getOffer_id().getFailureRate();
		return good;
	}
	public void delivering()
	{
		
		// set a timer and send the Object to the customer when the timer ends
		
		if (getLogger() != null) getLogger().info("nextstep: "+ nextStep);
		if (timer<0) {
			requestDBandWait(new EnumMessage<Queries>(Queries.GET_DEL_ORDER),Role.Com.Del.CHECK_DEL_ORDER);
		}
		else switch (nextStep){
		case Role.Com.Del.CHECK_DEL_ORDER:{
			dbResp = checkDBResponse (Queries.DELIVERY_VOUCHER);
			if (dbResp != null){
				DBorder delVoucher = (DBorder)dbResp.getContent()[0];
				ObjectOfValue oov = prepareOOV(delVoucher);
				dialog = new EnumMessage<Conversation>(Conversation.object_of_value,oov);
				sendMessage (delVoucher.getCustomer_id().getCustomer_address(), dialog);
				sendMessage (AGR.COMMUNITY,AGR.IN_GROUP,AGR.DB_IN_ROLE,
						new EnumMessage<Queries>(Queries.UPDATE_ORDER_TO_DELIVERED,delVoucher.getOrder_id()));
			}
		};break;
		default:{};break;
		}
	}
	
					// **** managing Complaints & Services Methods **** //
	public void sendRefundOrder(DBorder pOrder){
		quest = new EnumMessage<Collaboration> (Collaboration.REFUND_ORDER,pOrder);
		sendMessage (AGR.COMMUNITY,AGR.IN_GROUP, Role.Com.REF,quest);
	}
	public void delayedProblem(DBorder pOrder){
		// not yet used
	}
	public void notReceivedProblem(DBorder pOrder){
		
		OrderState oState = pOrder.getStatus();
		switch (oState){
		case booked:{
			if (getLogger() != null) getLogger().info("the order was booked but not payed");
			
			EnumMessage<Queries> reqDB = new EnumMessage<Queries>(Queries.UPDATE_ORDER_TO_CANCELLED,problematicOffer.number);
			code = sendMessage (AGR.COMMUNITY,AGR.IN_GROUP, AGR.DB_IN_ROLE,reqDB);
			if (code == ReturnCode.SUCCESS){
			reply = new EnumMessage<Conversation>(Conversation.compensation_response,MCS.cancellation);
			sendReply (dialog,reply);
			}
		}
			break;
		case cancelled:{
			reply = new EnumMessage<Conversation>(Conversation.compensation_response,MCS.reject);
			sendReply (dialog,reply);
		}
			break;
		case awaiting_delivery:{
			reply = new EnumMessage<Conversation>(Conversation.compensation_response,MCS.new_delivery);
			sendReply (dialog,reply);
		}
			break;
		case in_delivering:{
			reply = new EnumMessage<Conversation>(Conversation.compensation_response,MCS.new_delivery);
			sendReply (dialog,reply);
		}
			break;
		case delivered:{
			reply = new EnumMessage<Conversation>(Conversation.compensation_response,MCS.reject);
			sendReply (dialog,reply);
		}
			break;
		default:
			break;
		}	
	}
	public void damagedProblem(DBorder pOrder){
		OrderState oState = pOrder.getStatus();
		switch (oState){
		case delivered:{
			reply = new EnumMessage<Conversation>(Conversation.compensation_response,MCS.refunding);
			sendRefundOrder(pOrder);
			sendReply (dialog,reply);
		}break;
		default:{
			reply = new EnumMessage<Conversation>(Conversation.compensation_response,MCS.reject);
			sendReply (dialog,reply);	
		}break;
		}
	}
	public void failProblem(DBorder pOrder){
		OrderState oState = pOrder.getStatus();
		switch (oState){
		case delivered:{
			reply = new EnumMessage<Conversation>(Conversation.compensation_response,MCS.refunding);
			sendRefundOrder(pOrder);
			sendReply (dialog,reply);
		}break;
		default:{
			reply = new EnumMessage<Conversation>(Conversation.compensation_response,MCS.reject);
			sendReply (dialog,reply);	
		}break;
		}	
	}
	public void pCancelProblem(DBorder pOrder){
		OrderState oState = pOrder.getStatus();
		switch (oState){
		case booked:{
			if (getLogger() != null) getLogger().info("the order was booked but not payed");
			
			EnumMessage<Queries> reqDB = new EnumMessage<Queries>(Queries.UPDATE_ORDER_TO_CANCELLED,problematicOffer.number);
			code = sendMessage (AGR.COMMUNITY,AGR.IN_GROUP, AGR.DB_IN_ROLE,reqDB);
			if (code == ReturnCode.SUCCESS){
			reply = new EnumMessage<Conversation>(Conversation.compensation_response,MCS.cancellation);
			sendReply (dialog,reply);
			}
		}
			break;
		case cancelled:{
			reply = new EnumMessage<Conversation>(Conversation.compensation_response,MCS.reject);
			sendReply (dialog,reply);
		}
			break;
		case awaiting_delivery:{
			reply = new EnumMessage<Conversation>(Conversation.compensation_response,MCS.new_delivery);
			sendReply (dialog,reply);
		}
			break;
		case in_delivering:{
			reply = new EnumMessage<Conversation>(Conversation.compensation_response,MCS.new_delivery);
			sendReply (dialog,reply);
		}
			break;
		case delivered:{
			reply = new EnumMessage<Conversation>(Conversation.compensation_response,MCS.reject);
			sendReply (dialog,reply);
		}
			break;
		default:
			break;
		}	
	}
	public void evaluateProblem(DBorder pOrder){
		switch (problem){
		case no_problem: if (getLogger() != null) getLogger().info("error : msc has been requested for no_problem"); break;
		case delayed: delayedProblem(pOrder); break;			// <--- not yet used
		case not_received: notReceivedProblem(pOrder); break;
		case damaged: damagedProblem(pOrder); break;
		case fail: failProblem(pOrder); break;
		case payment_cancellation: pCancelProblem(pOrder); break;
		default: if (problem != null) if (getLogger() != null) getLogger().info("error : unknown problem"); break;
	}
	}
	public void managingCS(){
				
		if (timer < 0){
		dialog = null;
		dialog = getConversationMessage(Conversation.complaint);
		if (dialog != null){
//			cusID = dialog.getSender();
			problematicOffer = (Offer) dialog.getContent()[0];
			problem = (MCS) dialog.getContent()[1];
			reqDB = new EnumMessage<Queries>(Queries.GET_ORDER,problematicOffer.number);
			requestDBandWait(reqDB,Role.Com.Mcs.CHECK_ORDER_INFO);
			}
		} else {
			switch (nextStep){
			case Role.Com.Mcs.CHECK_ORDER_INFO:{
				dbResp = checkDBResponse(Queries.ORDER_INFO);
				if (dbResp != null) nextStep = Role.Com.Mcs.CHECK_ORDER_PROBLEM;
			}break;
			case Role.Com.Mcs.CHECK_ORDER_PROBLEM:{
				DBorder pOrder = (DBorder) dbResp.getContent()[0];
				if (pOrder == null ){
					if (getLogger() != null) getLogger().info("the order does not exist");
					reply = new EnumMessage<Conversation>(Conversation.compensation_response,MCS.cancellation);
					sendReply (dialog,reply);	
				}else{
					evaluateProblem(pOrder);
			}
			}break;
			default:{}break;
			}
		}
		// treating with the customer's contactingCS method:
		// assess the customer request and send refund offer , new product delivery or a reject
		// wait for customer response according to a timer
		// refund, deliver , assess again or stop negociation
		// save feedback
		
		
		// ne pas oublier d'annuler la livraison d'un objet si reclamation n�
	}
	
							// **** Refunding Methods **** //
	
	public void refunding()
	{
		quest = null;
		quest = getCollaborationMessage(Collaboration.REFUND_ORDER);
		if (quest != null){
			DBorder ord = (DBorder) quest.getContent()[0];
			int note = ord.getOffer_id().getPrice();
			fund -= note;
			dialog = new EnumMessage<Conversation>(Conversation.credit_note,note);
			sendMessage (ord.getCustomer_id().getCustomer_address(),dialog);
		}
		 
	}

}