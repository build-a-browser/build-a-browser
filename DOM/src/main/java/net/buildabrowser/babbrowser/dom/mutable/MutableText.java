package net.buildabrowser.babbrowser.dom.mutable;

import net.buildabrowser.babbrowser.dom.Text;
import net.buildabrowser.babbrowser.dom.mutable.imp.MutableTextImp;

public interface MutableText extends MutableNode, Text {

  static Text create(String text) {
    return new MutableTextImp(text);
  }
  
}
