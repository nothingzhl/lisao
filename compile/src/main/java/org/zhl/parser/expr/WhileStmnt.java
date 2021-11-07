package org.zhl.parser.expr;

import org.zhl.as.ASTList;
import org.zhl.as.ASTree;

import java.util.List;

/**
 * @author zhanghanlin
 * @date 2021/11/6
 **/
public class WhileStmnt extends ASTList {
    public WhileStmnt(List<ASTree> children) {
        super(children);
    }
    public ASTree condition(){
        return child(0);
    }
    public ASTree body(){
        return child(1);
    }

    @Override
    public String toString() {
        return String.format("(while {} {})",condition(),body());
    }
}
