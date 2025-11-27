package net.buildabrowser.babbrowser.dom.mutable.imp;

import java.util.LinkedList;
import java.util.List;

import net.buildabrowser.babbrowser.dom.Node;
import net.buildabrowser.babbrowser.dom.mutable.MutableNode;
import net.buildabrowser.babbrowser.dom.mutable.MutableNodeList;

public class MutableNodeImp implements MutableNode {

  private List<MutableNode> childNodes = new LinkedList<>();
  private final MutableNodeList nodeList = MutableNodeList.create(childNodes);

  private Object context;

  // TODO: NodeList
  @Override
  public MutableNodeList childNodes() {
    return nodeList;
  }

  @Override
  public Node appendChild(Node node) {
    // TODO: Use the spec method. Also, the case is not so great
    childNodes.add((MutableNode) node);
    return node;
  }

  @Override
  public void setContext(Object context) {
    this.context = context;
  }

  @Override
  public Object getContext() {
    return this.context;
  }
  
}
