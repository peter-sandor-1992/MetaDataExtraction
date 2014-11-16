package util;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * Reads properties from a .properties file.
 * @author Peter Sandor
 */
public enum PropertyProvider {
    
    INSTANCE;

    private static final Logger LOGGER = LogManager.getLogger(PropertyProvider.class.getName());
    private static final String BUNDLE_NAME = "res.metadata_extraction";
    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);

    public String getProperty(final String key) {
        try {
            return RESOURCE_BUNDLE.getString(key);
        } catch (final MissingResourceException ex) {
            LOGGER.error("Missing resource", ex);
            return null;
        }
    }
}