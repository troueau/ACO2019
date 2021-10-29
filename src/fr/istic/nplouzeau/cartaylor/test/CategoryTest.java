package fr.istic.nplouzeau.cartaylor.test;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CategoryTest extends CarTaylorTest {

    /*
     * test du getter getName de la class CategoryImpl
     */
    @Test
    void testGetNameCategory() {
        assertEquals("Exterior", exteriorCategory.getName());
    }

}
