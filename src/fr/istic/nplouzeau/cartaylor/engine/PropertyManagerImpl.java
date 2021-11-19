package fr.istic.nplouzeau.cartaylor.engine;

import fr.istic.nplouzeau.cartaylor.api.PropertyManager;

import java.util.Optional;
import java.util.Set;

public class PropertyManagerImpl implements PropertyManager {
    @Override
    public Set<String> getPropertyNames() {
        return null;
    }

    @Override
    public Set<String> getAvailablePropertyValues(String propertyName) {
        return null;
    }

    @Override
    public Optional<String> getProperty(String propertyName) {
        return Optional.empty();
    }

    @Override
    public void setProperty(String propertyName, String propertyValue) {

    }
}
