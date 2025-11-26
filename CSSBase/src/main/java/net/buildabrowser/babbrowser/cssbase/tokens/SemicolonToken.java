package net.buildabrowser.babbrowser.cssbase.tokens;

public record SemicolonToken() implements Token {
  
  private static SemicolonToken INSTANCE = new SemicolonToken();

  public static SemicolonToken create() {
    return INSTANCE;
  }

}
