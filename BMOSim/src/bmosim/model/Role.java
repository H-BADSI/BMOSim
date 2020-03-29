package bmosim.model;

public class Role {

	public class Com {

	    public final static String ADV="advertising";
	    public final static String REC="receiving";
	    public final static String PRE="preparing";
	    public final static String TRE="treating";
	    public final static String BIL="billing";
	    public final static String DEL="delivering";
	    public final static String MCS="managing_cs";
	    public final static String REF="refunding";
	    
	    public class Adv{
	    	public final static String CHECK = "check_offer";
	    	public final static String SEND = "send";
	    }
	    public class Rec{
	    	public final static String CHECK = "check_customer_info";
	    	public final static String REQDB = "request_DB";
	    	public final static String TEST = "test_prepare_role";
	    }
	    public class Pre{
	    	public final static String CHECK_CUS = "check_customer_data";
	    	public final static String GET_OFFER = "get_offer_data";
	    	public final static String CHECK_OFFER = "check_offer_data";
	    	public final static String CHECK_MAN_SELECT = "check_best_offer";
	    	public final static String PRE_SEND = "prepare_and_send";
	    }
	    public class Tre{
	    	public final static String CHECK = "check_object_data";
	    	public final static String CHECK_ORD = "checking_order_and_respond";
	    	public final static String CHECK_ALT = "check_alternative_offer";
	    	public final static String SAVE_CONT = "save_contract";
	    	public final static String CHECK_NUM = "check_contract_number";
	    	public final static String SEND = "send_contract";
	    	
	    }
	    public class Bil{
	    	public final static String CHECK_ORD = "check_order_saved";
	    }
	    public class Del{
	    	public final static String CHECK_DEL_ORDER = "check_del_order";
	    	public final static String CHECK_DEL_STATE = "check_del_state";
	    }
	    public class Mcs{
	    	public final static String CHECK_ORDER_INFO = "check_order_information";
	    	public final static String CHECK_ORDER_PROBLEM = "check_order_problem";
	    }
	    public class Ref{
	    	
	    }
	}
	
	public class Ana {

	    public final static String LEAD = "lead";
	    public final static String PROF = "profiling";
	    public final static String FB = "feedback";
	    
	    public class Lead{
	    	
	    }
	    public class Prof{
	    	
	    }
	    public class Fb{
	    	
	    }
	}
	public class Sup{
	    public final static String SURV = "survey";
	    public final static String ANAREP = "anarep";
	    public final static String LOYMAN = "loyman";
	    
	    public class Surv{
	    	
	    }
	    public class Anarep{
	    	
	    }
	    public class Loyman{
	    	
	    }
	}
	public class Man{
		public final static String ANSWER = "answering";
	    public final static String PERF = "performance";
	    public final static String STRAT = "strategy";
	    public final static String ENAB = "enabling";
	    
	    public class Perf{
	    	
	    }
	    public class Strat{
	    	
	    }
	    public class Enab{
	    	
	    }
	}
	
	
	
	
}
