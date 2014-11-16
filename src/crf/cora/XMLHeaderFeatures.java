package crf.cora;

import util.PropertyProvider;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * Writes "word label" paired rows in a file based on an XML file containing 
 * tagged scientific articles. 
 * If the labels list contains the tag it will be the word's label in the output 
 * file, otherwise the word's label will be 'other'.
 * 
 * The extraction is based on the cora corpus.
 * The input XML file must have a "NEW_HEADER" tag at the beginning of each header.
 * The XML file must be well formed, except the "NEW_HEADER" tag.
 * Line break in text is indicated as a "+L+" sequence.
 * @author Peter Sandor
 * 
 * corpus used for training: http://people.cs.umass.edu/~mccallum/data/cora-ie.tar.gz
 */
public class XMLHeaderFeatures {
        
    private static final Logger LOGGER = LogManager.getLogger(XMLHeaderFeatures.class.getName());
    //store the list of metadata labels to be kept
    private static final ArrayList<String> labels = new ArrayList<>();
    static {
        labels.addAll(Arrays.asList(PropertyProvider.INSTANCE.getProperty("labels").split(",")));
    }
    
    /**
     * Read the given XML file containing tagged headers of scientific articles and 
     * write "word label" pairs in the given output file, one pair on each line.
     * Text between tags which appear in the labels list will be written as a 
     * "word tag" pair, the rest of the text will be stored as "word other" pairs.
     */
    public static void generateXMLBasedFeatures() {
        String currentTag = "";
        String corpus = PropertyProvider.INSTANCE.getProperty("corpus");
        String headers = PropertyProvider.INSTANCE.getProperty("headers");

        try (Scanner sc = new Scanner(new File(corpus));FileWriter writer = new FileWriter(headers)) {
            while (sc.hasNext()) {
                String token = sc.next();
                if (token.equals("<NEW_HEADER>")) {
                    writer.write("\n");
                } else if (token.equals("+L+")) {
                    writer.write("+L+ other\n");
                } else if (token.startsWith("<") && token.endsWith(">")) { //XML tag
                        if (token.charAt(1) != '/') { //opening tag
                            token = token.substring(1, token.length() - 1);
                            if (labels.contains(token)) {
                                currentTag = token;
                            } else {
                                currentTag = "other";
                            }
                        }
                    } else {
                        writer.write(token + " " + currentTag + "\n");
                    }
                writer.flush();
            }
        } catch (IOException ex) {
            LOGGER.error("IO error at feature file generating from XML corpus.",ex);
        }
    }
}