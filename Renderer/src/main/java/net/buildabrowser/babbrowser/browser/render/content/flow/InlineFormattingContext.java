package net.buildabrowser.babbrowser.browser.render.content.flow;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import net.buildabrowser.babbrowser.browser.render.box.ElementBox;
import net.buildabrowser.babbrowser.browser.render.content.flow.fragment.FlowFragment;
import net.buildabrowser.babbrowser.browser.render.content.flow.fragment.LineBoxFragment;
import net.buildabrowser.babbrowser.css.engine.styles.ActiveStyles;

public class InlineFormattingContext {
 
  private final InlineStagingArea stagingArea;
  private final List<LineBox> lineBoxes;
  private final Deque<ActiveStyles> stylesStack;
  private LineBox activeLineBox;

  public InlineFormattingContext(ActiveStyles initialStyles) {
    this(new LineBox(), new ArrayDeque<>());
    stylesStack.push(initialStyles);
  }

  private InlineFormattingContext(LineBox firstLineBox, Deque<ActiveStyles> stylesStack) {
    this.stagingArea = new InlineStagingArea();
    this.lineBoxes = new LinkedList<>();
    this.stylesStack = stylesStack;
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
    stylesStack.push(elementBox.activeStyles());
  }

  public ElementBox popElement() {
    stylesStack.pop();
    return activeLineBox.popElement();
  }

  public LineBox lineBox() {
    return lineBoxes.getLast();
  }

  public void nextLine() {
    lineBoxes.add(lineBoxes.getLast().split());
    this.activeLineBox = lineBoxes.getLast();
  }

  public ActiveStyles activeStyles() {
    return stylesStack.peek();
  }

  public InlineFormattingContext split() {
    // TODO: Copy stack before passing?
    return new InlineFormattingContext(lineBoxes.getLast().split(), stylesStack);
  };

  public List<LineBoxFragment> fragments() {
    return lineBoxes.stream().map(box -> box.toFragment()).toList();
  }

}
