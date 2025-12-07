package net.buildabrowser.babbrowser.browser.render.box;

public interface DocumentBox extends Box {
  
  ElementBox htmlBox();

  void setChild(ElementBox child);

}
