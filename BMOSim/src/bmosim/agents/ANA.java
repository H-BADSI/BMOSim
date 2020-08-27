package bmosim.agents;

import bmosim.AbsAgents.AbstractANA;
import bmosim.exchange.dialog.Collaboration;
import bmosim.exchange.objects.report;
import bmosim.exchange.dialog.Conversation;
import bmosim.exchange.dialog.Queries;
import bmosim.exchange.filters.ConversationEnumFilter;
import bmosim.exchange.objects.ObjectOfValue;
import bmosim.hibernateDB.*;
import bmosim.model.AGR;
import bmosim.model.Role;
import madkit.message.EnumMessage;

import java.util.ArrayList;

public class ANA extends AbstractANA{

	EnumMessage<Collaboration> anaManMessage;
	
	public ANA(){}
	public ANA(Object conf) {
		super(conf);
		// TODO Auto-generated constructor stub
	}

	public void profiling ()
	{
		requestDBandWait(new EnumMessage<Queries>(Queries.GET_CUSTOMERS), null);

		dbResp=null;
		dbResp = checkDBResponse(Queries.CUSTOMER_INFO);

		if(dbResp==null){
			System.out.println("dbresp null");
		}else {
			for (Object em:dbResp.getContent()) {
				for (DBcustomer cus:(ArrayList<DBcustomer>)em) {
					requestDBandWait(new EnumMessage<Queries>(Queries.GET_CUSTOMER_ORDERS,cus.getCustomer_address_hashCode()), null);
					EnumMessage<Queries> dbResp1=checkDBResponse(Queries.ORDER_DATA);
					if(dbResp1==null){
                        System.out.println("dbresp1 null");
                    }else {
						for (Object em1:dbResp1.getContent()) {
							if(em1!=null)
							for (DBorder order : (ArrayList<DBorder>) em1) {
                                report rep = new report();

                                rep.customerID=cus.getCustomer_address_hashCode();
                                rep.cusSegment=customerSegmentation(cus);
                                rep.orderID=order.getOrder_id();
                                rep.status=order.getStatus();
                                rep.quantity=order.getQuantity();
                                rep.product=order.getOffer_id().getProduct_id();
                                rep.offerID=order.getOffer_id().getOffer_id();

                                System.out.println("rep+ "+rep.toString());

                                anaManMessage = new EnumMessage<Collaboration>(Collaboration.PROFILING_REPORT,rep);
								//		sending report to the manager
								sendMessage (AGR.COMMUNITY, AGR.IN_GROUP, AGR.MAN_IN_ROLE,anaManMessage);
							}
						}
					}
				}
			}
		}
//		EnumMessage<Conversation> dialog = getConversationMessage(Conversation.inquiry);
	}

/*	private void selectAnalysisType ()
	{
		// select one from the following: leadAnalysis , profilingAnalysis, feedbackAnalysis
	}
	
	private void leadAnalysis ()
	{
		//
	}
	

	
	private void feedbackAnalysis ()
	{
		//
	}
	
	private void makeReport ()
	{
		//
	}
	
	private void saveReport ()
	{
		//
	}*/
}
