package org.zhl.parser;

import org.zhl.as.ASTLeaf;
import org.zhl.token.StringToken;
import org.zhl.token.Token;

/**
 * @author zhanghanlin
 * @date 2021/11/3
 **/
public class StrToken extends AToken{

    protected StrToken(Class<? extends ASTLeaf> type) {
        super(type);
    }

    @Override
    protected boolean test(Token<?> t) {
        return t instanceof StringToken;
    }
}
