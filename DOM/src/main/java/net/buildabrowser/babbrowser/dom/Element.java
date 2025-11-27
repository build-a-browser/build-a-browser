package net.buildabrowser.babbrowser.dom;

import java.util.Map;

public interface Element extends Node {

  String name();

  String namespace();

  Map<String, String> attributes();

  void addAttribute(String name, String value);

}
