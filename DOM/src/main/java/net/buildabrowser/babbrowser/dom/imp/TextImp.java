package net.buildabrowser.babbrowser.dom.imp;

import net.buildabrowser.babbrowser.dom.Text;

public record TextImp(String text) implements Text {

  @Override
  public String toString() {
    return text;
  }

}
