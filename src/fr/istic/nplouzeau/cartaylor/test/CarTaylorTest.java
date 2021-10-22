package fr.istic.nplouzeau.cartaylor.test;

import fr.istic.nplouzeau.cartaylor.api.*;
import fr.istic.nplouzeau.cartaylor.engine.*;
import org.junit.jupiter.api.BeforeEach;

import java.util.*;

public class CarTaylorTest {

    Configurator configurator;
    Configuration configuration;
    CompatibilityManager compatibilityManager;

    Map<Category, PartType> mapCategoryPartTypeConfiguration;
    Map<Category, Set<PartType>> mapCategoryPartTypeConfigurator;

    Category engineCategory;
    PartType eg100;


    /**
     * Create the configuration with all the requirements incompatibilities, partType...
     */
    @BeforeEach
    void setup() {
        engineCategory = new CategoryImpl("Engine");
        Category transmissionCategory = new CategoryImpl("Transmission");
        Category exteriorCategory = new CategoryImpl("Exterior");
        Category interiorCategory = new CategoryImpl("Interior");

        eg100 = new PartTypeImpl("EG100", engineCategory);
        PartType eh120 = new PartTypeImpl("EH120", engineCategory);

        PartType tm5 = new PartTypeImpl("TM5", transmissionCategory);
        PartType ta5 = new PartTypeImpl("TA5", transmissionCategory);
        PartType tsf7 = new PartTypeImpl("TSF7", transmissionCategory);
        PartType tc120 = new PartTypeImpl("TC120", transmissionCategory);

        PartType xc = new PartTypeImpl("XC", exteriorCategory);
        PartType xs = new PartTypeImpl("XS", exteriorCategory);

        PartType in = new PartTypeImpl("IN", interiorCategory);
        PartType is = new PartTypeImpl("IS", interiorCategory);

        Set<PartType> enginePartTypeSet = new HashSet<>();
        enginePartTypeSet.add(eg100);
        enginePartTypeSet.add(eh120);

        Set<PartType> transmissionPartTypeSet = new HashSet<>();
        transmissionPartTypeSet.add(tm5);
        transmissionPartTypeSet.add(ta5);
        transmissionPartTypeSet.add(tsf7);
        transmissionPartTypeSet.add(tc120);

        Set<PartType> exteriorPartTypeSet = new HashSet<>();
        exteriorPartTypeSet.add(xc);
        exteriorPartTypeSet.add(xs);

        Set<PartType> interiorPartTypeSet = new HashSet<>();
        interiorPartTypeSet.add(in);
        interiorPartTypeSet.add(is);

        mapCategoryPartTypeConfigurator = new HashMap<>();

        mapCategoryPartTypeConfigurator.put(engineCategory, enginePartTypeSet);
        mapCategoryPartTypeConfigurator.put(transmissionCategory, transmissionPartTypeSet);
        mapCategoryPartTypeConfigurator.put(exteriorCategory, exteriorPartTypeSet);
        mapCategoryPartTypeConfigurator.put(interiorCategory, interiorPartTypeSet);

        configurator = new ConfiguratorImpl(compatibilityManager, mapCategoryPartTypeConfigurator, configuration);

        configuration = new ConfigurationImpl(configurator, compatibilityManager);
    }
}
