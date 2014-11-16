package model;

import java.util.HashMap;
import util.PropertyProvider;

/**
 * Provides storage for an article header, containing it's meta data as a
 * hash map with label-value pairs.
 * @author Peter Sandor
 */
public class ArticleHeader {
    
    private final HashMap<String, String> values;

    /**
     * Creates a new instance with empty values.
     */
    public ArticleHeader() {
        values = new HashMap<>();        
        for (String label : PropertyProvider.INSTANCE.getProperty("labels").split(",")) {
            values.put(label, "");
        }
    }
    
    /**
     * Getter for the value for the given label.
     * @param label the label for which the data is requested
     * @return the value for the required label
     */
    public String getValue(String label) {
        return values.get(label);
    }
    
    /**
     * Sets the given label's value.
     * @param label the label of the data
     * @param value the value to be stored
     */
    public void setValue(String label, String value) {
        values.put(label, value);
    }
    
    /**
     * Returns a list containing the labels.
     * @return the list of labels
     */
    public Iterable<String> getLabels() {
        return values.keySet();
    }    
}