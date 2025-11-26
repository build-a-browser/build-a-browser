package net.buildabrowser.babbrowser.cssbase.cssom;

import net.buildabrowser.babbrowser.cssbase.cssom.imp.CSSStyleSheetImp;

public interface CSSStyleSheet {
  
  CSSRuleList cssRules();

  static CSSStyleSheet create(CSSRuleList rules) {
    return new CSSStyleSheetImp(rules);
  }

}
