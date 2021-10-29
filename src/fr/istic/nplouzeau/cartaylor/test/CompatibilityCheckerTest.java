package fr.istic.nplouzeau.cartaylor.test;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CompatibilityCheckerTest extends CarTaylorTest {

    @Test
    void testGetIncompatibilitiesWithNotExistReference() {
        assertEquals(0, compatibilityManager.getIncompatibilities(null).size());
    }
    @Test
    void testGetIncompatibilitiesReturnEmptyList() {
        assertEquals(0, compatibilityManager.getIncompatibilities(eg100).size());
    }
    @Test
    void testGetIncompatibilities() {
        assertEquals(incompatibilitiesTSF7, compatibilityManager.getIncompatibilities(tsf7));
        assertEquals(1, compatibilityManager.getIncompatibilities(tsf7).size());
    }

    @Test
    void testGetRequirementsWithNotExistReference() {
        assertEquals(0, compatibilityManager.getRequirements(null).size());
    }
    @Test
    void testGetRequirementsReturnEmptyList() {
        assertEquals(0, compatibilityManager.getRequirements(eg100).size());
    }
    @Test
    void testGetRequirements() {
        assertEquals(requirementsTC120, compatibilityManager.getRequirements(tc120));
        assertEquals(1, compatibilityManager.getRequirements(tc120).size());
    }
}
