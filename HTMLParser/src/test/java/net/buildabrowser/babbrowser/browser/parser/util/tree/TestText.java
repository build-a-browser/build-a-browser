package net.buildabrowser.babbrowser.browser.parser.util.tree;

public record TestText(String text) implements TestNode {
  
  public static TestText testText(String text) {
    return new TestText(text);
  }

}
