package org.zhl.parser;

import org.zhl.as.ASTLeaf;
import org.zhl.as.ASTree;
import org.zhl.exception.ParseException;
import org.zhl.lexer.Lexer;
import org.zhl.token.IdentifyToken;
import org.zhl.token.Token;

import java.util.List;

/**
 * @author zhanghanlin
 * @date 2021/11/3
 **/
public class Leaf extends Element {

    protected String[] tokens;

    protected Leaf(String[] pat) {
        tokens = pat;
    }

    @Override
    protected void parse(Lexer lexer, List<ASTree> res) throws ParseException {
        Token<?> t = lexer.read();
        if (t instanceof IdentifyToken) {
            for (String token : tokens) {
                if (token.equals(t.getContent())) {
                    find(res, t);
                    return;
                }
            }
        }
        if (tokens.length > 0) {
            throw new ParseException();
        } else {
            throw new ParseException();
        }
    }

    protected void find(List<ASTree> res, Token<?> t) {
        res.add(new ASTLeaf(t));
    }

    @Override
    protected boolean match(Lexer lexer) throws ParseException {
        Token<?> t = lexer.peek(0);
        if (t instanceof IdentifyToken) {
            for (String token : tokens) {
                if (token.equals(t.getContent())) {
                    return true;
                }
            }
        }
        return false;
    }
}
