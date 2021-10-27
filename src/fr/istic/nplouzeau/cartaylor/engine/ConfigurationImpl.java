package fr.istic.nplouzeau.cartaylor.engine;

import fr.istic.nplouzeau.cartaylor.api.*;

import java.util.*;

public class ConfigurationImpl implements Configuration {

    private Configurator configurator;
    //Contains user's choice
    private Map<Category, PartType> mapCategoryPartType;
    private CompatibilityManager compatibilityManager;

    public ConfigurationImpl(Configurator configurator, CompatibilityManager compatibilityManager) {
        this.configurator = configurator;
        this.compatibilityManager = compatibilityManager;

        // Create mapCategory
        this.mapCategoryPartType = new HashMap<>();
        for (Category cat : configurator.getCategories()) {
            mapCategoryPartType.put(cat, null);
        }
    }


    @Override
    public boolean isValid() {
        if (!isComplete()) return false;
        // Check for all PartType if requirements and incompatibilities are respected
        for (Map.Entry<Category, PartType> entry1 : mapCategoryPartType.entrySet()) {
            Set<PartType> incompatibilities = new HashSet<>();
            Set<PartType> requirements = new HashSet<>();

            if(compatibilityManager!=null) {
                incompatibilities = compatibilityManager.getIncompatibilities(entry1.getValue());
                requirements = compatibilityManager.getRequirements(entry1.getValue());
            }

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
        return mapCategoryPartType.values().stream().noneMatch(Objects::isNull);
    }

    @Override
    public Set<PartType> getSelectedParts() {
        Set<PartType> setOfSelectedParts = new HashSet<>(mapCategoryPartType.values());
        return Collections.unmodifiableSet(setOfSelectedParts);
    }

    @Override
    public void selectPart(PartType chosenPart) {
        mapCategoryPartType.put(chosenPart.getCategory(), chosenPart);
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
