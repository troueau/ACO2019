package fr.istic.nplouzeau.cartaylor.engine;

import fr.istic.nplouzeau.cartaylor.api.Category;
import fr.istic.nplouzeau.cartaylor.api.PartType;

public class PartTypeImpl implements PartType {
    private String name;
    private Class<? extends PartImpl> classRef;
    private Category category;

    public PartTypeImpl(String name, Class<? extends PartImpl> classRef, Category category) {
        this.name = name;
        this.classRef = classRef;
        this.category = category;
    }

    public PartImpl newInstance() {
        Constructor<? extends PartImpl> constructor;
        try {
            constructor = classRef.getConstructor();
            return constructor.newInstance();
        } catch (Exception e) {
            Logger.getGlobal().log(Level.SEVERE, "constructor call failed", e);
            System.exit(-1);
        }
        return null;
    }
}