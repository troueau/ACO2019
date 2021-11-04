package fr.istic.nplouzeau.cartaylor.test;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CompatibilityCheckerTest extends CarTaylorTest {

    /*
     * test du getter d'incompatibilitées en passant une reference null en parametre
     */
    @Test
    void testGetIncompatibilitiesWithNotExistReference() {
        assertEquals(0, compatibilityManager.getIncompatibilities(null).size());
    }

    /*
     * test du getter d'incompatibilitées avec comme reference une PartType n'ayant aucune incompatibilitées
     */
    @Test
    void testGetIncompatibilitiesReturnEmptyList() {
        assertEquals(0, compatibilityManager.getIncompatibilities(eg100).size());
    }

    /*
     * test du getter d'incompatibilitées avec comme reference une PartType ayant 3 incompatibilitées (eg100, eg133, ed110)
     */
    @Test
    void testGetIncompatibilities() {
        assertEquals(incompatibilitiesTSF7, compatibilityManager.getIncompatibilities(tsf7));
        assertEquals(3, compatibilityManager.getIncompatibilities(tsf7).size());
    }

    /*
     * test du getter requirements en passant une reference null en paramètre
     */
    @Test
    void testGetRequirementsWithNotExistReference() {
        assertEquals(0, compatibilityManager.getRequirements(null).size());
    }

    /*
     * test du getter requirements avec comme reference une PartType n'ayant aucun requirements
     */
    @Test
    void testGetRequirementsReturnEmptyList() {
        assertEquals(0, compatibilityManager.getRequirements(eg100).size());
    }

    /*
     * test du getter requirements avec comme reference une PartType ayant 1 requirements (eh120)
     */
    @Test
    void testGetRequirements() {
        assertEquals(requirementsTC120, compatibilityManager.getRequirements(tc120));
        assertEquals(1, compatibilityManager.getRequirements(tc120).size());
    }
}
