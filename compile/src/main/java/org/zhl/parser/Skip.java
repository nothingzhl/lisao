package org.zhl.parser;

import org.zhl.as.ASTree;
import org.zhl.token.Token;

import java.util.List;

/**
 * @author zhanghanlin
 * @date 2021/11/3
 **/
public class Skip extends Leaf{
    protected Skip(String[] t) { super(t); }
    @Override
    protected void find(List<ASTree> res, Token<?> t) {}
}
