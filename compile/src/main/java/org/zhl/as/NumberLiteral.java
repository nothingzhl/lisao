package org.zhl.as;

import org.zhl.token.Token;

/**
 * @author zhanghanlin
 * @date 2021/11/2
 **/
public class NumberLiteral extends ASTLeaf{
    public NumberLiteral(Token<?> token) {
        super(token);
    }
    public int value(){
        return (Integer)token.getContent();
    }
}
