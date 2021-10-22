package fr.istic.nplouzeau.cartaylor.engine;

import fr.istic.nplouzeau.cartaylor.api.*;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;
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
        if (!isComplete()) return false;
        // Check for all PartType if requirements and incompatibilities are respected
        for (Map.Entry<Category, PartType> entry1 : mapCategoryPartType.entrySet()) {
            Set<PartType> incompatibilities = compatibilityManager.getIncompatibilities(entry1.getValue());
            Set<PartType> requirements = compatibilityManager.getRequirements(entry1.getValue());

            for (PartType requiredPartType : requirements) {
                if (!this.getSelectedParts().contains(requiredPartType)) return false;
            }

            for (Map.Entry<Category, PartType> entry2 : mapCategoryPartType.entrySet()) {
                if (incompatibilities.contains(entry2.getValue())) return false;
            }
        }
        return true;
    }

    @Override
    public boolean isComplete() {
        return mapCategoryPartType.values().stream().anyMatch(Objects::isNull);
    }

    @Override
    public Set<PartType> getSelectedParts() {
        return Collections.unmodifiableSet((Set<PartType>) mapCategoryPartType.values());
    }

    @Override
    public void selectPart(PartType chosenPart) {
        Category categoryChosenPart = chosenPart.getCategory();
        mapCategoryPartType.put(categoryChosenPart, chosenPart);
    }

    @Override
    public PartType getSelectionForCategory(Category category) {
        return mapCategoryPartType.get(category);
    }

    @Override
    public void unselectPartType(Category categoryToClear) {
        mapCategoryPartType.remove(categoryToClear);
    }

    @Override
    public void clear() {
        for(Category c : mapCategoryPartType.keySet()) {
            this.unselectPartType(c);
        }
    }
}
