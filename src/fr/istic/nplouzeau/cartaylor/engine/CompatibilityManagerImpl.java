package fr.istic.nplouzeau.cartaylor.engine;

import fr.istic.nplouzeau.cartaylor.api.CompatibilityManager;
import fr.istic.nplouzeau.cartaylor.api.Configurator;
import fr.istic.nplouzeau.cartaylor.api.PartType;

import java.util.Map;
import java.util.Set;

public class CompatibilityManagerImpl implements CompatibilityManager {

    private Configurator configurator;
    //A set of parts required for the key part
    private Map<PartType, Set<PartType>>  requirements;
    //A set of parts incompatible with the key part
    private Map<PartType, Set<PartType>> incompatibilities;

    public CompatibilityManagerImpl(Configurator configurator, Map<PartType, Set<PartType>> requirements, Map<PartType, Set<PartType>> incompatibilities) {
        this.configurator = configurator;
        this.requirements = requirements;
        this.incompatibilities = incompatibilities;
    }

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
