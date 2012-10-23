package org.pegdown.verbatim;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import net.sourceforge.plantuml.cucadiagram.dot.GraphvizUtils;

import org.pegdown.ast.ExpImageNode;
import org.pegdown.ast.Node;
import org.pegdown.ast.TextNode;
import org.pegdown.ast.VerbatimNode;

public class DotVerbatimProcessor implements VerbatimProcessor {

    private File targetDirectory;
    private String relPath;
    private int id = 0;

    public DotVerbatimProcessor(File targetDirectory, String relPath) {
        super();
        this.targetDirectory = targetDirectory;
        targetDirectory.mkdirs();
        this.relPath = relPath;
    }

    @Override
    public Node process(VerbatimNode verbatimNode) {
        final String name = "dot" + (id++) + ".png";
        try {
            final FileOutputStream result = new FileOutputStream(new File(targetDirectory, name));

            GraphvizUtils.create(verbatimNode.getText(), "png").createFile(result);
            result.close();

            return new ExpImageNode(null, relPath + "/" + name, new TextNode(name));
        } catch (IOException e) {
            e.printStackTrace();
            return new VerbatimNode(verbatimNode.getText() + "\n\n" + e.getMessage(), verbatimNode.getType() + ".error");
        } catch (InterruptedException e) {
            e.printStackTrace();
            return new VerbatimNode(verbatimNode.getText() + "\n\n" + e.getMessage(), verbatimNode.getType() + ".error");
        }
    }

}
