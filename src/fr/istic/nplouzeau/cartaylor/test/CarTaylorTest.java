package fr.istic.nplouzeau.cartaylor.test;

import fr.istic.nplouzeau.cartaylor.api.*;
import fr.istic.nplouzeau.cartaylor.engine.*;
import fr.istic.nplouzeau.cartaylor.engine.car.Engine;
import fr.istic.nplouzeau.cartaylor.engine.car.Exterior;
import fr.istic.nplouzeau.cartaylor.engine.car.Interior;
import fr.istic.nplouzeau.cartaylor.engine.car.Transmission;
import org.junit.jupiter.api.BeforeEach;

import java.util.*;

public class CarTaylorTest {

    Configurator configurator;
    Configuration configuration;
    CompatibilityChecker compatibilityChecker;
    CompatibilityManager compatibilityManager;

    Map<Category, Set<PartType>> mapCategoryPartTypeConfigurator;
    Map<Category, Part> mapCategoryPartTypeConfiguration;

    Category engineCategory, transmissionCategory, exteriorCategory, interiorCategory;
    PartType eg100, eg133, ed110, eh120, ed180, tm5, ta5, tsf7, tc120, xc, xs, in, is, xm, ih;

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

        eg100 = new PartTypeImpl("EG100", Engine.class, engineCategory, 500.0);
        eh120 = new PartTypeImpl("EH120", Engine.class, engineCategory, 850.0);
        ed180 = new PartTypeImpl("ED180", Engine.class, engineCategory, 900.0);
        eg133 = new PartTypeImpl("ED133", Engine.class, engineCategory, 550.0);
        ed110 = new PartTypeImpl("ED110", Engine.class, engineCategory, 600.0);

        tm5 = new PartTypeImpl("TM5", Transmission.class,transmissionCategory, 650.0);
        ta5 = new PartTypeImpl("TA5", Transmission.class, transmissionCategory, 660.0);
        tsf7 = new PartTypeImpl("TSF7", Transmission.class, transmissionCategory, 800.0);
        tc120 = new PartTypeImpl("TC120", Transmission.class, transmissionCategory, 900.0);

        xc = new PartTypeImpl("XC", Exterior.class, exteriorCategory, 587.0);
        xs = new PartTypeImpl("XS", Exterior.class, exteriorCategory, 900.0);
        xm = new PartTypeImpl("XM", Exterior.class, exteriorCategory, 800.0);

        in = new PartTypeImpl("IN", Interior.class, interiorCategory, 100.0);
        is = new PartTypeImpl("IS", Interior.class, interiorCategory, 230.0);
        ih = new PartTypeImpl("IH", Interior.class, interiorCategory, 260.0);



        Set<PartType> enginePartTypeSet = Set.of(eg100, eh120, ed180, eg133, ed110);

        Set<PartType> transmissionPartTypeSet = Set.of(tm5, ta5, tsf7, tc120);

        Set<PartType> exteriorPartTypeSet = Set.of(xc, xs, xm);

        Set<PartType> interiorPartTypeSet = Set.of(in, is, ih);

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
        incompatibilitiesTSF7.add(eg133);
        incompatibilitiesTSF7.add(ed110);
        incompatibilities.put(tsf7, incompatibilitiesTSF7);

        compatibilityManager = new CompatibilityManagerImpl(requirements, incompatibilities);

        mapCategoryPartTypeConfiguration = new HashMap<>();

        mapCategoryPartTypeConfiguration.put(engineCategory, null);
        mapCategoryPartTypeConfiguration.put(transmissionCategory, null);
        mapCategoryPartTypeConfiguration.put(exteriorCategory, null);
        mapCategoryPartTypeConfiguration.put(interiorCategory, null);

        configuration = new ConfigurationImpl(mapCategoryPartTypeConfiguration, compatibilityManager);

        configuration.selectPart(tm5);

        configurator = new ConfiguratorImpl(compatibilityManager, mapCategoryPartTypeConfigurator, configuration);

    }
}
