package net.buildabrowser.babbrowser.css.engine.property.display;

import net.buildabrowser.babbrowser.css.engine.property.CSSValue;

public record DisplayValue(OuterDisplayValue outerDisplayValue, InnerDisplayValue innerDisplayValue) implements CSSValue {
  
  public static enum OuterDisplayValue implements CSSValue {
    BLOCK, INLINE, RUN_IN, CONTENTS, NONE
  }

  public static enum InnerDisplayValue implements CSSValue {
    FLOW, FLOW_ROOT, TABLE, FLEX, GRID, RUBY
  }

  public static DisplayValue create(OuterDisplayValue outerDisplayValue, InnerDisplayValue innerDisplayValue) {
    return new DisplayValue(outerDisplayValue, innerDisplayValue);
  }

}
