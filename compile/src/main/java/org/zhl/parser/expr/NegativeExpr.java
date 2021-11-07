package org.zhl.parser.expr;

import org.zhl.as.ASTList;
import org.zhl.as.ASTree;

import java.util.List;

/**
 * @author zhanghanlin
 * @date 2021/11/6
 **/
public class NegativeExpr extends ASTList {
    public NegativeExpr(List<ASTree> children) {
        super(children);
    }
    public ASTree operand(){
        return child(0);
    }

    @Override
    public String toString() {
        return "-" + operand();
    }
}
