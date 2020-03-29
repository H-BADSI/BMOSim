package bmosim.exchange.objects;

import bmosim.exchange.dialog.Conversation;
import madkit.message.EnumMessage;

public class ConvAct extends EnumMessage<Conversation>{    // non testée

	/**
	 * 
	 */
	private static final long serialVersionUID = 8134295903386501262L;

	public ConvAct(Conversation code, Object[] parameters) {
		super(code, parameters);
		// TODO Auto-generated constructor stub
	}

}
