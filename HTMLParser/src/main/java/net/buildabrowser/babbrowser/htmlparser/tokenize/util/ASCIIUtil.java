package net.buildabrowser.babbrowser.htmlparser.tokenize.util;

public final class ASCIIUtil {
  
  private ASCIIUtil() {}

  public static boolean isAlpha(int ch) {
    return
      (ch >= 'a' && ch <= 'z')
      || (ch >= 'A' && ch <= 'Z');
  }

  public static int toLower(int ch) {
    if (ch >= 'A' && ch <= 'Z') {
      return ch - 0x20;
    }

    return ch;
  }

  public static boolean isHexDigit(int ch) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'isHexDigit'");
  }

  public static boolean isDigit(int ch) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'isDigit'");
  }

  public static int hexValue(int ch) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'hexValue'");
  }

}
