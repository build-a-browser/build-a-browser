package net.buildabrowser.babbrowser.browser.render.content.flow;

import java.util.LinkedList;
import java.util.List;

import net.buildabrowser.babbrowser.browser.render.box.ElementBox;
import net.buildabrowser.babbrowser.browser.render.content.flow.fragment.FlowFragment;
import net.buildabrowser.babbrowser.browser.render.content.flow.fragment.LineBoxFragment;

public class InlineFormattingContext {
 
  private final InlineStagingArea stagingArea;
  private final List<LineBox> lineBoxes;
  private LineBox activeLineBox;

  public InlineFormattingContext() {
    this(new LineBox());
  }

  private InlineFormattingContext(LineBox firstLineBox) {
    this.stagingArea = new InlineStagingArea();
    this.lineBoxes = new LinkedList<>();
    this.activeLineBox = firstLineBox;
    lineBoxes.add(activeLineBox);
  }

  public InlineStagingArea stagingArea() {
    return this.stagingArea;
  }

  public void addFragment(FlowFragment flowFragment) {
    activeLineBox.addFragment(flowFragment);
  }

  public void pushElement(ElementBox elementBox) {
    activeLineBox.pushElement(elementBox);
  }

  public ElementBox popElement() {
    return activeLineBox.popElement();
  }

  public InlineFormattingContext split() {
    return new InlineFormattingContext(lineBoxes.getLast().split());
  };

  public List<LineBoxFragment> fragments() {
    return lineBoxes.stream().map(box -> box.toFragment()).toList();
  }

}
