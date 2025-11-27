package net.buildabrowser.babbrowser.htmlparser.insertion;

import net.buildabrowser.babbrowser.dom.mutable.MutableNode;
import net.buildabrowser.babbrowser.htmlparser.insertion.imp.OpenElementStackImp;

public interface OpenElementStack {
  
  void pushNode(MutableNode node);

  MutableNode peek();

  MutableNode peek(int pos);

  MutableNode popNode();

  int size();

  static OpenElementStack create() {
    return new OpenElementStackImp();
  }

}
