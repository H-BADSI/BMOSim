package bmosim.AbsAgents;

import java.util.ArrayList;
import java.util.List;
import bmosim.exchange.dialog.Collaboration;
import bmosim.exchange.dialog.Queries;
import bmosim.exchange.param.ParamManAgent;
import bmosim.hibernateDB.DBcustomer;
import bmosim.hibernateDB.DBoffer;
import bmosim.hibernateDB.DBproduct;
import bmosim.hibernateDB.DBtype;
import bmosim.model.AGR;
import bmosim.model.Role;
import madkit.message.EnumMessage;

public abstract class AbstractMAN extends AbsEtsAgent{
	
	String stEvaluationTypes;
	String stStrategytypes;
	public List<Integer> evaluationTypes = new ArrayList<Integer>();
	public List<Integer> strategytypes = new ArrayList<Integer>();
	public int leadership;
		
	EnumMessage<Queries> reqDB;
	
	public AbstractMAN(){}
	public AbstractMAN(Object conf) {
		super(conf);
		// TODO Auto-generated constructor stub
	}
	public abstract void initOffersInDB();
	public abstract void highlight();
	public void activate () {
//		setLogLevel(Level.FINEST);
		requestCGR();
		requestRole(AGR.COMMUNITY, AGR.IN_GROUP, AGR.MAN_IN_ROLE);
		requestRoles();
		setParameters();
		initOffersInDB();
		highlight();
	}
	public void setLocalSettings(){
		ParamManAgent c = (ParamManAgent)configuration;
		ontologyUse = c.ontologyUse;
		aSpeed= c.speed;
		versatility = c.versatility;
		errorRate = c.errorRate;
		myRoles.addAll(c.roles);
		evaluationTypes.addAll(c.evaluationTypes);
		strategytypes.addAll(c.strategytypes);
		leadership = c.leadership;
	}
	public void completeSettings(){
		myRoles = stringToStringList(stMyRoles);
		evaluationTypes = stringToIntList(stEvaluationTypes);
		strategytypes = stringToIntList(stStrategytypes);
	}
	public void doIt(){
		
	    switch (role){
	    case Role.Man.ANSWER:{
	    	EnumMessage<Collaboration> quest = null;
	    	quest = checkAgentMessage(Collaboration.SELECT_BEST_OFFER);
	    	if (quest != null){
	    		DBcustomer customerInformation = (DBcustomer) quest.getContent()[1];
//	    		if (logger != null) logger.info("customerInfo -----------------------------> "+customerInformation);
	    		@SuppressWarnings("unchecked")
	    		DBoffer bestOffer = selectOffer((ArrayList<DBoffer>) quest.getContent()[0],customerInformation);
	    		EnumMessage<Collaboration> answer = new EnumMessage<Collaboration>(Collaboration.BEST_OFFER,bestOffer);
	    		sendReply(quest,answer);
	    		}
	    };break;
		case Role.Man.PERF:{
			//System.out.println("performance assessment");
		};break;
		case Role.Man.STRAT:{
			//System.out.println("strategy selection");
		};break;
		case Role.Man.ENAB:{
			//System.out.println("enabling agents");
		};break;
		default:{};
		}
	}
	public DBtype createObjType(int objType){
		DBtype type = new DBtype();
		type.setObj_type(objType);
		return type;
	}
	public DBproduct createProduct(int objType,String objName, int buyPrice, int sellPrice, int quality, int prodQuantity,
			int lifeTime){
		DBproduct product = new DBproduct();
		product.setProduct_name(objName);
		product.setBuy_price(buyPrice);
		product.setSell_price(sellPrice);
		product.setQuality(quality);
		product.setQuantity(prodQuantity);
		product.setTime_of_life(lifeTime);
		product.setType(createObjType(objType));
		return product;
	}
	public DBproduct createProduct(DBtype objType,String objName, int buyPrice, int sellPrice, int quality, int prodQuantity,
			int lifeTime){
		DBproduct product = new DBproduct();
		product.setProduct_name(objName);
		product.setBuy_price(buyPrice);
		product.setSell_price(sellPrice);
		product.setQuality(quality);
		product.setQuantity(prodQuantity);
		product.setTime_of_life(lifeTime);
		product.setType(objType);
		return product;
	}
	public DBoffer createOffer(int objType,String objName, int buyPrice, int sellPrice, int quality, int prodQuantity,
			int lifeTime, int offerQuantity,int offerPrice,int deliveryTime){
		DBoffer offre = new DBoffer();
		offre.setProduct_id(createProduct(objType, objName, buyPrice, sellPrice, quality, prodQuantity, lifeTime));
		offre.setQuantity(offerQuantity);
		offre.setPrice(offerPrice);
		offre.setTime(deliveryTime);
		return offre;
	}
	public DBoffer createOffer(DBproduct product, int offerQuantity,int offerPrice,int deliveryTime){
		DBoffer offre = new DBoffer();
		offre.setProduct_id(product);
		offre.setQuantity(offerQuantity);
		offre.setPrice(offerPrice);
		offre.setTime(deliveryTime);
		return offre;
	}
	public void addOfferInDB( int objType,String objName, int buyPrice, int sellPrice, int quality, int prodQuantity,
			int lifeTime, int offerQuantity,int offerPrice,int deliveryTime ){
				
		DBoffer offre = createOffer(objType, objName, buyPrice, sellPrice, quality, prodQuantity,
				lifeTime, offerQuantity, offerPrice, deliveryTime);
		saveToDB(Queries.SAVE_OFFER,offre);
	}
	public void addOfferInDB(DBproduct product, int offerQuantity,int offerPrice,int deliveryTime ){
				
		DBoffer offre = createOffer(product, offerQuantity, offerPrice, deliveryTime);
		saveToDB(Queries.SAVE_OFFER,offre);
	}
	public void addOfferInDB(DBoffer offre){
		saveToDB(Queries.SAVE_OFFER,offre);
	}
	public void highlightAnOffer(int x){
		reqDB = new EnumMessage<Queries>(Queries.SET_HIGHLIGHTED_OFFER,x);
		sendMessage(AGR.COMMUNITY,AGR.IN_GROUP,AGR.DB_IN_ROLE,reqDB);
	}


}
