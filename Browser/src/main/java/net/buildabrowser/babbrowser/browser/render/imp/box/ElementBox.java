package net.buildabrowser.babbrowser.browser.render.imp.box;

import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JPanel;

import net.buildabrowser.babbrowser.browser.dom.Element;
import net.buildabrowser.babbrowser.browser.render.core.box.Box;

public class ElementBox implements Box {

  @SuppressWarnings("unused")
  private final Element element;
  private final List<Box> children;

  public ElementBox(Element element, List<Box> children) {
    this.element = element;
    this.children = children;
  }

  @Override
  public JComponent render() {
    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

    for (Box child: children) {
      panel.add(child.render());
    }

    return panel;
  }

}
