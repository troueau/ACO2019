package fr.istic.nplouzeau.cartaylor.engine;

import fr.istic.nplouzeau.cartaylor.api.Category;
import fr.istic.nplouzeau.cartaylor.api.PartType;

public class PartTypeImpl implements PartType {

    String name;
    Category category;

    public PartTypeImpl(String name, Category category) {
        this.name = name;
        this.category = category;
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
