package org.zhl.token;

/**
 * @author zhanghanlin
 * @date 2021/10/31
 **/
public class StringToken extends Token<String>{

    private String content;

    public StringToken(int lineNumber, String content) {
        super(lineNumber);
        this.content = content;
    }

    @Override
    public TokenType getType() {
        return TokenType.String;
    }

    @Override
    public String getContent() {
        return content;
    }
}
