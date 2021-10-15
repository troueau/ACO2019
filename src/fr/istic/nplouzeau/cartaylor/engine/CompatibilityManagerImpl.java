package fr.istic.nplouzeau.cartaylor.engine;

import fr.istic.nplouzeau.cartaylor.api.CompatibilityManager;
import fr.istic.nplouzeau.cartaylor.api.PartType;

import java.util.Set;

public class CompatibilityManagerImpl implements CompatibilityManager {
    @Override
    public Set<PartType> getIncompatibilities(PartType reference) {
        return null;
    }

    @Override
    public Set<PartType> getRequirements(PartType reference) {
        return null;
    }

    @Override
    public void addIncompatibilities(PartType reference, Set<PartType> target) {

    }

    @Override
    public void removeIncompatibility(PartType reference, PartType target) {

    }

    @Override
    public void addRequirements(PartType reference, Set<PartType> target) {

    }

    @Override
    public void removeRequirement(PartType reference, PartType target) {

    }
}
