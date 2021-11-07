package org.zhl.parser.expr;

import org.zhl.as.ASTList;
import org.zhl.as.ASTree;

import java.util.List;

/**
 * @author zhanghanlin
 * @date 2021/11/6
 **/
public class IfStmnt extends ASTList {
    public IfStmnt(List<ASTree> children) {
        super(children);
    }
    public ASTree condition(){
        return child(0);
    }
    public ASTree thenBlock(){
        return child(1);
    }
    public ASTree elseBlock(){
        return numChildren() > 2 ? child(2) :null;
    }

    @Override
    public String toString() {
        return String.format("(if {} {} else {})",condition(),thenBlock(),elseBlock());
    }
}
