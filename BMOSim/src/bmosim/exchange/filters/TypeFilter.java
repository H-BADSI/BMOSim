package bmosim.exchange.filters;

import madkit.kernel.Message;
import madkit.message.ACLMessage;
import madkit.message.ActMessage;
import madkit.message.BooleanMessage;
import madkit.message.EnumMessage;
import madkit.message.IntegerMessage;
import madkit.message.KernelMessage;
import madkit.message.MessageFilter;
import madkit.message.ObjectMessage;
import madkit.message.SchedulingMessage;
import madkit.message.StringMessage;
import madkit.message.XMLMessage;
import madkit.message.hook.MessageEvent;

public class TypeFilter implements MessageFilter{
	
	String typeMessage;
	boolean tf;
	public TypeFilter(String x){
		typeMessage = x;
	}
	@Override
	public boolean accept(Message arg0) {
		
		switch(typeMessage){
		case "string":{
			if (arg0 instanceof StringMessage) tf = true;
		};break;
		case "object":{
			if (arg0 instanceof ObjectMessage) tf = true;
		};break;
		case "ACL":{
			if (arg0 instanceof ACLMessage) tf = true;
		};break;
		case "enum":{
			if (arg0 instanceof EnumMessage) tf = true;
		};break;
		case "XML":{
			if (arg0 instanceof XMLMessage) tf = true;
		};break;
		case "act":{
			if (arg0 instanceof ActMessage) tf = true;
		};break;
		case "boolean":{
			if (arg0 instanceof BooleanMessage) tf = true;
		};break;
		case "kernel":{
			if (arg0 instanceof KernelMessage) tf = true;
		};break;
		case "integer":{
			if (arg0 instanceof IntegerMessage) tf = true;
		};break;
		case "event":{
			if (arg0 instanceof MessageEvent) tf = true;
		};break;
		case "scheduling":{
			if (arg0 instanceof SchedulingMessage) tf = true;
		};break;
		default:{tf = false;}
		}
		return tf;
		
	}
}
