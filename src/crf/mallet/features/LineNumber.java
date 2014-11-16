package crf.mallet.features;

import cc.mallet.pipe.Pipe;
import cc.mallet.types.Instance;
import cc.mallet.types.TokenSequence;

/**
 * A feature encoding the line number.
 * @author Peter Sandor
 */
public class LineNumber extends Pipe {
    
    String featureName = "ROW=";

    /**
     * Adds the row number on which the token is as a feature to the instance.
     * @param instance the instance to be processed
     * @return the instance with the features added
     */
    @Override
    public Instance pipe(Instance instance) {
        int row = 1;
        
        TokenSequence sequence = (TokenSequence)instance.getData();
        for (int i=0; i<sequence.size(); i++) {
            sequence.get(i).setFeatureValue(featureName + row, 1.0);
            if (sequence.get(i).getText().equals("+L+")) {
                row++;
            }
        }
        return instance;
    }
}