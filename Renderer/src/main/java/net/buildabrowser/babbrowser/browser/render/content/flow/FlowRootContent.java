package net.buildabrowser.babbrowser.browser.render.content.flow;

import net.buildabrowser.babbrowser.browser.render.box.Box;
import net.buildabrowser.babbrowser.browser.render.box.BoxContent;
import net.buildabrowser.babbrowser.browser.render.box.ElementBox;
import net.buildabrowser.babbrowser.browser.render.box.ElementBoxDimensions;
import net.buildabrowser.babbrowser.browser.render.content.flow.floatbox.FloatTracker;
import net.buildabrowser.babbrowser.browser.render.content.flow.fragment.ManagedBoxFragment;
import net.buildabrowser.babbrowser.browser.render.layout.LayoutConstraint;
import net.buildabrowser.babbrowser.browser.render.layout.LayoutContext;
import net.buildabrowser.babbrowser.browser.render.paint.PaintCanvas;

public class FlowRootContent implements BoxContent {

  private final ElementBox rootBox;

  private final FlowBlockLayout blockLayout;
  private final FlowInlineLayout inlineLayout;
  private final FloatTracker floatTracker;

  private ManagedBoxFragment rootFragment;

  public FlowRootContent(ElementBox box) {
    this.rootBox = box;
    this.blockLayout = new FlowBlockLayout(this);
    this.inlineLayout = new FlowInlineLayout(this);
    this.floatTracker = FloatTracker.create();
  }

  @Override
  public void prelayout(LayoutContext layoutContext) {
    for (Box child: rootBox.childBoxes()) {
      if (child instanceof ElementBox elementBox) {
        elementBox.content().prelayout(layoutContext);
      }
    }

    ElementBoxDimensions dimensions = rootBox.dimensions();

    floatTracker.reset();
    blockLayout.reset(rootBox);
    blockLayout.addChildrenToBlock(
      layoutContext, rootBox, LayoutConstraint.MIN_CONTENT, LayoutConstraint.AUTO);
    dimensions.setPreferredMinWidthConstraint(
      blockLayout.close(LayoutConstraint.MIN_CONTENT, LayoutConstraint.AUTO).width());

    floatTracker.reset();
    blockLayout.reset(rootBox);
    blockLayout.addChildrenToBlock(
      layoutContext, rootBox, LayoutConstraint.MAX_CONTENT, LayoutConstraint.AUTO);
    dimensions.setPreferredWidthConstraint(
      blockLayout.close(LayoutConstraint.MAX_CONTENT, LayoutConstraint.AUTO).width());
  }

  @Override
  public void layout(
    LayoutContext layoutContext, LayoutConstraint widthConstraint, LayoutConstraint heightConstraint
  ) {
    floatTracker.reset();

    blockLayout.reset(rootBox);
    blockLayout.addChildrenToBlock(layoutContext, rootBox, widthConstraint, heightConstraint);

    this.rootFragment = blockLayout.close(widthConstraint, heightConstraint);
    rootBox.dimensions().setComputedSize(rootFragment.width(), rootFragment.height());
  }

  @Override
  public void paint(PaintCanvas canvas) {
    FlowRootContentPainter.paintFragment(canvas, rootFragment);
  }

  FlowBlockLayout blockLayout() {
    return this.blockLayout;
  }

  FlowInlineLayout inlineLayout() {
    return this.inlineLayout;
  }

  FloatTracker floatTracker() {
    return this.floatTracker;
  }

  // For testing
  ManagedBoxFragment rootFragment() {
    return this.rootFragment;
  }

}
