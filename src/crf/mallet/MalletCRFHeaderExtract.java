package crf.mallet;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.regex.Pattern;

import cc.mallet.pipe.Pipe;
import cc.mallet.pipe.SerialPipes;
import cc.mallet.pipe.SimpleTaggerSentence2TokenSequence;
import cc.mallet.pipe.TokenSequence2FeatureVectorSequence;
import cc.mallet.pipe.tsf.LexiconMembership;
import cc.mallet.pipe.tsf.OffsetConjunctions;
import cc.mallet.pipe.tsf.RegexMatches;
import crf.mallet.features.LineNamePercentage;
import crf.mallet.features.LineTokenCount;
import crf.mallet.features.LineNumber;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * Generates specific features for header meta data extraction for a CRF model.
 * @author Peter Sandor
 */
public class MalletCRFHeaderExtract extends MalletCRF {
    
    private static final Logger LOGGER = LogManager.getLogger(MalletCRFHeaderExtract.class.getName());
    
    @Override
    public void generateFeatures() {
        ArrayList<Pipe> pipes = new ArrayList<>();

        //Create new features from all possible conjunctions with other features
        //{0, 1, 2} means a conjunction for the current and the next two features
        //{0, -1, -2} means a conjunction for the current and the previous two features
        int[][] conjunctions = {new int[] {0, 1, 2},
                                new int[] {0, -1, -2}};

        pipes.add(new SimpleTaggerSentence2TokenSequence());
		pipes.add(new OffsetConjunctions(conjunctions));
        
        //regular expression based features
        pipes.add(new RegexMatches("INITCAPS", Pattern.compile("\\p{Lu}.*"))); 
        pipes.add(new RegexMatches("ALLCAPS", Pattern.compile("\\p{Lu}+")));
        pipes.add(new RegexMatches("INITDIGIT", Pattern.compile("^[0-9].*")));
        pipes.add(new RegexMatches("HASDIGIT", Pattern.compile(".*[0-9].*")));
        pipes.add(new RegexMatches("SINGLEDIGIT", Pattern.compile("[0-9]")));
        pipes.add(new RegexMatches("HASDASH", Pattern.compile(".*-.*")));
        pipes.add(new RegexMatches("INITDASH", Pattern.compile("-.*")));
        pipes.add(new RegexMatches("ENDSDASH", Pattern.compile(".*-")));
		pipes.add(new RegexMatches("INITPUNCT", Pattern.compile("^\\p{Punct}.*")));
        pipes.add(new RegexMatches("ENDSPUNCT", Pattern.compile(".*\\p{Punct}")));        
        
        pipes.add(new RegexMatches("EMAIL", Pattern.compile("[ \\w._%+-,]+@([\\w.-]+\\.)*[A-Za-z]{2,4}")));
        pipes.add(new RegexMatches("WEBADDRESS", Pattern.compile("(http\\:\\/\\/|https\\:\\/\\/){0,1}([\\w][\\w\\-]*\\.)+[\\w]{2,3}")));
        pipes.add(new RegexMatches("YEAR", Pattern.compile("19[0-9][0-9]|20[0-9][0-9]")));
        
        //features based on appearance in a lexicon
        try {
            pipes.add(new LexiconMembership("LEX_ABSTRACT", new File("lexicons/abstract.txt"), true));
            pipes.add(new LexiconMembership("LEX_ADDRESS", new File("lexicons/address.txt"), true));
            pipes.add(new LexiconMembership("LEX_AFFILIATION", new File("lexicons/affiliation.txt"), true));
            pipes.add(new LexiconMembership("LEX_CITY", new File("lexicons/city.txt"), true));
            pipes.add(new LexiconMembership("LEX_COUNTRY", new File("lexicons/country.txt"), true));
            pipes.add(new LexiconMembership("LEX_EMAIL", new File("lexicons/email.txt"), true));
            pipes.add(new LexiconMembership("LEX_ABSTRACT", new File("lexicons/abstract.txt"), true));
            pipes.add(new LexiconMembership("LEX_KEYWORD", new File("lexicons/keyword.txt"), true));
            pipes.add(new LexiconMembership("LEX_MONTH", new File("lexicons/month.txt"), true));
            pipes.add(new LexiconMembership("LEX_NAME", new File("lexicons/name.txt"), false));
            pipes.add(new LexiconMembership("LEX_PHONE", new File("lexicons/phone.txt"), true));
            pipes.add(new LexiconMembership("LEX_UNIVERSITY", new File("lexicons/university.txt"), true));
            pipes.add(new LexiconMembership("LEX_WEB", new File("lexicons/web.txt"), true));
        } catch (FileNotFoundException ex) {
            LOGGER.error("Lexicon not found.", ex);
        }

        //number based features
        pipes.add(new LineNumber());
        pipes.add(new LineTokenCount());
        pipes.add(new LineNamePercentage());

        pipes.add(new TokenSequence2FeatureVectorSequence());

        pipe = new SerialPipes(pipes);
    }
}