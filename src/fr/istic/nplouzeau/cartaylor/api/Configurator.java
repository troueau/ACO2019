package fr.istic.nplouzeau.cartaylor.api;

import java.util.Set;

public interface Configurator {

    Set<Category> getCategories();

    Set<PartType> getVariants(Category category);

    Configuration getConfiguration();

    CompatibilityChecker getCompatibilityChecker();

}
