package net.buildabrowser.babbrowser.cssbase.tokenizer.imp;

import java.io.IOException;

import net.buildabrowser.babbrowser.cssbase.tokenizer.CSSTokenizer;
import net.buildabrowser.babbrowser.cssbase.tokenizer.CSSTokenizerInput;
import net.buildabrowser.babbrowser.cssbase.tokens.ColonToken;
import net.buildabrowser.babbrowser.cssbase.tokens.DelimToken;
import net.buildabrowser.babbrowser.cssbase.tokens.EOFToken;
import net.buildabrowser.babbrowser.cssbase.tokens.HashToken;
import net.buildabrowser.babbrowser.cssbase.tokens.LCBracketToken;
import net.buildabrowser.babbrowser.cssbase.tokens.RCBracketToken;
import net.buildabrowser.babbrowser.cssbase.tokens.SemicolonToken;
import net.buildabrowser.babbrowser.cssbase.tokens.Token;
import net.buildabrowser.babbrowser.cssbase.tokens.WhitespaceToken;

public class CSSTokenizerImp implements CSSTokenizer {

  private final IdentTokenizer identTokenizer = new IdentTokenizer();

  @Override
  public Token consumeAToken(CSSTokenizerInput stream) throws IOException {
    int ch = stream.read();
    return switch (ch) {
      case '\n', ' ', '\t' -> consumeWhitespace(stream);
      case '#' -> consumeNumberSign(stream);
      case ':' -> ColonToken.create();
      case ';' -> SemicolonToken.create();
      case '{' -> LCBracketToken.create();
      case '}' -> RCBracketToken.create();
      case -1 -> EOFToken.create();
      default -> handleOtherValue(stream, ch);
    };
  }

  private Token handleOtherValue(CSSTokenizerInput stream, int ch) throws IOException {
    if (TokenizerUtil.isIdentStartCodePoint(ch)) {
      stream.unread(ch);
      return identTokenizer.consumeAnIdentLikeToken(stream);
    }
    throw new UnsupportedOperationException("Not yet implemented!");
  }

  private WhitespaceToken consumeWhitespace(CSSTokenizerInput stream) throws IOException {
    int ch;
    while ((ch = stream.read()) == '\n' || ch == ' ' || ch == '\t') {}
    stream.unread(ch);

    return WhitespaceToken.create();
  }

  private Token consumeNumberSign(CSSTokenizerInput stream) throws IOException {
    if (TokenizerUtil.isIdentCodePoint(stream.peek()) || TokenizerUtil.isValidEscape(stream)) {
      boolean isId = TokenizerUtil.wouldStartAnIdentSequence(stream);
      String seq = identTokenizer.consumeIdentSequence(stream);
      return HashToken.create(
        seq,
        isId ? HashToken.Type.ID : HashToken.Type.UNRESTRICTED);
    }

    return DelimToken.create(stream.read());
  }
  
}
