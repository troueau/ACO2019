package fr.istic.nplouzeau.cartaylor.test;

import fr.istic.nplouzeau.cartaylor.api.PartType;
import fr.istic.nplouzeau.cartaylor.engine.CompatibilityManagerImpl;
import fr.istic.nplouzeau.cartaylor.exception.AlreadyManageException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;


public class CompatibilityManagerTest extends CarTaylorTest {

    Map<PartType, Set<PartType>> emptyHashMap;

    @BeforeEach
    void init() {
        emptyHashMap = new HashMap<>();

    }

    /*
     * test du constructeur de CompatibilityManager
     */
    @Test
    void testConstructor() {
        assertDoesNotThrow(() -> new CompatibilityManagerImpl(null, null));
    }

    /*
     * test d'ajout d'incompatibilities, aucune existantes precedemment
     */
    @Test
    void testAddIncompatibilitiesWhenNoPreviousIncompatibilities() {
        Set<PartType> incompatibilitiesEG100 = Set.of(tm5, ta5);

        try {
            compatibilityManager.addIncompatibilities(eg100, incompatibilitiesEG100);
        } catch (AlreadyManageException e) {
            e.printStackTrace();
        }

        assertEquals(incompatibilitiesEG100, compatibilityManager.getIncompatibilities(eg100));
    }

    /*
     * test d'ajout d'incompatibilities, d'autres présentes precedemment
     */
    @Test
    void testAddIncompatibilitiesWhenThereIsPreviousIncompatibilities() {
        Set<PartType> incompatibilities2Tsf7 = Set.of(xc, in);
        Set<PartType> expected = new HashSet<>();
        expected.add(xc);
        expected.add(in);
        expected.addAll(incompatibilitiesTSF7);

        try {
            compatibilityManager.addIncompatibilities(tsf7, incompatibilities2Tsf7);
        } catch (AlreadyManageException e) {
            e.printStackTrace();
        }

        assertEquals(expected, compatibilityManager.getIncompatibilities(tsf7));
    }

    /*
     * test d'ajout d'incompatibilities, une exception est levée
     */
    @Test
    void testAddIncompatibilitiesException() {
        assertThrows(AlreadyManageException.class, () -> compatibilityManager.addIncompatibilities(tc120, requirementsTC120));
    }

    /*
     * test d'ajout de requirements, aucune presentes precedemment
     */
    @Test
    void testAddRequirementsWhenNoPreviousRequirements() {
        Set<PartType> requirementsEG100 = Set.of(tm5, ta5);

        try {
            compatibilityManager.addRequirements(eg100, requirementsEG100);
        } catch (AlreadyManageException e) {
            e.printStackTrace();
        }

        assertEquals(requirementsEG100, compatibilityManager.getRequirements(eg100));
    }

    /*
     * test d'ajout de requirements, d'autres presentes precedemment
     */
    @Test
    void testAddRequirementsWhenThereIsPreviousRequirements() {
        Set<PartType> requirements2Tc120 = Set.of(xc, in);
        Set<PartType> expected = new HashSet<>();
        expected.add(xc);
        expected.add(in);
        expected.addAll(requirementsTC120);

        try {
            compatibilityManager.addRequirements(tc120, requirements2Tc120);
        } catch (AlreadyManageException e) {
            e.printStackTrace();
        }

        assertEquals(expected, compatibilityManager.getRequirements(tc120));
    }

    /*
     * test d'ajout de requirements, une exception est levée
     */
    @Test
    void testAddRequirementsException() {
        assertThrows(AlreadyManageException.class, () -> compatibilityManager.addRequirements(tsf7, incompatibilitiesTSF7));
    }

    /*
     * test de l'ajout de requirements symmétrique, c-a-d si on ajoute des requirements pour une PartType, ajoute aussi aux PartType required la PartType source
     */
    @Test
    void testAddRequirementsSymetric() {
        Set<PartType> partTypeToAdd = new HashSet<>();
        partTypeToAdd.add(xm);
        try {
            compatibilityManager.addRequirements(ih, partTypeToAdd);
        } catch (AlreadyManageException e) {
            e.printStackTrace();
        }

        Set<PartType> expectedSetOfPartTypeRequiredForXM = Set.of(ih);
        Set<PartType> expectedSetOfPartTypeRequiredForIH = Set.of(xm);

        assertEquals(expectedSetOfPartTypeRequiredForIH, compatibilityManager.getRequirements(ih));
        assertEquals(expectedSetOfPartTypeRequiredForXM, compatibilityManager.getRequirements(xm));
    }

    /*
     * test remove incompatibilites, une exception est levée
     */
    @Test
    void testRemoveIncompatibilityWhenRefNotExist() {
        assertDoesNotThrow(() -> compatibilityManager.removeIncompatibility(null, eg100));
    }

    /*
     * test remove incompatibilites, une exception est levée
     */
    @Test
    void testRemoveIncompatibilityWhenTargetNotExist() {
        assertDoesNotThrow(() -> compatibilityManager.removeIncompatibility(tsf7, null));
    }

    /*
     * test remove incompatibilites, on verifie qu'aucune exception est levée
     */
    @Test
    void testRemoveIncompatibilityWhenTargetIsNotInIncompatibilities() {
        assertDoesNotThrow(() -> compatibilityManager.removeIncompatibility(tsf7, is));
        assertEquals(Set.of(eg100, eg133, ed110), compatibilityManager.getIncompatibilities(tsf7));
    }

    /*
     * test remove incompatibilites, on verifie qu'aucune exception est levée
     */
    @Test
    void testRemoveIncompatibilityWhenMappingValueIsNull() {
        assertDoesNotThrow(() -> compatibilityManager.removeIncompatibility(xs, is));
        assertEquals(Collections.emptySet(), compatibilityManager.getIncompatibilities(xs));
    }

    /*
     * test remove incompatibilites, on verifie qu'aucune exception est levée
     */
    @Test
    void testRemoveIncompatibility() {
        Set<PartType> expectedIncompatibilitiesList = Set.of(eg133, ed110);
        assertDoesNotThrow(() -> compatibilityManager.removeIncompatibility(tsf7, eg100));
        assertEquals(expectedIncompatibilitiesList, compatibilityManager.getIncompatibilities(tsf7));
    }

    /*
     * test remove requirements, on verifie qu'aucune exception est levée
     */
    @Test
    void testRemoveRequirementWhenRefNotExist() {
        assertDoesNotThrow(() -> compatibilityManager.removeRequirement(null, eg100));
    }

    /*
     * test remove requirements, on verifie qu'aucune exception est levée
     */
    @Test
    void testRequirementsWhenTargetNotExist() {
        assertDoesNotThrow(() -> compatibilityManager.removeRequirement(eh120, null));
    }

    /*
     * test remove requirements, on verifie qu'aucune exception est levée
     */
    @Test
    void testRemoveRequirementWhenTargetIsNotInRequirements() {
        assertDoesNotThrow(() -> compatibilityManager.removeRequirement(tc120, is));
        assertEquals(Set.of(eh120), compatibilityManager.getRequirements(tc120));
    }

    /*
     * test remove requirements, on verifie qu'aucune exception est levée
     */
    @Test
    void testRemoveRequirementyWhenMappingValueIsNull() {
        assertDoesNotThrow(() -> compatibilityManager.removeRequirement(xs, is));
        assertEquals(Collections.emptySet(), compatibilityManager.getRequirements(xs));
    }

    /*
     * test remove requirements, on verifie qu'aucune exception est levée
     */
    @Test
    void testRemoveRequirement() {
        assertDoesNotThrow(() -> compatibilityManager.removeRequirement(tc120, eh120));
        assertEquals(Collections.emptySet(), compatibilityManager.getRequirements(tc120));
    }

}
