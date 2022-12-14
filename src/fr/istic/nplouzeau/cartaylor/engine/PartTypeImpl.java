package fr.istic.nplouzeau.cartaylor.engine;

import fr.istic.nplouzeau.cartaylor.api.Category;
import fr.istic.nplouzeau.cartaylor.api.PartType;

import java.util.Objects;


/**
 * Implementation of the interface PartType
 * @author Arnaud DELOURMEL, Tom ROUSSEAU
 */
public class PartTypeImpl implements PartType {

    String name;
    Category category;

    /**
     * Constructor
     * @param name The name of the PartType
     * @param category The category
     */
    public PartTypeImpl(String name, Category category) {
        this.name = Objects.requireNonNull(name);
        this.category = Objects.requireNonNull(category);
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Category getCategory() {
        return this.category;
    }
}
