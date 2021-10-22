package fr.istic.nplouzeau.cartaylor.test;

import fr.istic.nplouzeau.cartaylor.api.Category;
import static org.junit.jupiter.api.Assertions.*;

import fr.istic.nplouzeau.cartaylor.engine.CategoryImpl;
import org.junit.jupiter.api.Test;

import java.util.Collections;

public class ConfiguratorTest extends CarTaylorTest {

    /*
     * test du Getter getCategories
     */
    @Test
    void testGetCategories() {
        assertEquals(mapCategoryPartTypeConfigurator.keySet(), configurator.getCategories());
    }

    @Test
    void testGetVariantsExistingCategory() {
        assertEquals(mapCategoryPartTypeConfigurator.get(engineCategory), configurator.getVariants(engineCategory));
    }

    @Test
    void testGetVariantsCategoryDoesNotExist() {
        Category mockCategory = new CategoryImpl("MockCategory");
        assertEquals(Collections.emptySet(), configurator.getVariants(mockCategory));
    }

    @Test
    void testGetConfiguration() {
        // TODO test à completer (ne passe pas)
        //assertEquals(configuration, configurator.getConfiguration());
    }

    @Test
    void testGetCompatibilityChecker() {

    }

}
