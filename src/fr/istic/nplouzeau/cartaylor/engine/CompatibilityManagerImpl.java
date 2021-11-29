package fr.istic.nplouzeau.cartaylor.engine;

import fr.istic.nplouzeau.cartaylor.api.CompatibilityManager;
import fr.istic.nplouzeau.cartaylor.api.PartType;
import fr.istic.nplouzeau.cartaylor.exception.AlreadyManageException;

import java.util.*;

public class CompatibilityManagerImpl implements CompatibilityManager {

    private static final String ERR_ALREADY_IN_REQUIREMENTS = "You can't add %s in incompatibilities because it is already in requirements";
    private static final String ERR_ALREADY_IN_INCOMPATIBILITIES = "You can't add %s in requirements because it is already in incompatibilities";

    //A set of parts required for the key part
    private Map<PartType, Set<PartType>>  requirements;
    //A set of parts incompatible with the key part
    private Map<PartType, Set<PartType>> incompatibilities;

    /**
     * Construct CompatibilityManagerImpl
     * @param requirements The map of requirements can be empty or null
     * @param incompatibilities The map of the incompatibilities can be empty or null
     */
    public CompatibilityManagerImpl(Map<PartType, Set<PartType>> requirements, Map<PartType, Set<PartType>> incompatibilities) {
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

        Set<PartType> actualPartTypeSet = Objects.requireNonNullElse(incompatibilities.get(reference), new HashSet<>());
        actualPartTypeSet.addAll(target);
        incompatibilities.put(reference, actualPartTypeSet);
    }

    @Override
    public void removeIncompatibility(PartType reference, PartType target) {
        if (reference != null) {
            Set<PartType> tmp = Objects.requireNonNullElse(incompatibilities.get(reference), new HashSet<>());
            tmp.remove(target);
        }
    }

    @Override
    public void addRequirements(PartType reference, Set<PartType> target) throws AlreadyManageException {
        // Check if one of the new Requirements is not in the Incompatibilites list
        Set<PartType> incompatibilitiesForReference = getIncompatibilities(reference);
        for (PartType elemToAdd : target) {
            if (incompatibilitiesForReference.contains(elemToAdd)) {
                throw new AlreadyManageException(String.format(ERR_ALREADY_IN_INCOMPATIBILITIES, elemToAdd.toString()));
            }
        }

        Set<PartType> actualPartTypeSet = Objects.requireNonNullElse(requirements.get(reference), new HashSet<>());
        actualPartTypeSet.addAll(target);

        requirements.put(reference, actualPartTypeSet);
    }

    @Override
    public void removeRequirement(PartType reference, PartType target) {
        if (reference != null) {
            Set<PartType> tmp = Objects.requireNonNullElse(requirements.get(reference), new HashSet<>());
            tmp.remove(target);
        }
    }
}
