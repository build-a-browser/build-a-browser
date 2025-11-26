package net.buildabrowser.babbrowser.cssbase.tokens;

public record RCBracketToken() implements Token {
  
  private static RCBracketToken INSTANCE = new RCBracketToken();

  public static RCBracketToken create() {
    return INSTANCE;
  }

}
