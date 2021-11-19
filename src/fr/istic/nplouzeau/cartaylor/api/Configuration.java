package fr.istic.nplouzeau.cartaylor.api;


import java.util.Optional;
import java.util.Set;

/**
 * A public class to get informations about the configuration
 */
public interface Configuration {

    // TODO : ajouter cette nouvelle implementation

    /**
     * method to get the parts currently composing the configuration
     * @return set of type Part
     */
	public Set<Part> getSelectedParts();

    /**
     * get the part selected for a chosen category
     * @param category chosen category
     * @return part in type Part
     */
	public Optional<Part> getSelectionForCategory(Category category);

    /**
     * tell if the current configuration is correct and valid
     * @return boolean telling if the configuration is valid or not
     */
    boolean isValid();

    /**
     * tell if the current configuration is complete
     * @return boolean telling if the configuration is complete
     */
    boolean isComplete();

    /**
     * select a part of the current configuration
     * @param chosenPart part that we want to select
     */
    void selectPart(PartType chosenPart);

    /**
     * clear a category from his partType
     * @param categoryToClear the category we want clear
     */
    void unselectPartType(Category categoryToClear);

    /**
     * method to clear all the current configuration
     */
    void clear();

}
