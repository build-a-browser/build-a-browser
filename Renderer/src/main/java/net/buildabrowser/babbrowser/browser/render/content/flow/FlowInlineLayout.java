package net.buildabrowser.babbrowser.browser.render.content.flow;

import java.util.List;
import java.util.Stack;

import net.buildabrowser.babbrowser.browser.render.box.Box;
import net.buildabrowser.babbrowser.browser.render.box.ElementBox;
import net.buildabrowser.babbrowser.browser.render.box.ElementBox.BoxLevel;
import net.buildabrowser.babbrowser.browser.render.box.TextBox;
import net.buildabrowser.babbrowser.browser.render.content.flow.fragment.FlowFragment;
import net.buildabrowser.babbrowser.browser.render.content.flow.fragment.LineBoxFragment;
import net.buildabrowser.babbrowser.browser.render.content.flow.fragment.ManagedBoxFragment;
import net.buildabrowser.babbrowser.browser.render.content.flow.fragment.TextFragment;
import net.buildabrowser.babbrowser.browser.render.content.flow.fragment.UnmanagedBoxFragment;
import net.buildabrowser.babbrowser.browser.render.layout.LayoutConstraint;
import net.buildabrowser.babbrowser.browser.render.layout.LayoutContext;
import net.buildabrowser.babbrowser.browser.render.paint.FontMetrics;

public class FlowInlineLayout {

  private final Stack<InlineFormattingContext> inlineStack = new Stack<>();
  private final FlowRootContent rootContent;

  public FlowInlineLayout(FlowRootContent rootContent) {
    this.rootContent = rootContent;
  }

  public void stopInline() {
    InlineFormattingContext formattingContext = inlineStack.pop();
    BlockFormattingContext blockContext = rootContent.blockLayout().activeContext();
    for (LineBoxFragment fragment: formattingContext.fragments()) {
      positionFragment(fragment, blockContext.currentY());
      blockContext.addFragment(fragment);
      blockContext.minWidth(fragment.width());
      blockContext.increaseY(fragment.height());
    }
  }

  public void startInline() {
    inlineStack.push(new InlineFormattingContext());
  }

  public void addToInline(LayoutContext layoutContext, Box childBox, LayoutConstraint layoutConstraint) {
    switch (childBox) {
      case ElementBox elementBox -> addToInline(layoutContext, elementBox, layoutConstraint);
      case TextBox textBox -> addTextToInline(layoutContext, textBox);
      default -> throw new UnsupportedOperationException("Unknown box type!");
    }
  }

  private void addToInline(LayoutContext layoutContext, ElementBox elementBox, LayoutConstraint layoutConstraint) {
    if (elementBox.boxLevel().equals(BoxLevel.BLOCK_LEVEL)) {
      InlineFormattingContext nextContext = inlineStack.peek().split();
      stopInline();
      rootContent.blockLayout().addToBlock(layoutContext, elementBox, layoutConstraint);
      inlineStack.push(nextContext);
    } else if (FlowUtil.isInFlow(elementBox)) {
      addManagedBlockToInline(layoutContext, elementBox, layoutConstraint);
    } else {
      addUnmanagedBlockToInline(layoutContext, elementBox, layoutConstraint);
    }
  }

  private void addManagedBlockToInline(LayoutContext layoutContext, ElementBox elementBox, LayoutConstraint layoutConstraint) {
    inlineStack.peek().pushElement(elementBox);
    for (Box childBox: elementBox.childBoxes()) {
      addToInline(layoutContext, childBox, layoutConstraint);
    }
    inlineStack.peek().popElement();
  }

  private void addUnmanagedBlockToInline(LayoutContext layoutContext, ElementBox elementBox, LayoutConstraint layoutConstraint) {
    if (!layoutConstraint.isPreLayoutConstraint()) {
      elementBox.content().layout(layoutContext, layoutConstraint);
    }
    int width = elementBox.dimensions().getComputedWidth();
    int height = elementBox.dimensions().getComputedHeight();

    UnmanagedBoxFragment newFragment = new UnmanagedBoxFragment(width, height, elementBox);

    InlineFormattingContext parentContext = inlineStack.peek();
    parentContext.addFragment(newFragment);
  }

  private void addTextToInline(LayoutContext layoutContext, TextBox textBox) {
    FontMetrics fontMetrics = layoutContext.fontMetrics();
    String text = textBox.text().trim();
    if (text.isBlank()) return;

    int width = fontMetrics.stringWidth(text);
    int height = fontMetrics.fontHeight();
    TextFragment newFragment = new TextFragment(width, height, text);

    InlineFormattingContext parentContext = inlineStack.peek();
    parentContext.addFragment(newFragment);
  }
  
  private void positionFragment(LineBoxFragment fragment, int y) {
    fragment.setPos(0, y);
    positionFragmentElements(fragment.fragments());
  }

  private void positionFragmentElements(List<FlowFragment> fragments) {
    int x = 0;
    for (FlowFragment child: fragments) {
      child.setPos(x, 0);
      x += child.width();
      if (child instanceof ManagedBoxFragment managedBoxFragment) {
        positionFragmentElements(managedBoxFragment.fragments());
      }
    }
  }

}
