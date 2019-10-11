package fr.istic.nplouzeau.cartaylor.api;

import java.util.Set;

public interface CompatibilityManager extends CompatibilityChecker {

    void addIncompatibilities(PartType reference, Set<PartType> target);

    void removeIncompatibility(PartType reference, PartType target);

    void addRequirements(PartType reference, Set<PartType> target);

    void removeRequirement(PartType reference, PartType target);

}
