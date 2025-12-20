package net.buildabrowser.babbrowser.css.engine.matcher.imp;

import java.util.ArrayList;
import java.util.List;

import net.buildabrowser.babbrowser.css.engine.matcher.CSSMatcher;
import net.buildabrowser.babbrowser.css.engine.matcher.ElementSet;
import net.buildabrowser.babbrowser.css.engine.matcher.simple.SimpleSelectorMatchers;
import net.buildabrowser.babbrowser.cssbase.cssom.CSSRule;
import net.buildabrowser.babbrowser.cssbase.cssom.CSSRuleList;
import net.buildabrowser.babbrowser.cssbase.cssom.CSSStyleSheet;
import net.buildabrowser.babbrowser.cssbase.cssom.StyleRule;
import net.buildabrowser.babbrowser.cssbase.cssom.StyleSheetList;
import net.buildabrowser.babbrowser.cssbase.cssom.extra.WeightedStyleRule;
import net.buildabrowser.babbrowser.cssbase.cssom.extra.WeightedStyleRule.RuleSource;
import net.buildabrowser.babbrowser.cssbase.selector.AttributeSelector;
import net.buildabrowser.babbrowser.cssbase.selector.ComplexSelector;
import net.buildabrowser.babbrowser.cssbase.selector.IdSelector;
import net.buildabrowser.babbrowser.cssbase.selector.SelectorPart;
import net.buildabrowser.babbrowser.cssbase.selector.SelectorSpecificity;
import net.buildabrowser.babbrowser.cssbase.selector.TypeSelector;
import net.buildabrowser.babbrowser.dom.Document;
import net.buildabrowser.babbrowser.dom.Element;
import net.buildabrowser.babbrowser.dom.Node;
import net.buildabrowser.babbrowser.dom.mutable.DocumentChangeListener;

public class CSSMatcherImp implements CSSMatcher {

  private final SimpleSelectorMatchers matchers = new SimpleSelectorMatchers();
  
  private final CSSMatcherContext context;

  public CSSMatcherImp(CSSMatcherContext context) {
    this.context = context;
  }

  @Override
  public void applyStylesheets(Document document) {
    StyleSheetList stylesheets = document.styleSheets();
    for (int i = 0; i < stylesheets.length(); i++) {
      CSSStyleSheet styleSheet = stylesheets.item(i);
      CSSRuleList ruleList = styleSheet.cssRules();
      for (int j = 0; j < ruleList.length(); j++) {
        applyRule(ruleList.item(j), RuleSource.AUTHOR, i, j);
      }
    }
  }

  @Override
  public DocumentChangeListener documentChangeListener() {
    return new DocumentChangeListener() {

      @Override
      public void onNodeAdded(Node node) {
        matchers.onNodeAdded(node);
      }

      @Override
      public void onNodeRemoved(Node node) {
        matchers.onNodeRemoved(node);
      }

      @Override
      public void onAttributeChanged(Element element, String attrName, String prevValue, String newValue) {
        matchers.onAttributeChanged(element, attrName, prevValue, newValue);
      }

      @Override
      public void onStylesheetAdded(CSSStyleSheet styleSheet) {
        CSSRuleList ruleList = styleSheet.cssRules();
        for (int j = 0; j < ruleList.length(); j++) {
          registerRule(ruleList.item(j));
        }
      }
      
    };    
  }

  private void registerRule(CSSRule cssRule) {
    if (!(cssRule instanceof StyleRule styleRule)) return;

    for (ComplexSelector complexSelector: styleRule.complexSelectors()) {
      for (SelectorPart selectorPart: complexSelector.parts()) {
        matchers.addSelectorReference(selectorPart);
      }
    }
  }

  private void applyRule(
    CSSRule cssRule,
    RuleSource ruleSource,
    int sheetOrdering,
    int ruleOrdering
  ) {
    if (!(cssRule instanceof StyleRule styleRule)) return;
    
    for (ComplexSelector complexSelector: styleRule.complexSelectors()) {
      List<ElementSet> toIntersect = new ArrayList<>();
      for (SelectorPart selectorPart: complexSelector.parts()) {
        toIntersect.add(matchers.match(selectorPart));
      }

      if (toIntersect.size() == 0) continue;
      ElementSet matchedElements = ElementSet.intersectMany(toIntersect);
      SelectorSpecificity specificity = computeSpecificity(complexSelector);
      WeightedStyleRule weightedRule = new WeightedStyleRule(
        styleRule, specificity, ruleSource, sheetOrdering, ruleOrdering);

      for (Element element: matchedElements) {
        context.onMatched(element, weightedRule);
      }
    }
  }

  private SelectorSpecificity computeSpecificity(ComplexSelector selector) {
    int numIdSelectors = 0;
    int numClassSelectors = 0;
    int numTypeSelectors = 0;
    for (SelectorPart selectorPart: selector.parts()) {
      switch (selectorPart) {
        case IdSelector _ -> numIdSelectors++;
        case AttributeSelector _ -> numClassSelectors++;
        case TypeSelector _ -> numTypeSelectors++;
        default -> {}
      }
    }

    return new SelectorSpecificity(numIdSelectors, numClassSelectors, numTypeSelectors);
  }

}
