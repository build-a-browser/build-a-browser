package net.buildabrowser.babbrowser.cssbase.tokens;

public record ColonToken() implements Token {
  
  private static ColonToken INSTANCE = new ColonToken();

  public static ColonToken create() {
    return INSTANCE;
  }

}
