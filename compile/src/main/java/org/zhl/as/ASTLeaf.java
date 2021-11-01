package org.zhl.as;

import org.zhl.token.Token;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * astleaf
 *
 * @author zhanghanlin
 * @date 2021/10/31
 **/
public class ASTLeaf extends AsTree{

    private static List<AsTree> empty = new ArrayList<>();

    protected Token<?> token;

    public ASTLeaf(Token<?> token) {
        this.token = token;
    }

    @Override
    public AsTree child(int i) {
        throw new IndexOutOfBoundsException();
    }

    @Override
    public int numChildren() {
        return 0;
    }

    @Override
    public Iterator<AsTree> children() {
        return empty.iterator();
    }

    @Override
    public String location() {
        return "at line :" + token.getLineNumber();
    }

    @Override
    public String toString() {
        return token.getContent().toString();
    }
    public Token<?> getToken(){
        return token;
    }
}
