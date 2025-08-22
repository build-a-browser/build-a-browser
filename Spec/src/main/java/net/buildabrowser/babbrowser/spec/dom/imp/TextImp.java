package net.buildabrowser.babbrowser.spec.dom.imp;

import net.buildabrowser.babbrowser.spec.dom.Text;

public record TextImp(String text) implements Text {

  @Override
  public String toString() {
    return text;
  }

}
