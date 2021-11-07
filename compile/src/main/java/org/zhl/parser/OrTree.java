package org.zhl.parser;

import org.zhl.as.ASTree;
import org.zhl.exception.ParseException;
import org.zhl.lexer.Lexer;

import java.util.List;

/**
 * @author zhanghanlin
 * @date 2021/11/3
 **/
public class OrTree extends Element {

    protected Parser[] parsers;

    protected OrTree(Parser[] p) {
        parsers = p;
    }

    @Override
    protected void parse(Lexer lexer, List<ASTree> res) throws ParseException {
        Parser p = choose(lexer);
        if (p == null) {
            throw new ParseException();
        } else {
            res.add(p.parse(lexer));
        }
    }

    @Override
    protected boolean match(Lexer lexer) throws ParseException {
        return choose(lexer) != null;
    }

    protected Parser choose(Lexer lexer) throws ParseException {
        for (Parser p : parsers) {
            if (p.match(lexer)) {
                return p;
            }
        }
        return null;
    }

    protected void insert(Parser p) {
        Parser[] newParsers = new Parser[parsers.length + 1];
        newParsers[0] = p;
        System.arraycopy(parsers, 0, newParsers, 1, parsers.length);
        parsers = newParsers;
    }

}
