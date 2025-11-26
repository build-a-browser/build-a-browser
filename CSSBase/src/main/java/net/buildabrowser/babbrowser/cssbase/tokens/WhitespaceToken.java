package net.buildabrowser.babbrowser.cssbase.tokens;

public record WhitespaceToken() implements Token {
  
  private static WhitespaceToken INSTANCE = new WhitespaceToken();

  public static WhitespaceToken create() {
    return INSTANCE;
  }

}
