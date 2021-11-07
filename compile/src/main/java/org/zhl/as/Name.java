package org.zhl.as;

import org.zhl.token.Token;

/**
 * @author zhanghanlin
 * @date 2021/11/2
 **/
public class Name extends ASTLeaf{
    public Name(Token<?> token) {
        super(token);
    }
    public String name(){
        return (String)token.getContent();
    }
}
