package net.buildabrowser.babbrowser.cssbase.tokens;

public record EOFToken() implements Token {
  
  private static EOFToken INSTANCE = new EOFToken();

  public static EOFToken create() {
    return INSTANCE;
  }

}
