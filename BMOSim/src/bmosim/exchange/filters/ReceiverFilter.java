package bmosim.exchange.filters;

import madkit.kernel.AgentAddress;
import madkit.kernel.Message;
import madkit.message.MessageFilter;

public class ReceiverFilter implements MessageFilter{
	
	AgentAddress address;
	boolean tf = false;
	public ReceiverFilter(AgentAddress x){
		address = x;
	}
	@Override
	public boolean accept(Message m) {
		return m.getSender().equals(address);
	}
}
