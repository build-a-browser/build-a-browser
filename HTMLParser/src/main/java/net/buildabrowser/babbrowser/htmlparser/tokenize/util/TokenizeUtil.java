package net.buildabrowser.babbrowser.htmlparser.tokenize.util;

import net.buildabrowser.babbrowser.htmlparser.shared.ParseContext;
import net.buildabrowser.babbrowser.htmlparser.tokenize.TokenizeContext;

public final class TokenizeUtil {
  
  private TokenizeUtil() {}

  public static void emitTemporaryBuffer(TokenizeContext tokenizeContext, ParseContext parseContext) {
    String tmpbuf = tokenizeContext.temporaryBuffer().get();

    int i = 0;
    while (i < tmpbuf.length()) {
      int ch = tmpbuf.codePointAt(i);
      parseContext.emitCharacterToken(ch);
      i += Character.charCount(ch);
    }
  }

}
