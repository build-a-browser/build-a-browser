package net.buildabrowser.babbrowser.cssbase.tokens;

public record HashToken(String value, Type type) implements Token {

  public static HashToken create(String value, Type type) {
    return new HashToken(value, type);
  }

  public static enum Type {
    ID, UNRESTRICTED
  }

}
