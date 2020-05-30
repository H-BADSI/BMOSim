package bmosim.agents;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import bmosim.AbsAgents.AbsBMOAgent;
import bmosim.exchange.dialog.Conversation;
import bmosim.exchange.filters.ConversationEnumFilter;
import bmosim.exchange.objects.ObjectOfValue;
import bmosim.exchange.objects.Offer;
import bmosim.exchange.objects.Seller;
import bmosim.exchange.param.ParamCusAgent;
import bmosim.model.AGR;
import bmosim.model.MCS;
import bmosim.model.OrderState;
import bmosim.model.Role;
import madkit.kernel.*;
import madkit.message.EnumMessage;
import madkit.message.ObjectMessage;

public class CUS extends AbsBMOAgent{

	// Customer AGR parameters
	
	String myGroup = AGR.EX_GROUP;
	String myRole = AGR.CUS_EX_ROLE;
	
	// Customer Agent parameters
	int budgetAllowed;
	int budget;
	int customerAge;
	int qualityNeeded;
	int timeToDelivery;
	String stObjectTypes;
	String stChannelsUsed;
	List<Integer> objectTypes = new ArrayList<Integer>();
	List<Integer> channelsUsed = new ArrayList<Integer>();
	
	ArrayList<Offer> knownOffers = new ArrayList<Offer>();
	ArrayList<Offer> tmpKnownOffers = new ArrayList<Offer>();
	
	ArrayList<Seller> knownSellers = new ArrayList<Seller>();
	ObjectOfValue objectNeeded = new ObjectOfValue();
	
	// Optional parameters
	int coefficient = 12;
	int deliveryCoefficient = 2;
	double qualityTolerance = 1;
	double timeTolerance = 1.5;
	int intensity = 1000;	// this reduce the life time of the object
	int moreSatisfactionTime = 1000;	//additional lifetime use that increase the seller's score
	int timeToWaitResponse;
	int nbOfSendedInquiriesForOneNeed = 0;
	int nbOfInfoReqForOneNeed = 0;
	int nbInquiryPenalties = 0;
	int nbOfUpdatedOffersForOneNeed = 0;
	int nbUpdateOffersPenalties = 0;
	
	// Start Sync and behavior parameters
	int lifeTime;
	MCS problem;
	int addScore;
	ReturnCode code;
	
	// Communication
	
	EnumMessage<Conversation> n;
	ObjectMessage<ParamCusAgent> conf;
	
	ConversationEnumFilter filter;
	EnumMessage<Conversation> dialog;
	EnumMessage<Conversation> reply;
	
	Offer offre;
	Offer ordredOffer;
	Offer satisfyingOffer;
	ObjectOfValue obj = null;
	
	//probe parameters

	int nbSatisfyingOffers = 0;
	int nbAcceptableOffers = 0;
	int nbUnacceptableOffers = 0;
	
	Double totalUpdateWaitingTime = 0.0;
	Double nbUpdatedOffers = 0.0;
	Double waitingTimeAVG = 0.0;
	
	int nbOrders = 0;
	int nbOfPurchases = 0;
			
	
	public CUS(){
		
	}
	public CUS(Object conf) {
		super(conf);
		configuration = conf;
	}

	public void activate () {
		getLogger().setLevel(Level.FINEST);
		requestCGR();
		requestRole(AGR.COMMUNITY,AGR.EX_GROUP,AGR.CUS_EX_ROLE);
		setParameters();
	}
	public void setLocalSettings(){
		
		ParamCusAgent c = (ParamCusAgent) configuration;
		budgetAllowed = c.budget;
		customerAge = c.age;
		qualityNeeded = c.quality;
		timeToDelivery = c.time;
		objectTypes.addAll(c.objectTypes);
		channelsUsed.addAll(c.channels);

	}
	public void completeSettings(){
		objectTypes = stringToIntList(stObjectTypes);
		channelsUsed = stringToIntList(stChannelsUsed);
		timeToWaitResponse = timeToDelivery*coefficient;
		budget = budgetAllowed;

	}
	@SuppressWarnings("unchecked")
	public EnumMessage<Conversation> getConversationMessage(Conversation x){
		filter = new ConversationEnumFilter(x);
		EnumMessage<Conversation> y = null;
		y = (EnumMessage<Conversation>) nextMessage(filter);
		return y;
	}
	public void doThing() {
		double scoreSum = 0.0;
		for (int j=0; j< knownSellers.size();j++) scoreSum += knownSellers.get(j).score ;
		scoreSum = scoreSum / knownSellers.size();
//		if (logger != null) logger.info(" "+knownSellers.size());
		purgeMailBox_ExceptOffers();
		nextStep = "init";
		doIt();
	}
	public void doIt(){
//		if (logger != null) logger.info("hachcode: "+getAgentAddressIn(AGR.COMMUNITY, AGR.EX_GROUP, AGR.CUS_EX_ROLE).hashCode());
		switch (nextStep){
		case "init":{				//____________INIT_____________________
			if (getLogger() != null) getLogger().info(" Init ");
			problem = MCS.no_problem ;
			ordredOffer = null;
			addScore = 0;	// this value will be add to the seller's score
			satisfyingOffer = null;
			offerListener();
			needAssessment();
			if (knownSellers.size()>0){
				nbOfSendedInquiriesForOneNeed = 0;
				sendInquiries(objectNeeded);
				timer = timeToWaitResponse;
				if (getLogger() != null) getLogger().info(" timer test "+timer);
				tmpKnownOffers.clear();
				nextStep = "check_inform";
				}
		}; break;
		case "check_inform":{		//____________CHECK_INFORM_____________
			
			if (timer > 0){
				if (getLogger() != null) getLogger().info(" wait_inquiries_feedback "+ timer);
				timer--;
			} else {
				if (getLogger() != null) getLogger().info(" check_inform "+timer);
				dialog = null;
				nbOfInfoReqForOneNeed = 0;
				do{
					dialog = getConversationMessage(Conversation.info_request);
					if (dialog != null) {
						sendCustomerInfo(dialog);
						nbOfInfoReqForOneNeed++;
					}
					}while(dialog != null);
				nbInquiryPenalties = nbInquiryPenalties + nbOfSendedInquiriesForOneNeed - nbOfInfoReqForOneNeed;
				nbOfUpdatedOffersForOneNeed = 0;
				timer = timeToWaitResponse;
				nextStep = "check_update";
//				while (getConversationMessage(Conversation.updated_offer) != null);   //<-- purge
			}			
		}; break;
		
		case "check_update":{
			if (getLogger() != null) getLogger().info(" check_update "+timer);
			if (timer > 0) {
				newOfferListener();
				timer--;
			}else {
				
				if (nbOfInfoReqForOneNeed > nbOfUpdatedOffersForOneNeed) nbUpdateOffersPenalties = nbOfInfoReqForOneNeed - nbOfUpdatedOffersForOneNeed ;
				totalUpdateWaitingTime += nbUpdateOffersPenalties*timeToWaitResponse;
				offersEvaluation (tmpKnownOffers);
				nextStep = "compare_n_order";
			}
		}; break;
				
		case "compare_n_order":{				//____________UPDATE___________________
			if (getLogger() != null) getLogger().info(" compare_n_order ");
			offersComparison(knownOffers);
//			if (logger != null) logger.info(" offer comparison out, satisfying offer is" +satisfyingOffer );
			if (satisfyingOffer == null) nextStep = "init";
			else{
				sendOrder(satisfyingOffer);
				nbOrders++;
				timer =  (int) (timeToWaitResponse);                   //  <--- a revoir
				nextStep = "check_contract";
			}
		}; break;
		case "check_contract":{
			if (getLogger() != null) getLogger().info(" check_contract "+timer);
			dialog = null;
			dialog = getConversationMessage(Conversation.contract);
			if (dialog != null){
				if ( checkContract(satisfyingOffer,dialog) ) nextStep = "pay";
				else {
					addScore--;
					updateScore(satisfyingOffer.seller,addScore);
					nextStep = "init";
					}
			}else
				if (timer > 0)	timer--; 
				else {
					addScore--;
					updateScore(satisfyingOffer.seller,addScore);
					nextStep = "init";
					}
		};break;
		case "pay":{
			if (getLogger() != null) getLogger().info(" pay ");
			code = null ;
			sendPayment(ordredOffer);
			timer =  (int) (timeToWaitResponse);					//  <--- � revoir
			nextStep = "check_payment";
		};break;
		case "check_payment":{
			if (getLogger() != null) getLogger().info(" check_payment "+timer);
			dialog = null;
			dialog = getConversationMessage(Conversation.payment_state);
			if (dialog != null) {
				OrderState os = (OrderState) dialog.getContent()[0];
				if (getLogger() != null) getLogger().info(os.toString());
				if (os == OrderState.awaiting_delivery){
					timer = ordredOffer.object.time*deliveryCoefficient;
					nbOfPurchases++;
					nextStep = "check_normal";
				}else{
					budget += ordredOffer.object.price;
					addScore--;
					updateScore(satisfyingOffer.seller,addScore);
					nextStep = "init";
				}
			}else 
				if (timer > 0)	timer--; 
				else {
					addScore--;
					updateScore(satisfyingOffer.seller,addScore);
					problem = MCS.payment_cancellation;
					nextStep = "contact_cs";
					}
		};break;
		case "check_normal":{			// setting problem to 1 or let it to 0
			if (getLogger() != null) getLogger().info(" check_normal ");
			dialog = null;
			dialog = getConversationMessage(Conversation.object_of_value);
			if (dialog != null){
				addScore++;
				nextStep = "open";
			} else
				if (timer > 0)	timer--; else {
					problem = MCS.delayed ;				// not currently used
					addScore--;
					timer = satisfyingOffer.object.time*(int)((timeTolerance-1)*10);  //  <--- � revoir
					nextStep = "check_late";
					}
		};break;
		case "check_late":{				// setting problem to 2 or let it to 0 or 1
			if (getLogger() != null) getLogger().info(" check_late ");
			dialog = null;
			dialog = getConversationMessage(Conversation.object_of_value);
			if (dialog != null){
				nextStep = "open";
			} else
				if (timer > 0)	timer--; else {
					problem = MCS.not_received ;
					addScore--;
					updateScore(satisfyingOffer.seller,addScore);
					nextStep = "contact_cs";
					}
		};break;
		case "open":{				// setting problem to 3 or let it to 0 or 1
			if (getLogger() != null) getLogger().info(" open ");
			obj = openObject(dialog);
			if (obj.damage) {
				problem = MCS.damaged ;
				addScore-- ;
				nextStep = "contact_cs";
				}
			else {
				lifeTime = ((obj.quality-obj.failureRate+1)*100)-intensity;		// t::object lifetime 
				if (lifeTime>0){
					timer = lifeTime;
					nextStep = "use";
					}
				else {
					if (getLogger() != null) getLogger().info("warning: object lifetime = 0 ");
					problem = MCS.fail;
					addScore--;
					nextStep = "contact_cs";
				}
			}
		};break;
		case "use":{			//use the object and set problem to 4 if a failure was happened
			if (getLogger() != null) getLogger().info(" use ");
			if (timer > 0)	timer--; 
			else {
				int x = (objectNeeded.quality*100);							// x::needed lifetime
				if ( lifeTime >= x) {
					addScore++;
					if (getLogger() != null) getLogger().info("addscore++ now");
					if ((lifeTime-x)>moreSatisfactionTime) addScore++;
					updateScore(satisfyingOffer.seller,addScore);
					nextStep = "init";
				}
				else{
					problem = MCS.fail ;
					addScore--;
					nextStep = "contact_cs";
				}
			}
		};break;
		case "contact_cs":{
			if (getLogger() != null) getLogger().info(" contact_cs ");
			contactingCS(problem);
			nextStep = "check_compensation";
		}
		case "check_compensation":{
			if (getLogger() != null) getLogger().info(" check_compensation ");
			dialog = null;
			dialog = getConversationMessage(Conversation.compensation_response);
			if (dialog != null){
				MCS info = (MCS)dialog.getContent()[0];
				switch (info){
				case cancellation:{
					addScore--;
					budget += ordredOffer.object.price;
					nextStep = "init";
				}
					break;
				case new_delivery:{
					if (getScore(ordredOffer)>=0){
						timer = ordredOffer.object.time*deliveryCoefficient;
						nextStep = "check_normal";
						}
					else nextStep = "init";
				}
					break;
				case refunding:{
					timer = timeToWaitResponse;
					nextStep = "check_refund";			// --> or another behavior 
				}
					break;
				case new_offer:{
														// not yet used
				}
					break;
				case reject:
					addScore--;
					budget += ordredOffer.object.price;
					nextStep = "init";					// --> or another behavior 
					break;
				default: if (getLogger() != null) getLogger().info(" Error: bad MCS ");
					break;
				}
				updateScore(ordredOffer.seller,addScore);
			}else{
				if (timer < 0) {
					addScore--;
					updateScore(ordredOffer.seller,addScore);
					nextStep = "contact_cs";			//<--- � revoir nombre d'essais de n�gociation
				}
				else timer--;
			}
			
		};break;
		case "check_refund":{
			if (getLogger() != null) getLogger().info(" check_refund ");
			dialog = null;
			dialog = getConversationMessage(Conversation.credit_note);
			if (dialog != null){
				int refund = (int) dialog.getContent()[0];
				if (refund < ordredOffer.object.price  ) {
					addScore--;
					updateScore(ordredOffer.seller,addScore);
					nextStep = "init";
				}
				else {
					budget += refund;
					nextStep = "init";
				}
			}else{
				if (timer < 0) {
					addScore--;
					updateScore(ordredOffer.seller,addScore);
					nextStep = "init";
				}
				else timer--;
			}
		};break;
		default:{};
		}				
	}
	private void purgeMailBox_ExceptOffers(){
		while (getConversationMessage(Conversation.info_request) != null);
		while (getConversationMessage(Conversation.updated_offer) != null);
		while (getConversationMessage(Conversation.contract) != null);
		while (getConversationMessage(Conversation.compensation_response) != null);
	}
	private void offerSaver (EnumMessage<Conversation> mess){
		if (mess!=null) {
			offre =(Offer)mess.getContent()[0];
			if (isSellerKnown(offre.seller) < 0) { 
				Seller seller = new Seller();
				seller.id = offre.seller;
				seller.score = 0;
				knownSellers.add(seller);
				}		
			if (!knownOffers.contains(offre)) {
				knownOffers.add(offre);
				}
			}
	}
	private void offerTmpSaver(EnumMessage<Conversation> mess){
		if (mess!=null) {
			Offer x =(Offer)mess.getContent()[0];		
			if (!tmpKnownOffers.contains(x)) tmpKnownOffers.add(x);
			}
	}
	private void offerListener ()
	{
		n = getConversationMessage(Conversation.offer);
		while (n != null) {
			offerSaver(n);
			n = getConversationMessage(Conversation.offer);
		}
	}
	private void newOfferListener ()
	{
		n = null;
		n = getConversationMessage(Conversation.updated_offer);
		while (n != null) {
			nbOfUpdatedOffersForOneNeed++;
			nbUpdatedOffers++;
			totalUpdateWaitingTime += timeToWaitResponse-timer;
			if (getLogger() != null) getLogger().info("totalUpdateWaitingTime = "+totalUpdateWaitingTime);
			waitingTimeAVG = totalUpdateWaitingTime/nbUpdatedOffers;
			offerSaver(n);
			offerTmpSaver(n);
			Offer offre = (Offer) n.getContent()[0];
			if (getLogger() != null) getLogger().info("offer received (pqtt): "+offre.object.price+" "+offre.object.quality+" "+offre.object.time+" "+offre.object.type);
			knownSellers.get(isSellerKnown(offre.seller)).score++;
			n = getConversationMessage(Conversation.updated_offer);
		}
	}
	private int isSellerKnown(AgentAddress id){
		boolean x = false;
		int sellerIndex = -1;
		for (int i = 0 ; i < knownSellers.size() ; i++) 
					if (knownSellers.get(i).id == id) { x = true; sellerIndex = i;}
		if (x) return sellerIndex; else return -1;
	}
	private void needAssessment ()
	{
		//use agent properties to define objectNeeded property
		
		objectNeeded.price =budget;
		objectNeeded.quality =qualityNeeded;
		objectNeeded.time =timeToDelivery;
		int y = objectTypes.size();
		int x =y+1;
		while (x==y+1) {x = (int) (y*Math.random()+1);}
		objectNeeded.type =objectTypes.get(x-1);
		if (getLogger() != null) getLogger().info("need assessment (pqtt):  "+objectNeeded.price+" "+objectNeeded.quality+" "+objectNeeded.time+" "+objectNeeded.type);
	}
	private void sendInquiries(ObjectOfValue inq){    // sending inquiries to all known Sellers
		dialog = new EnumMessage<Conversation>(Conversation.inquiry,inq);
		int i;
	//	for (i = 0 ; i < knownSellers.size() ; i++)	sendMessage(knownSellers.get(i).id,dialog);
		for (i = 0 ; i < knownSellers.size() ; i++)	{
			sendMessage(AGR.COMMUNITY,AGR.EX_GROUP,Role.Com.REC,dialog);
			nbOfSendedInquiriesForOneNeed++;
		}
		
	}
	private void sendCustomerInfo(EnumMessage<Conversation> x){
		ParamCusAgent infocus = (ParamCusAgent) x.getContent()[0];
		/*
		 * TODO: preparing message content for sending partial or updated informations		 
		 * */
		infocus.budget = budgetAllowed;
		infocus.age = customerAge;
		infocus.quality = qualityNeeded;
		infocus.time = timeToDelivery;
		infocus.objectTypes = objectTypes;
		infocus.channels = channelsUsed;
		
		reply = new EnumMessage<Conversation>(Conversation.customer_info,infocus);
		sendReply (dialog,reply);
		nbOfInfoReqForOneNeed++;
	}	
	private int getScore(Offer offre){
		return knownSellers.get(isSellerKnown(offre.seller)).score;
	}	
	private void offersComparison (ArrayList <Offer> offersList )
	{
		//read offersList, compare them , and choose to buy or to re-assess
		satisfyingOffer = null;
		ArrayList<Integer> sufficiantOffers = new ArrayList<Integer>();
		ArrayList<Integer> insufficiantOffers = new ArrayList<Integer>();
		for (int i = 0 ; i < offersList.size() ; i++){
			offre = offersList.get(i);
			int score = getScore(offre);			
			if (score >= 0 &&	offre.object.type == objectNeeded.type && offre.object.price <= objectNeeded.price)
			{
				if ( offre.object.quality <= objectNeeded.quality &&
						offre.object.time <= objectNeeded.time) sufficiantOffers.add(i);
				else if (offre.object.quality <= objectNeeded.quality+qualityTolerance &&
						offre.object.time <= (objectNeeded.time*timeTolerance)) insufficiantOffers.add(i);
			}
		}
		if (!sufficiantOffers.isEmpty()){					//************choosing the best offer******************
			satisfyingOffer = offersList.get(chooseOffer(sufficiantOffers));
			knownSellers.get(isSellerKnown(satisfyingOffer.seller)).score++;	//TODO: � verifier
		} else {	if (!insufficiantOffers.isEmpty()){			//************choosing the least acceptable offer******
					satisfyingOffer = offersList.get(chooseOffer(insufficiantOffers));
						}else {
								// incrementer un compteur pour revoir les besoins ou les exigeances.
							}
				}
	}
	private void offersEvaluation (ArrayList <Offer> offersList)
	{
		ArrayList<Integer> sufficiantOffers = new ArrayList<Integer>();
		ArrayList<Integer> insufficiantOffers = new ArrayList<Integer>();
		for (int i = 0 ; i < offersList.size() ; i++){
			offre = offersList.get(i);
			int score = getScore(offre);			
			if (score >= 0 &&	offre.object.type == objectNeeded.type && offre.object.price <= objectNeeded.price)
			{
				if ( offre.object.quality <= objectNeeded.quality &&
						offre.object.time <= objectNeeded.time) sufficiantOffers.add(i);
				else if (offre.object.quality <= objectNeeded.quality+qualityTolerance &&
						offre.object.time <= (objectNeeded.time*timeTolerance)) insufficiantOffers.add(i);
				else nbUnacceptableOffers ++;
			}else nbUnacceptableOffers ++;
		}
		nbSatisfyingOffers += sufficiantOffers.size();
		nbAcceptableOffers += insufficiantOffers.size();
	}
	private int chooseOffer(ArrayList<Integer> canditateOffer){
		offre = knownOffers.get(canditateOffer.get(0)); 
			//		Initialization     //
			// bestOffer is initialized by anyone having the best SELLER-score in the case of scores equality
		int bestOffer = canditateOffer.get(0);
		for (int i = 0 ; i < canditateOffer.size() ; i++) 
		{
			if ( getScore(knownOffers.get(bestOffer)) < getScore(knownOffers.get(canditateOffer.get(i))) )
					bestOffer = canditateOffer.get(i);
		}
		//  calculating the best score
		double bestScore = getScore(offre)+	
				((objectNeeded.price - offre.object.price)/(objectNeeded.price*0.1)) +
				objectNeeded.quality - offre.object.quality +
				objectNeeded.time - offre.object.time;
		double score;
		for (int i = 1 ; i < canditateOffer.size() ; i++){			// comparing the sufficient offers
			score = 0;
			offre = knownOffers.get(canditateOffer.get(i));
			score = getScore(offre) +
			((objectNeeded.price - offre.object.price)/(objectNeeded.price*0.1)) +
			objectNeeded.quality - offre.object.quality +
			objectNeeded.time - offre.object.time;
			if (score > bestScore) { bestOffer = canditateOffer.get(i) ; bestScore = score; }
		}
		return bestOffer;
	}
	private void sendOrder(Offer offre){
		dialog = new EnumMessage<Conversation>(Conversation.order, offre);
		sendMessage(AGR.COMMUNITY, AGR.EX_GROUP, Role.Com.TRE, dialog);
	}
	private boolean checkContract(Offer offer, EnumMessage<Conversation> m){        // � completer 
		
		Offer contract = (Offer) m.getContent()[0];
		if (contract != null){
			if (offer.seller == contract.seller &&
					offer.object.price == contract.object.price &&
					offer.object.quality == contract.object.quality &&
					offer.object.time == contract.object.time &&
					offer.object.type == contract.object.type) 
			{
				ordredOffer = contract;
				if (getLogger() != null) getLogger().info(" customer :check_contract -- > OK");
				return true;
			}
			else {
				if (getLogger() != null) getLogger().info(" customer : contract not correct");
				return false;
			}
		}else{
			contract = (Offer) m.getContent()[1];
			if (contract == null)return false;
			else if (offer.seller == contract.seller && 
					offer.object.price >= contract.object.price &&
					offer.object.quality <= contract.object.quality &&
					offer.object.time >= contract.object.time &&
					offer.object.type == contract.object.type) {
				ordredOffer = contract;
				offerSaver(m);
				return true;
			}
			else return false;
		}
	}
	private ReturnCode sendPayment(Offer offer){
		ReturnCode ok = null;
		dialog = new EnumMessage<Conversation>(Conversation.payment, offer);
		ok = sendMessage(AGR.COMMUNITY, AGR.EX_GROUP, Role.Com.BIL,dialog);
		if ( ok != null ) budget = budget - offer.object.price;
			
		return ok;
	}
	private ObjectOfValue openObject (Message m)
	{								//check if the the object is not damaged
		@SuppressWarnings("unchecked")
		EnumMessage<Conversation> n = (EnumMessage<Conversation>) m;
		ObjectOfValue z = (ObjectOfValue)n.getContent()[0];
		return z;
	}
	private void contactingCS (MCS problem)
	{
		dialog = new EnumMessage<Conversation>(Conversation.complaint, ordredOffer, problem);
		sendMessage(AGR.COMMUNITY, AGR.EX_GROUP, Role.Com.MCS, dialog);
		timer = timeToWaitResponse;
		nextStep = "check_compensation";
			
	}
	private void updateScore (AgentAddress id, int addScore){
		int x = isSellerKnown(id);						//seller's index
		int y = knownSellers.get(x).score + addScore;	//seller's new score
		Seller seller = new Seller();
		seller.id = id;
		seller.score = y;
		knownSellers.set(x, seller);
	}
	public void payDay (){
		budget = budgetAllowed;
	}
/*	private void contactListener ()
	{

	}
	private void soundListener ()
	{
		Conversation.probing_request    <--
		Conversation.probing_answer  --->
	}
*/
}
