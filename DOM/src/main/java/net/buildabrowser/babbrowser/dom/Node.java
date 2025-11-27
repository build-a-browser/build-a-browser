package net.buildabrowser.babbrowser.dom;

public interface Node {
  
  public NodeList childNodes();

  public Node appendChild(Node node);

  // Non-spec

  void setContext(Object context);

  Object getContext();

}
