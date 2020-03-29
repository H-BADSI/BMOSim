package bmosim.exchange.filters;

import bmosim.exchange.dialog.Queries;
import madkit.kernel.Message;
import madkit.message.EnumMessage;
import madkit.message.MessageFilter;

public class QueriesEnumFilter implements MessageFilter{
	
	Queries typeMessage;
	boolean tf = false;;
	public QueriesEnumFilter(Queries x){
		 typeMessage = x;
	}
	@SuppressWarnings("unchecked")
	@Override
	public boolean accept(Message arg0) {

		if (arg0 instanceof EnumMessage) {
			EnumMessage<Queries> m ;
			m = (EnumMessage<Queries>) arg0;
		switch(typeMessage){
		case OFFER_DATA_REQUEST:{
			if (m.getCode() == Queries.OFFER_DATA_REQUEST) tf = true;
		};break;
		case OFFER_DATA:{
			if (m.getCode() == Queries.OFFER_DATA) tf = true;
		};break;
		case SAVE_CUSTOMER_DATA:{
			if (m.getCode() == Queries.SAVE_CUSTOMER_DATA) tf = true;
		};break;
		case CUSTOMER_DATA_REQUEST:{
			if (m.getCode() == Queries.CUSTOMER_DATA_REQUEST) tf = true;
		};break;
		case CUSTOMER_DATA:{
			if (m.getCode() == Queries.CUSTOMER_DATA) tf = true;
		};break;
		case OFFERS_REQUEST:{
			if (m.getCode() == Queries.OFFERS_REQUEST) tf = true;
		};break;
		case AVAILABLE_OFFERS:{
			if (m.getCode() == Queries.AVAILABLE_OFFERS) tf = true;
		};break;
		case OBJECT_DATA_REQUEST:{
			if (m.getCode() == Queries.OBJECT_DATA_REQUEST) tf = true;
		};break;
		case OBJECT_DATA:{
			if (m.getCode() == Queries.OBJECT_DATA) tf = true;
		};break;
//		case ALTERNATIVE_OFFER_DATA:{
//			if (m.getCode() == Queries.ALTERNATIVE_OFFER_DATA) tf = true;
//		};break;
		case SAVE_ORDER:{
			if (m.getCode() == Queries.SAVE_ORDER) tf = true;
		};break;
		case SET_ORDER_STATUS:{
			if (m.getCode() == Queries.SET_ORDER_STATUS) tf = true;
		};break;
		case UPDATE_ORDER_DELIVERY:{
			if (m.getCode() == Queries.UPDATE_ORDER_DELIVERY) tf = true;
		};break;
		case UPDATE_ORDER_TO_DELIVERING:{
			if (m.getCode() == Queries.UPDATE_ORDER_TO_DELIVERING) tf = true;
		};break;
		case UPDATE_ORDER_TO_DELIVERED:{
			if (m.getCode() == Queries.UPDATE_ORDER_TO_DELIVERED) tf = true;
		};break;
		case UPDATE_ORDER_TO_CANCELLED:{
			if (m.getCode() == Queries.UPDATE_ORDER_TO_CANCELLED) tf = true;
		};break;
		case ORDER_STATE_AWAITING_DELIVERY:{
			if (m.getCode() == Queries.ORDER_STATE_AWAITING_DELIVERY) tf = true;
		};break;
		case ORDER_STATE_IN_DELIVERY:{
			if (m.getCode() == Queries.ORDER_STATE_IN_DELIVERY) tf = true;
		};break;
		case ORDER_STATE_DELIVERED:{
			if (m.getCode() == Queries.ORDER_STATE_DELIVERED) tf = true;
		};break;
		case DELIVERY_VOUCHER:{
			if (m.getCode() == Queries.DELIVERY_VOUCHER) tf = true;
		};break;
		case SAVE_FEEDBACK:{
			if (m.getCode() == Queries.SAVE_FEEDBACK) tf = true;
		};break;
		case SAVE_TYPE:{
			if (m.getCode() == Queries.SAVE_TYPE) tf = true;
		};break;
		case SAVE_PRODUCT:{
			if (m.getCode() == Queries.SAVE_PRODUCT) tf = true;
		};break;
		case SAVE_OFFER:{
			if (m.getCode() == Queries.SAVE_OFFER) tf = true;
		};break;
		case CUSTOMER_INFO:{
			if (m.getCode() == Queries.CUSTOMER_INFO) tf = true;
		};break;
		case ORDER_DATA:{
			if (m.getCode() == Queries.ORDER_DATA) tf = true;
		};break;
		case ORDER_INFO:{
			if (m.getCode() == Queries.ORDER_INFO) tf = true;
		};break;
		
		
		default:{
			System.out.println("QueriesEnumFilter --> "+ typeMessage.toString() +" filter does not exist");
			tf = false;
			}
		}
			}
		return tf;
		
	}
}
