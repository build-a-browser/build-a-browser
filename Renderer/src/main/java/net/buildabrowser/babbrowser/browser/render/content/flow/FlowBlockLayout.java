package net.buildabrowser.babbrowser.browser.render.content.flow;

import java.util.Stack;

import net.buildabrowser.babbrowser.browser.render.box.Box;
import net.buildabrowser.babbrowser.browser.render.box.ElementBox;
import net.buildabrowser.babbrowser.browser.render.content.flow.fragment.ManagedBoxFragment;
import net.buildabrowser.babbrowser.browser.render.content.flow.fragment.UnmanagedBoxFragment;
import net.buildabrowser.babbrowser.browser.render.layout.LayoutConstraint;
import net.buildabrowser.babbrowser.browser.render.layout.LayoutContext;

public class FlowBlockLayout {

  private final Stack<BlockFormattingContext> blockStack = new Stack<>();
  private final FlowRootContent rootContent;
  
  private BlockFormattingContext rootContext;

  public FlowBlockLayout(FlowRootContent rootContent) {
    this.rootContent = rootContent;
  }

  public void reset(ElementBox rootBox) {
    this.rootContext = new BlockFormattingContext(rootBox);
    blockStack.clear();
    blockStack.add(rootContext);
  }

  public ManagedBoxFragment close() {
    return rootContext.close();
  }

  public BlockFormattingContext activeContext() {
    return blockStack.peek();
  }
  
  public void addChildrenToBlock(LayoutContext layoutContext, ElementBox box, LayoutConstraint layoutConstraint) {
    FlowInlineLayout inlineLayout = rootContent.inlineLayout();

    boolean isInInline = false;
    for (Box childBox: box.childBoxes()) {
      if (FlowUtil.isBlockLevel(childBox)) {
        if (isInInline) {
          inlineLayout.stopInline();
          isInInline = false;
        }
        addToBlock(layoutContext, (ElementBox) childBox, layoutConstraint);
      } else {
        if (!isInInline) {
          inlineLayout.startInline();
          isInInline = true;
        }
        inlineLayout.addToInline(layoutContext, childBox, layoutConstraint);
      }
    }

    if (isInInline) {
      inlineLayout.stopInline();
    }
  }

  public void addToBlock(LayoutContext layoutContext, ElementBox elementBox, LayoutConstraint layoutConstraint) {
    if (FlowUtil.isInFlow(elementBox)) {
      addManagedBlockToBlock(layoutContext, elementBox, layoutConstraint);
    } else {
      addUnmanagedBlockToBlock(layoutContext, elementBox, layoutConstraint);
    }
  }

  private void addManagedBlockToBlock(LayoutContext layoutContext, ElementBox childBox, LayoutConstraint layoutConstraint) {
    BlockFormattingContext childContext = new BlockFormattingContext(childBox);
    blockStack.push(childContext);
    addChildrenToBlock(layoutContext, childBox, layoutConstraint);
    ManagedBoxFragment newFragment = childContext.close();
    blockStack.pop();

    BlockFormattingContext parentContext = blockStack.peek();
    newFragment.setPos(0, parentContext.currentY());

    parentContext.increaseY(newFragment.height());
    parentContext.minWidth(newFragment.width());
    parentContext.addFragment(newFragment);
  }

  private void addUnmanagedBlockToBlock(LayoutContext layoutContext, ElementBox elementBox, LayoutConstraint layoutConstraint) {
    if (!layoutConstraint.isPreLayoutConstraint()) {
      elementBox.content().layout(layoutContext, layoutConstraint);
    }
    int width = FlowUtil.constraintWidth(elementBox.dimensions(), layoutConstraint);
    int height = elementBox.dimensions().getComputedHeight();

    UnmanagedBoxFragment newFragment = new UnmanagedBoxFragment(width, height, elementBox);

    BlockFormattingContext parentContext = blockStack.peek();
    newFragment.setPos(0, parentContext.currentY());

    parentContext.increaseY(height);
    parentContext.minWidth(width);
    parentContext.addFragment(newFragment);
  }

}
