package fr.istic.nplouzeau.cartaylor.engine.car;

import fr.istic.nplouzeau.cartaylor.api.PartType;
import fr.istic.nplouzeau.cartaylor.engine.PartImpl;

import java.util.Objects;
import java.util.Set;

enum Color {
    RED,
    WHITE,
    BLUE;
}

public class Exterior extends PartImpl {
    private Color paintColor = Color.WHITE;

    public Exterior(PartType type, double price) {
        super(type, price);
    }

    public String getColor() {
        return paintColor.name();
    }

    public void setColor(String color) {
        Color newColor = Color.valueOf(color);
        if(!Objects.isNull(newColor)) paintColor = newColor;
    }

    Set<String> possibleColors = Set.of("BLUE", "RED");

    public Exterior() {
        addProperty("paintColor",
                () -> getColor(),
                (c) -> setColor(c),
                possibleColors);
    }
}
