package net.buildabrowser.babbrowser.browser.parser.util.tree;

import java.util.List;

public record TestDocument(List<TestNode> children) implements TestNode {
  
  public static TestDocument testDocument(TestNode... children) {
    return new TestDocument(List.of(children));
  }

}
