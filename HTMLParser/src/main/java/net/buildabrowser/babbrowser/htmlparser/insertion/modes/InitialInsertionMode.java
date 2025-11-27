package net.buildabrowser.babbrowser.htmlparser.insertion.modes;

import net.buildabrowser.babbrowser.htmlparser.insertion.InsertionMode;
import net.buildabrowser.babbrowser.htmlparser.insertion.InsertionModes;
import net.buildabrowser.babbrowser.htmlparser.shared.ParseContext;
import net.buildabrowser.babbrowser.htmlparser.token.TagToken;

public class InitialInsertionMode implements InsertionMode {

  @Override
  public boolean emitCharacterToken(ParseContext parseContext, int ch) {
    switch (ch) {
      case '\t', '\n', '\f', '\r', ' ':
        return false;
      default:
        return handleAnythingElse(parseContext);
    }
  }

  @Override
  public boolean emitEOFToken(ParseContext parseContext) {
    return handleAnythingElse(parseContext);
  }

  @Override
  public boolean emitTagToken(ParseContext parseContext, TagToken tagToken) {
    return handleAnythingElse(parseContext);
  }

  private boolean handleAnythingElse(ParseContext parseContext) {
    // TODO: Handle quirks mode
    parseContext.setInsertionMode(InsertionModes.beforeHTMLInsertionMode);
    return true;
  }
  
}
