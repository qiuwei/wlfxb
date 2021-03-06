package eu.clarin.weblicht.wlfxb.io;

import eu.clarin.weblicht.wlfxb.md.xb.MetaData;
import eu.clarin.weblicht.wlfxb.tc.api.SentencesLayer;
import eu.clarin.weblicht.wlfxb.tc.api.Token;
import eu.clarin.weblicht.wlfxb.tc.api.TokensLayer;
import eu.clarin.weblicht.wlfxb.tc.xb.TextCorpusStored;
import eu.clarin.weblicht.wlfxb.xb.WLData;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;

/**
 * @author Yana Panchenko
 *
 */
//TODO so that it tests all layers, and so that all layers be in separate tests
public class WLDObjectorTest {

    @Rule
    public TemporaryFolder testFolder = new TemporaryFolder();

	private static final String INPUT_FILE_Textcorpus = "/data/objector/input_textcorpus.xml";
	private static final String INPUT_FILE_Lexicon = "/data/objector/input_lexicon.xml";
	private static final String OUTPUT_FILE_1 = "wld-output.xml";
	private static final String OUTPUT_FILE_2 = "wld-min-prefix-output.xml";

    public WLDObjectorTest() {
    }

    @Test
    public void testRead_Textcorpus() throws Exception {
        InputStream is = this.getClass().getResourceAsStream(INPUT_FILE_Textcorpus);
        testRead(is);
    }

    @Test
    public void testRead_Lexicon() throws Exception {
        InputStream is = this.getClass().getResourceAsStream(INPUT_FILE_Lexicon);
        testRead(is);
    }

    @Test
    public void testWrite_File() throws Exception {
        System.out.println("write");
        File file = testFolder.newFile(OUTPUT_FILE_1);
        WLData data = createWLTestData();
        WLDObjector.write(data, file);
    }

    @Test
    public void testWriteUsingMinimumNsPrefixes_File() throws Exception {
        System.out.println("write");
        File file = testFolder.newFile(OUTPUT_FILE_2);
        WLData data = createWLTestData();
        WLDObjector.write(data.getMetaData(), data.getTextCorpus(), file, false);
    }

    private void testRead(InputStream is) throws Exception {
        System.out.println("read");
        WLData wld = WLDObjector.read(is);
        System.out.println(" --- " + wld.getMetaData());
        System.out.println(" --- " + wld.getExternalData());
        System.out.println(" --- " + wld.getTextCorpus());
        System.out.println(" --- " + wld.getLexicon());
    }

    private WLData createWLTestData() {
        MetaData md = createTestMetadata();
        TextCorpusStored tc = createTestTextCorpus();
        WLData data = new WLData(md, tc);
        return data;
    }

    private TextCorpusStored createTestTextCorpus() {
        TextCorpusStored textCorpus = new TextCorpusStored("en");
        String text = "This is a test. This is the second sentence."; // 0-15, 16-44
        textCorpus.createTextLayer().addText(text);
        TokensLayer tokensLayer = textCorpus.createTokensLayer();
        String[] tokenStrings = "This is a test . This is the second sentence .".split(" ");
        List<Token> tokens = new ArrayList<Token>();
        for (String tokenString : tokenStrings) {
            Token token = tokensLayer.addToken(tokenString);
            tokens.add(token);
        }
        SentencesLayer sentencesLayer = textCorpus.createSentencesLayer();
        sentencesLayer.addSentence(tokens.subList(0, 5), 0, 15);
        sentencesLayer.addSentence(tokens.subList(5, 11), 16, 44);
        return textCorpus;
    }

    private MetaData createTestMetadata() {
        MetaData md = new MetaData();
        //data.metaData.source = "Tuebingen Uni";
        md.addMetaDataItem("title", "binding test");
        md.addMetaDataItem("author", "Yana");
        return md;
    }
}
