package crf.test;

/**
 * Statistical data for testing CRF models.
 * Calculates the precision, recall, accuracy and F measure. 
 * @author Peter Sandor
 */
public class Statistics {
    
    private int truePositive; //the number of correctly identified labels
    private int falsePositive; //the number of incorrectly identified labels
    private int trueNegative; //the number of correctly rejected labels
    private int falseNegative; //the number of incorrectly rejected labels

    public Statistics() {
        truePositive = 0;
        falsePositive = 0;
        trueNegative = 0;
        falseNegative = 0;
    }
    
    public int getTruePositive() {
        return truePositive;
    }

    public void setTruePositive(int truePositive) {
        this.truePositive = truePositive;
    }

    public int getFalsePositive() {
        return falsePositive;
    }

    public void setFalsePositive(int falsePositive) {
        this.falsePositive = falsePositive;
    }

    public int getTrueNegative() {
        return trueNegative;
    }

    public void setTrueNegative(int trueNegative) {
        this.trueNegative = trueNegative;
    }

    public int getFalseNegative() {
        return falseNegative;
    }

    public void setFalseNegative(int falseNegative) {
        this.falseNegative = falseNegative;
    }
    
    /**
     * Calculates the precision value.
     * @return the precision for this statistical data
     */
    public double getPrecision() {
        return truePositive * 100.0 / (truePositive + falsePositive);
    }
    
    /**
     * Calculates the recall value.
     * @return the recall for this statistical data
     */
    public double getRecall() {
        return truePositive * 100.0 / (truePositive + falseNegative);
    }
    
    /**
     * Calculates the accuracy value.
     * @return the accuracy for this statistical data
     */
    public double getAccuracy() {
        return (truePositive + trueNegative) * 100 / (truePositive + falsePositive + trueNegative + falseNegative);
    }
    
    /**
     * Calculates the F measure value.
     * @return the F measure for this statistical data
     */
    public double getFMeasure() {
        double precision = getPrecision();
        double recall = getRecall();
        return 2 * precision * recall / (precision + recall);
    }
}