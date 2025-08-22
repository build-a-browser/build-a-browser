package net.buildabrowser.babbrowser.browser.parser;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import net.buildabrowser.babbrowser.htmlparser.HTMLParser;
import net.buildabrowser.babbrowser.spec.dom.Document;
import net.buildabrowser.babbrowser.spec.dom.Element;
import net.buildabrowser.babbrowser.spec.dom.Text;

public class HTMLParserTest {
  
  private HTMLParser htmlParser;

  @BeforeEach
  public void BeforeEach() {
    this.htmlParser = HTMLParser.create();
  }

  @Test
  @DisplayName("Can parse empty document")
  public void canParseEmptyDocument() throws IOException {
    Document document = htmlParser.parse(new StringReader(""));
    Assertions.assertEquals(Document.create(List.of()), document);
  }
  
  @Test
  @DisplayName("Can parse document with text")
  public void canParseDocumentWithText() throws IOException {
    Document document = htmlParser.parse(new StringReader("Hello, World!"));
    Assertions.assertEquals(
      Document.create(List.of(Text.create("Hello, World!"))),
      document);
  }

  @Test
  @DisplayName("Can parse document with div")
  public void canParseDocumentWithDiv() throws IOException {
    Document document = htmlParser.parse(new StringReader("<div>Hello, World!</div>"));
    Assertions.assertEquals(
      Document.create(List.of(
        Element.create("div", List.of(
          Text.create("Hello, World!"))))),
      document);
  }

  @Test
  @DisplayName("Can parse document with self closing tag")
  public void canParseDocumentWithSelfClosingTag() throws IOException {
    Document document = htmlParser.parse(new StringReader("<img/><div>Hello, World!</div>"));
    Assertions.assertEquals(
      Document.create(List.of(
        Element.create("img", List.of()),
        Element.create("div", List.of(
          Text.create("Hello, World!"))))),
      document);
  }

  @Test
  @DisplayName("Can parse document with element with one attribute")
  public void canParseDocumentWithElementWithOneAttribute() throws IOException {
    Document document = htmlParser.parse(new StringReader("<img href=\"file.png\"/>"));
    Assertions.assertEquals(
      Document.create(List.of(
        Element.create("img", List.of(), Map.of(
          "href", "file.png"
        )))),
      document);
  }

  @Test
  @DisplayName("Can parse document with element with two attributes")
  public void canParseDocumentWithElementWithTwoAttributes() throws IOException {
    Document document = htmlParser.parse(new StringReader("<img href=\"file.png\" alt=\"Image\"/>"));
    Assertions.assertEquals(
      Document.create(List.of(
        Element.create("img", List.of(), Map.of(
          "href", "file.png",
          "alt", "Image"
        )))),
      document);
  }

}
