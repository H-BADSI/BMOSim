package bmosim.exchange.objects;

import bmosim.exchange.dialog.Queries;
import madkit.message.EnumMessage;

public class Query extends EnumMessage<Queries>{ // ****pas encore testée

	/**
	 * 
	 */
	private static final long serialVersionUID = -3669758598711729775L;

	public Query(Queries code, Object[] parameters) {
		super(code, parameters);
		// TODO Auto-generated constructor stub
	}

}
