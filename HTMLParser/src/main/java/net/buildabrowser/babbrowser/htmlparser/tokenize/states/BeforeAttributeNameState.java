package net.buildabrowser.babbrowser.htmlparser.tokenize.states;

import java.io.IOException;

import net.buildabrowser.babbrowser.htmlparser.shared.ParseContext;
import net.buildabrowser.babbrowser.htmlparser.tokenize.TokenizeContext;
import net.buildabrowser.babbrowser.htmlparser.tokenize.TokenizeState;

public class BeforeAttributeNameState implements TokenizeState {

  @Override
  public void consume(int ch, TokenizeContext tokenizeContext, ParseContext parseContext) throws IOException {
    // TODO: Other cases
    switch (ch) {
      case '=':
        tokenizeContext.setTokenizeState(new BeforeAttributeValueState());
        break;
      default:
        tokenizeContext.currentTagToken().startNewAttribute();
        tokenizeContext.reconsumeInTokenizeState(ch, new AttributeNameState());
        break;
    }
  }

}
