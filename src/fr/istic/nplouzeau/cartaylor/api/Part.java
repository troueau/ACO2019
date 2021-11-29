package fr.istic.nplouzeau.cartaylor.api;

public interface Part extends PropertyManager {

	/**
	 * get the name of the current part
	 * @return the part name into a string
	 */
	default String getName() {
		return this.getClass().getTypeName();
	};

	/**
	 * get the category for the current part
	 * @return category
	 */
	Category getCategory();


	/**
	 * get the type for the current part
	 * @return type
	 */
	PartType getType();

	/**
	 * get the price of the Part
	 * @return the price
	 */
	double getPrice();
}