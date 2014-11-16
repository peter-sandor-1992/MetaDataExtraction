package control;

import java.io.FileWriter;
import java.io.IOException;
import model.ArticleHeader;

/**
 * Stores article meta data into an XML file.
 * @author Peter Sandor
 */
public class XMLWriter {

    /**
     * Saves an article header meta data to an XML file. The saved header will
     * have a structure similar to the cora header corpus: it starts with a 
     * "NEW_HEADER" tag and continues with the text values between tags of their labels.
     * @param header the article header to be saved
     * @param fileName the path of the XML file
     * @throws IOException
     */
    public static void saveArticle(ArticleHeader header, String fileName) throws IOException {
        try (FileWriter writer = new FileWriter(fileName, true)) {
            writer.write("<NEW_HEADER>\n");
            for (String label : header.getLabels()) {
                String text = header.getValue(label);
                if (!text.equals("")) {
                    writer.write("<".concat(label).concat(">"));
                    writer.write(text);
                    writer.write("</".concat(label).concat(">\n"));
                }
            }
            writer.write("\n");
            writer.flush();
        }
    }
}