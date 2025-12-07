package net.buildabrowser.babbrowser.browser.render.box;

import java.util.List;

import net.buildabrowser.babbrowser.browser.render.box.imp.ElementBoxImp;
import net.buildabrowser.babbrowser.css.engine.styles.ActiveStyles;
import net.buildabrowser.babbrowser.dom.Element;
import net.buildabrowser.babbrowser.dom.mutable.MutableElement;

public interface ElementBox extends Box {

  ActiveStyles activeStyles();
  
  BoxContent content();

  Element element();

  ElementBoxDimensions dimensions();

  List<Box> childBoxes();

  void addChild(Box box);
 
  public static ElementBox create(MutableElement element, Box parentBox) {
    return new ElementBoxImp(element, parentBox);
  }

}
