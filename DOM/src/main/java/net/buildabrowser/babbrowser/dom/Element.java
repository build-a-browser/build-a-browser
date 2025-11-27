package net.buildabrowser.babbrowser.dom;

import java.util.Map;

public interface Element extends Node {

  String name();

  Map<String, String> attributes();

  void addAttribute(String name, String value);

}
