package fr.istic.nplouzeau.cartaylor.api;

import java.util.Set;

public interface CompatibilityChecker {

    Set<PartType> getIncompatibilities(PartType reference);

    Set<PartType> getRequirements(PartType reference);

}
