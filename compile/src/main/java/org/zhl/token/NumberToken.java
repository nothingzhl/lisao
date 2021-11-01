package org.zhl.token;

/**
 * @author zhanghanlin
 * @date 2021/10/31
 **/
public class NumberToken extends Token<Integer>{

    private Integer content;

    public NumberToken(int lineNumber, Integer content) {
        super(lineNumber);
        this.content = content;
    }

    @Override
    public TokenType getType() {
        return TokenType.NUMBER;
    }

    @Override
    public Integer getContent() {
        return content;
    }
}
