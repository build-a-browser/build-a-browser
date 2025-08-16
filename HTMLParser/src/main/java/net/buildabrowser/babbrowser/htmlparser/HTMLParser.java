package net.buildabrowser.babbrowser.htmlparser;

import java.io.IOException;
import java.io.Reader;

import net.buildabrowser.babbrowser.htmlparser.imp.HTMLParserImp;
import net.buildabrowser.babbrowser.spec.dom.Document;

public interface HTMLParser {
  
  Document parse(Reader streamReader) throws IOException;

  public static HTMLParser create() {
    return new HTMLParserImp();
  }

}
