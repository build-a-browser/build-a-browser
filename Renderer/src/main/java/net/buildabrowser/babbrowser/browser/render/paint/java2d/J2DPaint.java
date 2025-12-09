package net.buildabrowser.babbrowser.browser.render.paint.java2d;

import net.buildabrowser.babbrowser.browser.render.paint.Paint;

public class J2DPaint implements Paint {

  private int color;
  private int offsetX;
  private int offsetY;

  @Override
  public void setColor(int color) {
    this.color = color;
  }

  @Override
  public int getColor() {
    return this.color;
  }

  @Override
  public void incOffset(int x, int y) {
    this.offsetX += x;
    this.offsetY += y;
  }

  @Override
  public void setOffset(int x, int y) {
    this.offsetX = x;
    this.offsetY = y;
  }

  @Override
  public int offsetX() {
    return this.offsetX;
  }

  @Override
  public int offsetY() {
    return this.offsetY;
  }

}
