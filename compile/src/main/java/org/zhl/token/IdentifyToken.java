package org.zhl.token;

/**
 * @author zhanghanlin
 * @date 2021/10/31
 **/
public class IdentifyToken extends Token<String>{

    private String content;

    public IdentifyToken(int lineNumber,String content) {
        super(lineNumber);
        this.content = content;
    }

    @Override
    public TokenType getType() {
        return TokenType.IDENTIFIER;
    }

    @Override
    public String getContent() {
        return content;
    }
}
