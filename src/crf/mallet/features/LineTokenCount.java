package crf.mallet.features;

import cc.mallet.pipe.Pipe;
import cc.mallet.types.Instance;
import cc.mallet.types.TokenSequence;

/**
 * A feature encoding the number of tokens each line contains.
 * @author Peter Sandor
 */
public class LineTokenCount extends Pipe {
    
    String featureName = "TOKENCNT=";

    /**
     * Adds the number of tokens for each line as a feature to the instance.
     * @param instance the instance to be processed
     * @return the instance with the features added
     */
    @Override
    public Instance pipe(Instance instance) {
        int startingToken = 0;
        int count = 0;
        
        TokenSequence sequence = (TokenSequence)instance.getData();
        for (int i=0; i<sequence.size(); i++) {
            if (sequence.get(i).getText().equals("+L+")) {
                for (int j = startingToken; j <= i; j++) {
                    sequence.get(j).setFeatureValue(featureName + count, 1.0);
                }
                count = 0;
                startingToken = i + 1;
            } else {
                count++;
            }
        }
        return instance;
    }
}