package bmosim.control;

import java.util.logging.Level;

import bmosim.model.AGR;
import madkit.kernel.AbstractAgent;
import madkit.kernel.Scheduler;
import madkit.simulation.activator.GenericBehaviorActivator;


public class Schedul extends Scheduler{
	
	public static int payCycle;
	public static int simulationDuration;
	public static int stepDelay;

//	private static GenericBehaviorActivator<AbstractAgent> activatorResetSettings;
	private static GenericBehaviorActivator<AbstractAgent> activatorDoStep;
	private static GenericBehaviorActivator<AbstractAgent> activatorSatisfaction;
	private static GenericBehaviorActivator<AbstractAgent> activatornbSatisfyingOffers;
	private static GenericBehaviorActivator<AbstractAgent> activatornbAcceptableOffers;
	private static GenericBehaviorActivator<AbstractAgent> activatornbUnAcceptableOffers;
	private static GenericBehaviorActivator<AbstractAgent> activatorWaitingTimeAVG;
	private static GenericBehaviorActivator<AbstractAgent> activatorNbOrders;
	private static GenericBehaviorActivator<AbstractAgent> activatorPayDay;
	private static GenericBehaviorActivator<AbstractAgent> activatorPurchasesNb;
	private static GenericBehaviorActivator<AbstractAgent> activatorTurnover;
	private static GenericBehaviorActivator<AbstractAgent> activatorRefund;
	private static GenericBehaviorActivator<AbstractAgent> activatorUpdateDB;


	public void activate () {
		getLogger().setLevel(Level.FINE);
		setSimulationDuration(simulationDuration);
		setDelay(stepDelay);
//		requestRole(AGR.COMMUNITY, AGR.GROUP, AGR.SCHEDULER_ROLE);
//		requestRole(AGR.COMMUNITY, AGR.CONTROL_GROUP, AGR.SCHEDULER_ROLE);
		
//		activatorResetSettings= new GenericBehaviorActivator<AbstractAgent>("BMOSimulator", "BMOSIM", "Agent","resetSettings");
//		addActivator(activatorResetSettings);
		activatorDoStep= new GenericBehaviorActivator<AbstractAgent>(AGR.COMMUNITY, AGR.GROUP, AGR.ROLE,"step");
		addActivator(activatorDoStep);
		activatorSatisfaction= new GenericBehaviorActivator<AbstractAgent>(AGR.COMMUNITY, AGR.CONTROL_GROUP, AGR.WATCHER_ROLE,"satisfaction");
		addActivator(activatorSatisfaction);
		activatornbSatisfyingOffers= new GenericBehaviorActivator<AbstractAgent>(AGR.COMMUNITY, AGR.CONTROL_GROUP, AGR.WATCHER_ROLE,"satisfyingOffers");
		addActivator(activatornbSatisfyingOffers);
		activatornbAcceptableOffers= new GenericBehaviorActivator<AbstractAgent>(AGR.COMMUNITY, AGR.CONTROL_GROUP, AGR.WATCHER_ROLE,"acceptableOffers");
		addActivator(activatornbAcceptableOffers);
		activatornbUnAcceptableOffers= new GenericBehaviorActivator<AbstractAgent>(AGR.COMMUNITY, AGR.CONTROL_GROUP, AGR.WATCHER_ROLE,"unAcceptableOffers");
		addActivator(activatornbUnAcceptableOffers);
		activatorWaitingTimeAVG= new GenericBehaviorActivator<AbstractAgent>(AGR.COMMUNITY, AGR.CONTROL_GROUP, AGR.WATCHER_ROLE,"waitingTimeAVG");
		addActivator(activatorWaitingTimeAVG);
		activatorNbOrders= new GenericBehaviorActivator<AbstractAgent>(AGR.COMMUNITY, AGR.CONTROL_GROUP, AGR.WATCHER_ROLE,"nbOrders");
		addActivator(activatorNbOrders);
		activatorPurchasesNb= new GenericBehaviorActivator<AbstractAgent>(AGR.COMMUNITY, AGR.CONTROL_GROUP, AGR.WATCHER_ROLE,"purchasesNb");
		addActivator(activatorPurchasesNb);
		activatorTurnover= new GenericBehaviorActivator<AbstractAgent>(AGR.COMMUNITY, AGR.CONTROL_GROUP, AGR.WATCHER_ROLE,"turnover");
		addActivator(activatorTurnover);
		activatorRefund= new GenericBehaviorActivator<AbstractAgent>(AGR.COMMUNITY, AGR.CONTROL_GROUP, AGR.WATCHER_ROLE,"refund");
		addActivator(activatorRefund);

		activatorPayDay= new GenericBehaviorActivator<AbstractAgent>(AGR.COMMUNITY, AGR.EX_GROUP,AGR.CUS_EX_ROLE,"payDay");
		addActivator(activatorPayDay);

		activatorUpdateDB= new GenericBehaviorActivator<AbstractAgent>(AGR.COMMUNITY, AGR.CONTROL_GROUP, AGR.WATCHER_ROLE,"updateDB");
		addActivator(activatorUpdateDB);

		setSimulationState(SimulationState.RUNNING);

	}
	
	@Override
	public void doSimulationStep() {
//		if (logger != null)	logger.finer("Doing simulation step " + getGVT());
		
		if (getGVT() % payCycle ==0){
//			if (logger != null)	logger.info("Activating --------> activatorPayDay " + activatorPayDay);
			activatorPayDay.execute();
		}
		
//		if (logger != null) logger.info("Activating --------> activatorDoStep " + activatorDoStep);
		activatorDoStep.execute();
		
//		if (logger != null)	logger.info("Activating --------> activatornbSatisfyingOffers " + activatornbSatisfyingOffers);
		activatornbSatisfyingOffers.execute();
		
//		if (logger != null)	logger.info("Activating --------> activatornbAcceptableOffers " + activatornbAcceptableOffers);
		activatornbAcceptableOffers.execute();
		
//		if (logger != null)	logger.info("Activating --------> activatornbUnAcceptableOffers " + activatornbUnAcceptableOffers);
		activatornbUnAcceptableOffers.execute();
		
//		if (logger != null)	logger.info("Activating --------> activatorWaitingTimeAVG " + activatorWaitingTimeAVG);
		activatorWaitingTimeAVG.execute();
		
//		if (logger != null)	logger.info("Activating --------> activatorSatisfaction " + activatorSatisfaction);
		activatorSatisfaction.execute();

//		if (logger != null)	logger.info("Activating --------> activatorNbOrders " + activatorNbOrders);
		activatorNbOrders.execute();
		
//		if (logger != null)	logger.info("Activating --------> activatorPurchasesNb " + activatorPurchasesNb);
		activatorPurchasesNb.execute();
		
//		if (logger != null)	logger.info("Activating --------> activatorTurnover " + activatorTurnover);
		activatorTurnover.execute();
		
//		if (logger != null)	logger.info("Activating --------> activatorTurnover " + activatorTurnover);
		activatorRefund.execute();

		if (getGVT() % 10 ==0){
			activatorUpdateDB.execute();
		}
        setGVT(getGVT() + 1);

		if(Main.simState=="PAUSED")setSimulationState(SimulationState.PAUSED);
		if(Main.simState=="RUNNING")setSimulationState(SimulationState.RUNNING);
		if(Main.simState=="SHUTDOWN")setSimulationState(SimulationState.SHUTDOWN);

	}


    public void end(){
		activatorDoStep.killAgents();
        Main.reexecute();
	}

}
