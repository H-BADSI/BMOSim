package bmosim.agents;

import java.util.ArrayList;
import java.util.List;

import madkit.kernel.AgentAddress;
import madkit.message.EnumMessage;

import org.hibernate.Session;

import bmosim.AbsAgents.AbstractDB;
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
import bmosim.model.OrderState;


public class DB extends AbstractDB{
		
	public DB(boolean x){
		super(x);
	}
	public void offerDataRequest(){
//		if (logger != null) logger.info("offer_data_request");
		EnumMessage<Queries> response = new EnumMessage<Queries>(Queries.OFFER_DATA,gettingOffer(highlightedOffer));
		sendReply(query,response);
	}
	public void saveUnknownCustomerDataFromOrder(AgentAddress addr, Offer order, int orderChannel){
		DBcustomer dBcustomer = new DBcustomer();
    	dBcustomer.setCustomer_address_hashCode(addr.hashCode());
    	dBcustomer.setCustomer_address(addr);
    	dBcustomer.setNb_buy(0);
    	dBcustomer.setSum_buy(0);
    	dBcustomer.setScore(0);
    	
    	DBneed n = new DBneed();
    	n.setCustomer_id(addr.hashCode());
    	n.setPrice(order.object.price);
    	n.setQuality(order.object.quality);
    	n.setTime(order.object.time);
    	n.setType(order.object.type);
    	n.setTreated(false);
    	List<DBneed> ln = new ArrayList<DBneed>();
    	ln.add(n);
    	dBcustomer.setNeeds(ln);
    	
    	DBtype t = new DBtype();
    	t.setObj_type(order.object.type);
    	List<DBtype> lt = new ArrayList<DBtype>();
   		lt.add(t);
    	dBcustomer.setTypes(lt);
    	
    	DBchannel c = new DBchannel();
    	c.setChannel_id(orderChannel);
    	List<DBchannel> lc = new ArrayList<DBchannel>();	    	
    	lc.add(c);
      	dBcustomer.setChannels(lc);
    	
        updateCusNeed(dBcustomer);
    	
	}
	public void saveCustomerData(AgentAddress cusID,ObjectOfValue cusneed,ParamCusAgent cusinfo){
//		if (logger != null) logger.info("save_customer_data");
		DBcustomer dBcustomer = new DBcustomer();
    	dBcustomer.setCustomer_address_hashCode(query.getContent()[0].hashCode());
    	dBcustomer.setCustomer_address(cusID);
    	DBneed dBneed = new DBneed();
    	dBneed.setCustomer_id(query.getContent()[0].hashCode());
//   	ObjectOfValue z = (ObjectOfValue) query.getContent()[1];
    	dBneed.setPrice(cusneed.price);
    	dBneed.setQuality(cusneed.quality);
    	dBneed.setTime(cusneed.time);
    	dBneed.setType(cusneed.type);
    	dBneed.setTreated(false);
//    	ParamCusAgent y = (ParamCusAgent) query.getContent()[2];
    	dBcustomer.setBudget(cusinfo.budget);
    	dBcustomer.setAge(cusinfo.age);
    	dBcustomer.setQuality(cusinfo.quality);
    	dBcustomer.setTime(cusinfo.time);
    	
    	List<DBtype> w = new ArrayList<DBtype>();	    	
    	for (int i=0 ; i<cusinfo.objectTypes.size() ; i++){
    		DBtype x = new DBtype();
    		x.setObj_type(cusinfo.objectTypes.get(i));
    		w.add(x);
    		}
    	dBcustomer.setTypes(w);
    	
    	List<DBchannel> v = new ArrayList<DBchannel>();	    	
    	for (int i=0 ; i<cusinfo.channels.size() ; i++){
    		DBchannel x = new DBchannel();
    		x.setChannel_id(cusinfo.channels.get(i));
    		v.add(x);
    		}
    	dBcustomer.setChannels(v);
    	
    	List<DBneed> u = new ArrayList<DBneed>();
    	u.add(dBneed);
    	dBcustomer.setNeeds(u);
    	
    	dBcustomer.setNb_buy(0);
    	dBcustomer.setSum_buy(0);
    	dBcustomer.setScore(0);
    	
        updateCusNeed(dBcustomer);
	}
	public void customerDataRequest(){
//		if (logger != null) logger.info("customer_Data_request");
		DBneed dBneed = gettingNotTreatedNeed();
		EnumMessage<Queries> response;
		code = null;
		if (dBneed != null){
			
    		needid = dBneed.getNeed_id();
    		cusneed = retrieveCusNeed(dBneed);
    		
    		cusid = dBneed.getCustomer_id();
			DBcustomer dBcus = gettingCustomer(cusid);
		
	    	response = new EnumMessage<Queries>(Queries.CUSTOMER_DATA,true,needid,cusneed,dBcus);	
			code = sendReply(query,response);
			if (code != null) markAsTreated(dBneed);
		}else {
			response = new EnumMessage<Queries>(Queries.CUSTOMER_DATA,false);	
			code = sendReply(query,response);
			}
	}
	
	public ObjectOfValue retrieveCusNeed(DBneed dBneed){
		ObjectOfValue need = new ObjectOfValue();
		need.price = dBneed.getPrice();
		need.quality = dBneed.getQuality();
		need.time = dBneed.getTime();
		need.type = dBneed.getType();
		return need;
	}
	public void markAsTreated(DBneed dbneed){
		
		dbneed.setTreated(true);
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.saveOrUpdate(dbneed);
		session.getTransaction().commit();
		session.close();
	}
	public void objectDataRequest(Offer ordredOffer, AgentAddress cusAddress ){
//		if (logger != null) logger.info("object_data_request");
		DBoffer dBoffer = gettingOffer(ordredOffer.number);
		DBcustomer dBcustomer = gettingCustomer(cusAddress.hashCode());
		if (dBcustomer == null) {
			saveUnknownCustomerDataFromOrder(cusAddress,ordredOffer,1);
		} 
		EnumMessage<Queries> response = new EnumMessage<Queries>(Queries.OBJECT_DATA, dBoffer, dBcustomer);
		sendReply(query,response);
	}
	public void alternativeOfferDataRequest(Offer requestedOffer) {
//		if (logger != null) logger.info("alternative_offer_data_request");
		Session session = sessionFactory.openSession();
		@SuppressWarnings("unchecked")
		List<DBoffer> similarOffersList = session
    			.createQuery ( "from DBoffer as offers where offers.quantity > 0 and "
    					+ "offers.product_id.type.obj_type = " + requestedOffer.object.type )
    			.list();
		
		sendReply (query,new EnumMessage<Queries> (Queries.ALTERNATIVE_OFFER_DATA,similarOffersList,getCustomersAverageScore()));
    	session.close();
	}
	public void saveOrder(AgentAddress addr, Offer cont){
//		if (logger != null) logger.info("save_object_booking");
		DBcustomer cus = gettingCustomer(addr.hashCode());
		
		DBorder order = new DBorder();
		order.setCustomer_id(cus);
		order.setOffer_id(gettingOffer(cont.number));
		order.setStatus(OrderState.booked);
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.saveOrUpdate(order);
		Integer id = order.getOrder_id();
		session.getTransaction().commit();
		session.close();
		sendReply (query,new EnumMessage<Queries>(Queries.ORDER_DATA,id));

	}
	public void setOrderStatus (int orderID, OrderState y, Queries z){
		savedState = null;
		DBorder ord = gettingOrder(orderID);
		if (ord != null) {
			savedState = ord.getStatus();
			}
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.saveOrUpdate(ord);
		session.getTransaction().commit();
		session.close();
		sendReply (query,new EnumMessage<Queries>(z,savedState));
	}
	public void updateOrderStatus (int orderID, OrderState x, OrderState y, Queries z){
		savedState = null;
		DBorder ord = gettingOrder(orderID);
		if (ord != null) {
			if (ord.getStatus() == x) ord.setStatus(y);
			else if (getLogger() != null) getLogger().info("Error: order state update failure");
			savedState = ord.getStatus();
			}
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.saveOrUpdate(ord);
		session.getTransaction().commit();
		session.close();
		sendReply (query,new EnumMessage<Queries>(z,savedState));
	}
	public void updateOrderDelivery(int ordNb){
		updateOrderStatus(ordNb,OrderState.booked,OrderState.awaiting_delivery,Queries.ORDER_STATE_AWAITING_DELIVERY);
		}
    public void updateOrderToDelivering (int ordNb){
    	updateOrderStatus(ordNb,OrderState.awaiting_delivery,OrderState.in_delivering,Queries.ORDER_STATE_IN_DELIVERY);
    }
    public void updateOrderToDelivered (int ordNb){
    	updateOrderStatus(ordNb,OrderState.in_delivering,OrderState.delivered,Queries.ORDER_STATE_DELIVERED);
    }
    public void updateOrderToCancelled (int ordNb){
    	updateOrderStatus(ordNb,OrderState.booked,OrderState.cancelled,Queries.ORDER_STATE_CANCELLED);
    }
    
	public void saveFeedback(){
//		if (logger != null) logger.info("save_feedback");
	}
	
	public void saveType(DBtype type){
//		if (logger != null) logger.info("save_type");
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.saveOrUpdate(type);
		session.getTransaction().commit();
		session.close();
	}
	public void saveProduct(DBproduct product){
//		if (logger != null) logger.info("save_product");
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.saveOrUpdate(product);
		session.getTransaction().commit();
		session.close();
	}
	public void saveOffer(DBoffer offre){
//		if (logger != null) logger.info("save_offer");
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.saveOrUpdate(offre);
		session.getTransaction().commit();
		session.close();
	}
	
	
    public boolean findCus(int x) {
    	    	
    	Session session = sessionFactory.openSession();
		DBcustomer y = null;
		y = session.get(DBcustomer.class,x);
    	session.close();
    	if (y==null) return false; else return true;
    } 
    public void updateCusNeed(DBcustomer y) {
    	
    	Session session = sessionFactory.openSession();
    	session.beginTransaction();
    	DBcustomer cus = session.get(DBcustomer.class, y.getCustomer_address_hashCode());
    	if ( cus == null ) session.saveOrUpdate(y);
    	else {
    		if (y.getBudget() != 0) cus.setBudget(y.getBudget());
    		if (y.getAge() != 0) cus.setAge(y.getAge());
    		if (y.getQuality() != 0) cus.setQuality(y.getQuality());
    		if (y.getTime() != 0) cus.setTime(y.getTime());
    		if (y.getArea() != null) cus.setArea(y.getArea());
    		if (!y.getTypes().isEmpty()) cus.setTypes(y.getTypes());
    		if (!y.getChannels().isEmpty()) cus.setChannels(y.getChannels());
    		if (y.getNb_buy() != 0) cus.setNb_buy(y.getNb_buy());
    		if (y.getSum_buy() != 0) cus.setSum_buy(y.getSum_buy());
    		if (y.getScore() != 0) cus.setScore(y.getScore());	    			
    	List<DBneed> list = cus.getNeeds();
    	int i=0;
    	boolean b = false;
    	while ( i< list.size() && b==false ) {
    		DBneed need = list.get(i);
    		if (need.getPrice() == y.getNeeds().get(0).getPrice() &&
    				need.getQuality() == y.getNeeds().get(0).getQuality() &&
    					need.getTime() == y.getNeeds().get(0).getTime() &&
    						need.getType() == y.getNeeds().get(0).getType() &&
    							need.isTreated() == false
    								) b = true; else i++;
    		}
    		if (!b){
    			list.add(y.getNeeds().get(0));
    			cus.setNeeds(list);
    			session.update(cus);
    		}
    	}
    	session.getTransaction().commit();
        session.close();
    }

    public DBneed gettingNotTreatedNeed(){
    		
    	Session session = sessionFactory.openSession();
    	@SuppressWarnings("unchecked")
		List<DBneed> x = (List<DBneed>)session
    			.createQuery("from DBneed as need where need.treated= false")
    			.setMaxResults(1)
    			.list();
    	session.close();
    	if (x.isEmpty()) return null; else return (DBneed)x.get(0);
    }
    
    public void getDelOrder(){
    	Session session = sessionFactory.openSession();
    	@SuppressWarnings("unchecked")
    	List<DBorder> x = (List<DBorder>)session
    			.createQuery("from DBorder as order where order.status = '" + OrderState.awaiting_delivery+"'")
    			.setMaxResults(1)
    			.list();
    	if (!x.isEmpty()) {
    		DBorder cOrder = x.get(0);
    		code = null;
    		code = sendReply(query,new EnumMessage<Queries>(Queries.DELIVERY_VOUCHER,cOrder));
    		if (code != null) updateOrderToDelivering(cOrder.getOrder_id());
    	}
    	session.close();
    }


    public double getCustomersAverageScore(){
    	
    	Session session = sessionFactory.openSession();
    	double x = (double) session.createQuery ("select avg(customers.score) from DBcustomer customers ").list().get(0);
    	session.close();
    	return x;
    }

    public void offersRequest(ObjectOfValue cusneed){
    	
		Session session = sessionFactory.openSession();
		@SuppressWarnings("unchecked")
		List<DBoffer> offersList = session
    			.createQuery ( "from DBoffer as offers where offers.quantity <> 0 and "
    					+ "offers.product_id.type.obj_type = " + cusneed.type + " and "
    					+ "offers.product_id.sell_price <= " + cusneed.price + " and "
    					+ "offers.product_id.quality <= " + cusneed.quality + " and "
    					+ "offers.time >= " + cusneed.time )
    			.list();
		if (offersList.isEmpty()) offersList.add(gettingOffer(highlightedOffer));
		sendReply (query,new EnumMessage<Queries> (Queries.AVAILABLE_OFFERS,offersList));
    	session.close();	
    }
    
    public void getCustomer(AgentAddress cusAddr){
    	DBcustomer cusinfo = gettingCustomer(cusAddr.hashCode());
    	sendReply(query,new EnumMessage<Queries>(Queries.CUSTOMER_INFO,cusinfo));
    }

    /*brahim*/
//	public void getCustomers(){
//		ArrayList<DBcustomer> cusinfo = gettingCustomers();
//		sendReply(query,new EnumMessage<Queries>(Queries.CUSTOMER_INFO,cusinfo));
//	}
	/*brahim*/
    
    public void getOrder(int ordID){
    	DBorder ordinfo = gettingOrder(ordID);
    	sendReply(query,new EnumMessage<Queries>(Queries.ORDER_INFO,ordinfo));
    }

    
    
    
}    
    
    
    
/*    protected void create() {
    	Book book = new Book();
        book.setTitle("Effective Java");
        book.setAuthor("Joshua Bloch");
        book.setPrice(32.59f);
     
        Session session = sessionFactory.openSession();
        session.beginTransaction();
     
        session.save(book);
     
        session.getTransaction().commit();
        session.close();
    }
 
    protected void read() {
    	Session session = sessionFactory.openSession();
    	 
        long bookId = 20;
        Book book = session.get(Book.class, bookId);
     
        System.out.println("Title: " + book.getTitle());
        System.out.println("Author: " + book.getAuthor());
        System.out.println("Price: " + book.getPrice());
     
        session.close();
    }
 
    protected void update() {
    	Book book = new Book();
        book.setId(20);
        book.setTitle("Ultimate Java Programming");
        book.setAuthor("Nam Ha Minh");
        book.setPrice(19.99f);
     
        Session session = sessionFactory.openSession();
        session.beginTransaction();
     
        session.update(book);
     
        session.getTransaction().commit();
        session.close();
    }
 
    protected void delete() {
    	Book book = new Book();
        book.setId(20);
     
        Session session = sessionFactory.openSession();
        session.beginTransaction();
     
        session.delete(book);
     
        session.getTransaction().commit();
        session.close();
    }
 */
