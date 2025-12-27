package net.buildabrowser.babbrowser.htmlparser.tokenize.states;

import net.buildabrowser.babbrowser.htmlparser.shared.ParseContext;
import net.buildabrowser.babbrowser.htmlparser.tokenize.TokenizeContext;
import net.buildabrowser.babbrowser.htmlparser.tokenize.TokenizeState;
import net.buildabrowser.babbrowser.htmlparser.tokenize.imp.TokenizeStates;

public class DataState implements TokenizeState {

  @Override
  public void consume(int ch, TokenizeContext context, ParseContext parseContext) {
    switch (ch) {
      case '&':
        context.setReturnState(this);
        context.setTokenizeState(TokenizeStates.characterReferenceState);
        break;
      case '<':
        context.setTokenizeState(TokenizeStates.tagOpenState);
        break;
      case 0:
        parseContext.parseError();
        parseContext.emitCharacterToken(ch);
        break;
      case TokenizeContext.EOF:
        parseContext.emitEOFToken();
        break;
      default:
        parseContext.emitCharacterToken(ch);
        break;
    }
  }
  
}
