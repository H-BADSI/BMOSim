package bmosim.bmo.impl;

import bmosim.bmo.*;

import java.util.Collection;

import org.protege.owl.codegeneration.WrappedIndividual;
import org.protege.owl.codegeneration.impl.WrappedIndividualImpl;

import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLOntology;


/**
 * Generated by Protege (http://protege.stanford.edu).<br>
 * Source Class: DefaultBMElement <br>
 * @version generated on Thu Oct 26 02:05:01 WAT 2017 by Hichem
 */
public class DefaultBMElement extends WrappedIndividualImpl implements BMElement {

    public DefaultBMElement(OWLOntology ontology, IRI iri) {
        super(ontology, iri);
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
