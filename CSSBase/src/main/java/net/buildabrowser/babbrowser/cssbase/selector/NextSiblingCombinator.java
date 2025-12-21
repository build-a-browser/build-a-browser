package net.buildabrowser.babbrowser.cssbase.selector;

public record NextSiblingCombinator() implements SelectorPart {
  
  private static NextSiblingCombinator INSTANCE = new NextSiblingCombinator();

  public static NextSiblingCombinator create() {
    return INSTANCE;
  }

}
