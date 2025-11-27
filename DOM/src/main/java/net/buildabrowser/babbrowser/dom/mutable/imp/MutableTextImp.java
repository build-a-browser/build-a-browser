package net.buildabrowser.babbrowser.dom.mutable.imp;

import net.buildabrowser.babbrowser.dom.mutable.MutableDocument;
import net.buildabrowser.babbrowser.dom.mutable.MutableText;

public class MutableTextImp extends MutableNodeImp implements MutableText {

  private final StringBuilder data;
  private final MutableDocument ownerDocument;

  public MutableTextImp(String text, MutableDocument ownerDocument) {
    this.data = new StringBuilder(text);
    this.ownerDocument = ownerDocument;
  }

  @Override
  public MutableDocument ownerDocument() {
    return this.ownerDocument;
  }

  @Override
  public String data() {
    return this.data.toString();
  }

  @Override
  public String toString() {
    return this.data.toString();
  }

  @Override
  public void appendCharacter(int ch) {
    data.appendCodePoint(ch);
  }
  
}
