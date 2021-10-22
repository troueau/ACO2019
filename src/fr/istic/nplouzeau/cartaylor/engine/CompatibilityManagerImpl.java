package fr.istic.nplouzeau.cartaylor.engine;

import fr.istic.nplouzeau.cartaylor.api.CompatibilityManager;
import fr.istic.nplouzeau.cartaylor.api.Configurator;
import fr.istic.nplouzeau.cartaylor.api.PartType;
import fr.istic.nplouzeau.cartaylor.exception.AlreadyManageException;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        return Collections.unmodifiableSet(incompatibilities.get(reference));
     }

    @Override
    public Set<PartType> getRequirements(PartType reference) {
        return Collections.unmodifiableSet(requirements.get(reference));
    }

    @Override
    public void addIncompatibilities(PartType reference, Set<PartType> target) throws AlreadyManageException {
        Set<PartType> oldPartTypeSet = incompatibilities.get(reference);

        // Check if one of the new Incompatibility is not in the Requirements list
        Set<PartType> requirementsForReference = getRequirements(reference);
        for (PartType elemToAdd : target) {
            if (requirementsForReference.contains(elemToAdd)) {
                // TODO add msg
                throw new AlreadyManageException("");
            }
        }

        if(target.addAll(oldPartTypeSet)) incompatibilities.put(reference, target); //Pas sur, il y a d'autre moyens de le faire, vérifier si null ou vide ...
    }

    @Override
    public void removeIncompatibility(PartType reference, PartType target) {
        incompatibilities.get(reference).remove(target);
    }

    @Override
    public void addRequirements(PartType reference, Set<PartType> target) throws AlreadyManageException {
        Set<PartType> oldPartTypeSet = requirements.get(reference);

        // Check if one of the new Incompatibility is not in the Requirements list
        Set<PartType> incompatibilitiesForReference = getIncompatibilities(reference);
        for (PartType elemToAdd : target) {
            if (incompatibilitiesForReference.contains(elemToAdd)) {
                // TODO add msg
                throw new AlreadyManageException("");
            }
        }


        if(target.addAll(oldPartTypeSet)) requirements.put(reference, target); //Pas sur, il y a d'autre moyens de le faire, vérifier si null ou vide ...
    }

    @Override
    public void removeRequirement(PartType reference, PartType target) {
        requirements.get(reference).remove(target);
    }
}
