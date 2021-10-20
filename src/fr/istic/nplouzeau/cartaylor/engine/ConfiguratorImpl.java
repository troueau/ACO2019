package fr.istic.nplouzeau.cartaylor.engine;

import fr.istic.nplouzeau.cartaylor.api.*;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

public class ConfiguratorImpl implements Configurator {

    private CompatibilityChecker compatibilityChecker;
    // Contains all the partType for specific Category
    private Map<Category, Set<PartType>> mapCategoryPartType;
    private Configuration configuration;

    public ConfiguratorImpl(CompatibilityChecker compatibilityChecker, Map<Category, Set<PartType>> mapCategoryPartType, Configuration configuration) {
        this.compatibilityChecker = compatibilityChecker;
        this.mapCategoryPartType = mapCategoryPartType;
        this.configuration = configuration;
    }


    @Override
    public Set<Category> getCategories() {
        return Collections.unmodifiableSet(mapCategoryPartType.keySet());
    }

    @Override
    public Set<PartType> getVariants(Category category) {
        Set<PartType> res = mapCategoryPartType.get(category);
        res = res == null ? Collections.emptySet() : res;
        return Collections.unmodifiableSet(res);
    }

    @Override
    public Configuration getConfiguration() {
        return configuration;
    }

    @Override
    public CompatibilityChecker getCompatibilityChecker() {
        return compatibilityChecker;
    }
}
