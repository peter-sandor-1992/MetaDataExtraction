package crf.mallet.features;

import cc.mallet.pipe.Pipe;
import cc.mallet.types.Instance;
import cc.mallet.types.TokenSequence;
import crf.util.LexiconManager;

/**
 * A feature encoding the percentage of names for each line.
 * @author Peter Sandor
 */
public class LineNamePercentage extends Pipe {
    
    String featureName = "NAMEPERC=";

    /**
     * Adds the names_count/token_count ratio as a feature to the instance.
     * @param instance the instance to be processed
     * @return the instance with the features added
     */
    @Override
    public Instance pipe(Instance instance) {
        int startingToken = 0;
        int nameCount = 0;
        
        TokenSequence sequence = (TokenSequence)instance.getData();
        for (int i=0; i<sequence.size(); i++) {
            if (sequence.get(i).getText().equals("+L+")) {
                for (int j = startingToken; j <= i; j++) {
                    sequence.get(j).setFeatureValue(featureName + (double)nameCount / (i-startingToken), (double)nameCount / (i-startingToken));
                }
                nameCount = 0;
                startingToken = i + 1;
            } else {
                if (LexiconManager.contains("name", sequence.get(i).getText())) {
                    nameCount++;
                }
            }
        }
        return instance;
    }
}