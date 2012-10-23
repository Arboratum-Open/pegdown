package org.pegdown;

import java.io.File;

import org.pegdown.verbatim.DotVerbatimProcessor;
import org.pegdown.verbatim.UmlVerbatimProcessor;
import org.testng.annotations.Test;

public class VerbatimProcessorTest extends AbstractTest {
    private PegDownProcessor processor;

    @Override
    protected PegDownProcessor getProcessor() {
        return processor;
    }

    @Test
    public void test() throws Exception {
        processor = new PegDownProcessor(Extensions.FENCED_CODE_BLOCKS);
        processor.registerVerbatimProcessor("dot", new DotVerbatimProcessor(new File("verbatim/images/"), "../images/"));
        processor.registerVerbatimProcessor("uml", new UmlVerbatimProcessor(new File("verbatim/images/"), "../images/"));
        test("verbatim/dot_Fenced_Code_Blocks");
        test("verbatim/uml_Fenced_Code_Blocks");
    }


}
