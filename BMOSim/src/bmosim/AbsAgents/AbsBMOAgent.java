package bmosim.AbsAgents;

import java.util.ArrayList;
import java.util.List;

import bmosim.exchange.filters.TypeFilter;
import bmosim.model.AGR;
import madkit.kernel.AbstractAgent;
import madkit.message.ObjectMessage;

public abstract class AbsBMOAgent extends AbstractAgent{
		
		// AGR properties
		public ReturnCode code;
		public Object configuration = null;
		public int timer = -1;
		public String nextStep;
		
		public AbsBMOAgent(){
			
		}
		public AbsBMOAgent (Object conf) {
			configuration = conf;
		}
		public void requestCGR(){
			requestRole(AGR.COMMUNITY, AGR.GROUP, AGR.ROLE);
		}
		public void step(){
//			if (logger != null) logger.info(" timer_begin: "+timer);
			if (timer <0) doThing(); else doIt();
//			if (logger != null) logger.info(" timer_end "+timer);
		}
		public abstract void doThing();
		public abstract void doIt();
		public abstract void setLocalSettings();
		public abstract void completeSettings();
		public void setParameters(){
			if (configuration != null) setLocalSettings();
			 else {
				completeSettings();
			}
		}
		public void resetSettings(){
			TypeFilter filter = new TypeFilter("object");
			@SuppressWarnings("unchecked")
			ObjectMessage<Object> m = (ObjectMessage<Object>) nextMessage(filter);
			configuration = m.getContent();
			setLocalSettings();
		}
		public List<Integer> stringToIntList(String st){
			String[] tab = st.split(" ");
			List<Integer> intList = new ArrayList<Integer>();
			for (int i=0;i<tab.length;i++){
				intList.add(Integer.parseInt(tab[i]));				
			}
			return intList;
		}
		public List<String> stringToStringList(String st){
			String[] tab = st.split(" ");
			List<String> intList = new ArrayList<String>();
			for (int i=0;i<tab.length;i++){
				intList.add(tab[i]);				
			}
			return intList;
		}
}
