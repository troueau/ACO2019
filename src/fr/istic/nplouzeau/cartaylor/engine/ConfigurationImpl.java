package fr.istic.nplouzeau.cartaylor.engine;

import fr.istic.nplouzeau.cartaylor.api.*;

import java.io.PrintStream;
import java.util.*;

public class ConfigurationImpl implements Configuration {

    //Contains user's choice
    private Map<Category, Part> mapCategoryPartType;
    private CompatibilityManager compatibilityManager;

    public ConfigurationImpl(Map<Category, Part> mapCategoryPartType, CompatibilityManager compatibilityManager) {
        this.mapCategoryPartType = mapCategoryPartType;
        this.compatibilityManager = compatibilityManager;
    }


    @Override
    public boolean isValid() {
        if (!isComplete()) return false;
        // Check for all PartType if requirements and incompatibilities are respected
        for (Map.Entry<Category, Part> entry1 : mapCategoryPartType.entrySet()) {

            Set<PartType> incompatibilities = Objects.isNull(compatibilityManager) ? Collections.emptySet() : compatibilityManager.getIncompatibilities(entry1.getValue());
            Set<PartType> requirements = Objects.isNull(compatibilityManager) ? Collections.emptySet() : compatibilityManager.getRequirements(entry1.getValue());

            for (PartType requiredPartType : requirements) {
                if (!this.getSelectedParts().contains(requiredPartType)) return false;
            }

            for (Map.Entry<Category, Part> entry2 : mapCategoryPartType.entrySet()) {
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
    public Set<Part> getSelectedParts() {
        Set<Part> setOfSelectedParts = new HashSet<>(mapCategoryPartType.values());
        return Collections.unmodifiableSet(setOfSelectedParts);
    }

    @Override
    public void selectPart(Part chosenPart) {
        mapCategoryPartType.put(chosenPart.getCategory(), chosenPart);
    }

    @Override
    public Optional<Part> getSelectionForCategory(Category category) {
        Part part = mapCategoryPartType.get(category);
        return Objects.isNull(part) ? Optional.empty() : Optional.of(part);
    }

    @Override
    public void unselectPartType(Category categoryToClear) {
        mapCategoryPartType.put(categoryToClear, null);
    }

    @Override
    public void clear() {
        for(Category c : mapCategoryPartType.keySet()) {
            this.unselectPartType(c);
        }
    }
    public void printDescription(PrintStream ps) {
        StringBuilder tmp = new StringBuilder();
        for (Map.Entry elem : mapCategoryPartType.entrySet()) {
            tmp.append(elem.getKey().toString());
            tmp.append("\n");
            tmp.append(elem.getValue().toString());
        }
    }
}
