package org.pegdown.verbatim;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import net.sourceforge.plantuml.SourceStringReader;
import org.pegdown.ast.ExpImageNode;
import org.pegdown.ast.Node;
import org.pegdown.ast.TextNode;
import org.pegdown.ast.VerbatimNode;

public class UmlVerbatimProcessor implements VerbatimProcessor {

    private File targetDirectory;
    private String relPath;
    private int id = 0;

    public UmlVerbatimProcessor(File targetDirectory, String relPath) {
        super();
        this.targetDirectory = targetDirectory;
        targetDirectory.mkdirs();
        this.relPath = relPath;
    }

    @Override
    public Node process(VerbatimNode verbatimNode) {
        final String name = "uml" + (id++) + ".png";
        try {
            final FileOutputStream result = new FileOutputStream(new File(targetDirectory, name));

            SourceStringReader reader = new SourceStringReader("@startuml\n" + verbatimNode.getText() + "\n@enduml\n" );
            reader.generateImage(result);

            result.close();

            return new ExpImageNode(null, relPath + "/" + name, new TextNode(name));
        } catch (IOException e) {
            e.printStackTrace();
            return new VerbatimNode(verbatimNode.getText() + "\n\n" + e.getMessage(), verbatimNode.getType() + ".error");
        }
    }

}
