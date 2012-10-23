package org.pegdown.verbatim;

import org.pegdown.ast.Node;
import org.pegdown.ast.VerbatimNode;

public interface VerbatimProcessor {
   Node process(VerbatimNode verbatimNode);
}
