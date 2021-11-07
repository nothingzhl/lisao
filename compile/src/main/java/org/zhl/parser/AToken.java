package org.zhl.parser;

import org.zhl.as.ASTLeaf;
import org.zhl.as.ASTree;
import org.zhl.exception.ParseException;
import org.zhl.lexer.Lexer;
import org.zhl.token.Token;

import java.util.List;

/**
 * @author zhanghanlin
 * @date 2021/11/3
 **/
public abstract class AToken extends Element {
    protected Factory factory;

    protected AToken(Class<? extends ASTLeaf> type) {
        if (type == null) {
            type = ASTLeaf.class;
        }
        factory = Factory.get(type, Token.class);
    }

    @Override
    protected void parse(Lexer lexer, List<ASTree> res) throws ParseException {
        Token<?> t = lexer.read();
        if (test(t)) {
            ASTree leaf = factory.make(t);
            res.add(leaf);
        } else {
            throw new ParseException();
        }
    }

    @Override
    protected boolean match(Lexer lexer) throws ParseException {
        return test(lexer.peek(0));
    }

    protected abstract boolean test(Token<?> t);
}
