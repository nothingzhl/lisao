package org.zhl.as;

import lombok.NoArgsConstructor;
import org.zhl.token.Token;

import java.util.List;

/**
 * @author zhanghanlin
 * @date 2021/11/2
 **/
public class BinaryExpr extends ASTList{

    public BinaryExpr(List<ASTree> children) {
        super(children);
    }

    public ASTree left(){
        return child(0);
    }
    public String operator(){
        return (String)((ASTLeaf)child(1)).token.getContent();
    }
    public ASTree right(){
        return child(2);
    }
}
