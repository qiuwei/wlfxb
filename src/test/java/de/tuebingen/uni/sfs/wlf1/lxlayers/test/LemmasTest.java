/**
 *
 */
package de.tuebingen.uni.sfs.wlf1.lxlayers.test;

import de.tuebingen.uni.sfs.wlf1.lx.api.LemmasLayer;
import de.tuebingen.uni.sfs.wlf1.lx.xb.LemmasLayerStored;
import de.tuebingen.uni.sfs.wlf1.test.utils.TestUtils;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Yana Panchenko
 *
 */
public class LemmasTest {

    private static final String INPUT = "/data/lx-lemmas/layer-input.xml";
    private static final String OUTPUT = "/tmp/layer-output.xml";

    @Test
    public void testReadAndWriteBack() throws Exception {

        InputStream is = this.getClass().getResourceAsStream(INPUT);
        OutputStream os = new FileOutputStream(OUTPUT);


        LemmasLayer layer = TestUtils.read(LemmasLayerStored.class, is);
        System.out.println(layer);
        TestUtils.write(layer, os);

        is.close();
        os.close();

        Assert.assertEquals(9, layer.size());
        Assert.assertEquals("Peter", layer.getLemma(0).getString());
        Assert.assertEquals(".", layer.getLemma(layer.size() - 1).getString());

    }
}
