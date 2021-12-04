package fr.istic.nplouzeau.cartaylor.api;

/**
 * A public type to organize part types
 */
public interface PartType {

    /**
     * get the name of the current part
     * @return the part name into a string
     */
    String getName();

    /**
     * get the category for the current part
     * @return category
     */
    Category getCategory();


    /**
     * get the price for the current part
     * @return the price
     */
    double getPrice();
}
