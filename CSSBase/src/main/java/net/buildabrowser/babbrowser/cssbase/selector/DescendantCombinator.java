package net.buildabrowser.babbrowser.cssbase.selector;

public record DescendantCombinator() implements SelectorPart {
  
  private static DescendantCombinator INSTANCE = new DescendantCombinator();

  public static DescendantCombinator create() {
    return INSTANCE;
  }

}
