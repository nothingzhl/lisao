package org.zhl.parser.expr;

import org.zhl.as.ASTList;
import org.zhl.as.ASTree;

import java.util.List;

/**
 * @author zhanghanlin
 * @date 2021/11/6
 **/
public class PrimaryExpr extends ASTList {
    public PrimaryExpr(List<ASTree> children) {
        super(children);
    }
    public static ASTree create(List<ASTree> c){
        return c.size() == 1 ? c.get(0): new PrimaryExpr(c);
    }
}
