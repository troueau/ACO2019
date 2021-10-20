package fr.istic.nplouzeau.cartaylor.engine;

import fr.istic.nplouzeau.cartaylor.api.*;

import java.util.Map;
import java.util.Set;

public class ConfigurationImpl implements Configuration {

    private Configurator configurator;
    //Contains user's choice
    private Map<Category, PartType> mapCategoryPartType;
    private CompatibilityManager compatibilityManager;

    public ConfigurationImpl(Configurator configurator, Map<Category, PartType> mapCategoryPartType, CompatibilityManager compatibilityManager) {
        this.configurator = configurator;
        this.mapCategoryPartType = mapCategoryPartType;
        this.compatibilityManager = compatibilityManager;
    }


    @Override
    public boolean isValid() {
        return false;
    }

    @Override
    public boolean isComplete() {
        return false;
    }

    @Override
    public Set<PartType> getSelectedParts() {
        return null;
    }

    @Override
    public void selectPart(PartType chosenPart) {

    }

    @Override
    public PartType getSelectionForCategory(Category category) {
        return null;
    }

    @Override
    public void unselectPartType(Category categoryToClear) {

    }

    @Override
    public void clear() {

    }
}
