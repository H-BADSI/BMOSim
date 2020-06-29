package bmosim.control;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import bmosim.agents.COM;
import bmosim.agents.CUS;
import bmosim.exchange.objects.Seller;
import bmosim.ihm3.Repository.FeedRepo.FeedRepo;
import bmosim.model.AGR;
import bmosim.model.Role;
import madkit.kernel.Watcher;
import madkit.simulation.probe.PropertyProbe;

public class Observer extends Watcher{

	PropertyProbe<CUS,ArrayList<Seller>> customerSatisfactionProbe =
			new PropertyProbe<CUS,ArrayList<Seller>>
			(AGR.COMMUNITY,AGR.EX_GROUP,AGR.CUS_EX_ROLE,"knownSellers");
	
	PropertyProbe<CUS,Integer> nbSatisfyingOffersProbe =
			new PropertyProbe<CUS,Integer>
			(AGR.COMMUNITY,AGR.EX_GROUP,AGR.CUS_EX_ROLE,"nbSatisfyingOffers");
	
	PropertyProbe<CUS,Integer> nbAcceptableOffersProbe =
			new PropertyProbe<CUS,Integer>
			(AGR.COMMUNITY,AGR.EX_GROUP,AGR.CUS_EX_ROLE,"nbAcceptableOffers");
	
	PropertyProbe<CUS,Integer> nbUnacceptableOffersProbe =
			new PropertyProbe<CUS,Integer>
			(AGR.COMMUNITY,AGR.EX_GROUP,AGR.CUS_EX_ROLE,"nbUnacceptableOffers");	
	
	PropertyProbe<CUS,Double> waitingTimeAVGProbe =
			new PropertyProbe<CUS,Double>
			(AGR.COMMUNITY,AGR.EX_GROUP,AGR.CUS_EX_ROLE,"waitingTimeAVG");
	
	PropertyProbe<CUS,Integer> nbOrdersProbe =
			new PropertyProbe<CUS,Integer>
			(AGR.COMMUNITY,AGR.EX_GROUP,AGR.CUS_EX_ROLE,"nbOrders");
	
	PropertyProbe<CUS,Integer> nbOfPurchasesProbe =
			new PropertyProbe<CUS,Integer>
			(AGR.COMMUNITY,AGR.EX_GROUP,AGR.CUS_EX_ROLE,"nbOfPurchases");
	
	PropertyProbe<COM,Integer> turnoverProbe =
			new PropertyProbe<COM,Integer>
			(AGR.COMMUNITY, AGR.EX_GROUP, Role.Com.BIL,"fund");
	
	PropertyProbe<COM,Integer> refundProbe =
			new PropertyProbe<COM,Integer>
			(AGR.COMMUNITY, AGR.EX_GROUP, Role.Com.REF,"fund");
	
	static List<CUS> customers;
	static List<COM> commercials;
	static ArrayList<Seller> sellersList;
	static Double globalScore;
	static Double scoreSum;
	static ArrayList<Double> stepScore = new ArrayList<Double>();

	static Double satOffersNb;
	static Double accOffersNb;
	static Double unAccOffersNb;
	static Double updateOffersWaitingTime;
	static Double ordersTotalNb;
	static Double purchasesTotalNb;
	static Double turnover;
	static Double refund;
	static Double avgT;


	
	public void activate(){
		getLogger().setLevel(Level.OFF); /*brahim*/
		requestRole(AGR.COMMUNITY, AGR.CONTROL_GROUP, AGR.WATCHER_ROLE);
		addProbe(customerSatisfactionProbe);
		addProbe(nbSatisfyingOffersProbe);
		addProbe(nbAcceptableOffersProbe);
		addProbe(nbUnacceptableOffersProbe);
		addProbe(waitingTimeAVGProbe);
		addProbe(nbOrdersProbe);
		addProbe(nbOfPurchasesProbe);
		addProbe(turnoverProbe);
		addProbe(refundProbe);


	}

	public void satisfaction(){
		customers = customerSatisfactionProbe.getCurrentAgentsList();
		globalScore = 0.0;
		for (int i=0; i<customers.size();i++){
			sellersList = customerSatisfactionProbe.getPropertyValue(customers.get(i));
			scoreSum = 0.0;
			for (int j=0; j< sellersList.size();j++) scoreSum += sellersList.get(j).score ;
			scoreSum = scoreSum / sellersList.size();
			globalScore += scoreSum;
			if(globalScore.isNaN()) globalScore=0.0;//BRAHIM
		}
		stepScore.add(globalScore);
		if (getLogger() != null) getLogger().info("avgSat= "+globalScore.toString());

	}
	public void satisfyingOffers(){
		customers = nbSatisfyingOffersProbe.getCurrentAgentsList();
		satOffersNb = 0.0;
		for (int i=0; i<customers.size();i++){
			satOffersNb += nbSatisfyingOffersProbe.getPropertyValue(customers.get(i));
//			System.out.println("**************"+nbSatisfyingOffersProbe.getPropertyValue(customers.get(i)));
			if(satOffersNb.isNaN()) satOffersNb=0.0;//BRAHIM
		}
		if (getLogger() != null) getLogger().info("nbsat= "+satOffersNb.toString());
		Double avg = satOffersNb/customers.size();
		if (getLogger() != null) getLogger().info("nbsatAVG= "+avg.toString());
	}
	public void acceptableOffers(){
		customers = nbAcceptableOffersProbe.getCurrentAgentsList();
		accOffersNb = 0.0;
		for (int i=0; i<customers.size();i++){
			accOffersNb += nbAcceptableOffersProbe.getPropertyValue(customers.get(i));
			if(accOffersNb.isNaN()) accOffersNb=0.0;//BRAHIM
		}
		if (getLogger() != null) getLogger().info("nbacc= "+accOffersNb.toString());
		Double avg = accOffersNb/customers.size();
		avgT=avg;
		if (getLogger() != null) getLogger().info("nbaccAVG= "+avg.toString());
	}
	public void unAcceptableOffers(){
		customers = nbUnacceptableOffersProbe.getCurrentAgentsList();
		unAccOffersNb = 0.0;
		for (int i=0; i<customers.size();i++){
			unAccOffersNb += nbUnacceptableOffersProbe.getPropertyValue(customers.get(i));
			if(unAccOffersNb.isNaN()) unAccOffersNb=0.0;//BRAHIM
		}
			if (getLogger() != null) getLogger().info("nbunacc= "+unAccOffersNb.toString());
		Double avg = unAccOffersNb/customers.size();
		if (getLogger() != null) getLogger().info("nbunaccAVG= "+avg.toString());
	}
	public void waitingTimeAVG(){
		customers = waitingTimeAVGProbe.getCurrentAgentsList();
		updateOffersWaitingTime = 0.0;
		for (int i=0; i<customers.size();i++){
			updateOffersWaitingTime += waitingTimeAVGProbe.getPropertyValue(customers.get(i));
			if(updateOffersWaitingTime.isNaN()) updateOffersWaitingTime=0.0;//BRAHIM
		}
		Double avg = updateOffersWaitingTime/customers.size();
		if (getLogger() != null) getLogger().info("waitTimeAVG= "+avg.toString());
	}
	public void nbOrders(){
		customers = nbOrdersProbe.getCurrentAgentsList();
		ordersTotalNb = 0.0;
		for (int i=0; i<customers.size();i++){
			ordersTotalNb += nbOrdersProbe.getPropertyValue(customers.get(i));
			if(ordersTotalNb.isNaN()) ordersTotalNb=0.0;//BRAHIM
		}
		if (getLogger() != null) getLogger().info("ordersTotalNb= "+ordersTotalNb.toString());
		Double avg = ordersTotalNb/customers.size();
		if (getLogger() != null) getLogger().info("ordersNbAVG= "+avg.toString());
	}
	
	public void purchasesNb(){
		customers = nbOfPurchasesProbe.getCurrentAgentsList();
		purchasesTotalNb = 0.0;
		for (int i=0; i<customers.size();i++){
			purchasesTotalNb += nbOfPurchasesProbe.getPropertyValue(customers.get(i));
			if(purchasesTotalNb.isNaN()) purchasesTotalNb=0.0;//BRAHIM
		}
		if (getLogger() != null) getLogger().info("purchasesTotalNb= "+purchasesTotalNb.toString());
		Double avg = purchasesTotalNb/customers.size();
		if (getLogger() != null) getLogger().info("purchasesNbAVG= "+avg.toString());
	}
	public void turnover(){
		
		commercials = turnoverProbe.getCurrentAgentsList();
		turnover = 0.0;
		for (int i=0; i<commercials.size();i++){
			turnover += turnoverProbe.getPropertyValue(commercials.get(i));
			if(turnover.isNaN()) turnover=0.0;//BRAHIM
		}
		if (getLogger() != null) getLogger().info("turnoverProbe= "+turnover.toString());
	}
	public void refund(){
		
		commercials = refundProbe.getCurrentAgentsList();
		refund = 0.0;
		for (int i=0; i<commercials.size();i++){
			refund += refundProbe.getPropertyValue(commercials.get(i));
			if(refund.isNaN()) refund=0.0;//BRAHIM
		}
		if (getLogger() != null) getLogger().info("refundProbe= "+refund.toString());
	}

	public void updateDB()  {
			new FeedRepo().insertFeeds(Generator.idInst,Observer.globalScore,Observer.satOffersNb,Observer.accOffersNb,
					Observer.unAccOffersNb,Observer.avgT,Observer.ordersTotalNb.intValue(),Observer.purchasesTotalNb.intValue()
                    ,Observer.turnover,Observer.refund);
	}

}
