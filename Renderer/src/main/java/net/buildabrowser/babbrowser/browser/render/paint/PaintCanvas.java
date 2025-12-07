package net.buildabrowser.babbrowser.browser.render.paint;

import java.awt.image.BufferedImage;
import java.util.function.Consumer;

public interface PaintCanvas {
  
  void pushPaint();

  void popPaint();

  void alterPaint(Consumer<Paint> func);

  void drawBox(int x, int y, int w, int h);

  void drawText(int x, int y, String text);

  // TODO: Replace with something more portable
  void drawImage(int x, int y, BufferedImage image);

  FontMetrics fontMetrics();

}
