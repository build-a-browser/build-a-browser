package net.buildabrowser.babbrowser.browser.render.layout;

import net.buildabrowser.babbrowser.browser.render.layout.LayoutConstraint.LayoutConstraintType;

public final class LayoutUtil {
  
  private LayoutUtil() {}

  public static int constraintOrDim(LayoutConstraint constraint, int dim) {
    return constraint.type().equals(LayoutConstraintType.BOUNDED) ?
      constraint.value() : dim;
  }

}
