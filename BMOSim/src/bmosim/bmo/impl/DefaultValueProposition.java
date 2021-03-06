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
 * Source Class: DefaultValueProposition <br>
 * @version generated on Thu Oct 26 02:05:01 WAT 2017 by Hichem
 */
public abstract class DefaultValueProposition extends WrappedIndividualImpl implements ValueProposition {

    public DefaultValueProposition(OWLOntology ontology, IRI iri) {
        super((CodeGenerationInference) ontology, iri);
    }





    /* ***************************************************
     * Object Property http://www.owl-ontologies.com/unnamed.owl#isDeliveredBy
     */
     
    public Collection<? extends DistributionChannel> getIsDeliveredBy() {
        return getDelegate().getPropertyValues(getOwlIndividual(),
                                               Vocabulary.OBJECT_PROPERTY_ISDELIVEREDBY,
                                               DefaultDistributionChannel.class);
    }

    public boolean hasIsDeliveredBy() {
	   return !getIsDeliveredBy().isEmpty();
    }

    public void addIsDeliveredBy(DistributionChannel newIsDeliveredBy) {
        getDelegate().addPropertyValue(getOwlIndividual(),
                                       Vocabulary.OBJECT_PROPERTY_ISDELIVEREDBY,
                                       newIsDeliveredBy);
    }

    public void removeIsDeliveredBy(DistributionChannel oldIsDeliveredBy) {
        getDelegate().removePropertyValue(getOwlIndividual(),
                                          Vocabulary.OBJECT_PROPERTY_ISDELIVEREDBY,
                                          oldIsDeliveredBy);
    }


    /* ***************************************************
     * Object Property http://www.owl-ontologies.com/unnamed.owl#isImplementedBy
     */

    public Collection<? extends ValueConfiguration> getIsImplementedBy() {
        return getDelegate().getPropertyValues(getOwlIndividual(),
                                               Vocabulary.OBJECT_PROPERTY_ISIMPLEMENTEDBY,
                                               DefaultValueConfiguration.class);
    }

    public boolean hasIsImplementedBy() {
	   return !getIsImplementedBy().isEmpty();
    }

    public void addIsImplementedBy(ValueConfiguration newIsImplementedBy) {
        getDelegate().addPropertyValue(getOwlIndividual(),
                                       Vocabulary.OBJECT_PROPERTY_ISIMPLEMENTEDBY,
                                       newIsImplementedBy);
    }

    public void removeIsImplementedBy(ValueConfiguration oldIsImplementedBy) {
        getDelegate().removePropertyValue(getOwlIndividual(),
                                          Vocabulary.OBJECT_PROPERTY_ISIMPLEMENTEDBY,
                                          oldIsImplementedBy);
    }


    /* ***************************************************
     * Object Property http://www.owl-ontologies.com/unnamed.owl#requires
     */

    public Collection<? extends Resource> getRequires() {
        return getDelegate().getPropertyValues(getOwlIndividual(),
                                               Vocabulary.OBJECT_PROPERTY_REQUIRES,
                                               DefaultResource.class);
    }

    public boolean hasRequires() {
	   return !getRequires().isEmpty();
    }

    public void addRequires(Resource newRequires) {
        getDelegate().addPropertyValue(getOwlIndividual(),
                                       Vocabulary.OBJECT_PROPERTY_REQUIRES,
                                       newRequires);
    }

    public void removeRequires(Resource oldRequires) {
        getDelegate().removePropertyValue(getOwlIndividual(),
                                          Vocabulary.OBJECT_PROPERTY_REQUIRES,
                                          oldRequires);
    }


    /* ***************************************************
     * Object Property http://www.owl-ontologies.com/unnamed.owl#targets
     */

    public Collection<? extends CustomerSegment> getTargets() {
        return getDelegate().getPropertyValues(getOwlIndividual(),
                                               Vocabulary.OBJECT_PROPERTY_TARGETS,
                                               DefaultCustomerSegment.class);
    }

    public boolean hasTargets() {
	   return !getTargets().isEmpty();
    }

    public void addTargets(CustomerSegment newTargets) {
        getDelegate().addPropertyValue(getOwlIndividual(),
                                       Vocabulary.OBJECT_PROPERTY_TARGETS,
                                       newTargets);
    }

    public void removeTargets(CustomerSegment oldTargets) {
        getDelegate().removePropertyValue(getOwlIndividual(),
                                          Vocabulary.OBJECT_PROPERTY_TARGETS,
                                          oldTargets);
    }


    /* ***************************************************
     * Data Property http://www.owl-ontologies.com/unnamed.owl#lifeCycle
     */

    public Collection<? extends Object> getLifeCycle() {
		return getDelegate().getPropertyValues(getOwlIndividual(), Vocabulary.DATA_PROPERTY_LIFECYCLE, Object.class);
    }

    public boolean hasLifeCycle() {
		return !getLifeCycle().isEmpty();
    }

    public void addLifeCycle(Object newLifeCycle) {
	    getDelegate().addPropertyValue(getOwlIndividual(), Vocabulary.DATA_PROPERTY_LIFECYCLE, newLifeCycle);
    }

    public void removeLifeCycle(Object oldLifeCycle) {
		getDelegate().removePropertyValue(getOwlIndividual(), Vocabulary.DATA_PROPERTY_LIFECYCLE, oldLifeCycle);
    }


    /* ***************************************************
     * Data Property http://www.owl-ontologies.com/unnamed.owl#lifeCycleComments
     */

    public Collection<? extends String> getLifeCycleComments() {
		return getDelegate().getPropertyValues(getOwlIndividual(), Vocabulary.DATA_PROPERTY_LIFECYCLECOMMENTS, String.class);
    }

    public boolean hasLifeCycleComments() {
		return !getLifeCycleComments().isEmpty();
    }

    public void addLifeCycleComments(String newLifeCycleComments) {
	    getDelegate().addPropertyValue(getOwlIndividual(), Vocabulary.DATA_PROPERTY_LIFECYCLECOMMENTS, newLifeCycleComments);
    }

    public void removeLifeCycleComments(String oldLifeCycleComments) {
		getDelegate().removePropertyValue(getOwlIndividual(), Vocabulary.DATA_PROPERTY_LIFECYCLECOMMENTS, oldLifeCycleComments);
    }


    /* ***************************************************
     * Data Property http://www.owl-ontologies.com/unnamed.owl#priceLevel
     */

    public Collection<? extends Object> getPriceLevel() {
		return getDelegate().getPropertyValues(getOwlIndividual(), Vocabulary.DATA_PROPERTY_PRICELEVEL, Object.class);
    }

    public boolean hasPriceLevel() {
		return !getPriceLevel().isEmpty();
    }

    public void addPriceLevel(Object newPriceLevel) {
	    getDelegate().addPropertyValue(getOwlIndividual(), Vocabulary.DATA_PROPERTY_PRICELEVEL, newPriceLevel);
    }

    public void removePriceLevel(Object oldPriceLevel) {
		getDelegate().removePropertyValue(getOwlIndividual(), Vocabulary.DATA_PROPERTY_PRICELEVEL, oldPriceLevel);
    }


    /* ***************************************************
     * Data Property http://www.owl-ontologies.com/unnamed.owl#priceLevelComments
     */

    public Collection<? extends String> getPriceLevelComments() {
		return getDelegate().getPropertyValues(getOwlIndividual(), Vocabulary.DATA_PROPERTY_PRICELEVELCOMMENTS, String.class);
    }

    public boolean hasPriceLevelComments() {
		return !getPriceLevelComments().isEmpty();
    }

    public void addPriceLevelComments(String newPriceLevelComments) {
	    getDelegate().addPropertyValue(getOwlIndividual(), Vocabulary.DATA_PROPERTY_PRICELEVELCOMMENTS, newPriceLevelComments);
    }

    public void removePriceLevelComments(String oldPriceLevelComments) {
		getDelegate().removePropertyValue(getOwlIndividual(), Vocabulary.DATA_PROPERTY_PRICELEVELCOMMENTS, oldPriceLevelComments);
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


    /* ***************************************************
     * Data Property http://www.owl-ontologies.com/unnamed.owl#valueLevel
     */

    public Collection<? extends Object> getValueLevel() {
		return getDelegate().getPropertyValues(getOwlIndividual(), Vocabulary.DATA_PROPERTY_VALUELEVEL, Object.class);
    }

    public boolean hasValueLevel() {
		return !getValueLevel().isEmpty();
    }

    public void addValueLevel(Object newValueLevel) {
	    getDelegate().addPropertyValue(getOwlIndividual(), Vocabulary.DATA_PROPERTY_VALUELEVEL, newValueLevel);
    }

    public void removeValueLevel(Object oldValueLevel) {
		getDelegate().removePropertyValue(getOwlIndividual(), Vocabulary.DATA_PROPERTY_VALUELEVEL, oldValueLevel);
    }


    /* ***************************************************
     * Data Property http://www.owl-ontologies.com/unnamed.owl#valueLevelComments
     */

    public Collection<? extends String> getValueLevelComments() {
		return getDelegate().getPropertyValues(getOwlIndividual(), Vocabulary.DATA_PROPERTY_VALUELEVELCOMMENTS, String.class);
    }

    public boolean hasValueLevelComments() {
		return !getValueLevelComments().isEmpty();
    }

    public void addValueLevelComments(String newValueLevelComments) {
	    getDelegate().addPropertyValue(getOwlIndividual(), Vocabulary.DATA_PROPERTY_VALUELEVELCOMMENTS, newValueLevelComments);
    }

    public void removeValueLevelComments(String oldValueLevelComments) {
		getDelegate().removePropertyValue(getOwlIndividual(), Vocabulary.DATA_PROPERTY_VALUELEVELCOMMENTS, oldValueLevelComments);
    }


}
