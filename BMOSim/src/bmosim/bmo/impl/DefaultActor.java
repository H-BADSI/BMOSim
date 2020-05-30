package bmosim.bmo.impl;

import bmosim.bmo.*;

import java.util.Collection;

import org.protege.owl.codegeneration.WrappedIndividual;
import org.protege.owl.codegeneration.impl.WrappedIndividualImpl;

import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLOntology;


/**
 * Generated by Protege (http://protege.stanford.edu).<br>
 * Source Class: DefaultActor <br>
 * @version generated on Thu Oct 26 02:05:01 WAT 2017 by Hichem
 */
public class DefaultActor extends WrappedIndividualImpl implements Actor {

    public DefaultActor(OWLOntology ontology, IRI iri) {
        super(ontology, iri);
    }





    /* ***************************************************
     * Object Property http://www.owl-ontologies.com/unnamed.owl#isRelatedVia
     */
     
    public Collection<? extends PartnershipAgreement> getIsRelatedVia() {
//        return getDelegate().getPropertyValues(getOwlIndividual(),
//                                               Vocabulary.OBJECT_PROPERTY_ISRELATEDVIA,
//                                               DefaultPartnershipAgreement.class);
        return null;
    }

    public boolean hasIsRelatedVia() {
	   return !getIsRelatedVia().isEmpty();
    }

    public void addIsRelatedVia(PartnershipAgreement newIsRelatedVia) {
//        getDelegate().addPropertyValue(getOwlIndividual(),
//                                       Vocabulary.OBJECT_PROPERTY_ISRELATEDVIA,
//                                       newIsRelatedVia);

    }

    public void removeIsRelatedVia(PartnershipAgreement oldIsRelatedVia) {
//        getDelegate().removePropertyValue(getOwlIndividual(),
//                                          Vocabulary.OBJECT_PROPERTY_ISRELATEDVIA,
//                                          oldIsRelatedVia);
    }


    /* ***************************************************
     * Data Property http://www.owl-ontologies.com/unnamed.owl#reasoning
     */
     
    public Collection<? extends String> getReasoning() {
//		return getDelegate().getPropertyValues(getOwlIndividual(), Vocabulary.DATA_PROPERTY_REASONING, String.class);
        return null;
    }

    public boolean hasReasoning() {
		return !getReasoning().isEmpty();
    }

    public void addReasoning(String newReasoning) {
//	    getDelegate().addPropertyValue(getOwlIndividual(), Vocabulary.DATA_PROPERTY_REASONING, newReasoning);
    }

    public void removeReasoning(String oldReasoning) {
//		getDelegate().removePropertyValue(getOwlIndividual(), Vocabulary.DATA_PROPERTY_REASONING, oldReasoning);
    }


    /* ***************************************************
     * Data Property http://www.owl-ontologies.com/unnamed.owl#synonyms
     */
     
    public Collection<? extends String> getSynonyms() {
//		return getDelegate().getPropertyValues(getOwlIndividual(), Vocabulary.DATA_PROPERTY_SYNONYMS, String.class);
        return null;
    }

    public boolean hasSynonyms() {
		return !getSynonyms().isEmpty();
    }

    public void addSynonyms(String newSynonyms) {
//	    getDelegate().addPropertyValue(getOwlIndividual(), Vocabulary.DATA_PROPERTY_SYNONYMS, newSynonyms);
    }

    public void removeSynonyms(String oldSynonyms) {
//		getDelegate().removePropertyValue(getOwlIndividual(), Vocabulary.DATA_PROPERTY_SYNONYMS, oldSynonyms);
    }


}
