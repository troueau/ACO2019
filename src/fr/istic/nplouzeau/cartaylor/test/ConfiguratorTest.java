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

    /*
     * test du Getter des variants où la categorie existe dans la map
     */
    @Test
    void testGetVariantsExistingCategory() {
        assertEquals(mapCategoryPartTypeConfigurator.get(engineCategory), configurator.getVariants(engineCategory));
    }

    /*
     * test du Getter des variants où la categorie n'existe pas dans la map -> le getter retourne donc un emptySet
     */
    @Test
    void testGetVariantsCategoryDoesNotExist() {
        Category mockCategory = new CategoryImpl("MockCategory");
        assertEquals(Collections.emptySet(), configurator.getVariants(mockCategory));
    }

    /*
     * test du getter renvoyant la configuration contenue dans la class configurator
     */
    @Test
    void testGetConfiguration() {
        assertEquals(configuration, configurator.getConfiguration());
    }

    /*
     * test du getter renvoyant le compatibilityManager contenu dans la class configurator
     */
    @Test
    void testGetCompatibilityManager() {
        assertEquals(compatibilityManager, configurator.getCompatibilityChecker());
    }

}
