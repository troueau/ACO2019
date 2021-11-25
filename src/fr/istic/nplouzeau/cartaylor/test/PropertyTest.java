package fr.istic.nplouzeau.cartaylor.test;

import fr.istic.nplouzeau.cartaylor.engine.PartImpl;
import fr.istic.nplouzeau.cartaylor.engine.PartTypeImpl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;
import java.util.Set;

public class PropertyTest extends CarTaylorTest {

    @DisplayName("Test for property, color should be WHITE")
    @Test
    void testGetProperty() {
        assertEquals(Optional.of("WHITE"), ((PartTypeImpl)xm).newInstance().getProperty("paintColor"));
    }

    @DisplayName("Test set a property values")
    @Test
    void testSetProperty() {
        PartImpl xmPart = ((PartTypeImpl)xm).newInstance();
        xmPart.setProperty("paintColor", "RED");
        assertEquals(Optional.of("RED"), xmPart.getProperty("paintColor"));
        xmPart.setProperty("paintColor", "BLUE");
        assertEquals(Optional.of("BLUE"), xmPart.getProperty("paintColor"));
    }

    @DisplayName("Test set a property values for a property that does not exist")
    @Test
    void testSetPropertyThatDoesNotExit() {
        assertThrows(IllegalArgumentException.class, () -> ((PartTypeImpl)xm).newInstance().setProperty("fakePropertyName", "RED"));
    }

    @DisplayName("Test getAvailablePropertyValues")
    @Test
    void testGetAvailablePropertyValues() {
        Set<String> expectedAvailablePropertyValues = Set.of("BLUE", "RED");
        assertEquals(expectedAvailablePropertyValues, ((PartTypeImpl)xm).newInstance().getAvailablePropertyValues("paintColor"));
    }
}
