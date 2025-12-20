package net.buildabrowser.babbrowser.browser.render.context.imp;

import java.util.Set;
import java.util.TreeSet;

import net.buildabrowser.babbrowser.browser.render.context.ElementContext;
import net.buildabrowser.babbrowser.css.engine.styles.ActiveStyles;
import net.buildabrowser.babbrowser.css.engine.styles.util.ActiveStylesGenerator;
import net.buildabrowser.babbrowser.cssbase.cssom.extra.WeightedStyleRule;

public class ElementContextImp implements ElementContext {

  private final Set<WeightedStyleRule> styleRules = new TreeSet<>(WeightedStyleRule::compare);

  private ActiveStyles activeStyles = null;
  
  @Override
  public void onCSSRuleMatched(WeightedStyleRule styleRule) {
    styleRules.add(styleRule);
    this.activeStyles = null;
  }

  @Override
  public void onCSSRuleUnmatched(WeightedStyleRule styleRule) {
    styleRules.remove(styleRule);
    this.activeStyles = null;
  }

  @Override
  public ActiveStyles activeStyles() {
    if (this.activeStyles == null) {
      this.activeStyles = ActiveStylesGenerator.generateActiveStyles(styleRules);
    }

    return this.activeStyles;
  }
  
}
