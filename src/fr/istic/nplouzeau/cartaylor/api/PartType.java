package fr.istic.nplouzeau.cartaylor.api;

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
}
