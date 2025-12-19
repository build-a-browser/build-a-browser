package net.buildabrowser.babbrowser.htmlparser.token.imp;

import net.buildabrowser.babbrowser.htmlparser.token.DoctypeToken;

public class DoctypeTokenImp implements DoctypeToken {

  private final StringBuilder nameBuilder = new StringBuilder();
  private final StringBuilder publicIdentifierBuilder = new StringBuilder();
  private final StringBuilder systemIdentifierBuilder = new StringBuilder();
  
  private boolean forceQuirks;

  @Override
  public void setForceQuirks(boolean forceQuirks) {
    this.forceQuirks = forceQuirks;
  }

  @Override
  public boolean forceQuirks() {
    return this.forceQuirks;
  }

  @Override
  public String name() {
    return nameBuilder.toString();
  }

  @Override
  public void appendCodePointToName(int ch) {
    nameBuilder.append(ch);
  }

  @Override
  public void setPublicIdentifier(String identifier) {
    publicIdentifierBuilder.setLength(0);
    publicIdentifierBuilder.append(identifier);
  }

  @Override
  public String getPublicIdentifier() {
    return publicIdentifierBuilder.toString();
  }

  @Override
  public void appendCodePointToPublicIdentifier(int ch) {
    publicIdentifierBuilder.appendCodePoint(ch);
  }

  @Override
  public void setSystemIdentifier(String identifier) {
    systemIdentifierBuilder.setLength(0);
    systemIdentifierBuilder.append(identifier);
  }

  @Override
  public String getSystemIdentifier() {
    return systemIdentifierBuilder.toString();
  }

  @Override
  public void appendCodePointToSystemIdentifier(int ch) {
    systemIdentifierBuilder.appendCodePoint(ch);
  }
  
}
