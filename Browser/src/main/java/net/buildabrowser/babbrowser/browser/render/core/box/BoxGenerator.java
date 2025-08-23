package net.buildabrowser.babbrowser.browser.render.core.box;

import java.util.List;

import net.buildabrowser.babbrowser.browser.render.imp.box.BoxGeneratorImp;
import net.buildabrowser.babbrowser.dom.Node;

public interface BoxGenerator {
  
  List<Box> box(Node node);

  static BoxGenerator create() {
    return new BoxGeneratorImp();
  };

}