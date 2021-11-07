package org.zhl.parser.expr;

import org.zhl.as.ASTList;
import org.zhl.as.ASTree;

import java.util.List;

/**
 * @author zhanghanlin
 * @date 2021/11/6
 **/
public class BlockStmnt extends ASTList {
    public BlockStmnt(List<ASTree> children) {
        super(children);
    }
}
