package net.buildabrowser.babbrowser.htmlparser.tokenize.imp;

import net.buildabrowser.babbrowser.htmlparser.tokenize.TokenizeState;
import net.buildabrowser.babbrowser.htmlparser.tokenize.states.AfterAttributeValueQuotedState;
import net.buildabrowser.babbrowser.htmlparser.tokenize.states.AfterDoctypeNameState;
import net.buildabrowser.babbrowser.htmlparser.tokenize.states.AttributeNameState;
import net.buildabrowser.babbrowser.htmlparser.tokenize.states.AttributeValueDoubleQuotedState;
import net.buildabrowser.babbrowser.htmlparser.tokenize.states.BeforeAttributeNameState;
import net.buildabrowser.babbrowser.htmlparser.tokenize.states.BeforeAttributeValueState;
import net.buildabrowser.babbrowser.htmlparser.tokenize.states.BeforeDoctypeNameState;
import net.buildabrowser.babbrowser.htmlparser.tokenize.states.DataState;
import net.buildabrowser.babbrowser.htmlparser.tokenize.states.DoctypeNameState;
import net.buildabrowser.babbrowser.htmlparser.tokenize.states.DoctypeState;
import net.buildabrowser.babbrowser.htmlparser.tokenize.states.EndTagOpenState;
import net.buildabrowser.babbrowser.htmlparser.tokenize.states.MarkupDeclarationOpenState;
import net.buildabrowser.babbrowser.htmlparser.tokenize.states.RawTextEndTagNameState;
import net.buildabrowser.babbrowser.htmlparser.tokenize.states.RawTextEndTagOpenState;
import net.buildabrowser.babbrowser.htmlparser.tokenize.states.RawTextLessThanSignState;
import net.buildabrowser.babbrowser.htmlparser.tokenize.states.RawTextState;
import net.buildabrowser.babbrowser.htmlparser.tokenize.states.SelfClosingStartTagState;
import net.buildabrowser.babbrowser.htmlparser.tokenize.states.TagNameState;
import net.buildabrowser.babbrowser.htmlparser.tokenize.states.TagOpenState;

public final class TokenizeStates {
  
  private TokenizeStates() {}

  public static final TokenizeState afterAttributeValueQuotedState = new AfterAttributeValueQuotedState();
  public static final TokenizeState afterAttributeNameState = new AttributeNameState();
  public static final TokenizeState afterDoctypeNameState = new AfterDoctypeNameState();
  public static final TokenizeState attributeValueDoubleQuotedState = new AttributeValueDoubleQuotedState();
  public static final TokenizeState beforeAttributeNameState = new BeforeAttributeNameState();
  public static final TokenizeState beforeAttributeValueState = new BeforeAttributeValueState();
  public static final TokenizeState beforeDoctypeNameState = new BeforeDoctypeNameState();
  public static final TokenizeState dataState = new DataState();
  public static final TokenizeState doctypeNameState = new DoctypeNameState();
  public static final TokenizeState doctypeState = new DoctypeState();
  public static final TokenizeState endTagOpenState = new EndTagOpenState();
  public static final TokenizeState markupDeclarationOpenState = new MarkupDeclarationOpenState();
  public static final TokenizeState rawTextEndTagNameState = new RawTextEndTagNameState();
  public static final TokenizeState rawTextEndTagOpenState = new RawTextEndTagOpenState();
  public static final TokenizeState rawTextLessThanSignState = new RawTextLessThanSignState();
  public static final TokenizeState rawTextState = new RawTextState();
  public static final TokenizeState selfClosingStartTagState = new SelfClosingStartTagState();
  public static final TokenizeState tagNameState = new TagNameState();
  public static final TokenizeState tagOpenState = new TagOpenState();

}
