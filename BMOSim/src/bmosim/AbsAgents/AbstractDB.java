package bmosim.AbsAgents;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import bmosim.exchange.dialog.Queries;
import bmosim.exchange.objects.ObjectOfValue;
import bmosim.exchange.objects.Offer;
import bmosim.exchange.param.ParamCusAgent;
import bmosim.hibernateDB.DBchannel;
import bmosim.hibernateDB.DBcustomer;
import bmosim.hibernateDB.DBneed;
import bmosim.hibernateDB.DBoffer;
import bmosim.hibernateDB.DBorder;
import bmosim.hibernateDB.DBproduct;
import bmosim.hibernateDB.DBtype;
import bmosim.model.AGR;
import bmosim.model.OrderState;
import madkit.kernel.Agent;
import madkit.kernel.AgentAddress;
import madkit.kernel.Message;
import madkit.message.EnumMessage;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

public abstract class AbstractDB extends Agent{

	// DataBase Properties
	
		boolean useOntology;
		
		// AGR properties
		
		String myCommunity=AGR.COMMUNITY;
		String myGroup=AGR.IN_GROUP;
		String myRole=AGR.DB_IN_ROLE; 
		
		// Start Sync & messaging
		
		protected Message m = null;
		protected EnumMessage<Queries> query;
		protected ReturnCode code;
		
		// DB access properties
		protected SessionFactory sessionFactory;
		
		// actual research variables
		
		protected int needid = 0;
		protected int cusid = 0;
		protected ObjectOfValue cusneed = new ObjectOfValue();
		protected AgentAddress cusaddr = null;
		protected ParamCusAgent cusinfo = new ParamCusAgent();
		protected OrderState savedState = null;
		protected int highlightedOffer;
		
		public AbstractDB(boolean x){
			useOntology = x;
		}

		public void activate () {
			getLogger().setLevel(Level.FINEST);
//			requestRole(AGR.COMMUNITY, AGR.GROUP, AGR.ROLE);
			requestRole(myCommunity, myGroup, myRole);	
			
//	    	BasicConfigurator.configure();
	    	sessionFactory = new Configuration().configure("bmosim/hibernateDB/hibernate.cfg.xml").buildSessionFactory();

		}
		@SuppressWarnings("unchecked")
		public void live () {
			while (true){
				query = null;
				query =(EnumMessage<Queries>) waitNextMessage();
				proceedEnumMessage(query);
//			query = (EnumMessage<Queries>) waitNextMessage(new TypeFilter("enum"));
//			switch (query.getCode()){
//	//----------------------------------------------------------------------------------------------------
//			case OFFER_DATA_REQUEST:{
//				if (logger != null) logger.info("offer_data_request");
//				offerDataRequest();
//			};break;
//	//----------------------------------------------------------------------------------------------------		
//			case SAVE_CUSTOMER_DATA:{
//				if (logger != null) logger.info("save_customer_data");
//				saveCustomerData();
//			};break;
//	//----------------------------------------------------------------------------------------------------	
//			case CUSTOMER_DATA_REQUEST:{
//				if (logger != null) logger.info("customer_Data_request");
//				customerDataRequest();
//			};break;
//	//----------------------------------------------------------------------------------------------------
//			case OBJECT_DATA_REQUEST:{
//				if (logger != null) logger.info("object_data_request");
//				objectDataRequest();
//			};break;
//	//----------------------------------------------------------------------------------------------------
//			case ALTERNATIVE_OFFER_DATA_REQUEST:{
//				if (logger != null) logger.info("alternative_offer_data_request");
//				alternativeOfferDataRequest();
//			};break;
//	//----------------------------------------------------------------------------------------------------
//			case SAVE_ORDER:{
//				if (logger != null) logger.info("save_object_booking");
//				Integer id = saveOrder();
//				sendReply (query,new EnumMessage<Queries>(Queries.ORDER_DATA,id));
//			};break;
//	//----------------------------------------------------------------------------------------------------
//			case UPDATE_ORDER:{
//				if (logger != null) logger.info("save_object_order");
//				updateOrder((int)query.getContent()[0], (OrderState)query.getContent()[1]);
//			};break;
//	//----------------------------------------------------------------------------------------------------
//			case SAVE_FEEDBACK:{
//				if (logger != null) logger.info("save_feedback");
//				saveFeedback();
//			};break;
//	//----------------------------------------------------------------------------------------------------
////			case save_offer:{
////				if (logger != null) logger.info("save_offer");
////				save_offer();
////			};break;
//	//----------------------------------------------------------------------------------------------------
//			default:{};
//			}
			}
		}
		public void end () {
			sessionFactory.close();
		}
		public abstract void offerDataRequest();
		public abstract void saveCustomerData(AgentAddress cusID,ObjectOfValue cusneed,ParamCusAgent cusinfo);
		public abstract void customerDataRequest();
		public abstract void objectDataRequest(Offer ordredOffer, AgentAddress cusAddress );
		public abstract void alternativeOfferDataRequest(Offer requestedOffer);
		public abstract void saveOrder(AgentAddress addr, Offer cont);
		public abstract void updateOrderDelivery(int ordNb);
		public abstract void updateOrderToDelivering (int ordNb);
		public abstract void updateOrderToDelivered (int ordNb);
		public abstract void saveFeedback();
		public abstract void saveType(DBtype type);
		public abstract void saveProduct(DBproduct product);
		public abstract void saveOffer(DBoffer offre);
		public abstract void offersRequest(ObjectOfValue cusneed);
		
		public abstract boolean findCus(int x);
		public abstract void updateCusNeed(DBcustomer y);
		public abstract DBneed gettingNotTreatedNeed();
		
		public abstract void getCustomer(AgentAddress cusAddr);
//		public abstract void getCustomers();//brahim
		
		

		public DBchannel gettingChannel(int iD){
			Session session = sessionFactory.openSession();
			DBchannel db = session.get(DBchannel.class,iD).clone();
			session.close();
			return db;
		}
	    public DBcustomer gettingCustomer(int cusID){
	    	Session session = sessionFactory.openSession();
	    	System.out.println("cusid: "+cusID);
	    	DBcustomer db = null;
	    	if (session.get(DBcustomer.class,cusID) != null) db = session.get(DBcustomer.class,cusID).clone();
	    	session.close();
	    	return db;
	    }
	    /*brahim*/

		private static <T> List<T> loadAllData(Class<T> type, Session session) {
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<T> criteria = builder.createQuery(type);
			criteria.from(type);
			List<T> data = session.createQuery(criteria).getResultList();
			return data;
		}
		public ArrayList<DBcustomer> gettingCustomers(){
			Session session = sessionFactory.openSession();
			ArrayList<DBcustomer> dbs = new ArrayList<>();
			dbs.addAll(loadAllData(DBcustomer.class,session));
			session.close();
			return dbs;
		}
	    /*brahim*/
		public DBneed gettingNeed(int iD){
			Session session = sessionFactory.openSession();
			DBneed db = session.get(DBneed.class,iD).clone();
			session.close();
			return db;
		}
		public DBoffer gettingOffer(int iD){
			Session session = sessionFactory.openSession();
			DBoffer db = session.get(DBoffer.class,iD).clone();
			session.close();
			return db;
		}
		public DBorder gettingOrder(int iD){
			Session session = sessionFactory.openSession();
			DBorder db = session.get(DBorder.class,iD).clone();
			session.close();
			return db;
		}
		public DBproduct gettingProduct(int iD){
			Session session = sessionFactory.openSession();
			DBproduct db = session.get(DBproduct.class,iD).clone();
			session.close();
			return db;
		}
		public DBtype gettingType(int iD){
			Session session = sessionFactory.openSession();
			DBtype db = session.get(DBtype.class,iD).clone();
			session.close();
			return db;
		}
		
		public void setHighlightedOffer(int x){
			highlightedOffer = x;
		}

//		public <T> Object getting (Class <T> dbClass,int iD){
//	    	Session session = sessionFactory.openSession();
//	    	Object dbcus = session.get(dbClass, iD);
//	    	session.close();
//	    	return dbcus;
//	    }
}
