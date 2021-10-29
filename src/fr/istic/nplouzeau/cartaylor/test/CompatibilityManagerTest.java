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


    @Test
    void testConstructor() {
        assertDoesNotThrow(() -> new CompatibilityManagerImpl(configurator, null, null));
    }
    @Test
    void testConstructorWithException() {
        assertThrows(NullPointerException.class,() -> new CompatibilityManagerImpl(null, emptyHashMap, emptyHashMap));
    }

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
    @Test
    void testAddIncompatibilitiesException() {
        assertThrows(AlreadyManageException.class, () -> compatibilityManager.addIncompatibilities(tc120, requirementsTC120));
    }


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
    @Test
    void testAddRequirementsException() {
        assertThrows(AlreadyManageException.class, () -> compatibilityManager.addRequirements(tsf7, incompatibilitiesTSF7));
    }

    @Test
    void testRemoveIncompatibilityWhenRefNotExist() {
        assertDoesNotThrow(() -> compatibilityManager.removeIncompatibility(null, eg100));
    }
    @Test
    void testRemoveIncompatibilityWhenTargetNotExist() {
        assertDoesNotThrow(() -> compatibilityManager.removeIncompatibility(tsf7, null));
    }

    @Test
    void testRemoveIncompatibilityWhenTargetIsNotInIncompatibilities() {
        assertDoesNotThrow(() -> compatibilityManager.removeIncompatibility(tsf7, is));
        assertEquals(Set.of(eg100), compatibilityManager.getIncompatibilities(tsf7));
    }
    @Test
    void testRemoveIncompatibilityWhenMappingValueIsNull() {
        assertDoesNotThrow(() -> compatibilityManager.removeIncompatibility(xs, is));
        assertEquals(Collections.emptySet(), compatibilityManager.getIncompatibilities(xs));
    }
    @Test
    void testRemoveIncompatibility() {
        assertDoesNotThrow(() -> compatibilityManager.removeIncompatibility(tsf7, eg100));
        assertEquals(Collections.emptySet(), compatibilityManager.getIncompatibilities(tsf7));
    }

    @Test
    void testRemoveRequirementWhenRefNotExist() {
        assertDoesNotThrow(() -> compatibilityManager.removeRequirement(null, eg100));
    }
    @Test
    void testRequirementsWhenTargetNotExist() {
        assertDoesNotThrow(() -> compatibilityManager.removeRequirement(eh120, null));
    }

    @Test
    void testRemoveRequirementWhenTargetIsNotInRequirements() {
        assertDoesNotThrow(() -> compatibilityManager.removeRequirement(tc120, is));
        assertEquals(Set.of(eh120), compatibilityManager.getRequirements(tc120));
    }
    @Test
    void testRemoveRequirementyWhenMappingValueIsNull() {
        assertDoesNotThrow(() -> compatibilityManager.removeRequirement(xs, is));
        assertEquals(Collections.emptySet(), compatibilityManager.getRequirements(xs));
    }

    @Test
    void testRemoveRequirement() {
        assertDoesNotThrow(() -> compatibilityManager.removeRequirement(tc120, eh120));
        assertEquals(Collections.emptySet(), compatibilityManager.getRequirements(tc120));
    }
//
//    @Test
//    void testAddIncompatibiliitesException() {
//
//    }





    /*
     * Test pour la méthode addRequirements
     * Test addIncopatibilities classique
     * Test avec avec un élément qui est déjà dans la liste des incompatibilitées
     * Test avec avec un élément qui est dans la liste des requirements
     *
     * Test pour la méthode removeIncompatibility
     * Test classique
     * Test avec un élément pas présent dans la liste
     *
     *
     * Test pour la méthode addRequirements
     * Test addIncopatibilities classique
     * Test avec avec un élément qui est déjà dans la liste des requirements
     * Test avec avec un élément qui est dans la liste des imcompatibilitées
     *
     * Test pour la méthode removeRequirement
     * Test classique
     * Test avec un élément pas présent dans la liste
     */
}
