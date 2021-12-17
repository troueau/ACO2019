package fr.istic.nplouzeau.cartaylor.test;

import fr.istic.nplouzeau.cartaylor.api.Category;
import static org.junit.jupiter.api.Assertions.*;

import fr.istic.nplouzeau.cartaylor.engine.CategoryImpl;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
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

    @Test
    void testPrintDescriptionWhenComplete() throws UnsupportedEncodingException {
        String expected = "<table border=\"2\"> <thead> <tr><th> Category </th> <th> Part </th> <th> Variant </th> <th> Price </th> </tr> </thead> <tbody> <tr> <td> Transmission </td> <td>TM5 </td> <td> </td> <td>650.0 </td>\n" +
                "<tr> <td> Interior </td> <td>IH </td> <td> </td> <td>260.0 </td>\n" +
                "<tr> <td> Engine </td> <td>EG100 </td> <td> </td> <td>500.0 </td>\n" +
                "<tr> <td> Exterior </td> <td>XS </td> <td>WHITE </td> <td>900.0 </td>\n" +
                " </tr> </tbody> </table>";
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        configuration.selectPart(xs);
        configuration.selectPart(ih);
        configuration.selectPart(eg100);

        configurator.printDescription(new PrintStream(os));

        assertEquals(expected, os.toString(StandardCharsets.UTF_8));
    }

    @Test
    void testPrintDescriptionWhenNotComplete() throws UnsupportedEncodingException {
        String expected = "";
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        configuration.selectPart(xs);
        configuration.selectPart(eg100);

        configurator.printDescription(new PrintStream(os));

        assertEquals(expected, os.toString(StandardCharsets.UTF_8));
    }


    @Test
    void testPrintDescriptionWhenNotValid() throws UnsupportedEncodingException {
        String expected = "";
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        configuration.selectPart(tsf7);
        configuration.selectPart(eg100);
        configuration.selectPart(xs);
        configuration.selectPart(ih);

        configurator.printDescription(new PrintStream(os));

        assertEquals(expected, os.toString(StandardCharsets.UTF_8));
    }

}
