package net.buildabrowser.babbrowser.css.engine.matcher;

import net.buildabrowser.babbrowser.css.engine.matcher.imp.CSSMatcherImp;
import net.buildabrowser.babbrowser.cssbase.cssom.extra.WeightedStyleRule;
import net.buildabrowser.babbrowser.dom.Document;
import net.buildabrowser.babbrowser.dom.Node;
import net.buildabrowser.babbrowser.dom.mutable.DocumentChangeListener;

public interface CSSMatcher {
  
  DocumentChangeListener documentChangeListener();

  void applyStylesheets(Document document);

  interface CSSMatcherContext {
    
    void onMatched(Node node, WeightedStyleRule matchedRule);

    void onUnmatched(Node node, WeightedStyleRule matchedRule);

  }

  static CSSMatcher create(CSSMatcherContext context) {
    return new CSSMatcherImp(context);
  }

}
