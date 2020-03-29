package bmosim.model;

public enum MCS {
	
	// customer problems
	no_problem,    		//0
	delayed,			//1
	not_received,		//2
	damaged,			//3
	fail,				//4
	payment_cancellation,//5
	
	// commercial responses
	cancellation,
	new_delivery,
	refunding,
	new_offer, // better product, price or delays
	reject;
}
