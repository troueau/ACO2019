package fr.istic.nplouzeau.cartaylor.test;

import fr.istic.nplouzeau.cartaylor.api.Part;
import fr.istic.nplouzeau.cartaylor.api.PartType;
import fr.istic.nplouzeau.cartaylor.engine.PartTypeImpl;
import fr.istic.nplouzeau.cartaylor.exception.AlreadyManageException;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class ConfigurationTest extends CarTaylorTest {

    /*
     * test de la methode isValid si la configuration n'est pas complete
     */
    @Test
    void testIsValidWhenConfigurationIsNotComplete() {
        assertFalse(configuration.isValid());
    }

    /*
     * test de la methode isValid quand aucune incompatibilités ni obligations ne sont présentes pour les partType
     */
    @Test
    void testIsValidWhenNoIncompatibilitiesAndNoRequirements() {
        //On complete la configuration avec des PartType au hasard
        configuration.selectPart(eg100);
        configuration.selectPart(in);
        configuration.selectPart(xc);

        //On verifie que la configuration est bien complete
        assertTrue(configuration.isComplete());

        //On peut maintenant tester si la configuration est bien valide
        assertTrue(configuration.isValid());
    }

    /*
     * test de la methode isValid en ajoutant des requirements et une configuration qui ne correspond pas à la compatibilité choisie
     */
    @Test
    void testIsValidWhenRequirementsDoesNotMatchConfiguration() {
        Set<PartType> partTypeToAdd = new HashSet<>();
        partTypeToAdd.add(xm);
        try {
            compatibilityManager.addRequirements(ih, partTypeToAdd);
        } catch (AlreadyManageException e) {
            e.printStackTrace();
        }
        configuration.selectPart(eg100);
        configuration.selectPart(ih);
        configuration.selectPart(xs);


        assertFalse(configuration.isValid());
    }

    /*
     * test de la methode isValid en ajoutant des requirements et une bonne configuration
     */
    @Test
    void testIsValidWhenRequirementsMatchConfiguration() {
        Set<PartType> partTypeToAdd = new HashSet<>();
        partTypeToAdd.add(xm);
        try {
            compatibilityManager.addRequirements(ih, partTypeToAdd);
        } catch (AlreadyManageException e) {
            e.printStackTrace();
        }
        configuration.selectPart(eg100);
        configuration.selectPart(ih);
        configuration.selectPart(xm);


        assertTrue(configuration.isValid());
    }

    /*
     *  test de isValid quand on ajoute une part qui est dans la liste des incompatibilités, la methode doit donc retrouner faux
     */
    @Test
    void testIsValidWithIncompatibilitiesThatDoesNotMatchWithConfiguration() {
        configuration.selectPart(eg100);
        configuration.selectPart(ih);
        configuration.selectPart(xm);
        configuration.selectPart(tsf7);

        assertFalse(configuration.isValid());
    }

    /*
     *  test de isValid avec des incompatibilités qui marchent
     */
    @Test
    void testIsValidWithIncompatibilitiesThatMatchWithConfiguration() {
        configuration.selectPart(ed180);
        configuration.selectPart(ih);
        configuration.selectPart(xm);
        configuration.selectPart(tsf7);

        assertTrue(configuration.isValid());
    }

    /*
     * test du getter getSelectedParts
     */
    @Test
    void testGetSelectedParts() {
        Set<String> expectedSetOfSelectedPartType = new HashSet<>();
        expectedSetOfSelectedPartType.add(((PartTypeImpl) tm5).newInstance().getName());

        //just get the name (String) of the selected parts of the current configuration
        Set<String> configSelectedParts = new HashSet<>();
        for(Part p: configuration.getSelectedParts()) {
            configSelectedParts.add(p.getName());
        }

        assertEquals(expectedSetOfSelectedPartType, configSelectedParts);
    }

    /*
     * test du getter getSelectionForCategory when a part is chosen
     */
    @Test
    void testGetSelectionForCategoryWhenPartChosen() {
        Optional<Part> opt = configuration.getSelectionForCategory(transmissionCategory);
        opt.ifPresent(part -> assertEquals(((PartTypeImpl) tm5).newInstance().getName(), part.getName()));
    }

    /*
     * test du getter getSelectionForCategory when a part is chosen
     */
    @Test
    void testGetSelectionForCategoryWhenNoPartHasBeenChosen() {
        assertFalse(configuration.getSelectionForCategory(engineCategory).isPresent());
    }

    /*
     * test de la methode verifiant si une configuration est complete
     */
    @Test
    void testIsComplete() {
        assertFalse(configuration.isComplete());
    }

    /*
     * test de la methode selectPart
     */
    @Test
    void testSelectPartOnCurrentConfiguration() {
        assertEquals(Optional.empty(), configuration.getSelectionForCategory(engineCategory));
        configuration.selectPart(eg100);
        Optional<Part> opt = configuration.getSelectionForCategory(engineCategory);
        opt.ifPresent(part -> assertEquals(Optional.of(((PartTypeImpl) eg100).newInstance()).get().getName(), part.getName()));
    }

    /*
     * test de la methode selectPart quand la partType que l'on selectionne est deja dans la configuration
     * PAS TROP D'INTERET !
     */
    @Test
    void testSelectPartWhenAlreadySelected() {
        Optional<Part> optPart = configuration.getSelectionForCategory(transmissionCategory);
        assertEquals(Optional.of(((PartTypeImpl)tm5).newInstance()).get().getName(), optPart.get().getName());
        configuration.selectPart(tm5);
        assertEquals(Optional.of(((PartTypeImpl)tm5).newInstance()).get().getName(), optPart.get().getName());
    }

    /*
     * test de la methode unselectPartType -> deselectionner la partType d'une categorie
     */
    @Test
    void testUnselectPart() {
        configuration.unselectPartType(transmissionCategory);
        assertEquals(Optional.empty(), configuration.getSelectionForCategory(transmissionCategory));
    }

    /*
     * test de la methode unselectPartType quand aucun partType n'est associé à la categorie choisie
     */
    @Test
    void testUnselectPartWhenAlreadyEmpty() {
        assertEquals(Optional.empty(), configuration.getSelectionForCategory(interiorCategory));
        configuration.unselectPartType(interiorCategory);
        assertEquals(Optional.empty(), configuration.getSelectionForCategory(interiorCategory));
    }

    /*
     * test de la methode clear -> 1 test clear toute une config
     */
    @Test
    void testClear() {
        configuration.clear();
        assertEquals(Set.of(), configuration.getSelectedParts());
    }
}
