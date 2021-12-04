package fr.istic.nplouzeau.cartaylor.engine;

import fr.istic.nplouzeau.cartaylor.api.Category;
import fr.istic.nplouzeau.cartaylor.api.PartType;

import java.lang.reflect.Constructor;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PartTypeImpl implements PartType {
    private String name;
    private Class<? extends PartImpl> classRef;
    private Category category;
    private double price;

    public PartTypeImpl(String name, Class<? extends PartImpl> classRef, Category category, double price) {
        this.name = name;
        this.classRef = classRef;
        this.category = category;
        this.price = price;
    }

    public PartImpl newInstance() {
        Constructor<? extends PartImpl> constructor;
        try {
            constructor = classRef.getDeclaredConstructor(PartType.class);
            return constructor.newInstance(this);
        } catch (Exception e) {
            Logger.getGlobal().log(Level.SEVERE, "constructor call failed", e);
            System.exit(-1);
        }
        return null;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Category getCategory() {
        return category;
    }

    @Override
    public double getPrice() { return price;  }
}