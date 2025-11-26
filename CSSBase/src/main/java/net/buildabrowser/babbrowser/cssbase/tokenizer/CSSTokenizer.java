package net.buildabrowser.babbrowser.cssbase.tokenizer;

import java.io.IOException;

import net.buildabrowser.babbrowser.cssbase.tokenizer.imp.CSSTokenizerImp;
import net.buildabrowser.babbrowser.cssbase.tokens.Token;

public interface CSSTokenizer {

  Token consumeAToken(CSSTokenizerInput stream) throws IOException;

  static CSSTokenizer create() {
    return new CSSTokenizerImp();
  }

}
