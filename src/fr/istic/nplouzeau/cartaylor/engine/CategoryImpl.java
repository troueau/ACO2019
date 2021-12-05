package fr.istic.nplouzeau.cartaylor.engine;

import fr.istic.nplouzeau.cartaylor.api.Category;

/**
 * Implementation of the interface Category
 * @author Arnaud DELOURMEL, Tom ROUSSEAU
 */
public class CategoryImpl implements Category {

    private String name;

    public CategoryImpl(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
