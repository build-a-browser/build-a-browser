package net.buildabrowser.babbrowser.dom;

public interface NodeList extends Iterable<Node> {

  Node item(long index);

  long length();

}
