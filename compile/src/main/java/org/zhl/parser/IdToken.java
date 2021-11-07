package org.zhl.parser;

import org.zhl.as.ASTLeaf;
import org.zhl.token.IdentifyToken;
import org.zhl.token.Token;

import java.util.HashSet;
import java.util.Set;

/**
 * @author zhanghanlin
 * @date 2021/11/3
 **/
public class IdToken extends AToken{

    Set<String> reserved;
    protected IdToken(Class<? extends ASTLeaf> type, Set<String> r) {
        super(type);
        reserved = r != null ? r : new HashSet<>();
    }

    @Override
    protected boolean test(Token<?> t) {
        return t instanceof IdentifyToken;
    }
}
