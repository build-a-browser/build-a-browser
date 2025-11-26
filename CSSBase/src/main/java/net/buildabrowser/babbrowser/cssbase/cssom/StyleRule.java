package net.buildabrowser.babbrowser.cssbase.cssom;

import java.util.List;

import net.buildabrowser.babbrowser.cssbase.selector.ComplexSelector;

public record StyleRule(List<ComplexSelector> complexSelectors, List<Declaration> declarations) implements CSSRule {

  public static StyleRule create(List<ComplexSelector> complexSelectors, List<Declaration> declarations) {
    return new StyleRule(complexSelectors, declarations);
  }

}
