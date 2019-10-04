package fr.istic.nplouzeau.cartaylor.api;


import java.util.Optional;
import java.util.Set;

public interface Configuration {

    boolean isValid();

    boolean isComplete();

    Set<PartType> getSelectedParts();

    void selectPart(PartType chosenPart);

    Optional<PartType> getSelectionForCategory(Category category);

    void unselectPartType(Category categoryToClear);

    void clear();

}
