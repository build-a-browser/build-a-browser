package net.buildabrowser.babbrowser.browser.render.box;

public interface Box {

  void invalidate(InvalidationLevel invalidationLevel);

  static enum InvalidationLevel {
    LAYOUT, PAINT
  }

}
