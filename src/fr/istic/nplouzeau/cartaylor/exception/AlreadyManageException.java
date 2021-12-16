package fr.istic.nplouzeau.cartaylor.exception;

/**
 * Exception in case for compatibilityManager.
 * In case on incompatibily is already in requirement and vice versa
 * @author Arnaud DELOURME, Tom ROUSSEAU
 */
public class AlreadyManageException extends Exception{
    /**
     * Constructor
     * @param errorMessage the message of the error
     */
    public AlreadyManageException(String errorMessage) {
        super(errorMessage);
    }
}

