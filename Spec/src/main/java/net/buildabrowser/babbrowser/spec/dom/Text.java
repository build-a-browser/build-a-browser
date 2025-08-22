package net.buildabrowser.babbrowser.spec.dom;

import net.buildabrowser.babbrowser.spec.dom.imp.TextImp;

public interface Text extends Node {

  String text();

  static Text create(String text) {
    return new TextImp(text);
  }

}
