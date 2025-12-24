package net.buildabrowser.babbrowser.browser.render.content.flow;

import java.util.List;
import java.util.Stack;

import net.buildabrowser.babbrowser.browser.render.box.Box;
import net.buildabrowser.babbrowser.browser.render.box.ElementBox;
import net.buildabrowser.babbrowser.browser.render.box.ElementBox.BoxLevel;
import net.buildabrowser.babbrowser.browser.render.box.ElementBoxDimensions;
import net.buildabrowser.babbrowser.browser.render.box.TextBox;
import net.buildabrowser.babbrowser.browser.render.content.flow.InlineStagingArea.ManagedBoxEntryMarker;
import net.buildabrowser.babbrowser.browser.render.content.flow.InlineStagingArea.ManagedBoxExitMarker;
import net.buildabrowser.babbrowser.browser.render.content.flow.InlineStagingArea.StagedBlockLevelBox;
import net.buildabrowser.babbrowser.browser.render.content.flow.InlineStagingArea.StagedText;
import net.buildabrowser.babbrowser.browser.render.content.flow.InlineStagingArea.StagedUnmanagedBox;
import net.buildabrowser.babbrowser.browser.render.content.flow.fragment.FlowFragment;
import net.buildabrowser.babbrowser.browser.render.content.flow.fragment.LineBoxFragment;
import net.buildabrowser.babbrowser.browser.render.content.flow.fragment.ManagedBoxFragment;
import net.buildabrowser.babbrowser.browser.render.content.flow.fragment.TextFragment;
import net.buildabrowser.babbrowser.browser.render.content.flow.fragment.UnmanagedBoxFragment;
import net.buildabrowser.babbrowser.browser.render.layout.LayoutConstraint;
import net.buildabrowser.babbrowser.browser.render.layout.LayoutConstraint.LayoutConstraintType;
import net.buildabrowser.babbrowser.browser.render.layout.LayoutContext;
import net.buildabrowser.babbrowser.browser.render.layout.LayoutUtil;
import net.buildabrowser.babbrowser.browser.render.paint.FontMetrics;
import net.buildabrowser.babbrowser.css.engine.property.CSSProperty;
import net.buildabrowser.babbrowser.css.engine.property.whitespace.WhitespaceCollapseValue;
import net.buildabrowser.babbrowser.css.engine.styles.ActiveStyles;

public class FlowInlineLayout {

  private final Stack<InlineFormattingContext> inlineStack = new Stack<>();
  private final FlowRootContent rootContent;

  public FlowInlineLayout(FlowRootContent rootContent) {
    this.rootContent = rootContent;
  }

  public void stopInline(
    LayoutContext layoutContext,
    LayoutConstraint widthConstraint,
    LayoutConstraint heightConstraint,
    ActiveStyles parentStyles
  ) {
    LineWhitespaceCollapser.collapseWhitespace(
      inlineStack.peek().stagingArea(),
      (WhitespaceCollapseValue) parentStyles.getProperty(CSSProperty.WHITE_SPACE_COLLAPSE));
    addStagedElements(layoutContext, widthConstraint, heightConstraint);
    stopInlineUnstaged(layoutContext, widthConstraint, heightConstraint);
  }

  public void startInline() {
    inlineStack.push(new InlineFormattingContext());
  }

  public void stageInline(Box box) {
    InlineStagingArea stagingArea = inlineStack.peek().stagingArea();
    if (box instanceof TextBox textBox) {
      stagingArea.pushStagedElement(new StagedText(textBox, textBox.text()));
    } else if (box instanceof ElementBox elementBox) {
      if (elementBox.boxLevel().equals(BoxLevel.BLOCK_LEVEL)) {
        stagingArea.pushStagedElement(new StagedBlockLevelBox(elementBox));
      } else if (!FlowUtil.isInFlow(elementBox)) {
        stagingArea.pushStagedElement(new StagedUnmanagedBox(elementBox));
      } else {
        stagingArea.pushStagedElement(new ManagedBoxEntryMarker(elementBox));
        for (Box childBox: elementBox.childBoxes()) {
          stageInline(childBox);
        }
        stagingArea.pushStagedElement(new ManagedBoxExitMarker(elementBox));
      }
    } else {
      throw new UnsupportedOperationException("Unknown box type!");
    }
  }

  private void addStagedElements(LayoutContext layoutContext, LayoutConstraint widthConstraint, LayoutConstraint heightConstraint) {
    InlineStagingArea stagingArea = inlineStack.peek().stagingArea();
    stagingArea.resetCursor();
    while (!stagingArea.done()) {
      switch (stagingArea.next()) {
        case StagedText stagedText -> addTextToInline(layoutContext, stagedText);
        case StagedUnmanagedBox stagedUnmanagedBox -> addUnmanagedBlockToInline(
          layoutContext, stagedUnmanagedBox.elementBox(), widthConstraint, heightConstraint);
        case StagedBlockLevelBox stagedBlockLevelBox -> addBlockLevelToInline(
          layoutContext, stagedBlockLevelBox.elementBox(), widthConstraint, heightConstraint);
        case ManagedBoxEntryMarker marker -> inlineStack.peek().pushElement(marker.elementBox());
        case ManagedBoxExitMarker _ -> inlineStack.peek().popElement();
        default -> throw new UnsupportedOperationException("Unknown staging element type");
      }
    }
  }

  private void stopInlineUnstaged(
    LayoutContext layoutContext,
    LayoutConstraint widthConstraint,
    LayoutConstraint heightConstraint
  ) {
    InlineFormattingContext formattingContext = inlineStack.pop();
    BlockFormattingContext blockContext = rootContent.blockLayout().activeContext();
    for (LineBoxFragment fragment: formattingContext.fragments()) {
      positionFragment(fragment, blockContext.currentY());
      blockContext.addFragment(fragment);
      blockContext.minWidth(fragment.width());
      blockContext.increaseY(fragment.height());
    }
  }

  private void addBlockLevelToInline(
    LayoutContext layoutContext,
    ElementBox elementBox,
    LayoutConstraint widthConstraint,
    LayoutConstraint heightConstraint
  ) {
    InlineFormattingContext nextContext = inlineStack.peek().split();
    stopInlineUnstaged(layoutContext, widthConstraint, heightConstraint);
    rootContent.blockLayout().addToBlock(
      layoutContext, elementBox, widthConstraint, heightConstraint);
    inlineStack.push(nextContext);
  }

  private void addUnmanagedBlockToInline(
    LayoutContext layoutContext,
    ElementBox childBox,
    LayoutConstraint parentWidthConstraint,
    LayoutConstraint parentHeightConstraint
  ) {
    ActiveStyles childStyles = childBox.activeStyles();
    LayoutConstraint childWidthConstraint = childBox.isReplaced() ?
      FlowWidthUtil.determineBlockReplacedWidth(
        layoutContext, parentWidthConstraint, childStyles, childBox.dimensions()) :
      determineInlineBlockNonReplacedWidth(
        layoutContext, parentWidthConstraint, childStyles, childBox.dimensions());
    LayoutConstraint childHeightContraint = childBox.isReplaced() ?
      FlowHeightUtil.evaluateReplacedBlockHeight(
        layoutContext, parentHeightConstraint, childWidthConstraint,
        childStyles, childBox.dimensions()) :
      FlowHeightUtil.evaluateNonReplacedBlockLevelHeight(
        layoutContext, parentHeightConstraint, childStyles);

    if (!parentWidthConstraint.isPreLayoutConstraint()) {
      childBox.content().layout(layoutContext, childWidthConstraint, childHeightContraint);
    }

    int width = LayoutUtil.constraintOrDim(childWidthConstraint, childBox.dimensions().getComputedWidth());
    int height = childBox.dimensions().getComputedHeight();

    UnmanagedBoxFragment newFragment = new UnmanagedBoxFragment(width, height, childBox);

    InlineFormattingContext parentContext = inlineStack.peek();
    parentContext.addFragment(newFragment);
  }

  private void addTextToInline(LayoutContext layoutContext, StagedText stagedText) {
    FontMetrics fontMetrics = layoutContext.fontMetrics();
    String text = stagedText.currentText();
    if (text.isEmpty()) return;

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

  private LayoutConstraint determineInlineBlockNonReplacedWidth(
    LayoutContext layoutContext,
    LayoutConstraint parentConstraint,
    ActiveStyles childStyles,
    ElementBoxDimensions boxDimensions
  ) {
    LayoutConstraint baseWidth = FlowWidthUtil.evaluateBaseSize(
      layoutContext, parentConstraint, childStyles.getProperty(CSSProperty.WIDTH), childStyles);
    
    if (!baseWidth.type().equals(LayoutConstraintType.AUTO)) {
      return baseWidth;
    }

    if (parentConstraint.isPreLayoutConstraint()) {
      return parentConstraint;
    }

    int preferredMinWidth = boxDimensions.preferredMinWidthConstraint();
    int preferredWidth = boxDimensions.preferredWidthConstraint();
    int availableWidth = parentConstraint.value();
    if (!parentConstraint.type().equals(LayoutConstraintType.BOUNDED)) {
      return LayoutConstraint.of(preferredWidth);
    }

    return LayoutConstraint.of(Math.min(Math.max(preferredMinWidth, availableWidth), preferredWidth));
  }

}
