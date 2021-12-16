package fr.istic.nplouzeau.cartaylor.engine;

import fr.istic.nplouzeau.cartaylor.api.Category;

import java.util.Objects;

/**
 * Implementation of the interface Category
 * @author Arnaud DELOURMEL, Tom ROUSSEAU
 */
public class CategoryImpl implements Category {

    private String name;

    /**
     * Constructor
     * @param name The name of the category
     */
    public CategoryImpl(String name) {
        this.name = Objects.requireNonNull(name);
    }

    @Override
    public String getName() {
        return this.name;
    }
}
