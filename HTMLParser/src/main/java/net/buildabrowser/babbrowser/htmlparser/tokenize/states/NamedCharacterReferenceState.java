package net.buildabrowser.babbrowser.htmlparser.tokenize.states;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.buildabrowser.babbrowser.htmlparser.shared.ParseContext;
import net.buildabrowser.babbrowser.htmlparser.tokenize.TokenizeContext;
import net.buildabrowser.babbrowser.htmlparser.tokenize.TokenizeState;
import net.buildabrowser.babbrowser.htmlparser.tokenize.imp.TokenizeStates;

public class NamedCharacterReferenceState implements TokenizeState {

  private final Map<String, String> referenceMap;
  private final List<String> optionsWithoutAmpersand;

  public NamedCharacterReferenceState(Map<String, String> referenceMap) {
    this.referenceMap = referenceMap;
    this.optionsWithoutAmpersand = new ArrayList<>();
    for (String option: referenceMap.keySet()) {
      optionsWithoutAmpersand.add(option.substring(1));
    }
  }

  @Override
  public void consume(int ch, TokenizeContext tokenizeContext, ParseContext parseContext) throws IOException {
    // Dummy for now
    tokenizeContext.setTokenizeState(TokenizeStates.dataState);
  }

  @Override
  public boolean lookaheadMatched(String value, TokenizeContext tokenizeContext, ParseContext parseContext) {
    // TODO:Is it case sensitive?
    if (referenceMap.containsKey("&" + value)) {
      return true;
    } else {
      // TODO: Finish
      tokenizeContext.setTokenizeState(TokenizeStates.dataState);
      return false;
    }
  }

  @Override
  public List<String> lookaheadOptions() {
    return this.optionsWithoutAmpersand;
  }

  // TODO: Finish this case
  
}
