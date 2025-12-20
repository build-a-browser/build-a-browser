package net.buildabrowser.babbrowser.browser.render.context;

import net.buildabrowser.babbrowser.browser.render.context.imp.ElementContextImp;
import net.buildabrowser.babbrowser.css.engine.styles.ActiveStyles;
import net.buildabrowser.babbrowser.cssbase.cssom.extra.WeightedStyleRule;

public interface ElementContext {
  
  void onCSSRuleMatched(WeightedStyleRule matchedRule);

  void onCSSRuleUnmatched(WeightedStyleRule matchedRule);

  ActiveStyles activeStyles();

  static ElementContext create() {
    return new ElementContextImp();
  }

}
