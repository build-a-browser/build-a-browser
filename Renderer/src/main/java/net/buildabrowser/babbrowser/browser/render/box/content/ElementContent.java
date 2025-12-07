package net.buildabrowser.babbrowser.browser.render.box.content;

import java.util.ArrayList;
import java.util.List;

import net.buildabrowser.babbrowser.browser.render.box.Box;
import net.buildabrowser.babbrowser.browser.render.box.BoxContent;
import net.buildabrowser.babbrowser.browser.render.box.ElementBox;
import net.buildabrowser.babbrowser.browser.render.box.TextBox;
import net.buildabrowser.babbrowser.browser.render.layout.LayoutContext;
import net.buildabrowser.babbrowser.browser.render.paint.FontMetrics;
import net.buildabrowser.babbrowser.browser.render.paint.PaintCanvas;

public class ElementContent implements BoxContent {

  private final ElementBox box;
  private final List<TextRun> textRuns;

  public ElementContent(ElementBox box) {
    this.box = box;
    this.textRuns = new ArrayList<>(1);
  }

  @Override
  public void layout(LayoutContext layoutContext) {
    FontMetrics fontMetrics = layoutContext.fontMetrics();
    int x = box.dimensions().getLayoutX();
    int y = box.dimensions().getLayoutY();
    int width = 0;
    for (Box childBox: box.childBoxes()) {
      switch (childBox) {
        case ElementBox elementBox:
          elementBox.dimensions().setLayoutPos(x, y);
          elementBox.content().layout(layoutContext);
          y += elementBox.dimensions().getComputedHeight();
          width = Math.max(width, elementBox.dimensions().getComputedWidth());
          break;

        case TextBox textBox:
          String text = textBox.text().data().trim();
          textRuns.add(new TextRun(x, y, textBox.text().data()));
          width = Math.max(width, fontMetrics.stringWidth(text));
          if (text.length() > 0) {
            y += fontMetrics.fontHeight();
          }
          break;
      
        default:
          throw new UnsupportedOperationException("Unsupported box type!");
      }
    }

    box.dimensions().setComputedSize(width, y - box.dimensions().getLayoutY());
  }

  @Override
  public void paint(PaintCanvas canvas) {
    canvas.pushPaint();
    
    canvas.alterPaint(paint -> paint.setColor(0xFFFFFFFF));
    canvas.drawBox(
      box.dimensions().getLayoutX(),
      box.dimensions().getLayoutY(),
      box.dimensions().getComputedWidth(),
      box.dimensions().getComputedHeight()
    );


    canvas.alterPaint(paint -> paint.setColor(box.activeStyles().textColor()));

    for (Box childBox: box.childBoxes()) {
      if (childBox instanceof ElementBox elementBox) {
        elementBox.content().paint(canvas);
      }
    }

    for (TextRun textRun: textRuns) {
      canvas.drawText(textRun.layoutX(), textRun.layoutY(), textRun.text());
    }
    canvas.popPaint();
  }

  private static record TextRun(int layoutX, int layoutY, String text) {

  }
  
}
