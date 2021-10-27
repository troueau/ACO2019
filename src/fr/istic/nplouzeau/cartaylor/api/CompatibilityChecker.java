package fr.istic.nplouzeau.cartaylor.api;

import java.util.Set;

/**
 * A public type to get compatibilities for chosen part types
 */
public interface CompatibilityChecker {
    /**
     * Allow to get the incompatible elements of the PartType
     * @param reference reference of the element
     * @return An immutable set of the incompatible elements of the PartType or an empty set if the reference doesn't exist.
     */
    Set<PartType> getIncompatibilities(PartType reference);

    /**
     * Allow the get the requirements for the reference
     * @param reference reference of a part type
     * @return An immutable set of the requirements of the PartType or an empty set if the reference doesn't exist.
     */
    Set<PartType> getRequirements(PartType reference);

}
