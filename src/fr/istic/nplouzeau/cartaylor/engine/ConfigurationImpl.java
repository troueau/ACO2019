package fr.istic.nplouzeau.cartaylor.engine;

import fr.istic.nplouzeau.cartaylor.api.*;

import java.util.*;


/**
 * Implementation of the interface Configuration
 * @author Arnaud DELOURMEL, Tom ROUSSEAU
 */
public class ConfigurationImpl implements Configuration {

    //Contains user's choice
    private Map<Category, PartType> mapCategoryPartType;
    private CompatibilityManager compatibilityManager;

    /**
     * Constructor
     * @param mapCategoryPartType Map
     * @param compatibilityManager CompatibilityManager
     */
    public ConfigurationImpl(Map<Category, PartType> mapCategoryPartType, CompatibilityManager compatibilityManager) {
        this.mapCategoryPartType = Objects.requireNonNull(mapCategoryPartType);
        this.compatibilityManager = Objects.requireNonNull(compatibilityManager);
    }


    @Override
    public boolean isValid() {
        if (!isComplete()) return false;
        // Check for all PartType if requirements and incompatibilities are respected
        for (Map.Entry<Category, PartType> entry1 : mapCategoryPartType.entrySet()) {

            Set<PartType> incompatibilities = Objects.isNull(compatibilityManager) ? Collections.emptySet() : compatibilityManager.getIncompatibilities(entry1.getValue());
            Set<PartType> requirements = Objects.isNull(compatibilityManager) ? Collections.emptySet() : compatibilityManager.getRequirements(entry1.getValue());

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
        mapCategoryPartType.put(Objects.requireNonNull(categoryToClear), null);
    }

    @Override
    public void clear() {
        for(Category c : mapCategoryPartType.keySet()) {
            this.unselectPartType(c);
        }
    }
}
