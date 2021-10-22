package fr.istic.nplouzeau.cartaylor.api;

import fr.istic.nplouzeau.cartaylor.exception.AlreadyManageException;

import java.util.Set;

/**
 * A public type to manage compatibilities and incompatibilities
 */
public interface CompatibilityManager extends CompatibilityChecker {
    /**
     * Allow to add new incompatibilities for a PartType
     * @param reference The reference of the PartType to modified
     * @param target Collection of the new incompatibilities
     */
    void addIncompatibilities(PartType reference, Set<PartType> target) throws AlreadyManageException;

    /**
     * Allow to remove one incompatibilie of one PartType
     * @param reference reference of a part type
     * @param target The incompatibilitie to remove
     */
    void removeIncompatibility(PartType reference, PartType target);

    /**
     * Allow to add new requirements of one PartType
     * @param reference reference of a part type
     * @param target The new requirements to add
     */
    void addRequirements(PartType reference, Set<PartType> target) throws AlreadyManageException;

    /**
     * Allow to remove one requirement of one PartType
     * @param reference reference of a part type
     * @param target The requirement to remove
     */
    void removeRequirement(PartType reference, PartType target);

}
