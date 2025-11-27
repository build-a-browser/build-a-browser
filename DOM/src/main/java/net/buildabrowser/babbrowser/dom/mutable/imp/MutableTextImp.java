package net.buildabrowser.babbrowser.dom.mutable.imp;

import net.buildabrowser.babbrowser.dom.Text;

public class MutableTextImp extends MutableNodeImp implements Text {

  private final String text;

  public MutableTextImp(String text) {
    this.text = text;
  }

  @Override
  public String text() {
    return this.text;
  }

  @Override
  public String toString() {
    return this.text;
  }
  
}
