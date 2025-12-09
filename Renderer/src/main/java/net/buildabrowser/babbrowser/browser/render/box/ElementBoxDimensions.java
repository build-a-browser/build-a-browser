package net.buildabrowser.babbrowser.browser.render.box;

import net.buildabrowser.babbrowser.browser.render.box.imp.ElementBoxDimensionsImp;

public interface ElementBoxDimensions {

  void setComputedSize(int w, int h);

  int getComputedWidth();

  int getComputedHeight();

  static ElementBoxDimensions create() {
    return new ElementBoxDimensionsImp();
  }
  
}
