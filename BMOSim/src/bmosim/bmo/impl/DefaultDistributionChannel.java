package bmosim.bmo.impl;

import bmosim.bmo.*;

import java.util.Collection;

import org.protege.owl.codegeneration.WrappedIndividual;
import org.protege.owl.codegeneration.impl.WrappedIndividualImpl;

import org.protege.owl.codegeneration.inference.CodeGenerationInference;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLOntology;


/**
 * Generated by Protege (http://protege.stanford.edu).<br>
 * Source Class: DefaultDistributionChannel <br>
 * @version generated on Thu Oct 26 02:05:01 WAT 2017 by Hichem
 */
public class DefaultDistributionChannel extends WrappedIndividualImpl implements DistributionChannel {

    public DefaultDistributionChannel(OWLOntology ontology, IRI iri) {
        super((CodeGenerationInference) ontology, iri);
    }





    /* ***************************************************
     * Object Property http://www.owl-ontologies.com/unnamed.owl#delivers
     */
     
    public Collection<? extends ValueProposition> getDelivers() {
        return getDelegate().getPropertyValues(getOwlIndividual(),
                                               Vocabulary.OBJECT_PROPERTY_DELIVERS,
                                               DefaultValueProposition.class);
    }

    public boolean hasDelivers() {
	   return !getDelivers().isEmpty();
    }

    public void addDelivers(ValueProposition newDelivers) {
        getDelegate().addPropertyValue(getOwlIndividual(),
                                       Vocabulary.OBJECT_PROPERTY_DELIVERS,
                                       newDelivers);
    }

    public void removeDelivers(ValueProposition oldDelivers) {
        getDelegate().removePropertyValue(getOwlIndividual(),
                                          Vocabulary.OBJECT_PROPERTY_DELIVERS,
                                          oldDelivers);
    }


    /* ***************************************************
     * Object Property http://www.owl-ontologies.com/unnamed.owl#serves
     */
     
    public Collection<? extends CustomerSegment> getServes() {
        return getDelegate().getPropertyValues(getOwlIndividual(),
                                               Vocabulary.OBJECT_PROPERTY_SERVES,
                                               DefaultCustomerSegment.class);
    }

    public boolean hasServes() {
	   return !getServes().isEmpty();
    }

    public void addServes(CustomerSegment newServes) {
        getDelegate().addPropertyValue(getOwlIndividual(),
                                       Vocabulary.OBJECT_PROPERTY_SERVES,
                                       newServes);
    }

    public void removeServes(CustomerSegment oldServes) {
        getDelegate().removePropertyValue(getOwlIndividual(),
                                          Vocabulary.OBJECT_PROPERTY_SERVES,
                                          oldServes);
    }


    /* ***************************************************
     * Data Property http://www.owl-ontologies.com/unnamed.owl#channelCategory
     */
     
    public Collection<? extends String> getChannelCategory() {
		return getDelegate().getPropertyValues(getOwlIndividual(), Vocabulary.DATA_PROPERTY_CHANNELCATEGORY, String.class);
    }

    public boolean hasChannelCategory() {
		return !getChannelCategory().isEmpty();
    }

    public void addChannelCategory(String newChannelCategory) {
	    getDelegate().addPropertyValue(getOwlIndividual(), Vocabulary.DATA_PROPERTY_CHANNELCATEGORY, newChannelCategory);
    }

    public void removeChannelCategory(String oldChannelCategory) {
		getDelegate().removePropertyValue(getOwlIndividual(), Vocabulary.DATA_PROPERTY_CHANNELCATEGORY, oldChannelCategory);
    }


    /* ***************************************************
     * Data Property http://www.owl-ontologies.com/unnamed.owl#customerBuyingCycle
     */
     
    public Collection<? extends Object> getCustomerBuyingCycle() {
		return getDelegate().getPropertyValues(getOwlIndividual(), Vocabulary.DATA_PROPERTY_CUSTOMERBUYINGCYCLE, Object.class);
    }

    public boolean hasCustomerBuyingCycle() {
		return !getCustomerBuyingCycle().isEmpty();
    }

    public void addCustomerBuyingCycle(Object newCustomerBuyingCycle) {
	    getDelegate().addPropertyValue(getOwlIndividual(), Vocabulary.DATA_PROPERTY_CUSTOMERBUYINGCYCLE, newCustomerBuyingCycle);
    }

    public void removeCustomerBuyingCycle(Object oldCustomerBuyingCycle) {
		getDelegate().removePropertyValue(getOwlIndividual(), Vocabulary.DATA_PROPERTY_CUSTOMERBUYINGCYCLE, oldCustomerBuyingCycle);
    }


    /* ***************************************************
     * Data Property http://www.owl-ontologies.com/unnamed.owl#reasoning
     */
     
    public Collection<? extends String> getReasoning() {
		return getDelegate().getPropertyValues(getOwlIndividual(), Vocabulary.DATA_PROPERTY_REASONING, String.class);
    }

    public boolean hasReasoning() {
		return !getReasoning().isEmpty();
    }

    public void addReasoning(String newReasoning) {
	    getDelegate().addPropertyValue(getOwlIndividual(), Vocabulary.DATA_PROPERTY_REASONING, newReasoning);
    }

    public void removeReasoning(String oldReasoning) {
		getDelegate().removePropertyValue(getOwlIndividual(), Vocabulary.DATA_PROPERTY_REASONING, oldReasoning);
    }


    /* ***************************************************
     * Data Property http://www.owl-ontologies.com/unnamed.owl#synonyms
     */
     
    public Collection<? extends String> getSynonyms() {
		return getDelegate().getPropertyValues(getOwlIndividual(), Vocabulary.DATA_PROPERTY_SYNONYMS, String.class);
    }

    public boolean hasSynonyms() {
		return !getSynonyms().isEmpty();
    }

    public void addSynonyms(String newSynonyms) {
	    getDelegate().addPropertyValue(getOwlIndividual(), Vocabulary.DATA_PROPERTY_SYNONYMS, newSynonyms);
    }

    public void removeSynonyms(String oldSynonyms) {
		getDelegate().removePropertyValue(getOwlIndividual(), Vocabulary.DATA_PROPERTY_SYNONYMS, oldSynonyms);
    }


}
