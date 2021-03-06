package bmosim.bmo;

import bmosim.bmo.impl.*;

import java.util.Collection;

import org.protege.owl.codegeneration.CodeGenerationFactory;
import org.protege.owl.codegeneration.WrappedIndividual;
import org.protege.owl.codegeneration.impl.FactoryHelper;
import org.protege.owl.codegeneration.impl.ProtegeJavaMapping;
import org.protege.owl.codegeneration.inference.CodeGenerationInference;
import org.protege.owl.codegeneration.inference.SimpleInference;

import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;

/**
 * A class that serves as the entry point to the generated code providing access
 * to existing individuals in the ontology and the ability to create new individuals in the ontology.<p>
 * 
 * Generated by Protege (http://protege.stanford.edu).<br>
 * Source Class: MyFactory<br>
 * @version generated on Thu Oct 26 02:05:01 WAT 2017 by Hichem
 */
public class MyFactory implements CodeGenerationFactory {
    private OWLOntology ontology;
    private ProtegeJavaMapping javaMapping = new ProtegeJavaMapping();
    private FactoryHelper delegate;
    private CodeGenerationInference inference;

    public MyFactory(OWLOntology ontology) {
	    this(ontology, new SimpleInference(ontology));
    }
    
    public MyFactory(OWLOntology ontology, CodeGenerationInference inference) {
        this.ontology = ontology;
        this.inference = inference;
        javaMapping.initialize(ontology, inference);
        delegate = new FactoryHelper(ontology, inference);
    }

    public OWLOntology getOwlOntology() {
        return ontology;
    }
    
    public void saveOwlOntology() throws OWLOntologyStorageException {
        ontology.getOWLOntologyManager().saveOntology(ontology);
    }
    
    public void flushOwlReasoner() {
        delegate.flushOwlReasoner();
    }
    
    public boolean canAs(WrappedIndividual resource, Class<? extends WrappedIndividual> javaInterface) {
    	return javaMapping.canAs(resource, javaInterface);
    }
    
    public  <X extends WrappedIndividual> X as(WrappedIndividual resource, Class<? extends X> javaInterface) {
    	return javaMapping.as(resource, javaInterface);
    }
    
    public Class<?> getJavaInterfaceFromOwlClass(OWLClass cls) {
        return javaMapping.getJavaInterfaceFromOwlClass(cls);
    }
    
    public OWLClass getOwlClassFromJavaInterface(Class<?> javaInterface) {
	    return javaMapping.getOwlClassFromJavaInterface(javaInterface);
    }
    
    public CodeGenerationInference getInference() {
        return inference;
    }

    /* ***************************************************
     * Class http://www.owl-ontologies.com/unnamed.owl#Activity
     */

    {
        javaMapping.add("http://www.owl-ontologies.com/unnamed.owl#Activity", Activity.class, DefaultActivity.class);
    }

    /**
     * Creates an instance of type Activity.  Modifies the underlying ontology.
     */
    public Activity createActivity(String name) {
		return delegate.createWrappedIndividual(name, Vocabulary.CLASS_ACTIVITY, DefaultActivity.class);
    }

    /**
     * Gets an instance of type Activity with the given name.  Does not modify the underlying ontology.
     * @param name the name of the OWL named individual to be retrieved.
     */
    public Activity getActivity(String name) {
		return delegate.getWrappedIndividual(name, Vocabulary.CLASS_ACTIVITY, DefaultActivity.class);
    }

    /**
     * Gets all instances of Activity from the ontology.
     */
    public Collection<? extends Activity> getAllActivityInstances() {
		return delegate.getWrappedIndividuals(Vocabulary.CLASS_ACTIVITY, DefaultActivity.class);
    }


    /* ***************************************************
     * Class http://www.owl-ontologies.com/unnamed.owl#Actor
     */

    {
        javaMapping.add("http://www.owl-ontologies.com/unnamed.owl#Actor", Actor.class, DefaultActor.class);
    }

    /**
     * Creates an instance of type Actor.  Modifies the underlying ontology.
     */
    public Actor createActor(String name) {
		return delegate.createWrappedIndividual(name, Vocabulary.CLASS_ACTOR, DefaultActor.class);
    }

    /**
     * Gets an instance of type Actor with the given name.  Does not modify the underlying ontology.
     * @param name the name of the OWL named individual to be retrieved.
     */
    public Actor getActor(String name) {
		return delegate.getWrappedIndividual(name, Vocabulary.CLASS_ACTOR, DefaultActor.class);
    }

    /**
     * Gets all instances of Actor from the ontology.
     */
    public Collection<? extends Actor> getAllActorInstances() {
		return delegate.getWrappedIndividuals(Vocabulary.CLASS_ACTOR, DefaultActor.class);
    }


    /* ***************************************************
     * Class http://www.owl-ontologies.com/unnamed.owl#BMElement
     */

    {
        javaMapping.add("http://www.owl-ontologies.com/unnamed.owl#BMElement", BMElement.class, DefaultBMElement.class);
    }

    /**
     * Creates an instance of type BMElement.  Modifies the underlying ontology.
     */
    public BMElement createBMElement(String name) {
		return delegate.createWrappedIndividual(name, Vocabulary.CLASS_BMELEMENT, DefaultBMElement.class);
    }

    /**
     * Gets an instance of type BMElement with the given name.  Does not modify the underlying ontology.
     * @param name the name of the OWL named individual to be retrieved.
     */
    public BMElement getBMElement(String name) {
		return delegate.getWrappedIndividual(name, Vocabulary.CLASS_BMELEMENT, DefaultBMElement.class);
    }

    /**
     * Gets all instances of BMElement from the ontology.
     */
    public Collection<? extends BMElement> getAllBMElementInstances() {
		return delegate.getWrappedIndividuals(Vocabulary.CLASS_BMELEMENT, DefaultBMElement.class);
    }


    /* ***************************************************
     * Class http://www.owl-ontologies.com/unnamed.owl#CoreCapability
     */

    {
        javaMapping.add("http://www.owl-ontologies.com/unnamed.owl#CoreCapability", CoreCapability.class, DefaultCoreCapability.class);
    }

    /**
     * Creates an instance of type CoreCapability.  Modifies the underlying ontology.
     */
    public CoreCapability createCoreCapability(String name) {
		return delegate.createWrappedIndividual(name, Vocabulary.CLASS_CORECAPABILITY, DefaultCoreCapability.class);
    }

    /**
     * Gets an instance of type CoreCapability with the given name.  Does not modify the underlying ontology.
     * @param name the name of the OWL named individual to be retrieved.
     */
    public CoreCapability getCoreCapability(String name) {
		return delegate.getWrappedIndividual(name, Vocabulary.CLASS_CORECAPABILITY, DefaultCoreCapability.class);
    }

    /**
     * Gets all instances of CoreCapability from the ontology.
     */
    public Collection<? extends CoreCapability> getAllCoreCapabilityInstances() {
		return delegate.getWrappedIndividuals(Vocabulary.CLASS_CORECAPABILITY, DefaultCoreCapability.class);
    }


    /* ***************************************************
     * Class http://www.owl-ontologies.com/unnamed.owl#CostAccount
     */

    {
        javaMapping.add("http://www.owl-ontologies.com/unnamed.owl#CostAccount", CostAccount.class, DefaultCostAccount.class);
    }

    /**
     * Creates an instance of type CostAccount.  Modifies the underlying ontology.
     */
    public CostAccount createCostAccount(String name) {
		return delegate.createWrappedIndividual(name, Vocabulary.CLASS_COSTACCOUNT, DefaultCostAccount.class);
    }

    /**
     * Gets an instance of type CostAccount with the given name.  Does not modify the underlying ontology.
     * @param name the name of the OWL named individual to be retrieved.
     */
    public CostAccount getCostAccount(String name) {
		return delegate.getWrappedIndividual(name, Vocabulary.CLASS_COSTACCOUNT, DefaultCostAccount.class);
    }

    /**
     * Gets all instances of CostAccount from the ontology.
     */
    public Collection<? extends CostAccount> getAllCostAccountInstances() {
		return delegate.getWrappedIndividuals(Vocabulary.CLASS_COSTACCOUNT, DefaultCostAccount.class);
    }


    /* ***************************************************
     * Class http://www.owl-ontologies.com/unnamed.owl#CustomerSegment
     */

    {
        javaMapping.add("http://www.owl-ontologies.com/unnamed.owl#CustomerSegment", CustomerSegment.class, DefaultCustomerSegment.class);
    }

    /**
     * Creates an instance of type CustomerSegment.  Modifies the underlying ontology.
     */
    public CustomerSegment createCustomerSegment(String name) {
		return delegate.createWrappedIndividual(name, Vocabulary.CLASS_CUSTOMERSEGMENT, DefaultCustomerSegment.class);
    }

    /**
     * Gets an instance of type CustomerSegment with the given name.  Does not modify the underlying ontology.
     * @param name the name of the OWL named individual to be retrieved.
     */
    public CustomerSegment getCustomerSegment(String name) {
		return delegate.getWrappedIndividual(name, Vocabulary.CLASS_CUSTOMERSEGMENT, DefaultCustomerSegment.class);
    }

    /**
     * Gets all instances of CustomerSegment from the ontology.
     */
    public Collection<? extends CustomerSegment> getAllCustomerSegmentInstances() {
		return delegate.getWrappedIndividuals(Vocabulary.CLASS_CUSTOMERSEGMENT, DefaultCustomerSegment.class);
    }


    /* ***************************************************
     * Class http://www.owl-ontologies.com/unnamed.owl#DistributionChannel
     */

    {
        javaMapping.add("http://www.owl-ontologies.com/unnamed.owl#DistributionChannel", DistributionChannel.class, DefaultDistributionChannel.class);
    }

    /**
     * Creates an instance of type DistributionChannel.  Modifies the underlying ontology.
     */
    public DistributionChannel createDistributionChannel(String name) {
		return delegate.createWrappedIndividual(name, Vocabulary.CLASS_DISTRIBUTIONCHANNEL, DefaultDistributionChannel.class);
    }

    /**
     * Gets an instance of type DistributionChannel with the given name.  Does not modify the underlying ontology.
     * @param name the name of the OWL named individual to be retrieved.
     */
    public DistributionChannel getDistributionChannel(String name) {
		return delegate.getWrappedIndividual(name, Vocabulary.CLASS_DISTRIBUTIONCHANNEL, DefaultDistributionChannel.class);
    }

    /**
     * Gets all instances of DistributionChannel from the ontology.
     */
    public Collection<? extends DistributionChannel> getAllDistributionChannelInstances() {
		return delegate.getWrappedIndividuals(Vocabulary.CLASS_DISTRIBUTIONCHANNEL, DefaultDistributionChannel.class);
    }


    /* ***************************************************
     * Class http://www.owl-ontologies.com/unnamed.owl#DistributionLink
     */

    {
        javaMapping.add("http://www.owl-ontologies.com/unnamed.owl#DistributionLink", DistributionLink.class, DefaultDistributionLink.class);
    }

    /**
     * Creates an instance of type DistributionLink.  Modifies the underlying ontology.
     */
    public DistributionLink createDistributionLink(String name) {
		return delegate.createWrappedIndividual(name, Vocabulary.CLASS_DISTRIBUTIONLINK, DefaultDistributionLink.class);
    }

    /**
     * Gets an instance of type DistributionLink with the given name.  Does not modify the underlying ontology.
     * @param name the name of the OWL named individual to be retrieved.
     */
    public DistributionLink getDistributionLink(String name) {
		return delegate.getWrappedIndividual(name, Vocabulary.CLASS_DISTRIBUTIONLINK, DefaultDistributionLink.class);
    }

    /**
     * Gets all instances of DistributionLink from the ontology.
     */
    public Collection<? extends DistributionLink> getAllDistributionLinkInstances() {
		return delegate.getWrappedIndividuals(Vocabulary.CLASS_DISTRIBUTIONLINK, DefaultDistributionLink.class);
    }


    /* ***************************************************
     * Class http://www.owl-ontologies.com/unnamed.owl#PartnershipAgreement
     */

    {
        javaMapping.add("http://www.owl-ontologies.com/unnamed.owl#PartnershipAgreement", PartnershipAgreement.class, DefaultPartnershipAgreement.class);
    }

    /**
     * Creates an instance of type PartnershipAgreement.  Modifies the underlying ontology.
     */
    public PartnershipAgreement createPartnershipAgreement(String name) {
		return delegate.createWrappedIndividual(name, Vocabulary.CLASS_PARTNERSHIPAGREEMENT, DefaultPartnershipAgreement.class);
    }

    /**
     * Gets an instance of type PartnershipAgreement with the given name.  Does not modify the underlying ontology.
     * @param name the name of the OWL named individual to be retrieved.
     */
    public PartnershipAgreement getPartnershipAgreement(String name) {
		return delegate.getWrappedIndividual(name, Vocabulary.CLASS_PARTNERSHIPAGREEMENT, DefaultPartnershipAgreement.class);
    }

    /**
     * Gets all instances of PartnershipAgreement from the ontology.
     */
    public Collection<? extends PartnershipAgreement> getAllPartnershipAgreementInstances() {
		return delegate.getWrappedIndividuals(Vocabulary.CLASS_PARTNERSHIPAGREEMENT, DefaultPartnershipAgreement.class);
    }


    /* ***************************************************
     * Class http://www.owl-ontologies.com/unnamed.owl#RelationshipMechanism
     */

    {
        javaMapping.add("http://www.owl-ontologies.com/unnamed.owl#RelationshipMechanism", RelationshipMechanism.class, DefaultRelationshipMechanism.class);
    }

    /**
     * Creates an instance of type RelationshipMechanism.  Modifies the underlying ontology.
     */
    public RelationshipMechanism createRelationshipMechanism(String name) {
		return delegate.createWrappedIndividual(name, Vocabulary.CLASS_RELATIONSHIPMECHANISM, DefaultRelationshipMechanism.class);
    }

    /**
     * Gets an instance of type RelationshipMechanism with the given name.  Does not modify the underlying ontology.
     * @param name the name of the OWL named individual to be retrieved.
     */
    public RelationshipMechanism getRelationshipMechanism(String name) {
		return delegate.getWrappedIndividual(name, Vocabulary.CLASS_RELATIONSHIPMECHANISM, DefaultRelationshipMechanism.class);
    }

    /**
     * Gets all instances of RelationshipMechanism from the ontology.
     */
    public Collection<? extends RelationshipMechanism> getAllRelationshipMechanismInstances() {
		return delegate.getWrappedIndividuals(Vocabulary.CLASS_RELATIONSHIPMECHANISM, DefaultRelationshipMechanism.class);
    }


    /* ***************************************************
     * Class http://www.owl-ontologies.com/unnamed.owl#Resource
     */

    {
        javaMapping.add("http://www.owl-ontologies.com/unnamed.owl#Resource", Resource.class, DefaultResource.class);
    }

    /**
     * Creates an instance of type Resource.  Modifies the underlying ontology.
     */
    public Resource createResource(String name) {
		return delegate.createWrappedIndividual(name, Vocabulary.CLASS_RESOURCE, DefaultResource.class);
    }

    /**
     * Gets an instance of type Resource with the given name.  Does not modify the underlying ontology.
     * @param name the name of the OWL named individual to be retrieved.
     */
    public Resource getResource(String name) {
		return delegate.getWrappedIndividual(name, Vocabulary.CLASS_RESOURCE, DefaultResource.class);
    }

    /**
     * Gets all instances of Resource from the ontology.
     */
    public Collection<? extends Resource> getAllResourceInstances() {
		return delegate.getWrappedIndividuals(Vocabulary.CLASS_RESOURCE, DefaultResource.class);
    }


    /* ***************************************************
     * Class http://www.owl-ontologies.com/unnamed.owl#RevenueStream
     */

    {
        javaMapping.add("http://www.owl-ontologies.com/unnamed.owl#RevenueStream", RevenueStream.class, DefaultRevenueStream.class);
    }

    /**
     * Creates an instance of type RevenueStream.  Modifies the underlying ontology.
     */
    public RevenueStream createRevenueStream(String name) {
		return delegate.createWrappedIndividual(name, Vocabulary.CLASS_REVENUESTREAM, DefaultRevenueStream.class);
    }

    /**
     * Gets an instance of type RevenueStream with the given name.  Does not modify the underlying ontology.
     * @param name the name of the OWL named individual to be retrieved.
     */
    public RevenueStream getRevenueStream(String name) {
		return delegate.getWrappedIndividual(name, Vocabulary.CLASS_REVENUESTREAM, DefaultRevenueStream.class);
    }

    /**
     * Gets all instances of RevenueStream from the ontology.
     */
    public Collection<? extends RevenueStream> getAllRevenueStreamInstances() {
		return delegate.getWrappedIndividuals(Vocabulary.CLASS_REVENUESTREAM, DefaultRevenueStream.class);
    }


    /* ***************************************************
     * Class http://www.owl-ontologies.com/unnamed.owl#ValueChain
     */

    {
        javaMapping.add("http://www.owl-ontologies.com/unnamed.owl#ValueChain", ValueChain.class, DefaultValueChain.class);
    }

    /**
     * Creates an instance of type ValueChain.  Modifies the underlying ontology.
     */
    public ValueChain createValueChain(String name) {
		return delegate.createWrappedIndividual(name, Vocabulary.CLASS_VALUECHAIN, DefaultValueChain.class);
    }

    /**
     * Gets an instance of type ValueChain with the given name.  Does not modify the underlying ontology.
     * @param name the name of the OWL named individual to be retrieved.
     */
    public ValueChain getValueChain(String name) {
		return delegate.getWrappedIndividual(name, Vocabulary.CLASS_VALUECHAIN, DefaultValueChain.class);
    }

    /**
     * Gets all instances of ValueChain from the ontology.
     */
    public Collection<? extends ValueChain> getAllValueChainInstances() {
		return delegate.getWrappedIndividuals(Vocabulary.CLASS_VALUECHAIN, DefaultValueChain.class);
    }


    /* ***************************************************
     * Class http://www.owl-ontologies.com/unnamed.owl#ValueChainActivity
     */

    {
        javaMapping.add("http://www.owl-ontologies.com/unnamed.owl#ValueChainActivity", ValueChainActivity.class, DefaultValueChainActivity.class);
    }

    /**
     * Creates an instance of type ValueChainActivity.  Modifies the underlying ontology.
     */
    public ValueChainActivity createValueChainActivity(String name) {
		return delegate.createWrappedIndividual(name, Vocabulary.CLASS_VALUECHAINACTIVITY, DefaultValueChainActivity.class);
    }

    /**
     * Gets an instance of type ValueChainActivity with the given name.  Does not modify the underlying ontology.
     * @param name the name of the OWL named individual to be retrieved.
     */
    public ValueChainActivity getValueChainActivity(String name) {
		return delegate.getWrappedIndividual(name, Vocabulary.CLASS_VALUECHAINACTIVITY, DefaultValueChainActivity.class);
    }

    /**
     * Gets all instances of ValueChainActivity from the ontology.
     */
    public Collection<? extends ValueChainActivity> getAllValueChainActivityInstances() {
		return delegate.getWrappedIndividuals(Vocabulary.CLASS_VALUECHAINACTIVITY, DefaultValueChainActivity.class);
    }


    /* ***************************************************
     * Class http://www.owl-ontologies.com/unnamed.owl#ValueConfiguration
     */

    {
        javaMapping.add("http://www.owl-ontologies.com/unnamed.owl#ValueConfiguration", ValueConfiguration.class, DefaultValueConfiguration.class);
    }

    /**
     * Creates an instance of type ValueConfiguration.  Modifies the underlying ontology.
     */
    public ValueConfiguration createValueConfiguration(String name) {
		return delegate.createWrappedIndividual(name, Vocabulary.CLASS_VALUECONFIGURATION, DefaultValueConfiguration.class);
    }

    /**
     * Gets an instance of type ValueConfiguration with the given name.  Does not modify the underlying ontology.
     * @param name the name of the OWL named individual to be retrieved.
     */
    public ValueConfiguration getValueConfiguration(String name) {
		return delegate.getWrappedIndividual(name, Vocabulary.CLASS_VALUECONFIGURATION, DefaultValueConfiguration.class);
    }

    /**
     * Gets all instances of ValueConfiguration from the ontology.
     */
    public Collection<? extends ValueConfiguration> getAllValueConfigurationInstances() {
		return delegate.getWrappedIndividuals(Vocabulary.CLASS_VALUECONFIGURATION, DefaultValueConfiguration.class);
    }


    /* ***************************************************
     * Class http://www.owl-ontologies.com/unnamed.owl#ValueNetwork
     */

    {
        javaMapping.add("http://www.owl-ontologies.com/unnamed.owl#ValueNetwork", ValueNetwork.class, DefaultValueNetwork.class);
    }

    /**
     * Creates an instance of type ValueNetwork.  Modifies the underlying ontology.
     */
    public ValueNetwork createValueNetwork(String name) {
		return delegate.createWrappedIndividual(name, Vocabulary.CLASS_VALUENETWORK, DefaultValueNetwork.class);
    }

    /**
     * Gets an instance of type ValueNetwork with the given name.  Does not modify the underlying ontology.
     * @param name the name of the OWL named individual to be retrieved.
     */
    public ValueNetwork getValueNetwork(String name) {
		return delegate.getWrappedIndividual(name, Vocabulary.CLASS_VALUENETWORK, DefaultValueNetwork.class);
    }

    /**
     * Gets all instances of ValueNetwork from the ontology.
     */
    public Collection<? extends ValueNetwork> getAllValueNetworkInstances() {
		return delegate.getWrappedIndividuals(Vocabulary.CLASS_VALUENETWORK, DefaultValueNetwork.class);
    }


    /* ***************************************************
     * Class http://www.owl-ontologies.com/unnamed.owl#ValueNetworkActivity
     */

    {
        javaMapping.add("http://www.owl-ontologies.com/unnamed.owl#ValueNetworkActivity", ValueNetworkActivity.class, DefaultValueNetworkActivity.class);
    }

    /**
     * Creates an instance of type ValueNetworkActivity.  Modifies the underlying ontology.
     */
    public ValueNetworkActivity createValueNetworkActivity(String name) {
		return delegate.createWrappedIndividual(name, Vocabulary.CLASS_VALUENETWORKACTIVITY, DefaultValueNetworkActivity.class);
    }

    /**
     * Gets an instance of type ValueNetworkActivity with the given name.  Does not modify the underlying ontology.
     * @param name the name of the OWL named individual to be retrieved.
     */
    public ValueNetworkActivity getValueNetworkActivity(String name) {
		return delegate.getWrappedIndividual(name, Vocabulary.CLASS_VALUENETWORKACTIVITY, DefaultValueNetworkActivity.class);
    }

    /**
     * Gets all instances of ValueNetworkActivity from the ontology.
     */
    public Collection<? extends ValueNetworkActivity> getAllValueNetworkActivityInstances() {
		return delegate.getWrappedIndividuals(Vocabulary.CLASS_VALUENETWORKACTIVITY, DefaultValueNetworkActivity.class);
    }


    /* ***************************************************
     * Class http://www.owl-ontologies.com/unnamed.owl#ValueProposition
     */

    {
        javaMapping.add("http://www.owl-ontologies.com/unnamed.owl#ValueProposition", ValueProposition.class, DefaultValueProposition.class);
    }

    /**
     * Creates an instance of type ValueProposition.  Modifies the underlying ontology.
     */
    public ValueProposition createValueProposition(String name) {
		return delegate.createWrappedIndividual(name, Vocabulary.CLASS_VALUEPROPOSITION, DefaultValueProposition.class);
    }

    /**
     * Gets an instance of type ValueProposition with the given name.  Does not modify the underlying ontology.
     * @param name the name of the OWL named individual to be retrieved.
     */
    public ValueProposition getValueProposition(String name) {
		return delegate.getWrappedIndividual(name, Vocabulary.CLASS_VALUEPROPOSITION, DefaultValueProposition.class);
    }

    /**
     * Gets all instances of ValueProposition from the ontology.
     */
    public Collection<? extends ValueProposition> getAllValuePropositionInstances() {
		return delegate.getWrappedIndividuals(Vocabulary.CLASS_VALUEPROPOSITION, DefaultValueProposition.class);
    }


    /* ***************************************************
     * Class http://www.owl-ontologies.com/unnamed.owl#ValueShop
     */

    {
        javaMapping.add("http://www.owl-ontologies.com/unnamed.owl#ValueShop", ValueShop.class, DefaultValueShop.class);
    }

    /**
     * Creates an instance of type ValueShop.  Modifies the underlying ontology.
     */
    public ValueShop createValueShop(String name) {
		return delegate.createWrappedIndividual(name, Vocabulary.CLASS_VALUESHOP, DefaultValueShop.class);
    }

    /**
     * Gets an instance of type ValueShop with the given name.  Does not modify the underlying ontology.
     * @param name the name of the OWL named individual to be retrieved.
     */
    public ValueShop getValueShop(String name) {
		return delegate.getWrappedIndividual(name, Vocabulary.CLASS_VALUESHOP, DefaultValueShop.class);
    }

    /**
     * Gets all instances of ValueShop from the ontology.
     */
    public Collection<? extends ValueShop> getAllValueShopInstances() {
		return delegate.getWrappedIndividuals(Vocabulary.CLASS_VALUESHOP, DefaultValueShop.class);
    }


    /* ***************************************************
     * Class http://www.owl-ontologies.com/unnamed.owl#ValueShopActivity
     */

    {
        javaMapping.add("http://www.owl-ontologies.com/unnamed.owl#ValueShopActivity", ValueShopActivity.class, DefaultValueShopActivity.class);
    }

    /**
     * Creates an instance of type ValueShopActivity.  Modifies the underlying ontology.
     */
    public ValueShopActivity createValueShopActivity(String name) {
		return delegate.createWrappedIndividual(name, Vocabulary.CLASS_VALUESHOPACTIVITY, DefaultValueShopActivity.class);
    }

    /**
     * Gets an instance of type ValueShopActivity with the given name.  Does not modify the underlying ontology.
     * @param name the name of the OWL named individual to be retrieved.
     */
    public ValueShopActivity getValueShopActivity(String name) {
		return delegate.getWrappedIndividual(name, Vocabulary.CLASS_VALUESHOPACTIVITY, DefaultValueShopActivity.class);
    }

    /**
     * Gets all instances of ValueShopActivity from the ontology.
     */
    public Collection<? extends ValueShopActivity> getAllValueShopActivityInstances() {
		return delegate.getWrappedIndividuals(Vocabulary.CLASS_VALUESHOPACTIVITY, DefaultValueShopActivity.class);
    }


}
