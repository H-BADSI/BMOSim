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
 * Source Class: DefaultValueShop <br>
 * @version generated on Thu Oct 26 02:05:01 WAT 2017 by Hichem
 */
public abstract class DefaultValueShop extends WrappedIndividualImpl implements ValueShop {

    public DefaultValueShop(OWLOntology ontology, IRI iri) {
        super((CodeGenerationInference) ontology, iri);
    }





    /* ***************************************************
     * Object Property http://www.owl-ontologies.com/unnamed.owl#creates
     */
     
    public Collection<? extends Resource> getCreates() {
        return getDelegate().getPropertyValues(getOwlIndividual(),
                                               Vocabulary.OBJECT_PROPERTY_CREATES,
                                               DefaultResource.class);
    }

    public boolean hasCreates() {
	   return !getCreates().isEmpty();
    }

    public void addCreates(Resource newCreates) {
        getDelegate().addPropertyValue(getOwlIndividual(),
                                       Vocabulary.OBJECT_PROPERTY_CREATES,
                                       newCreates);
    }

    public void removeCreates(Resource oldCreates) {
        getDelegate().removePropertyValue(getOwlIndividual(),
                                          Vocabulary.OBJECT_PROPERTY_CREATES,
                                          oldCreates);
    }


    /* ***************************************************
     * Object Property http://www.owl-ontologies.com/unnamed.owl#implements
     */

    public Collection<? extends ValueProposition> getImplements() {
        return getDelegate().getPropertyValues(getOwlIndividual(),
                                               Vocabulary.OBJECT_PROPERTY_IMPLEMENTS,
                                               DefaultValueProposition.class);
    }

    public boolean hasImplements() {
	   return !getImplements().isEmpty();
    }

    public void addImplements(ValueProposition newImplements) {
        getDelegate().addPropertyValue(getOwlIndividual(),
                                       Vocabulary.OBJECT_PROPERTY_IMPLEMENTS,
                                       newImplements);
    }

    public void removeImplements(ValueProposition oldImplements) {
        getDelegate().removePropertyValue(getOwlIndividual(),
                                          Vocabulary.OBJECT_PROPERTY_IMPLEMENTS,
                                          oldImplements);
    }


    /* ***************************************************
     * Object Property http://www.owl-ontologies.com/unnamed.owl#needs
     */

    public Collection<? extends Resource> getNeeds() {
        return getDelegate().getPropertyValues(getOwlIndividual(),
                                               Vocabulary.OBJECT_PROPERTY_NEEDS,
                                               DefaultResource.class);
    }

    public boolean hasNeeds() {
	   return !getNeeds().isEmpty();
    }

    public void addNeeds(Resource newNeeds) {
        getDelegate().addPropertyValue(getOwlIndividual(),
                                       Vocabulary.OBJECT_PROPERTY_NEEDS,
                                       newNeeds);
    }

    public void removeNeeds(Resource oldNeeds) {
        getDelegate().removePropertyValue(getOwlIndividual(),
                                          Vocabulary.OBJECT_PROPERTY_NEEDS,
                                          oldNeeds);
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
