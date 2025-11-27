package net.buildabrowser.babbrowser.dom.mutable.imp;

import java.util.HashMap;
import java.util.Map;

import net.buildabrowser.babbrowser.dom.Node;
import net.buildabrowser.babbrowser.dom.mutable.MutableElement;

public class MutableElementImp extends MutableNodeImp implements MutableElement {

  private final Map<String, String> attributes = new HashMap<>();

  private final String name;

  public MutableElementImp(String name) {
    this.name = name;
  }

  public MutableElementImp(String name, Map<String, String> attributes) {
    this.name = name;
    attributes.forEach((k, v) -> this.attributes.put(k, v));
  }

  @Override
  public String name() {
    return this.name;
  }

  @Override
  public Map<String, String> attributes() {
    return this.attributes;
  }

  @Override
  public void addAttribute(String name, String value) {
    attributes.put(name, value);
  }
  
  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder("<");
    builder.append(name);
    for (Map.Entry<String, String> attributePairs: attributes.entrySet()) {
      builder.append(' ');
      builder.append(attributePairs.getKey());
      builder.append("=\"");
      builder.append(attributePairs.getValue());
      builder.append('"');
    }
    builder.append(">");
    for (Node child: childNodes()) {
      builder.append(child.toString());
    }
    builder
      .append("</")
      .append(name)
      .append(">");
    
    return builder.toString();
  }

}
