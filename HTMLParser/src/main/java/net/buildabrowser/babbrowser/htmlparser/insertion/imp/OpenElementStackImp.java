package net.buildabrowser.babbrowser.htmlparser.insertion.imp;

import java.util.Stack;

import net.buildabrowser.babbrowser.dom.algo.StyleAlgos;
import net.buildabrowser.babbrowser.dom.mutable.MutableElement;
import net.buildabrowser.babbrowser.dom.mutable.MutableNode;
import net.buildabrowser.babbrowser.htmlparser.insertion.OpenElementStack;
import net.buildabrowser.babbrowser.htmlparser.insertion.util.ParseElementUtil;

public class OpenElementStackImp implements OpenElementStack {

  private final Stack<MutableNode> stack = new Stack<>();

  @Override
  public void pushNode(MutableNode node) {
    stack.push(node);
  }

  @Override
  public MutableNode peek() {
    return stack.peek();
  }

  @Override
  public MutableNode peek(int pos) {
		return stack.get(stack.size() - pos - 1);
	}

  @Override
  public MutableNode popNode() {
    MutableNode node = stack.pop();
    if (ParseElementUtil.isHTMLElementWithName(node, "style")) {
      StyleAlgos.updateAStyleBlock((MutableElement) node);
    }
    return node;
  }

  @Override
  public int size() {
    return stack.size();
  }
  
}
