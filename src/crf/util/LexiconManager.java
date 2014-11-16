package crf.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * Store lexicons in a hash-map identified by its name (name of the lexicon, list of tokens it contains).
 * The lexicons are read from the 'lexicons' folder and it's tokens are in alphabetical order.
 * @author Peter Sandor
 */
public class LexiconManager {
    
    private static final Logger LOGGER = LogManager.getLogger(LexiconManager.class.getName());    
    private static final HashMap<String, ArrayList<String>> lexicons = new HashMap<>();
    
    static {
        lexicons.put("name", new ArrayList<String>());
        readLexicons();
    }
    
    private static void readLexicons() {
        for (String lexiconName : lexicons.keySet()) {
            try (BufferedReader reader = new BufferedReader(new FileReader("lexicons/" + lexiconName + ".txt"))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    lexicons.get(lexiconName).add(line);
                }
            } catch (FileNotFoundException ex) {
                LOGGER.error("lexiconName + \": lexicon not found.", ex);
            } catch (IOException ex) {
                LOGGER.error("IO error at lexicon processing.", ex);
            }
        }
    }
    
    /**
     * Return a binary search based answer whether the given lexicon contains the token.
     * @param lexiconName the lexicon to be searched
     * @param token the token to be looked for
     * @return true if the lexicon contains the token, false otherwise
     */
    public static boolean contains(String lexiconName, String token) {
        return Collections.binarySearch(lexicons.get(lexiconName), token) >= 0;
    }
}