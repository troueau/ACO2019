package fr.istic.nplouzeau.cartaylor.engine;

import fr.istic.nplouzeau.cartaylor.api.Category;
import fr.istic.nplouzeau.cartaylor.api.Part;
import fr.istic.nplouzeau.cartaylor.api.PartType;

import java.util.*;

/**
 * Implementation of the interface Part
 * @author Arnaud DELOURMEL, Tom ROUSSEAU
 */
public class PartImpl extends PropertyManagerImpl implements Part {

    private PartType type;

    public PartImpl(PartType type) {
        this.type = Objects.requireNonNull(type);
    }

    @Override
    public Category getCategory() {
        return type.getCategory();
    }

    @Override
    public PartType getType() {
        return type;
    }

}