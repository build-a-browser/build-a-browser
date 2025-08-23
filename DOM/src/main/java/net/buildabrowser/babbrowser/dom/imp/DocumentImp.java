package net.buildabrowser.babbrowser.dom.imp;

import java.util.List;

import net.buildabrowser.babbrowser.dom.Document;
import net.buildabrowser.babbrowser.dom.Node;

public record DocumentImp(List<Node> children) implements Document {
  
  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    for (Node child: children) {
      builder.append(child.toString());
    }
    
    return builder.toString();
  }

}
