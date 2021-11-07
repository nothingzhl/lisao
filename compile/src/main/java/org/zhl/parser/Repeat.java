package org.zhl.parser;

import org.zhl.as.ASTList;
import org.zhl.as.ASTree;
import org.zhl.exception.ParseException;
import org.zhl.lexer.Lexer;

import java.util.List;

/**
 * @author zhanghanlin
 * @date 2021/11/3
 **/
public class Repeat extends Element {
    protected Parser parser;
    protected boolean onlyOnce;

    protected Repeat(Parser p, boolean once) {
        parser = p;
        onlyOnce = once;
    }

    @Override
    protected void parse(Lexer lexer, List<ASTree> res) throws ParseException {
        while (parser.match(lexer)) {
            ASTree t = parser.parse(lexer);
            if (t.getClass() != ASTList.class || t.numChildren() > 0) {
                res.add(t);
            }
            if (onlyOnce) {
                break;
            }
        }
    }

    @Override
    protected boolean match(Lexer lexer) throws ParseException {
        return parser.match(lexer);
    }
}
