package net.buildabrowser.babbrowser.browser.render.box.content.flow;

import java.util.ArrayList;
import java.util.List;

import net.buildabrowser.babbrowser.browser.render.box.ElementBox;
import net.buildabrowser.babbrowser.browser.render.box.content.flow.FlowFragment.ManagedBoxFragment;

public class BlockFormattingContext {

  private final ElementBox elementBox;

  private final List<FlowFragment> fragments;
  private final int initX;
  private final int initY;

  private int width;
  private int y;

  public BlockFormattingContext(int x, int y, ElementBox elementBox) {
    this.initX = x;
    this.initY = y;
    this.elementBox = elementBox;

    this.fragments = new ArrayList<>();
  }

  public int currentX() {
    return this.initX;
  }

  public int currentY() {
    return this.y;
  }

  public void increaseY(int yInc) {
    this.y += yInc;
  }

  public void minWidth(int minWidth) {
    this.width = Math.max(width, minWidth);
  }

  public void addFragment(FlowFragment newFragment) {
    this.fragments.add(newFragment);
  }

  public ManagedBoxFragment close() {
    return new ManagedBoxFragment(
      initX, initY, width, y - initY,
      elementBox, fragments);
  }

}