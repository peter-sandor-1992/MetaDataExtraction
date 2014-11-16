package crf.test;

import cc.mallet.types.Sequence;
import crf.cora.CoraHeaders;
import crf.mallet.MalletCRFHeaderExtract;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import model.ArticleHeader;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import util.PropertyProvider;

/**
 * Tests a CRF model against a list of headers.
 * @author Peter Sandor
 */
public class CRFTester {    

    private static final Logger LOGGER = LogManager.getLogger(CRFTester.class.getName());
    
    /**
     * Loads and tests the trained CRF model.
     * The results are printed in the given file.
     * @param resultsFileName the path of the while where the results will be printed
     */
    public static void test(String resultsFileName) {
        try {
            MalletCRFHeaderExtract crf = new MalletCRFHeaderExtract();
            crf.loadModel(PropertyProvider.INSTANCE.getProperty("crfModel"));
            CRFTester.generateTestResults(crf, resultsFileName);
        } catch (IOException | ClassNotFoundException ex) {
            LOGGER.error("CRF model loading error.", ex);
        }
    }
    
    /**
     * Calculates the precision, recall, accuracy and F measure for each label
     * using the given CRF model on the test headers.
     * @param crf the CRF model to be tested
     * @param testResultsFileName the path of the output file containing the test results
     */
    public static void generateTestResults(MalletCRFHeaderExtract crf, String testResultsFileName) {
        //the list of headers used for testing
        List<String> headers;
        try {
            headers = CoraHeaders.readCoraHeaders(PropertyProvider.INSTANCE.getProperty("testHeaders"));
        } catch (IOException ex) {
            LOGGER.error("Test headers file cannot be processed.",ex);
            return;
        }

        //a header containing the predicted text for each label by the CRF
        ArticleHeader predictions = new ArticleHeader();

        //a header storing the actual text for each label from the headers
        ArticleHeader correctLabels = new ArticleHeader();

        //a hash map storing the test results for each label
        HashMap<String, Statistics> testResults = createStatisticsHashmap();

        for (String header : headers) {
            int headerTokensCount = 0; //the number of tokens in the current header
            StringBuilder text = new StringBuilder();                
            for (String line : header.split("\n")) {
                if (!line.equals("+L+")) {
                    headerTokensCount++;
                }
                int spacePos = line.indexOf(' ');
                String word = line.substring(0, spacePos);
                text.append(word.concat("\n"));
                String label = line.substring(spacePos + 1);
                //store the correct text for each label
                correctLabels.setValue(label, correctLabels.getValue(label).concat(word).concat(" "));
            }

            String[] tokens = text.toString().split("\n");
            //predict labels for the tokens using the trained CRF tagger
            Sequence[] labels = crf.predict(text.toString());
            //store the predictions in the hashmap
            for (int j=0; j<labels[0].size(); j++) {
                String label = labels[0].get(j).toString();
                predictions.setValue(label, predictions.getValue(label).concat(tokens[j]).concat(" "));
            }

            //calculate test results for each label in this header
            for (String label : correctLabels.getLabels()) {
                List<String> correctTokens = Arrays.asList(correctLabels.getValue(label).replaceAll("\\+L\\+ ", "").split(" "));
                List<String> predictedTokens = Arrays.asList(predictions.getValue(label).replaceAll("\\+L\\+ ", "").split(" "));

                int correctPredictions = 0; //the number of correctly tagged tokens for this label
                for (String correctToken : correctTokens) {
                    if (predictedTokens.contains(correctToken)) {
                        correctPredictions++;
                    }
                }

                int tp = testResults.get(label).getTruePositive();
                int fp = testResults.get(label).getFalsePositive();
                int tn = testResults.get(label).getTrueNegative();
                int fn = testResults.get(label).getFalseNegative();
                //increase the number of true positives with the correctly identified tokens' count
                testResults.get(label).setTruePositive(tp + correctPredictions);
                //increase the number of false positives with the number of incorrectly identified tokens' count
                testResults.get(label).setFalsePositive(fp + predictedTokens.size() - correctPredictions);
                //increase the number of true negatives with the number of correctly rejected tokens' count
                testResults.get(label).setTrueNegative(tn + headerTokensCount - predictedTokens.size());
                //increase the number of false negatives with the number of incorrectly rejected tokens' count
                testResults.get(label).setFalseNegative(fn + correctTokens.size() - correctPredictions);
            }

            //clear the predicted and correct headers for the next result
            predictions = new ArticleHeader();
            correctLabels = new ArticleHeader();
        }

        //print the test results into the specified file
        try (FileWriter writer = new FileWriter(testResultsFileName)) {
            writer.write(String.format("%-13s%-11s%-8s%-10s%s", "Label", "Precision", "Recall", "Accuracy", "F measure"));
            for (String label : testResults.keySet()) {
                writer.write(String.format("\n%-13s%-11.2f%-8.2f%-10.2f%.2f",
                                            label,
                                            testResults.get(label).getPrecision(),
                                            testResults.get(label).getRecall(),
                                            testResults.get(label).getAccuracy(),
                                            testResults.get(label).getFMeasure()));
            }
            writer.flush();
        } catch (IOException ex) {
            LOGGER.error("Error at saving test results to file.", ex);
        }
    }
    
    /**
     * Create a hash map to store statistics for each label.
     * @return the hash map containing the labels with initial statistics data (zeros)
     */
    public static HashMap<String, Statistics> createStatisticsHashmap() {
        HashMap<String, Statistics> labels = new HashMap<>();

        for (String label : PropertyProvider.INSTANCE.getProperty("labels").split(",")) {
            labels.put(label, new Statistics());
        }

        return labels;
    }
}