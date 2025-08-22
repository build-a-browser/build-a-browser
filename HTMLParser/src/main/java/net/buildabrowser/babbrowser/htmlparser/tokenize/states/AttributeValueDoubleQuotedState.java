package net.buildabrowser.babbrowser.htmlparser.tokenize.states;

import java.io.IOException;

import net.buildabrowser.babbrowser.htmlparser.shared.ParseContext;
import net.buildabrowser.babbrowser.htmlparser.tokenize.TokenizeContext;
import net.buildabrowser.babbrowser.htmlparser.tokenize.TokenizeState;

public class AttributeValueDoubleQuotedState implements TokenizeState {

  @Override
  public void consume(int ch, TokenizeContext tokenizeContext, ParseContext parseContext) throws IOException {
    switch (ch) {
      // TODO: Other cases
      case '"':
        tokenizeContext.setTokenizeState(new AfterAttributeValueQuotedState());
        break;
      default:
        tokenizeContext.currentTagToken().appendToAttributeValue(ch);
        break;
    }
  }

}
