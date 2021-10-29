package fr.istic.nplouzeau.cartaylor.test;

import fr.istic.nplouzeau.cartaylor.api.*;
import fr.istic.nplouzeau.cartaylor.engine.*;
import org.junit.jupiter.api.BeforeEach;

import java.util.*;

public class CarTaylorTest {

    Configurator configurator;
    Configuration configuration;
    CompatibilityChecker compatibilityChecker;
    CompatibilityManager compatibilityManager;

    Map<Category, Set<PartType>> mapCategoryPartTypeConfigurator;
    Map<Category, PartType> mapCategoryPartTypeConfiguration;

    Category engineCategory, transmissionCategory, exteriorCategory, interiorCategory;
    PartType eg100, eh120, tm5, ta5, tsf7, tc120, xc, xs, in, is;

    Set<PartType> requirementsTC120, incompatibilitiesTSF7;

    /**
     * Create the configuration with all the requirements incompatibilities, partType...
     */
    @BeforeEach
    void setup() {
        engineCategory = new CategoryImpl("Engine");
        transmissionCategory = new CategoryImpl("Transmission");
        exteriorCategory = new CategoryImpl("Exterior");
        interiorCategory = new CategoryImpl("Interior");

        eg100 = new PartTypeImpl("EG100", engineCategory);
        eh120 = new PartTypeImpl("EH120", engineCategory);

        tm5 = new PartTypeImpl("TM5", transmissionCategory);
        ta5 = new PartTypeImpl("TA5", transmissionCategory);
        tsf7 = new PartTypeImpl("TSF7", transmissionCategory);
        tc120 = new PartTypeImpl("TC120", transmissionCategory);

        xc = new PartTypeImpl("XC", exteriorCategory);
        xs = new PartTypeImpl("XS", exteriorCategory);

        in = new PartTypeImpl("IN", interiorCategory);
        is = new PartTypeImpl("IS", interiorCategory);

        Set<PartType> enginePartTypeSet = Set.of(eg100, eh120);

        Set<PartType> transmissionPartTypeSet = Set.of(tm5, ta5, tsf7, tc120);

        Set<PartType> exteriorPartTypeSet = Set.of(xc, xs);

        Set<PartType> interiorPartTypeSet = Set.of(in, is);

        mapCategoryPartTypeConfigurator = new HashMap<>();

        mapCategoryPartTypeConfigurator.put(engineCategory, enginePartTypeSet);
        mapCategoryPartTypeConfigurator.put(transmissionCategory, transmissionPartTypeSet);
        mapCategoryPartTypeConfigurator.put(exteriorCategory, exteriorPartTypeSet);
        mapCategoryPartTypeConfigurator.put(interiorCategory, interiorPartTypeSet);


        Map<PartType, Set<PartType>> requirements = new HashMap<>();
        requirementsTC120 = new HashSet<>();
        requirementsTC120.add(eh120);
        requirements.put(tc120, requirementsTC120);

        Map<PartType, Set<PartType>> incompatibilities = new HashMap<>();
        incompatibilitiesTSF7 = new HashSet<>();
        incompatibilitiesTSF7.add(eg100);
        incompatibilities.put(tsf7, incompatibilitiesTSF7);

        compatibilityManager = new CompatibilityManagerImpl(requirements, incompatibilities);

        mapCategoryPartTypeConfiguration = new HashMap<>();

        mapCategoryPartTypeConfiguration.put(engineCategory, null);
        mapCategoryPartTypeConfiguration.put(transmissionCategory, null);
        mapCategoryPartTypeConfiguration.put(exteriorCategory, null);
        mapCategoryPartTypeConfiguration.put(interiorCategory, null);

        configuration = new ConfigurationImpl(mapCategoryPartTypeConfiguration, compatibilityManager);
        configuration.selectPart(tm5);

        configurator = new ConfiguratorImpl(compatibilityChecker, mapCategoryPartTypeConfigurator, configuration);

    }
}
