package fr.istic.nplouzeau.cartaylor.engine;

import fr.istic.nplouzeau.cartaylor.api.CompatibilityManager;
import fr.istic.nplouzeau.cartaylor.api.Configurator;
import fr.istic.nplouzeau.cartaylor.api.PartType;
import fr.istic.nplouzeau.cartaylor.exception.AlreadyManageException;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CompatibilityManagerImpl implements CompatibilityManager {

    private static final String ERR_ALREADY_IN_REQUIREMENTS = "You can't add %s in incompatibilities because it is already in requirements";
    private static final String ERR_ALREADY_IN_INCOMPATIBILITIES = "You can't add %s in requirements because it is already in incompatibilities";

    private Configurator configurator;
    //A set of parts required for the key part
    private Map<PartType, Set<PartType>>  requirements;
    //A set of parts incompatible with the key part
    private Map<PartType, Set<PartType>> incompatibilities;

    /**
     * Construct CompatibilityManagerImpl
     * @param configurator the reference to the configurator must be not null
     * @param requirements The map of requirements can be empty or null
     * @param incompatibilities The map of the incompatibilities can be empty or null
     */
    public CompatibilityManagerImpl(Configurator configurator, Map<PartType, Set<PartType>> requirements, Map<PartType, Set<PartType>> incompatibilities) {
        this.configurator = Objects.requireNonNull(configurator);
        this.requirements = Objects.requireNonNullElse(requirements, new HashMap<>());
        this.incompatibilities = Objects.requireNonNullElse(incompatibilities, new HashMap<>());
    }

    @Override
    public Set<PartType> getIncompatibilities(PartType reference) {
        Set<PartType> ret = Objects.requireNonNullElse(incompatibilities.get(reference), Collections.emptySet());
        return Collections.unmodifiableSet(ret);
     }

    @Override
    public Set<PartType> getRequirements(PartType reference) {
        Set<PartType> ret = Objects.requireNonNullElse(requirements.get(reference), Collections.emptySet());
        return Collections.unmodifiableSet(ret);
    }

    @Override
    public void addIncompatibilities(PartType reference, Set<PartType> target) throws AlreadyManageException {
        // Check if one of the new Incompatibility is not in the Requirements list
        Set<PartType> requirementsForReference = getRequirements(reference);
        for (PartType elemToAdd : target) {
            if (requirementsForReference.contains(elemToAdd)) {
                throw new AlreadyManageException(String.format(ERR_ALREADY_IN_REQUIREMENTS, elemToAdd.toString()));
            }
        }

        Set<PartType> actualPartTypeSet = incompatibilities.get(reference);
        actualPartTypeSet.addAll(target);
//        if(target.addAll(oldPartTypeSet)) incompatibilities.put(reference, target); //Pas sur, il y a d'autre moyens de le faire, vérifier si null ou vide ...
    }

    @Override
    public void removeIncompatibility(PartType reference, PartType target) {
        incompatibilities.get(reference).remove(target);
    }

    @Override
    public void addRequirements(PartType reference, Set<PartType> target) throws AlreadyManageException {

        // TODO what happen if this instruction return null (reference isn't in the requirements list)
        Set<PartType> oldPartTypeSet = requirements.get(reference);

        // Check if one of the new Requirements is not in the Incompatibilites list
        Set<PartType> incompatibilitiesForReference = getIncompatibilities(reference);
        for (PartType elemToAdd : target) {
            if (incompatibilitiesForReference.contains(elemToAdd)) {
                throw new AlreadyManageException(String.format(ERR_ALREADY_IN_INCOMPATIBILITIES, elemToAdd.toString()));
            }
        }


        if(target.addAll(oldPartTypeSet)) requirements.put(reference, target); // TODO Pas sur, il y a d'autre moyens de le faire, vérifier si null ou vide ...
    }

    @Override
    public void removeRequirement(PartType reference, PartType target) {
        requirements.get(reference).remove(target);
    }
}
