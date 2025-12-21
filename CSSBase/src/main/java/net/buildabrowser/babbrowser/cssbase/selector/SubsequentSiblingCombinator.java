package net.buildabrowser.babbrowser.cssbase.selector;

public record SubsequentSiblingCombinator() implements SelectorPart {
  
  private static SubsequentSiblingCombinator INSTANCE = new SubsequentSiblingCombinator();

  public static SubsequentSiblingCombinator create() {
    return INSTANCE;
  }

}
