package fr.istic.nplouzeau.cartaylor.api;

public interface Part extends PropertyManager {

	default String getName() {
		return this.getClass().getTypeName();
	};
	Category getCategory();
	PartType getType();
}