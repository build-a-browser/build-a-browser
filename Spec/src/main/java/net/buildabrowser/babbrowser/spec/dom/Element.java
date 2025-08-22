package net.buildabrowser.babbrowser.spec.dom;

import java.util.List;
import java.util.Map;

import net.buildabrowser.babbrowser.spec.dom.imp.ElementImp;

public interface Element extends Node {

  String name();

  List<Node> children();

  Map<String, String> attributes();

  void addAttribute(String name, String value);

  static Element create(String name, List<Node> children) {
    return new ElementImp(name, children);
  }

  static Element create(String name, List<Node> children, Map<String, String> attributes) {
    return new ElementImp(name, children, attributes);
  }

}
