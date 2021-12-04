package fr.istic.nplouzeau.cartaylor.test;

import fr.istic.nplouzeau.cartaylor.api.Part;
import fr.istic.nplouzeau.cartaylor.engine.PartTypeImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class PartTest extends CarTaylorTest {

    @DisplayName("Test getter getCategory")
    @Test
    void testGetCategory() {
        Part tm5Part = ((PartTypeImpl) tm5).newInstance();

        assertEquals(transmissionCategory, tm5Part.getCategory());
    }

    @DisplayName("Test getter getType")
    @Test
    void testGetType() {
        Part tm5Part = ((PartTypeImpl) tm5).newInstance();

        assertEquals(tm5, tm5Part.getType());
    }
}