package net.buildabrowser.babbrowser.browser.render.box;

import net.buildabrowser.babbrowser.browser.render.layout.LayoutContext;
import net.buildabrowser.babbrowser.browser.render.paint.PaintCanvas;

public interface BoxContent {
  
  void layout(LayoutContext layoutContext);

  void paint(PaintCanvas canvas);

}
