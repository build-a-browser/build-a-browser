package net.buildabrowser.babbrowser.dom;

import net.buildabrowser.babbrowser.cssbase.cssom.DocumentOrShadowRoot;

public interface Document extends Node, DocumentOrShadowRoot {

  // Non-spec

  void onNodeAdded(Node node);

  void onNodeRemoved(Node node);

}
