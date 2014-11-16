package crf.train;

import crf.mallet.MalletCRFHeaderExtract;
import java.io.IOException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import util.PropertyProvider;

/**
 * Trains and saves a CRF model based on the given training data.
 * @author Peter Sandor
 */
public class CRFTrainer {
    
    private static final Logger LOGGER = LogManager.getLogger(CRFTrainer.class.getName());
    
    /**
     * Trains and saves the CRF model.
     */
    public static void train() {
        MalletCRFHeaderExtract crf = new MalletCRFHeaderExtract();
        crf.generateFeatures();
        try {
            crf.train(PropertyProvider.INSTANCE.getProperty("trainingHeaders"));
            crf.saveModel(PropertyProvider.INSTANCE.getProperty("crfModel"));
        } catch (IOException ex) {
            LOGGER.error("Error at training and saving the model.",ex);
        }
    }
}