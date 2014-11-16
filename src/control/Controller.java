package control;

import java.io.File;
import java.io.IOException;
import model.ArticleHeader;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import pdfTextExtraction.util.TextUtils;
import view.MetadataGUI;

/**
 * Controller class for the GUI.
 * @author Peter Sandor
 */
public class Controller {
    
    private static final Logger LOGGER = LogManager.getLogger(Controller.class.getName());
    
    private final MetadataGUI view;
    private CRFPredictor crfPredictor;
    
    /**
     * Creates a new instance by binding the GUI reference and creating the 
     * CRF predictor to be used for header tagging.
     * @param view the GUI to control
     */
    public Controller(MetadataGUI view) {
        this.view = view;
        try {
            this.crfPredictor = new CRFPredictor();
        } catch (IOException | ClassNotFoundException ex) {
            LOGGER.error("CRF model loading error.", ex);
            view.showErrorDialog("The CRF model could not be loaded.", true);
        }
    }
    
    /**
     * Tags an article header using a trained CRF predictor.
     * @param pdfFile the path of the XML article
     */
    public void predictHeader(String pdfFile) {
        try {
            if (!pdfFile.endsWith(".pdf")) {
                pdfFile += ".pdf";
            }
            if (!new File(pdfFile).exists()) {
                view.showErrorDialog("The file was not found.", false);
                return;
            }
            view.fillMetadata(crfPredictor.predictHeader(TextUtils.getHeader(pdfFile)));
        } catch (IOException ex) {
            LOGGER.error("Error at pdf file processing.",ex);
            view.showErrorDialog("The file cannot be processed.", false);
        }
    }
    
    /**
     * Saves a tagged article header into an XML file.
     * @param xmlFileName the path of the XML file
     * @param header the header to be stored
     */
    public void saveToXML(String xmlFileName, ArticleHeader header) {
        try {
            if (!xmlFileName.endsWith(".xml")) {
                xmlFileName += ".xml";
            }
            XMLWriter.saveArticle(header, xmlFileName);
        } catch (IOException ex) {
            LOGGER.error("Error at xml file processing.",ex);
            view.showErrorDialog("A problem occrued while saving to XML.", false);
        }
    }
}