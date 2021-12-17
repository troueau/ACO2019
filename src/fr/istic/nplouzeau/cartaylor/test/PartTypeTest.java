package fr.istic.nplouzeau.cartaylor.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class PartTypeTest extends CarTaylorTest {

    /*
     * test du getter getName
     */
    @Test
    public void testGetNamePartType() {
        assertEquals("EG100", eg100.getName());
    }

    /*
     * test du getter getCategory
     */
    @Test
    public void testGetNameCategory() {
        assertEquals(engineCategory, eg100.getCategory());
    }

    /*
     * Test du getter getPrice
     */
    @Test
    public void testGetPrice() {
        assertEquals(500.0, eg100.getPrice(), epsilon);
    }

}
