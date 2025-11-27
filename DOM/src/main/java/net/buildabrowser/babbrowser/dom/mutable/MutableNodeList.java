package net.buildabrowser.babbrowser.dom.mutable;

import java.util.List;

import net.buildabrowser.babbrowser.dom.NodeList;
import net.buildabrowser.babbrowser.dom.mutable.imp.MutableNodeListImp;

public interface MutableNodeList extends NodeList {
  
  MutableNode item(long index);

  static MutableNodeList create(List<MutableNode> childNodes) {
    return new MutableNodeListImp(childNodes);
  }

}
