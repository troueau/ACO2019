package fr.istic.nplouzeau.cartaylor.api;

import java.util.Set;

/**
 * a public type to get information from a chosen category
 */
public interface Configurator {

    /**
     * get the categories for the current configuration
     * @return a set of category
     */
    Set<Category> getCategories();

    /**
     * get all the parts for a chosen category
     * @param category PartType's category
     * @return a set of PartType
     */
    Set<PartType> getVariants(Category category);

    /**
     * get the current configuration
     * @return a configuration
     */
    Configuration getConfiguration();

    /**
     * get the compatibility for teh current configuration
     * @return CompatibilityChecker
     */
    CompatibilityChecker getCompatibilityChecker();

}
