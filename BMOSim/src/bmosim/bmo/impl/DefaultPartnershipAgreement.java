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
 * Source Class: DefaultPartnershipAgreement <br>
 * @version generated on Thu Oct 26 02:05:01 WAT 2017 by Hichem
 */
public abstract class DefaultPartnershipAgreement extends WrappedIndividualImpl implements PartnershipAgreement {

    public DefaultPartnershipAgreement(OWLOntology ontology, IRI iri) {
        super((CodeGenerationInference) ontology, iri);
    }





    /* ***************************************************
     * Object Property http://www.owl-ontologies.com/unnamed.owl#relates
     */
     
    public Collection<? extends Actor> getRelates() {
        return getDelegate().getPropertyValues(getOwlIndividual(),
                                               Vocabulary.OBJECT_PROPERTY_RELATES,
                                               DefaultActor.class);
    }

    public boolean hasRelates() {
	   return !getRelates().isEmpty();
    }

    public void addRelates(Actor newRelates) {
        getDelegate().addPropertyValue(getOwlIndividual(),
                                       Vocabulary.OBJECT_PROPERTY_RELATES,
                                       newRelates);
    }

    public void removeRelates(Actor oldRelates) {
        getDelegate().removePropertyValue(getOwlIndividual(),
                                          Vocabulary.OBJECT_PROPERTY_RELATES,
                                          oldRelates);
    }


    /* ***************************************************
     * Data Property http://www.owl-ontologies.com/unnamed.owl#competitionDegree
     */

    public Collection<? extends Object> getCompetitionDegree() {
		return getDelegate().getPropertyValues(getOwlIndividual(), Vocabulary.DATA_PROPERTY_COMPETITIONDEGREE, Object.class);
    }

    public boolean hasCompetitionDegree() {
		return !getCompetitionDegree().isEmpty();
    }

    public void addCompetitionDegree(Object newCompetitionDegree) {
	    getDelegate().addPropertyValue(getOwlIndividual(), Vocabulary.DATA_PROPERTY_COMPETITIONDEGREE, newCompetitionDegree);
    }

    public void removeCompetitionDegree(Object oldCompetitionDegree) {
		getDelegate().removePropertyValue(getOwlIndividual(), Vocabulary.DATA_PROPERTY_COMPETITIONDEGREE, oldCompetitionDegree);
    }


    /* ***************************************************
     * Data Property http://www.owl-ontologies.com/unnamed.owl#competitionDegreeComments
     */

    public Collection<? extends String> getCompetitionDegreeComments() {
		return getDelegate().getPropertyValues(getOwlIndividual(), Vocabulary.DATA_PROPERTY_COMPETITIONDEGREECOMMENTS, String.class);
    }

    public boolean hasCompetitionDegreeComments() {
		return !getCompetitionDegreeComments().isEmpty();
    }

    public void addCompetitionDegreeComments(String newCompetitionDegreeComments) {
	    getDelegate().addPropertyValue(getOwlIndividual(), Vocabulary.DATA_PROPERTY_COMPETITIONDEGREECOMMENTS, newCompetitionDegreeComments);
    }

    public void removeCompetitionDegreeComments(String oldCompetitionDegreeComments) {
		getDelegate().removePropertyValue(getOwlIndividual(), Vocabulary.DATA_PROPERTY_COMPETITIONDEGREECOMMENTS, oldCompetitionDegreeComments);
    }


    /* ***************************************************
     * Data Property http://www.owl-ontologies.com/unnamed.owl#integrationDegree
     */

    public Collection<? extends Object> getIntegrationDegree() {
		return getDelegate().getPropertyValues(getOwlIndividual(), Vocabulary.DATA_PROPERTY_INTEGRATIONDEGREE, Object.class);
    }

    public boolean hasIntegrationDegree() {
		return !getIntegrationDegree().isEmpty();
    }

    public void addIntegrationDegree(Object newIntegrationDegree) {
	    getDelegate().addPropertyValue(getOwlIndividual(), Vocabulary.DATA_PROPERTY_INTEGRATIONDEGREE, newIntegrationDegree);
    }

    public void removeIntegrationDegree(Object oldIntegrationDegree) {
		getDelegate().removePropertyValue(getOwlIndividual(), Vocabulary.DATA_PROPERTY_INTEGRATIONDEGREE, oldIntegrationDegree);
    }


    /* ***************************************************
     * Data Property http://www.owl-ontologies.com/unnamed.owl#integrationDegreeComments
     */

    public Collection<? extends String> getIntegrationDegreeComments() {
		return getDelegate().getPropertyValues(getOwlIndividual(), Vocabulary.DATA_PROPERTY_INTEGRATIONDEGREECOMMENTS, String.class);
    }

    public boolean hasIntegrationDegreeComments() {
		return !getIntegrationDegreeComments().isEmpty();
    }

    public void addIntegrationDegreeComments(String newIntegrationDegreeComments) {
	    getDelegate().addPropertyValue(getOwlIndividual(), Vocabulary.DATA_PROPERTY_INTEGRATIONDEGREECOMMENTS, newIntegrationDegreeComments);
    }

    public void removeIntegrationDegreeComments(String oldIntegrationDegreeComments) {
		getDelegate().removePropertyValue(getOwlIndividual(), Vocabulary.DATA_PROPERTY_INTEGRATIONDEGREECOMMENTS, oldIntegrationDegreeComments);
    }


    /* ***************************************************
     * Data Property http://www.owl-ontologies.com/unnamed.owl#partnershipCategory
     */

    public Collection<? extends Object> getPartnershipCategory() {
		return getDelegate().getPropertyValues(getOwlIndividual(), Vocabulary.DATA_PROPERTY_PARTNERSHIPCATEGORY, Object.class);
    }

    public boolean hasPartnershipCategory() {
		return !getPartnershipCategory().isEmpty();
    }

    public void addPartnershipCategory(Object newPartnershipCategory) {
	    getDelegate().addPropertyValue(getOwlIndividual(), Vocabulary.DATA_PROPERTY_PARTNERSHIPCATEGORY, newPartnershipCategory);
    }

    public void removePartnershipCategory(Object oldPartnershipCategory) {
		getDelegate().removePropertyValue(getOwlIndividual(), Vocabulary.DATA_PROPERTY_PARTNERSHIPCATEGORY, oldPartnershipCategory);
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
     * Data Property http://www.owl-ontologies.com/unnamed.owl#strategicImportance
     */

    public Collection<? extends Object> getStrategicImportance() {
		return getDelegate().getPropertyValues(getOwlIndividual(), Vocabulary.DATA_PROPERTY_STRATEGICIMPORTANCE, Object.class);
    }

    public boolean hasStrategicImportance() {
		return !getStrategicImportance().isEmpty();
    }

    public void addStrategicImportance(Object newStrategicImportance) {
	    getDelegate().addPropertyValue(getOwlIndividual(), Vocabulary.DATA_PROPERTY_STRATEGICIMPORTANCE, newStrategicImportance);
    }

    public void removeStrategicImportance(Object oldStrategicImportance) {
		getDelegate().removePropertyValue(getOwlIndividual(), Vocabulary.DATA_PROPERTY_STRATEGICIMPORTANCE, oldStrategicImportance);
    }


    /* ***************************************************
     * Data Property http://www.owl-ontologies.com/unnamed.owl#strategicImportanceComments
     */

    public Collection<? extends String> getStrategicImportanceComments() {
		return getDelegate().getPropertyValues(getOwlIndividual(), Vocabulary.DATA_PROPERTY_STRATEGICIMPORTANCECOMMENTS, String.class);
    }

    public boolean hasStrategicImportanceComments() {
		return !getStrategicImportanceComments().isEmpty();
    }

    public void addStrategicImportanceComments(String newStrategicImportanceComments) {
	    getDelegate().addPropertyValue(getOwlIndividual(), Vocabulary.DATA_PROPERTY_STRATEGICIMPORTANCECOMMENTS, newStrategicImportanceComments);
    }

    public void removeStrategicImportanceComments(String oldStrategicImportanceComments) {
		getDelegate().removePropertyValue(getOwlIndividual(), Vocabulary.DATA_PROPERTY_STRATEGICIMPORTANCECOMMENTS, oldStrategicImportanceComments);
    }


    /* ***************************************************
     * Data Property http://www.owl-ontologies.com/unnamed.owl#switchingCost
     */

    public Collection<? extends Object> getSwitchingCost() {
		return getDelegate().getPropertyValues(getOwlIndividual(), Vocabulary.DATA_PROPERTY_SWITCHINGCOST, Object.class);
    }

    public boolean hasSwitchingCost() {
		return !getSwitchingCost().isEmpty();
    }

    public void addSwitchingCost(Object newSwitchingCost) {
	    getDelegate().addPropertyValue(getOwlIndividual(), Vocabulary.DATA_PROPERTY_SWITCHINGCOST, newSwitchingCost);
    }

    public void removeSwitchingCost(Object oldSwitchingCost) {
		getDelegate().removePropertyValue(getOwlIndividual(), Vocabulary.DATA_PROPERTY_SWITCHINGCOST, oldSwitchingCost);
    }


    /* ***************************************************
     * Data Property http://www.owl-ontologies.com/unnamed.owl#switchingCostComments
     */

    public Collection<? extends String> getSwitchingCostComments() {
		return getDelegate().getPropertyValues(getOwlIndividual(), Vocabulary.DATA_PROPERTY_SWITCHINGCOSTCOMMENTS, String.class);
    }

    public boolean hasSwitchingCostComments() {
		return !getSwitchingCostComments().isEmpty();
    }

    public void addSwitchingCostComments(String newSwitchingCostComments) {
	    getDelegate().addPropertyValue(getOwlIndividual(), Vocabulary.DATA_PROPERTY_SWITCHINGCOSTCOMMENTS, newSwitchingCostComments);
    }

    public void removeSwitchingCostComments(String oldSwitchingCostComments) {
		getDelegate().removePropertyValue(getOwlIndividual(), Vocabulary.DATA_PROPERTY_SWITCHINGCOSTCOMMENTS, oldSwitchingCostComments);
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
