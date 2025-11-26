package net.buildabrowser.babbrowser.cssbase.tokens;

public record IdentToken(String value) implements Token {

  public static IdentToken create(String value) {
    return new IdentToken(value);
  }

}
