package control;

import cc.mallet.types.Sequence;
import crf.mallet.MalletCRFHeaderExtract;
import java.io.IOException;
import java.util.List;
import model.ArticleHeader;
import pdfTextExtraction.layout.Line;
import pdfTextExtraction.util.TextUtils;
import util.PropertyProvider;

/**
 * Uses a trained CRF model to extract and tag the header of a PDF article.
 * @author Peter Sandor
 */
public class CRFPredictor {
    
    private final MalletCRFHeaderExtract crf;
    
    /**
     * Creates a new instance and loads a trained CRF model.
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public CRFPredictor() throws IOException, ClassNotFoundException {
        crf = new MalletCRFHeaderExtract();
        crf.loadModel(PropertyProvider.INSTANCE.getProperty("crfModel"));                
    }

    /**
     * Tags an article header by predicting labels for it's tokens.
     * @param lines the lines of the header
     * @return the predicted meta data
     */
    public ArticleHeader predictHeader(List<Line> lines) {
        //create a hash map to store the predicted text for each label
        ArticleHeader predictions = new ArticleHeader();
        
        StringBuilder textBuilder = new StringBuilder();
        for (Line line : lines) {
            //mark the end of each line with a "+L+" sequence for the CRF tagger
            textBuilder.append(line.getText().concat(" +L+ "));
        }
        
        String text = textBuilder.toString();
        String[] tokens = text.split(" ");
        Sequence[] labels = crf.predict(text.replace(' ', '\n'));
        //
        int maxFontSize = TextUtils.maxFontSize(lines);
        String labelBeforeTitle = "other"; //the first predicted label before the title
        String labelAfterTitle = "author"; //the first predicted label after the title        
        
        for (int i = 0; i < labels.length - 1; i++) {
            String label = labels[0].get(i).toString();
            String nextLabel = labels[0].get(i+1).toString();
            if (!label.equals("title") && nextLabel.equals("title")) {
                labelBeforeTitle = label;
            }
            if (label.equals("title") && !nextLabel.equals("title")) {
                labelAfterTitle = nextLabel;
            }
        }
        
        int lineCount = 0;
        //iterate through the predicted labels
        for (int j=0; j<labels[0].size(); j++) {
            String label = labels[0].get(j).toString();
            if (tokens[j].equals("+L+")) {
                lineCount++;
            } else {
                //if the token is part of a line with the biggest fontsize in 
                //the header, it's supposed to be labeled as 'title'
                if (lines.get(lineCount).getFontSize() == maxFontSize) {
                    label = "title";
                }

                //if the token is not part of a line with the biggest fontsize
                //but it was labeled as 'title' it's supposed to be a mistake
                if (lines.get(lineCount).getFontSize() != maxFontSize && label.equals("title")) {
                    //check that the title has already been found or not
                    if (predictions.getValue("title").equals("")) {
                        label = labelBeforeTitle;
                    } else {
                        label = labelAfterTitle;
                    }
                }
                
                //correction for e-mail addresses
                if (tokens[j].matches("[ \\w._%+-,()]+@([\\w.-]+\\.)*[A-Za-z]{2,4}")) {
                    label = "email";
                }

                predictions.setValue(label, predictions.getValue(label).concat(tokens[j] + " "));
            }
        }
        
        return predictions;
    }
}