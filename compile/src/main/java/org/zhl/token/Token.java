package org.zhl.token;

import org.apache.commons.lang3.math.NumberUtils;

/**
 * @author zhanghanlin
 * @date 2021/10/31
 **/
public abstract class Token<T> {

    /**
     * end of File
     */
    public static final Token EOF = new Token(NumberUtils.INTEGER_MINUS_ONE) {
        @Override
        public TokenType getType() {
            return null;
        }

        @Override
        public Object getContent() {
            return null;
        }
    };

    /**
     * end of line
     */
    public static final String EOL = "\\n";

    /**
     * 行号
     */
    private int lineNumber;

    public Token(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    /**
     * 类型
     *
     * @return {@link TokenType}
     */
    public abstract TokenType getType();

    /**
     * 内容
     *
     * @return {@link T}
     */
    public abstract T getContent();
}
