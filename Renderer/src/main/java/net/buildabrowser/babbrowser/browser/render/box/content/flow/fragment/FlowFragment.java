package net.buildabrowser.babbrowser.browser.render.box.content.flow.fragment;

public abstract class FlowFragment {
  
  private int posX;
  private int posY;

  public void setPosX(int x, int y) {
    this.posX = x;
    this.posY = y;
  }

  public int posX() {
    return this.posX;
  }

  public int posY() {
    return this.posY;
  }

}
