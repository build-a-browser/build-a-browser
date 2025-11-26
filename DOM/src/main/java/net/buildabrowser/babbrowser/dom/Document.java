package net.buildabrowser.babbrowser.dom;

import java.util.List;

import net.buildabrowser.babbrowser.cssbase.cssom.DocumentOrShadowRoot;
import net.buildabrowser.babbrowser.cssbase.cssom.StyleSheetList;
import net.buildabrowser.babbrowser.dom.imp.DocumentImp;

public interface Document extends Node, DocumentOrShadowRoot {

  List<Node> children();

  static Document create(List<Node> children) {
    return new DocumentImp(children, StyleSheetList.create(List.of()));
  }

  static Document create(List<Node> children, StyleSheetList styleSheetList) {
    return new DocumentImp(children, styleSheetList);
  }

}
