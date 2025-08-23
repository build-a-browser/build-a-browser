package net.buildabrowser.babbrowser.dom.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.buildabrowser.babbrowser.dom.Element;
import net.buildabrowser.babbrowser.dom.Node;

public record ElementImp(String name, List<Node> children, Map<String, String> attributes) implements Element {

  public ElementImp(String name, List<Node> children) {
    this(name, children, new HashMap<>(1));
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
    for (Node child: children) {
      builder.append(child.toString());
    }
    builder
      .append("</")
      .append(name)
      .append(">");
    
    return builder.toString();
  }

}
