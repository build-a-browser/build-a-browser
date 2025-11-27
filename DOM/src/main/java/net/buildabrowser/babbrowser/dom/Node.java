package net.buildabrowser.babbrowser.dom;

public interface Node {
  
  Document ownerDocument();

  NodeList childNodes();

  Node appendChild(Node node);

}
