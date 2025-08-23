package net.buildabrowser.babbrowser.browser.render.imp.box;

import java.awt.Dimension;

import javax.swing.JComponent;
import javax.swing.JTextField;

import net.buildabrowser.babbrowser.browser.render.core.box.Box;
import net.buildabrowser.babbrowser.dom.Text;

public class TextBox implements Box {
  
  private final Text text;

  public TextBox(Text text) {
    this.text = text;
  }

  @Override
  public JComponent render() {
    String formattedText = text.text().trim().replaceAll("[ \n\t\r]+", " ");
    JTextField textArea = new JTextField(formattedText);
    textArea.setMaximumSize(new Dimension(
      (int) textArea.getMaximumSize().getWidth(),
      (int) textArea.getPreferredSize().getHeight()));
    textArea.setEditable(false);
    textArea.setBorder(null);
    textArea.setOpaque(false);

    return textArea;
  }
  
}
