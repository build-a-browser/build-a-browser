package net.buildabrowser.babbrowser.cssbase.cssom;

import java.util.List;

import net.buildabrowser.babbrowser.cssbase.cssom.imp.StyleSheetListImp;

public interface StyleSheetList {

  CSSStyleSheet item(long index);

  long length();

  static StyleSheetList create(List<CSSStyleSheet> styleSheets) {
    return new StyleSheetListImp(styleSheets);
  }

}
