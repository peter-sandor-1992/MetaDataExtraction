package crf.cora;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import util.PropertyProvider;

/**
 * Manages corpora which have a similar structure to the cora corpus
 * (http://people.cs.umass.edu/~mccallum/data/cora-ie.tar.gz).
 * @author Peter Sandor
 */
public class CoraHeaders {
    
    /**
     * Stores the headers in a labeled cora header file in a list.
     * @param headersFileName path of the file containing the headers
     * @return the list of headers
     * @throws IOException
     */
    public static List<String> readCoraHeaders(String headersFileName) throws IOException {        
        try (BufferedReader reader = new BufferedReader(new FileReader(headersFileName))) {
            ArrayList<String> headers = new ArrayList<>();
            StringBuilder builder = new StringBuilder();
            String line;
            while ((line = reader.readLine())!= null) {
                //empty row, start of a new header
                if (line.equals("")) {
                    headers.add(builder.toString());
                    builder.setLength(0);
                } else {
                    builder.append(line.concat("\n"));
                }
            }
            return headers;
        }
    }

    /**
     * Splits a header file into two files: one for training and one for testing.
     * @param headersFileName path of the file containing the headers
     * @throws IOException
     */
    public static void createTrainingAndTestFiles(String headersFileName) throws IOException {
        List<String> headers = readCoraHeaders(headersFileName);
        //randomly shuffle the list of headers
        Collections.shuffle(headers);
        
        //take the first 600, these will act as the training data
        try(FileWriter writer = new FileWriter(PropertyProvider.INSTANCE.getProperty("trainingHeaders"))) {
            for (int i = 0; i < 600; i++) {
                for (String headerLine : headers.get(i).split("\n")) {
                    writer.write(headerLine.concat("\n"));
                }
                writer.write("\n");
                writer.flush();
            }
        }
        
        //the rest will be the testing data
        try(FileWriter writer = new FileWriter(PropertyProvider.INSTANCE.getProperty("testHeaders"))) {
            for (int i = 600; i < headers.size(); i++) {
                for (String headerLine : headers.get(i).split("\n")) {
                    writer.write(headerLine.concat("\n"));
                }
                writer.write("\n");
                writer.flush();
            }
        }
    }
}