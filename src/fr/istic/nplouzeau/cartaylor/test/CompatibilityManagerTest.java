package fr.istic.nplouzeau.cartaylor.test;

import fr.istic.nplouzeau.cartaylor.api.PartType;
import fr.istic.nplouzeau.cartaylor.engine.CompatibilityManagerImpl;
import fr.istic.nplouzeau.cartaylor.exception.AlreadyManageException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

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
        Map<PartType, Set<PartType>> incompatibilities = new HashMap<>();
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
    void testAddIncompatibilitesException() {
        assertThrows(AlreadyManageException.class, () -> compatibilityManager.addIncompatibilities(tc120, requirementsTC120));
    }


//
//    @Test
//    void testAddIncompatibiliitesException() {
//
//    }





    /*
     * Test pour la méthode addIncompatibilities
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
