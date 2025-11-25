package net.buildabrowser.babbrowser.htmlparser.tokenize.states;

import java.io.IOException;

import net.buildabrowser.babbrowser.htmlparser.shared.ParseContext;
import net.buildabrowser.babbrowser.htmlparser.tokenize.TokenizeContext;
import net.buildabrowser.babbrowser.htmlparser.tokenize.TokenizeState;

public class RawTextLessThanSignState implements TokenizeState {

  @Override
  public void consume(int ch, TokenizeContext tokenizeContext, ParseContext parseContext) throws IOException {
    switch (ch) {
      case '/':
        tokenizeContext.temporaryBuffer().clear();
        tokenizeContext.setTokenizeState(new RawTextEndTagOpenState());
        break;
      default:
        parseContext.emitCharacterToken('<');
        tokenizeContext.reconsumeInTokenizeState(ch, new RawTextState());
    }
  }

}
