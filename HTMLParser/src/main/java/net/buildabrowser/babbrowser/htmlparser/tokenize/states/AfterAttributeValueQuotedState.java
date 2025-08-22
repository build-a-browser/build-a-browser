package net.buildabrowser.babbrowser.htmlparser.tokenize.states;

import java.io.IOException;

import net.buildabrowser.babbrowser.htmlparser.shared.ParseContext;
import net.buildabrowser.babbrowser.htmlparser.tokenize.TokenizeContext;
import net.buildabrowser.babbrowser.htmlparser.tokenize.TokenizeState;

public class AfterAttributeValueQuotedState implements TokenizeState {

  @Override
  public void consume(int ch, TokenizeContext tokenizeContext, ParseContext parseContext) throws IOException {
    switch (ch) {
      // TODO: Other cases
      case '\t', '\n', '\f', ' ':
        tokenizeContext.setTokenizeState(new BeforeAttributeNameState());
        break;
      case '/':
        tokenizeContext.setTokenizeState(new SelfClosingStartTagState());
        break;
      case '>':
        tokenizeContext.setTokenizeState(new DataState());
        parseContext.emitTagToken(tokenizeContext.currentTagToken());
        break;
      default:
        throw new UnsupportedOperationException("Not yet implemented!");
    }
  }

}
