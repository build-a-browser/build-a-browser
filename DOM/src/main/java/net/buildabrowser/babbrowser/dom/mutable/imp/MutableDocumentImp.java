package net.buildabrowser.babbrowser.dom.mutable.imp;

import net.buildabrowser.babbrowser.cssbase.cssom.mutable.MutableStyleSheetList;
import net.buildabrowser.babbrowser.dom.Node;
import net.buildabrowser.babbrowser.dom.mutable.DocumentChangeListener;
import net.buildabrowser.babbrowser.dom.mutable.MutableDocument;

public class MutableDocumentImp extends MutableNodeImp implements MutableDocument {

  private final MutableStyleSheetList styleSheets = MutableStyleSheetList.create();
  private final DocumentChangeListener changeListener;


  public MutableDocumentImp(DocumentChangeListener changeListener) {
    this.changeListener = changeListener;
  }

  @Override
  public MutableStyleSheetList styleSheets() {
    return this.styleSheets;
  }

  @Override
  public void onNodeAdded(Node node) {
    changeListener.onNodeAdded(node);
  }

  @Override
  public void onNodeRemoved(Node node) {
    changeListener.onNodeRemoved(node);
  }
  
  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    for (Node child: childNodes()) {
      builder.append(child.toString());
    }
    
    return builder.toString();
  }

}
