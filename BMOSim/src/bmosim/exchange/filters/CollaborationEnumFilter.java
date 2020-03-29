package bmosim.exchange.filters;

import madkit.kernel.Message;
import madkit.message.EnumMessage;
import madkit.message.MessageFilter;
import bmosim.exchange.dialog.Collaboration;

public class CollaborationEnumFilter implements MessageFilter{
	
	Collaboration typeMessage;
	boolean tf = false;;
	
	public CollaborationEnumFilter(Collaboration x){
		 typeMessage = x;
	}
	@SuppressWarnings("unchecked")
	@Override
	public boolean accept(Message arg0) {

		if (arg0 instanceof EnumMessage) {
			EnumMessage<Collaboration> m ;
			m = (EnumMessage<Collaboration>) arg0;
		switch(typeMessage){
		case SELECT_BEST_OFFER:{
			if (m.getCode() == Collaboration.SELECT_BEST_OFFER) tf = true;
		};break;
		case BEST_OFFER:{
			if (m.getCode() == Collaboration.BEST_OFFER) tf = true;
		};break;
		case REFUND_ORDER:{
			if (m.getCode() == Collaboration.REFUND_ORDER) tf = true;
		};break;
		default:{
			System.out.println("CollaborationEnumFilter --> filter does not exist");
			tf = false;
			}
		}
			}
		return tf;
		
	}
}
