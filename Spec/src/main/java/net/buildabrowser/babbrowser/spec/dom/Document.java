package net.buildabrowser.babbrowser.spec.dom;

import java.util.List;

import net.buildabrowser.babbrowser.spec.dom.imp.DocumentImp;

public interface Document extends Node {

  List<Node> children();
  
  static Document create(List<Node> children) {
    return new DocumentImp(children);
  }

}
