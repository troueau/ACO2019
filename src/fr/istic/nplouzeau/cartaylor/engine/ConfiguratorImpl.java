package fr.istic.nplouzeau.cartaylor.engine;

import fr.istic.nplouzeau.cartaylor.api.*;

import java.util.Map;
import java.util.Set;

public class ConfiguratorImpl implements Configurator {

    private Set<Category> categories;
    private CompatibilityChecker compatibilityChecker;
    // Contains all the partType for specific Category
    private Map<Category, Set<PartType>> mapCategoryPartType;

    @Override
    public Set<Category> getCategories() {
        return null;
    }

    @Override
    public Set<PartType> getVariants(Category category) {
        return null;
    }

    @Override
    public Configuration getConfiguration() {
        return null;
    }

    @Override
    public CompatibilityChecker getCompatibilityChecker() {
        return null;
    }
}
