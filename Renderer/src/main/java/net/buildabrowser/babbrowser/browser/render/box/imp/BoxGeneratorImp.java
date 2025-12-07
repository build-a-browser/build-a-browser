package net.buildabrowser.babbrowser.browser.render.box.imp;

import java.util.ArrayList;
import java.util.List;

import net.buildabrowser.babbrowser.browser.render.box.Box;
import net.buildabrowser.babbrowser.browser.render.box.BoxGenerator;
import net.buildabrowser.babbrowser.browser.render.box.ElementBox;
import net.buildabrowser.babbrowser.browser.render.box.TextBox;
import net.buildabrowser.babbrowser.dom.Node;
import net.buildabrowser.babbrowser.dom.NodeList;
import net.buildabrowser.babbrowser.dom.Text;
import net.buildabrowser.babbrowser.dom.mutable.MutableElement;

public class BoxGeneratorImp implements BoxGenerator {
  
  @Override
  public List<Box> box(Box parentBox, Node node) {
    Box box = switch (node) {
      case Text text -> TextBox.create(text);
      case MutableElement element -> createElementBox(parentBox, element);
      default -> throw new UnsupportedOperationException("Unsupported Box Type");
    };

    return List.of(box);
  }

  private ElementBox createElementBox(Box parentBox, MutableElement element) {
    ElementBox elementBox = ElementBox.create(element, parentBox);
    for (Box childBox: createChildBoxes(elementBox, element.childNodes())) {
      elementBox.addChild(childBox);
    }

    return elementBox;
  }

  private List<Box> createChildBoxes(Box parentBox, NodeList children) {
    List<Box> childBoxes = new ArrayList<>((int) children.length());
    for (Node childNode: children) {
      childBoxes.addAll(box(parentBox, childNode));
    }

    return childBoxes;
  }

}
