package org.zhl.parser;

import org.zhl.as.ASTLeaf;
import org.zhl.token.NumberToken;
import org.zhl.token.Token;

/**
 * @author zhanghanlin
 * @date 2021/11/3
 **/
public class NumToken extends AToken {
    public NumToken(Class<? extends ASTLeaf> clazz) {
        super(clazz);
    }

    @Override
    protected boolean test(Token<?> t) {
        return t instanceof NumberToken;
    }
}
