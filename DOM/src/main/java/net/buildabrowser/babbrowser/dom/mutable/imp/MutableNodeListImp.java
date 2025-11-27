package net.buildabrowser.babbrowser.dom.mutable.imp;

import java.util.Iterator;
import java.util.List;

import net.buildabrowser.babbrowser.dom.Node;
import net.buildabrowser.babbrowser.dom.mutable.MutableNode;
import net.buildabrowser.babbrowser.dom.mutable.MutableNodeList;

public class MutableNodeListImp implements MutableNodeList {

  private final List<MutableNode> nodes;

  public MutableNodeListImp(List<MutableNode> nodes) {
    this.nodes = nodes;
  }

  @Override
  public long length() {
    return nodes.size();
  }

  @Override
  public Iterator<Node> iterator() {
    Iterator<MutableNode> it = nodes.iterator();
    return new Iterator<Node>() {
      @Override
      public boolean hasNext() {
        return it.hasNext();
      }

      @Override
      public Node next() {
        return it.next();
      }
    };
  }

  @Override
  public MutableNode item(long index) {
    return nodes.get((int) index);
  }
  
}
