package bmosim.agents;

import bmosim.AbsAgents.AbstractANA;
import bmosim.exchange.dialog.Conversation;
import bmosim.exchange.dialog.Queries;
import bmosim.exchange.filters.ConversationEnumFilter;
import bmosim.exchange.objects.ObjectOfValue;
import bmosim.hibernateDB.DBcustomer;
import bmosim.hibernateDB.DBneed;
import bmosim.hibernateDB.DBorder;
import bmosim.model.Role;
import madkit.message.EnumMessage;

import java.util.ArrayList;

public class ANA extends AbstractANA{
	
	public ANA(){}
	public ANA(Object conf) {
		super(conf);
		// TODO Auto-generated constructor stub
	}

	ArrayList<DBcustomer> cusinfo = new ArrayList<>();
	ObjectOfValue cusneed;


	public void profiling ()
	{
		requestDBandWait(new EnumMessage<Queries>(Queries.GET_CUSTOMERS), null);

		dbResp=null;
		dbResp = checkDBResponse(Queries.CUSTOMER_INFO);


		if(dbResp==null){
			System.out.println("null");

		}else {

			for (Object em:dbResp.getContent()
				 ) {
				System.out.println("NULNULNUL");
				for (DBcustomer cus:(ArrayList<DBcustomer>)em) {
					System.out.println("CUS-----"+cus.getCustomer_address_hashCode()
					+" "+cus.getAge()+" "+cus.getBudget()+" "+cus.getQuality()+" "+cus.getTime());
					requestDBandWait(new EnumMessage<Queries>(Queries.GET_CUSTOMER_NEEDS,cus.getCustomer_address_hashCode()), null);
					EnumMessage<Queries> dbResp1=checkDBResponse(Queries.CUSTOMER_DATA);
					if(dbResp1==null){
						System.out.println("dbresp1 is null");
					}else {
						for (Object em1:dbResp1.getContent()
						) {
							System.out.println("NULNULNUL5");
							if(em1!=null)
							for (DBneed need : (ArrayList<DBneed>) em1) {
								System.out.println("NEED----- " + need.getNeed_id()
										+ " " + need.getCustomer_id() + " " + need.getPrice() + " " + need.getQuality() + " " + need.getTime()
										+ " " + need.getType());
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
