package bmosim.bmo;

import java.util.Collection;

import org.protege.owl.codegeneration.WrappedIndividual;

import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLOntology;

/**
 * 
 * <p>
 * Generated by Protege (http://protege.stanford.edu). <br>
 * Source Class: DistributionChannel <br>
 * @version generated on Thu Oct 26 02:05:01 WAT 2017 by Hichem
 */

public interface DistributionChannel extends BMElement {

    /* ***************************************************
     * Property http://www.owl-ontologies.com/unnamed.owl#delivers
     */
     
    /**
     * Gets all property values for the delivers property.<p>
     * 
     * @returns a collection of values for the delivers property.
     */
    Collection<? extends ValueProposition> getDelivers();

    /**
     * Checks if the class has a delivers property value.<p>
     * 
     * @return true if there is a delivers property value.
     */
    boolean hasDelivers();

    /**
     * Adds a delivers property value.<p>
     * 
     * @param newDelivers the delivers property value to be added
     */
    void addDelivers(ValueProposition newDelivers);

    /**
     * Removes a delivers property value.<p>
     * 
     * @param oldDelivers the delivers property value to be removed.
     */
    void removeDelivers(ValueProposition oldDelivers);


    /* ***************************************************
     * Property http://www.owl-ontologies.com/unnamed.owl#serves
     */
     
    /**
     * Gets all property values for the serves property.<p>
     * 
     * @returns a collection of values for the serves property.
     */
    Collection<? extends CustomerSegment> getServes();

    /**
     * Checks if the class has a serves property value.<p>
     * 
     * @return true if there is a serves property value.
     */
    boolean hasServes();

    /**
     * Adds a serves property value.<p>
     * 
     * @param newServes the serves property value to be added
     */
    void addServes(CustomerSegment newServes);

    /**
     * Removes a serves property value.<p>
     * 
     * @param oldServes the serves property value to be removed.
     */
    void removeServes(CustomerSegment oldServes);


    /* ***************************************************
     * Property http://www.owl-ontologies.com/unnamed.owl#channelCategory
     */
     
    /**
     * Gets all property values for the channelCategory property.<p>
     * 
     * @returns a collection of values for the channelCategory property.
     */
    Collection<? extends String> getChannelCategory();

    /**
     * Checks if the class has a channelCategory property value.<p>
     * 
     * @return true if there is a channelCategory property value.
     */
    boolean hasChannelCategory();

    /**
     * Adds a channelCategory property value.<p>
     * 
     * @param newChannelCategory the channelCategory property value to be added
     */
    void addChannelCategory(String newChannelCategory);

    /**
     * Removes a channelCategory property value.<p>
     * 
     * @param oldChannelCategory the channelCategory property value to be removed.
     */
    void removeChannelCategory(String oldChannelCategory);



    /* ***************************************************
     * Property http://www.owl-ontologies.com/unnamed.owl#customerBuyingCycle
     */
     
    /**
     * Gets all property values for the customerBuyingCycle property.<p>
     * 
     * @returns a collection of values for the customerBuyingCycle property.
     */
    Collection<? extends Object> getCustomerBuyingCycle();

    /**
     * Checks if the class has a customerBuyingCycle property value.<p>
     * 
     * @return true if there is a customerBuyingCycle property value.
     */
    boolean hasCustomerBuyingCycle();

    /**
     * Adds a customerBuyingCycle property value.<p>
     * 
     * @param newCustomerBuyingCycle the customerBuyingCycle property value to be added
     */
    void addCustomerBuyingCycle(Object newCustomerBuyingCycle);

    /**
     * Removes a customerBuyingCycle property value.<p>
     * 
     * @param oldCustomerBuyingCycle the customerBuyingCycle property value to be removed.
     */
    void removeCustomerBuyingCycle(Object oldCustomerBuyingCycle);



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
