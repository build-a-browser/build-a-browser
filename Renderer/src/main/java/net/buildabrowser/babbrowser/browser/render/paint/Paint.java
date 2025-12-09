package net.buildabrowser.babbrowser.browser.render.paint;

public interface Paint {
  
  void setColor(int color);

  int getColor();

  void incOffset(int x, int y);

  void setOffset(int x, int y);

  int offsetX();

  int offsetY();

}
