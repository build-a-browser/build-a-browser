package net.buildabrowser.babbrowser.cssbase.intermediate;

import java.util.List;

import net.buildabrowser.babbrowser.cssbase.tokens.Token;

public record SimpleBlock(Token type, List<Token> value) implements Token {

}
