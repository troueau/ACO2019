package fr.istic.nplouzeau.cartaylor.engine.car;

import fr.istic.nplouzeau.cartaylor.api.PartType;
import fr.istic.nplouzeau.cartaylor.engine.PartImpl;

public class Engine extends PartImpl  {
    public Engine(PartType type, double price) {
        super(type, price);
    }
}
