package fr.istic.nplouzeau.cartaylor.api;

import java.util.Set;

public interface CompatibilityChecker {
    /**
     * Allow to get the incompatible elements of the PartType
     * @param reference reference of the element
     * @return The incompatible elements of the PartType
     */
    Set<PartType> getIncompatibilities(PartType reference);

    /**
     * Allow the get the requirements for the reference
     * @param reference
     * @return The requirements of the PartType
     */
    Set<PartType> getRequirements(PartType reference);

}
