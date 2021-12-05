package fr.istic.nplouzeau.cartaylor.engine;

import fr.istic.nplouzeau.cartaylor.api.PropertyManager;

import java.util.Optional;
import java.util.Set;

/**
 * Implementation of the interface PropertyManager
 * @author Arnaud DELOURMEL, Tom ROUSSEAU
 */
public class PropertyManagerImpl implements PropertyManager {
    @Override
    public Set<String> getPropertyNames() {
        // TODO
        return null;
    }

    @Override
    public Set<String> getAvailablePropertyValues(String propertyName) {
        // TODO
        return null;
    }

    @Override
    public Optional<String> getProperty(String propertyName) {
        //TODO
        return Optional.empty();
    }

    @Override
    public void setProperty(String propertyName, String propertyValue) {
        //TODO
    }
}
