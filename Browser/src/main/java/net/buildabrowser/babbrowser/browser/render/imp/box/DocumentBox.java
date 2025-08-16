package net.buildabrowser.babbrowser.browser.render.imp.box;

import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JPanel;

import net.buildabrowser.babbrowser.browser.render.core.box.Box;
import net.buildabrowser.babbrowser.spec.dom.Document;

public class DocumentBox implements Box {

  @SuppressWarnings("unused")
  private final Document document;
  private final List<Box> children;

  public DocumentBox(Document document, List<Box> children) {
    this.document = document;
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
