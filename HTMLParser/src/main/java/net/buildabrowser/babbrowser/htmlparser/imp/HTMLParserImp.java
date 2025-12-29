package net.buildabrowser.babbrowser.htmlparser.imp;

import java.io.IOException;
import java.io.PushbackReader;
import java.io.Reader;
import java.util.List;

import net.buildabrowser.babbrowser.dom.mutable.DocumentChangeListener;
import net.buildabrowser.babbrowser.dom.mutable.MutableDocument;
import net.buildabrowser.babbrowser.htmlparser.HTMLParser;
import net.buildabrowser.babbrowser.htmlparser.shared.ParseContext;
import net.buildabrowser.babbrowser.htmlparser.tokenize.TokenizeBuffer;
import net.buildabrowser.babbrowser.htmlparser.tokenize.TokenizeContext;
import net.buildabrowser.babbrowser.htmlparser.tokenize.TokenizeState;

public class HTMLParserImp implements HTMLParser {

  private static final int EOF = -1;
  
  public MutableDocument parse(Reader streamReader, DocumentChangeListener changeListener) throws IOException {
    PushbackReader pushbackReader = new PushbackReader(streamReader, 16);
    TokenizeContext tokenizeContext = TokenizeContext.create(pushbackReader);
    MutableDocument document = MutableDocument.create(changeListener);
    ParseContext parseContext = ParseContext.create(document, tokenizeContext);
    TokenizeBuffer tokenizeBuffer = TokenizeBuffer.create();
    // TODO: Handle lookahead buffer

    int ch = 0;
    while ((ch = pushbackReader.read()) != EOF) {
      tokenizeNext(tokenizeContext, parseContext, tokenizeBuffer, pushbackReader, ch);
    }
    tokenizeNext(tokenizeContext, parseContext, tokenizeBuffer, pushbackReader,  EOF);

    return document;
  }

  private void tokenizeNext(
    TokenizeContext tokenizeContext, ParseContext parseContext,
    TokenizeBuffer tokenizeBuffer, PushbackReader reader, int ch
  ) throws IOException {
    TokenizeState tokenizeState = tokenizeContext.getTokenizeState();
    List<String> lookaheadOptions = tokenizeState.lookaheadOptions();

    if (lookaheadOptions == null || ch == -1) {
      tokenizeState.consume(ch, tokenizeContext, parseContext);
    } else {
      tokenizeBuffer.appendCodePoint(ch);
      String matched = tokenizeBuffer.matches(lookaheadOptions);
      if (matched != null) {
        tokenizeState.lookaheadMatched(matched, tokenizeContext, parseContext);
        tokenizeBuffer.reset();
        return;
      }

      if (!tokenizeBuffer.continues(lookaheadOptions)) {
        dumpBuffer(tokenizeContext, parseContext, reader, tokenizeBuffer);
      }
    }
  }

  private void dumpBuffer(TokenizeContext tokenizeContext, ParseContext parseContext, PushbackReader reader, TokenizeBuffer tokenizeBuffer) throws IOException {
    String tmpbuf = tokenizeBuffer.dump();
    tokenizeBuffer.reset();
    if (tmpbuf.isEmpty()) return;

    for (int i = tmpbuf.length() - 1; i >= 1; i--) {
      reader.unread(tmpbuf.codePointAt(i));
    }

    tokenizeContext.getTokenizeState().consume(
      tmpbuf.codePointAt(0), tokenizeContext, parseContext);
  };

}
