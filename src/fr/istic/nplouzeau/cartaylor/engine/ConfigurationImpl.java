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

            Set<PartType> incompatibilities = Objects.isNull(compatibilityManager) ? Collections.emptySet() : compatibilityManager.getIncompatibilities(entry1.getValue().getType());
            Set<PartType> requirements = Objects.isNull(compatibilityManager) ? Collections.emptySet() : compatibilityManager.getRequirements(entry1.getValue().getType());

            Set<PartType> selectedPartType = convertSetOfPartToSetOfPartType(this.getSelectedParts());

            if (!selectedPartType.containsAll(requirements)) return false;


            for (Map.Entry<Category, Part> entry2 : mapCategoryPartType.entrySet()) {
                if (incompatibilities.contains(entry2.getValue().getType())) return false;
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
        setOfSelectedParts.remove(null);
        return Collections.unmodifiableSet(setOfSelectedParts);
    }

    @Override
    public void selectPart(PartType chosenPart) {
        mapCategoryPartType.put(chosenPart.getCategory(), ((PartTypeImpl) chosenPart).newInstance());
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
        mapCategoryPartType.keySet().forEach(this::unselectPartType);
    }

    @Override
    public void printDescription(PrintStream ps) {
        // TODO
        StringBuilder tmp = new StringBuilder();
        mapCategoryPartType.forEach((key, value) -> {
            tmp.append(key.toString());
            tmp.append("\n");
            tmp.append(value.toString());
        });
    }

    @Override
    public double getPrice() {
        // TODO
        return 0;
    }



    private Set<PartType> convertSetOfPartToSetOfPartType(Set<Part> l) {
        Set<PartType> ret = new HashSet<>();
        for (Part elem : l) {
            ret.add(elem.getType());
        }
        return ret;
    }
}
