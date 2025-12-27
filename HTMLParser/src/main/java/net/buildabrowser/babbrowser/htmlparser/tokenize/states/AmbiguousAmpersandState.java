package net.buildabrowser.babbrowser.htmlparser.tokenize.states;

import java.io.IOException;

import net.buildabrowser.babbrowser.htmlparser.shared.ParseContext;
import net.buildabrowser.babbrowser.htmlparser.tokenize.TokenizeContext;
import net.buildabrowser.babbrowser.htmlparser.tokenize.TokenizeState;
import net.buildabrowser.babbrowser.htmlparser.tokenize.util.ASCIIUtil;

public class AmbiguousAmpersandState implements TokenizeState {

  @Override
  public void consume(int ch, TokenizeContext tokenizeContext, ParseContext parseContext) throws IOException {
    if (ch ==  ';') {
      parseContext.parseError();
      tokenizeContext.reconsumeInTokenizeState(ch, tokenizeContext.getReturnState());
    } else if (ASCIIUtil.isAlpha(ch)) {
      // TODO
    } else {
      tokenizeContext.reconsumeInTokenizeState(ch, tokenizeContext.getReturnState());
    }
  }
  
}
