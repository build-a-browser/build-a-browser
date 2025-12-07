package net.buildabrowser.babbrowser.browser.render.paint.java2d;

import net.buildabrowser.babbrowser.browser.render.paint.Paint;

public class J2DPaint implements Paint {

  private int color;

  @Override
  public void setColor(int color) {
    this.color = color;
  }

  @Override
  public int getColor() {
    return this.color;
  }

}
