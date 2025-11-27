package net.buildabrowser.babbrowser.dom.mutable;

import net.buildabrowser.babbrowser.dom.Node;

public interface MutableNode extends Node {

  MutableDocument ownerDocument();

  void setContext(Object context);

  Object getContext();

}
