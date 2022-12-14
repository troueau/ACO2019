package fr.istic.nplouzeau.cartaylor.test;

import fr.istic.nplouzeau.cartaylor.api.PartType;
import fr.istic.nplouzeau.cartaylor.exception.AlreadyManageException;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
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
     *  test de isValid quand on ajoute une parttype qui est dans la liste des incompatibilités, la methode doit donc retrouner faux
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
        Set<PartType> expectedSetOfSelectedPartType = new HashSet<>();
        expectedSetOfSelectedPartType.add(null); //Dans la configuration si il y a un ou + PartType qui ne sont pas selectionnés, alors il y a une valeur null dans le set
        expectedSetOfSelectedPartType.add(tm5);
        assertEquals(expectedSetOfSelectedPartType, configuration.getSelectedParts());
    }

    /*
     * test du getter getSelectionForCategory when a part is chosen
     */
    @Test
    void testGetSelectionForCategoryWhenPartChosen() {
        assertEquals(tm5, configuration.getSelectionForCategory(transmissionCategory));
    }

    /*
     * test du getter getSelectionForCategory when a part is chosen
     */
    @Test
    void testGetSelectionForCategoryWhenNoPartHasBeenChosen() {
        assertNull(configuration.getSelectionForCategory(engineCategory));
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
        assertNull(configuration.getSelectionForCategory(engineCategory));
        configuration.selectPart(eg100);
        assertEquals(eg100, configuration.getSelectionForCategory(engineCategory));
    }

    /*
     * test de la methode selectPart quand la partType que l'on selectionne est deja dans la configuration
     * PAS TROP D'INTERET !
     */
    @Test
    void testSelectPartWhenAlreadySelected() {
        assertEquals(tm5, configuration.getSelectionForCategory(transmissionCategory));
        configuration.selectPart(tm5);
        assertEquals(tm5, configuration.getSelectionForCategory(transmissionCategory));
    }

    /*
     * test de la methode unselectPartType -> deselectionner la partType d'une categorie
     */
    @Test
    void testUnselectPart() {
        assertEquals(tm5, configuration.getSelectionForCategory(transmissionCategory));
        configuration.unselectPartType(transmissionCategory);
        assertNull(configuration.getSelectionForCategory(transmissionCategory));
    }

    /*
     * test de la methode unselectPartType quand aucun partType n'est associé à la categorie choisie
     * TEST SANS INTERET ?
     */
    @Test
    void testUnselectPartWhenAlreadyNull() {
        assertNull(configuration.getSelectionForCategory(interiorCategory));
        configuration.unselectPartType(interiorCategory);
        assertNull(configuration.getSelectionForCategory(interiorCategory));
    }

    /*
     * test de la methode clear -> 1 test clear toute une config
     */
    @Test
    void testClear() {
        //On verifie déjà que la configuration n'est pas vide avant de clear
        Set<PartType> expectedSetOfSelectedPartType = new HashSet<>();
        expectedSetOfSelectedPartType.add(null);
        expectedSetOfSelectedPartType.add(tm5);
        assertEquals(expectedSetOfSelectedPartType, configuration.getSelectedParts());

        //On clear la config et on verifie qu'elle est bien null
        configuration.clear();
        expectedSetOfSelectedPartType.remove(tm5);
        assertEquals(expectedSetOfSelectedPartType, configuration.getSelectedParts());
    }
}
