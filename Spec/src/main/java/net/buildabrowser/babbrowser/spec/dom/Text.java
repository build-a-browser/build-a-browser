package net.buildabrowser.babbrowser.spec.dom;

public record Text(String text) implements Node {

  @Override
  public String toString() {
    return text;
  }

}
