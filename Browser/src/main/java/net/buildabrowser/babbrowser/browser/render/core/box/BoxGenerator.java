package net.buildabrowser.babbrowser.browser.render.core.box;

import java.util.List;

import net.buildabrowser.babbrowser.browser.dom.Node;
import net.buildabrowser.babbrowser.browser.render.imp.box.BoxGeneratorImp;

public interface BoxGenerator {
  
  List<Box> box(Node node);

  static BoxGenerator create() {
    return new BoxGeneratorImp();
  };

}