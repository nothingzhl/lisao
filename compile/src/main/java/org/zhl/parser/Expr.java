package org.zhl.parser;

import org.zhl.as.ASTLeaf;
import org.zhl.as.ASTree;
import org.zhl.exception.ParseException;
import org.zhl.lexer.Lexer;
import org.zhl.token.IdentifyToken;
import org.zhl.token.Token;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhanghanlin
 * @date 2021/11/3
 **/
public class Expr extends Element {

    protected Factory factory;
    protected Operators ops;
    protected Parser factor;

    protected Expr(Class<? extends ASTree> clazz, Parser exp, Operators map) {
        factory = Factory.getForASTList(clazz);
        ops = map;
        factor = exp;
    }

    @Override
    public void parse(Lexer lexer, List<ASTree> res) throws ParseException {
        ASTree right = factor.parse(lexer);
        Precedence prec;
        while ((prec = nextOperator(lexer)) != null) {
            right = doShift(lexer, right, prec.value);
        }

        res.add(right);
    }

    private ASTree doShift(Lexer lexer, ASTree left, int prec) throws ParseException {
        ArrayList<ASTree> list = new ArrayList<>();
        list.add(left);
        list.add(new ASTLeaf(lexer.read()));
        ASTree right = factor.parse(lexer);
        Precedence next;
        while ((next = nextOperator(lexer)) != null && rightIsExpr(prec, next)) {
            right = doShift(lexer, right, next.value);
        }

        list.add(right);
        return factory.make(list);
    }

    private Precedence nextOperator(Lexer lexer) throws ParseException {
        Token<?> t = lexer.peek(0);
        if (t instanceof IdentifyToken)
            return ops.get(t.getContent());
        else
            return null;
    }

    private static boolean rightIsExpr(int prec, Precedence nextPrec) {
        if (nextPrec.leftAssoc) {
            return prec < nextPrec.value;
        } else {
            return prec <= nextPrec.value;
        }
    }

    @Override
    protected boolean match(Lexer lexer) throws ParseException {
        return factor.match(lexer);
    }

}
