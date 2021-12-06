package fr.istic.nplouzeau.cartaylor.engine;

import fr.istic.nplouzeau.cartaylor.api.*;

import java.io.PrintStream;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * Implementation of the interface Configurator
 * @author Arnaud DELOURMEL, Tom ROUSSEAU
 */
public class ConfiguratorImpl implements Configurator {

    private CompatibilityChecker compatibilityChecker;
    // Contains all the partType for specific Category
    private Map<Category, Set<PartType>> mapCategoryPartType;
    private Configuration configuration;

    public ConfiguratorImpl(CompatibilityChecker compatibilityChecker, Map<Category, Set<PartType>> mapCategoryPartType, Configuration configuration) {
        this.compatibilityChecker = Objects.requireNonNull(compatibilityChecker);
        this.mapCategoryPartType = Objects.requireNonNull(mapCategoryPartType);
        this.configuration = Objects.requireNonNull(configuration);
    }


    @Override
    public Set<Category> getCategories() {
        return Collections.unmodifiableSet(mapCategoryPartType.keySet());
    }

    @Override
    public Set<PartType> getVariants(Category category) {
        Set<PartType> res = Objects.requireNonNullElse(mapCategoryPartType.get(category),Collections.emptySet());
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

    @Override
    public void printDescription(PrintStream ps) {
        // TODO
//        StringBuilder tmp = new StringBuilder();
//        mapCategoryPartType.forEach((key, value) -> {
//            tmp.append(key.toString());
//            tmp.append("\n");
//            tmp.append(value.toString());
//        });
    }
}
