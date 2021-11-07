package org.zhl.parser.expr;

import org.zhl.as.ASTLeaf;
import org.zhl.token.Token;

/**
 * @author zhanghanlin
 * @date 2021/11/6
 **/
public class StringLiteral extends ASTLeaf {
    public StringLiteral(Token<?> token) {
        super(token);
    }
    public String value(){
        return (String)getToken().getContent();
    }
}
