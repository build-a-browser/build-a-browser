package net.buildabrowser.babbrowser.browser.render.box.imp;

import net.buildabrowser.babbrowser.browser.render.box.TextBox;
import net.buildabrowser.babbrowser.dom.Text;

public record TextBoxImp(Text text) implements TextBox {

  @Override
  public void invalidate(InvalidationLevel invalidationLevel) {
    // TODO: Implement
  }
  
}
