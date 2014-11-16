package crf.mallet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StringReader;
import java.util.regex.Pattern;

import cc.mallet.fst.CRF;
import cc.mallet.fst.CRFTrainerByThreadedLabelLikelihood;
import cc.mallet.fst.MaxLatticeDefault;
import cc.mallet.fst.Transducer;
import cc.mallet.pipe.Pipe;
import cc.mallet.pipe.iterator.LineGroupIterator;
import cc.mallet.types.InstanceList;
import cc.mallet.types.Sequence;

/**
 * CRF model used to predict sequences.
 * @author Peter Sandor
 */
public abstract class MalletCRF {
    
    private final double gaussianVariance = 10.0;
    private final int numIterations = 500;
    private final int cacheSize = 100000;
    private final int numThreads = 4;
    private final int nBest = 2;
    private final boolean useSparse = true;

    protected CRF crf;
    protected Pipe pipe;
    protected CRFTrainerByThreadedLabelLikelihood crfTrainer;

    public void train(String fileName) throws FileNotFoundException {
        InstanceList trainData = new InstanceList(pipe);
        trainData.addThruPipe(new LineGroupIterator(new FileReader(new File(fileName)), Pattern.compile("^\\s*$"), true));

        crf = new CRF(pipe, null);
        crf.addStatesForLabelsConnectedAsIn(trainData);

        crfTrainer = new CRFTrainerByThreadedLabelLikelihood(crf, numThreads);

        //set sparse usage and gaussian variance
        crfTrainer.setUseSparseWeights(useSparse);
        crfTrainer.setGaussianPriorVariance(gaussianVariance);
        
        //train while converged, with a maximum number of numIterations iterations
        crfTrainer.train(trainData, numIterations);
        crfTrainer.shutdown();
    }

    public abstract void generateFeatures();

    public Pipe getPipe() {
        return pipe;
    }

    public CRF getCRF() {
        return crf;
    }
    
    /**
     * Loads the trained model from a serialized file.
     * @param fileName the path from where the model will be loaded
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void loadModel(String fileName) throws IOException, ClassNotFoundException {
        System.out.println("loading model...");
        
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            crf = (CRF)ois.readObject();
        }        
        pipe = crf.getInputPipe();
        
        System.out.println("model loaded");
    }

    /**
     * Saves the trained model into a file.
     * @param fileName the path where the model will be stored
     * @throws IOException
     */
    public void saveModel(String fileName) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(crf);
        }
    }

    public Sequence[] predict(Sequence data, int k) {
        return apply(crf, data, k);
    }

    public Sequence[] predict(Sequence data) {
        return apply(crf, data, nBest);
    }

    /**
     * Return the k highest-scoring sequences for the given data.
     * @param data the data to be predicted
     * @return array of the k highest-scoring output sequences
     */
    public Sequence[] predict(String data) {
        return predict(data, nBest);
    }
    
    /**
     * Return the k highest-scoring sequences for the given data.
     * @param data the data to be predicted
     * @param k the number of answers to return
     * @return array of the k highest-scoring output sequences
     */
    public Sequence[] predict(String data, int k) {
        InstanceList testData = new InstanceList(pipe);
        testData.addThruPipe(new LineGroupIterator(new StringReader(data), Pattern.compile("^\\s*$"), true));
        //System.out.println(testData.get(0).getData());
        return apply(crf, (Sequence)testData.get(0).getData(), k);
    }

    /**
    * Apply a transducer to an input sequence to produce the k highest-scoring
    * output sequences.
    * @param model the Transducer
    * @param input the input sequence
    * @param k the number of answers to return
    * @return array of the k highest-scoring output sequences
    */
    private Sequence[] apply(Transducer model, Sequence input, int k) {
        MaxLatticeDefault lattice = new MaxLatticeDefault(model, input, null, cacheSize);
        return lattice.bestOutputSequences(k).toArray(new Sequence[0]);
    }
}