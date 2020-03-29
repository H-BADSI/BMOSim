package bmosim.exchange.filters;

import bmosim.exchange.dialog.Conversation;
import madkit.kernel.Message;
import madkit.message.EnumMessage;
import madkit.message.MessageFilter;

public class ConversationEnumFilter implements MessageFilter{
	
	Conversation typeMessage;
	boolean tf = false;;
	public ConversationEnumFilter(Conversation x){
		 typeMessage = x;
	}
	@SuppressWarnings("unchecked")
	@Override
	public boolean accept(Message arg0) {

		if (arg0 instanceof EnumMessage) {
			EnumMessage<Conversation> m ;
			m = (EnumMessage<Conversation>) arg0;
		switch(typeMessage){
		case probing_request:{
			if (m.getCode() == Conversation.probing_request ) tf = true;
		};break;
		case probing_answer:{
			if (m.getCode() == Conversation.probing_answer ) tf = true;
		};break;
		case offer:{
			if (m.getCode() == Conversation.offer ) tf = true;
		};break;
		case inquiry:{
			if (m.getCode() == Conversation.inquiry ) tf = true;
		};break;
		case info_request:{
			if (m.getCode() == Conversation.info_request ) tf = true;
		};break;
		case customer_info:{
			if (m.getCode() == Conversation.customer_info ) tf = true;
		};break;
		case updated_offer:{
			if (m.getCode() == Conversation.updated_offer ) tf = true;
		};break;
		case order:{
			if (m.getCode() == Conversation.order ) tf = true;
		};break;
		case contract:{
			if (m.getCode() == Conversation.contract ) tf = true;
		};break;
		case payment:{
			if (m.getCode() == Conversation.payment ) tf = true;
		};break;
		case payment_state:{
			if (m.getCode() == Conversation.payment_state ) tf = true;
		};break;
		case object_of_value:{
			if (m.getCode() == Conversation.object_of_value ) tf = true;
		};break;
		case complaint:{
			if (m.getCode() == Conversation.complaint ) tf = true;
		};break;
		case complaint_offer:{
			if (m.getCode() == Conversation.complaint_offer ) tf = true;
		};break;
		case compensation_response:{
			if (m.getCode() == Conversation.compensation_response ) tf = true;
		};break;
		case credit_note:{
			if (m.getCode() == Conversation.credit_note ) tf = true;
		};break;

		
		
		
		default:{
			System.out.println("ConversationEnumFilter --> filter does not exist");
			tf = false;
			}
		}
			}
		return tf;
		
	}
}
