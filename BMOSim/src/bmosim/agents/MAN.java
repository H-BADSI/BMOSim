package bmosim.agents;

import java.io.File;

import bmosim.ihm3.controller.simulate;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyIRIMapper;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;
import org.semanticweb.owlapi.util.AutoIRIMapper;

import bmosim.AbsAgents.AbstractMAN;
import bmosim.bmo.CustomerSegment;
import bmosim.bmo.MyFactory;
import bmosim.bmo.ValueProposition;
import bmosim.hibernateDB.DBproduct;
import bmosim.hibernateDB.DBtype;

public class MAN extends AbstractMAN{

	public MAN(){}
	public MAN(Object conf) {
		super(conf);
	}
	
	public void highlight(){
		
		highlightAnOffer(1);
	}
	
	public void initOffersInDB() throws OWLOntologyCreationException {
//        simulate.tf1.setText("activate");
		//objType,
		//buyPrice, sellPrice, quality, prodQuantity, lifeTime,
		//offerQuantity, offerPrice, deliveryTime
		DBtype t1 = createObjType(1);
		DBtype t2 = createObjType(2);
		DBtype t3 = createObjType(3);
		DBproduct p1 = createProduct(t1,"p1",118,148,5,100000,1200);
		DBproduct p2 = createProduct(t1,"p2",144,180,4,100000,1400);
		DBproduct p3 = createProduct(t1,"p3",200,250,1,100000,2000);
		DBproduct p4 = createProduct(t1,"p4",155,194,2,100000,1800);
		DBproduct p5 = createProduct(t1,"p5",118,148,8,100000,1000);
		DBproduct p6 = createProduct(t1,"p6",55,69,10,100000,1000);
		DBproduct p7 = createProduct(t1,"p7",140,176,3,100000,1600);
		DBproduct p8 = createProduct(t1,"p8",140,176,6,100000,1000);
		DBproduct p9 = createProduct(t2,"p9",196,245,2,100000,1800);
		DBproduct p10 = createProduct(t3,"p10",88,111,3,100000,1600);
		DBproduct p11 = createProduct(t3,"p11",111,139,5,100000,1200);
		DBproduct p12 = createProduct(t3,"p12",73,92,4,100000,1400);

		addOfferInDB(p1,-1,148,3);
		addOfferInDB(p2,-1,180,3);
		addOfferInDB(p3,-1,250,3);
		addOfferInDB(p4,-1,194,3);
		addOfferInDB(p5,-1,148,3);
		addOfferInDB(p6,-1,69,3);
		addOfferInDB(p7,-1,176,3);
		addOfferInDB(p8,-1,176,3);
		addOfferInDB(p9,-1,245,3);
		addOfferInDB(p10,-1,111,3);
		addOfferInDB(p11,-1,139,3);
		addOfferInDB(p12,-1,92,3);


//		OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
//		OWLOntologyIRIMapper mapper = new AutoIRIMapper(new File ("src/bmosim/onto"), false);
//		manager.addIRIMapper(mapper);
//		File f = new File("src/bmosim/onto/BusModelOWL.owl");

//		OWLOntology bmoTest = manager.loadOntologyFromOntologyDocument(f);
//			MyFactory factory = new MyFactory(bmoTest);
//			ValueProposition vp1 = factory.createValueProposition("p1");
//			ValueProposition vp2 = factory.createValueProposition("p2");
//			ValueProposition vp3 = factory.createValueProposition("p3");
//			ValueProposition vp4 = factory.createValueProposition("p4");
//			ValueProposition vp5 = factory.createValueProposition("p5");
//			ValueProposition vp6 = factory.createValueProposition("p6");
//			ValueProposition vp7 = factory.createValueProposition("p7");
//			ValueProposition vp8 = factory.createValueProposition("p8");
//			ValueProposition vp9 = factory.createValueProposition("p9");
//			ValueProposition vp10 = factory.createValueProposition("p10");
//			ValueProposition vp11 = factory.createValueProposition("p11");
//			ValueProposition vp12 = factory.createValueProposition("p12");
//
//			CustomerSegment cs1 = factory.createCustomerSegment("1");
//			CustomerSegment cs2 = factory.createCustomerSegment("2");
//			CustomerSegment cs3 = factory.createCustomerSegment("3");
//			CustomerSegment cs4 = factory.createCustomerSegment("4");
//
//			vp1.addTargets(cs2);
//			vp1.addTargets(cs4);
//			vp2.addTargets(cs2);
//			vp2.addTargets(cs3);
//			vp3.addTargets(cs2);
//			vp3.addTargets(cs3);
//			vp4.addTargets(cs2);
//			vp4.addTargets(cs3);
//			vp5.addTargets(cs1);
//			vp5.addTargets(cs4);
//			vp6.addTargets(cs1);
//			vp6.addTargets(cs4);
//			vp7.addTargets(cs2);
//			vp7.addTargets(cs4);
//			vp8.addTargets(cs4);
//			vp9.addTargets(cs2);
//			vp9.addTargets(cs3);
//			vp10.addTargets(cs2);
//			vp10.addTargets(cs3);
//			vp11.addTargets(cs4);
//			vp12.addTargets(cs1);
//			vp12.addTargets(cs4);
//
//			cs1.addIsTargetedBy(vp5);
//			cs1.addIsTargetedBy(vp6);
//			cs1.addIsTargetedBy(vp12);
//			cs2.addIsTargetedBy(vp1);
//			cs2.addIsTargetedBy(vp2);
//			cs2.addIsTargetedBy(vp3);
//			cs2.addIsTargetedBy(vp4);
//			cs2.addIsTargetedBy(vp7);
//			cs2.addIsTargetedBy(vp9);
//			cs2.addIsTargetedBy(vp10);
//			cs3.addIsTargetedBy(vp2);
//			cs3.addIsTargetedBy(vp3);
//			cs3.addIsTargetedBy(vp4);
//			cs3.addIsTargetedBy(vp9);
//			cs3.addIsTargetedBy(vp10);
//			cs4.addIsTargetedBy(vp1);
//			cs4.addIsTargetedBy(vp5);
//			cs4.addIsTargetedBy(vp6);
//			cs4.addIsTargetedBy(vp7);
//			cs4.addIsTargetedBy(vp8);
//			cs4.addIsTargetedBy(vp11);
//			cs4.addIsTargetedBy(vp12);

//			OWLReasoner reasoner = new Reasoner.ReasonerFactory().createReasoner(bmoTest);
//			reasoner.precomputeInferences(InferenceType.OBJECT_PROPERTY_ASSERTIONS);
//			List<InferredAxiomGenerator<? extends OWLAxiom>> isTargeted = new ArrayList<InferredAxiomGenerator<? extends OWLAxiom>>();
//			isTargeted.add

//			try {
//				manager.saveOntology(bmoTest);
//			} catch (OWLOntologyStorageException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}


	}

	private void loadReports ()
	{
		// load reports from analysis and support
	}
	private void assessing ()
	{
		//
	}
	private void selectStrategy ()
	{
		//
	}
	private void planUpdate ()
	{
		//
	}
	private void scheduleMeetings ()
	{
		//
	}
	private void sendUpdate ()
	{
		// with timer
	}
	private void updateDiffusion ()
	{
		//
	}

}
