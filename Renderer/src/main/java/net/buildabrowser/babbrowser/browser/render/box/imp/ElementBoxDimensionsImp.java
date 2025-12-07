package net.buildabrowser.babbrowser.browser.render.box.imp;

import net.buildabrowser.babbrowser.browser.render.box.ElementBoxDimensions;

public class ElementBoxDimensionsImp implements ElementBoxDimensions {
  
  private int layoutX = 0;
  private int layoutY = 0;

  private int computedWidth = 0;
  private int computedHeight = 0;

  @Override
  public void setLayoutPos(int x, int y) {
    this.layoutX = x;
    this.layoutY = y;
  }

  @Override
  public int getLayoutX() {
    return this.layoutX;
  }

  @Override
  public int getLayoutY() {
    return this.layoutY;
  }

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
