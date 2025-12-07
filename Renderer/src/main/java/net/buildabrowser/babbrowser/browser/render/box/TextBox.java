package net.buildabrowser.babbrowser.browser.render.box;

import net.buildabrowser.babbrowser.browser.render.box.imp.TextBoxImp;
import net.buildabrowser.babbrowser.dom.Text;

public interface TextBox extends Box {
  
  Text text();

  static TextBox create(Text text) {
    return new TextBoxImp(text);
  }

}
