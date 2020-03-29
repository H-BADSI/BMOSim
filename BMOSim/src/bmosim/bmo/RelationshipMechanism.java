package bmosim.bmo;

import java.util.Collection;

import org.protege.owl.codegeneration.WrappedIndividual;

import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLOntology;

/**
 * 
 * <p>
 * Generated by Protege (http://protege.stanford.edu). <br>
 * Source Class: RelationshipMechanism <br>
 * @version generated on Thu Oct 26 02:05:01 WAT 2017 by Hichem
 */

public interface RelationshipMechanism extends BMElement {

    /* ***************************************************
     * Property http://www.owl-ontologies.com/unnamed.owl#customerEquity
     */
     
    /**
     * Gets all property values for the customerEquity property.<p>
     * 
     * @returns a collection of values for the customerEquity property.
     */
    Collection<? extends Object> getCustomerEquity();

    /**
     * Checks if the class has a customerEquity property value.<p>
     * 
     * @return true if there is a customerEquity property value.
     */
    boolean hasCustomerEquity();

    /**
     * Adds a customerEquity property value.<p>
     * 
     * @param newCustomerEquity the customerEquity property value to be added
     */
    void addCustomerEquity(Object newCustomerEquity);

    /**
     * Removes a customerEquity property value.<p>
     * 
     * @param oldCustomerEquity the customerEquity property value to be removed.
     */
    void removeCustomerEquity(Object oldCustomerEquity);



    /* ***************************************************
     * Property http://www.owl-ontologies.com/unnamed.owl#reasoning
     */
     
    /**
     * Gets all property values for the reasoning property.<p>
     * 
     * @returns a collection of values for the reasoning property.
     */
    Collection<? extends String> getReasoning();

    /**
     * Checks if the class has a reasoning property value.<p>
     * 
     * @return true if there is a reasoning property value.
     */
    boolean hasReasoning();

    /**
     * Adds a reasoning property value.<p>
     * 
     * @param newReasoning the reasoning property value to be added
     */
    void addReasoning(String newReasoning);

    /**
     * Removes a reasoning property value.<p>
     * 
     * @param oldReasoning the reasoning property value to be removed.
     */
    void removeReasoning(String oldReasoning);



    /* ***************************************************
     * Property http://www.owl-ontologies.com/unnamed.owl#synonyms
     */
     
    /**
     * Gets all property values for the synonyms property.<p>
     * 
     * @returns a collection of values for the synonyms property.
     */
    Collection<? extends String> getSynonyms();

    /**
     * Checks if the class has a synonyms property value.<p>
     * 
     * @return true if there is a synonyms property value.
     */
    boolean hasSynonyms();

    /**
     * Adds a synonyms property value.<p>
     * 
     * @param newSynonyms the synonyms property value to be added
     */
    void addSynonyms(String newSynonyms);

    /**
     * Removes a synonyms property value.<p>
     * 
     * @param oldSynonyms the synonyms property value to be removed.
     */
    void removeSynonyms(String oldSynonyms);



    /* ***************************************************
     * Common interfaces
     */

    OWLNamedIndividual getOwlIndividual();

    OWLOntology getOwlOntology();

    void delete();

}
