package fr.istic.nplouzeau.cartaylor.engine;

import fr.istic.nplouzeau.cartaylor.api.*;
import fr.istic.nplouzeau.cartaylor.engine.car.Exterior;

import java.io.PrintStream;
import java.util.*;

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
        Objects.requireNonNull(ps);
        if (configuration.isValid() && configuration.isComplete()) {
            StringBuilder ret = new StringBuilder();
            ret.append("<table border=\"2\"> <thead> <tr>");
            ret.append("<th> Category </th> <th> Part </th> <th> Variant </th> <th> Price </th> ");
            ret.append("</tr> </thead> <tbody> ");
            List<Category> oderedCategoryList = getOrderedCategoryList();

            for (Category cat : oderedCategoryList) {
                Optional<Part> tmp = configuration.getSelectionForCategory(cat);
                ret.append("<tr> <td> ");
                ret.append(cat.getName());
                ret.append(" </td> <td>");
                tmp.ifPresent(part -> ret.append(part.getType().getName()));
                ret.append(" </td> <td>");
                tmp.ifPresent(part -> {
                    if (part instanceof Exterior) {
                        ret.append(((Exterior) part).getColor());
                    }
                });

                ret.append(" </td> <td>");
                tmp.ifPresent(part -> ret.append(part.getType().getPrice()));
                ret.append(" </td>");
                ret.append("\n");
            }
            ret.append(" </tr> </tbody> </table>");
            ps.print(ret.toString());
        }

    }


    private List<Category> getOrderedCategoryList() {
        List<Category> categoryList = new ArrayList<Category>(mapCategoryPartType.keySet());
        categoryList.sort(Comparator.comparing(Category::getName));

        return categoryList;
    }
}
