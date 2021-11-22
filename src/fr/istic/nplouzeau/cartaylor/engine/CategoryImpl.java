package fr.istic.nplouzeau.cartaylor.engine;

import fr.istic.nplouzeau.cartaylor.api.Category;

import java.util.Objects;

public class CategoryImpl implements Category {

    private String name;

    public CategoryImpl(String name) {
        this.name = Objects.requireNonNull(name);
    }

    @Override
    public String getName() {
        return this.name;
    }
}
