package fr.istic.nplouzeau.cartaylor.test;

import fr.istic.nplouzeau.cartaylor.api.PartType;
import fr.istic.nplouzeau.cartaylor.engine.CompatibilityManagerImpl;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CompatibilityCheckerTest extends CarTaylorTest {




    @BeforeEach
    void init() {
        Map<PartType, Set<PartType>> requirements = new HashMap<>();
        requirementsTC120 = Set.of(eh120);
        requirements.put(tc120, requirementsTC120);

        Map<PartType, Set<PartType>> incompatibilities = new HashMap<>();
        incompatibilitiesTSF7 = Set.of(eg100);
        incompatibilities.put(tsf7, incompatibilitiesTSF7);

        compatibilityManager = new CompatibilityManagerImpl(configurator, requirements, incompatibilities);
    }

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
