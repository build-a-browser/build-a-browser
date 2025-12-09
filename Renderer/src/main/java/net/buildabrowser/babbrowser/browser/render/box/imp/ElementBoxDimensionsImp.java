package net.buildabrowser.babbrowser.browser.render.box.imp;

import net.buildabrowser.babbrowser.browser.render.box.ElementBoxDimensions;

public class ElementBoxDimensionsImp implements ElementBoxDimensions {
  
  private int computedWidth = 0;
  private int computedHeight = 0;

  @Override
  public void setComputedSize(int w, int h) {
    this.computedWidth = w;
    this.computedHeight = h;
  }

  @Override
  public int getComputedWidth() {
    return this.computedWidth;
  }

  @Override
  public int getComputedHeight() {
    return this.computedHeight;
  }
  
}
