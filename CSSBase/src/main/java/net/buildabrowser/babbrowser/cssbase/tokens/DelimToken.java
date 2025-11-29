package net.buildabrowser.babbrowser.cssbase.tokens;

public record DelimToken(int ch) implements Token {
  
  public static DelimToken create(int ch) {
    return new DelimToken(ch);
  }

}
