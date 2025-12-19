package net.buildabrowser.babbrowser.htmlparser.token;

import net.buildabrowser.babbrowser.htmlparser.token.imp.DoctypeTokenImp;

public interface DoctypeToken {
  
  void setForceQuirks(boolean forceQuirks);

  boolean forceQuirks();

  String name();

  void appendCodePointToName(int ch);

  void setPublicIdentifier(String identifier);

  String getPublicIdentifier();

  void appendCodePointToPublicIdentifier(int ch);

  void setSystemIdentifier(String identifier);

  String getSystemIdentifier();

  void appendCodePointToSystemIdentifier(int ch);

  static DoctypeToken create() {
    return new DoctypeTokenImp();
  }

}
