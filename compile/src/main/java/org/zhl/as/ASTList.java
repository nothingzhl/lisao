package org.zhl.as;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 * @author zhanghanlin
 * @date 2021/10/31
 **/
public class ASTList extends  AsTree{

    protected List<AsTree> children;

    public ASTList(List<AsTree> children) {
        this.children = children;
    }

    @Override
    public AsTree child(int i) {
        return children.get(i);
    }

    @Override
    public int numChildren() {
        return children.size();
    }

    @Override
    public Iterator<AsTree> children() {
        return children.iterator();
    }

    @Override
    public String location() {
        for (AsTree item : children) {
            String s = item.location();
            if (Objects.nonNull(s)) {
                return s;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        String sep = "";
        for (AsTree child : children) {
           sb.append(sep);
           sep= " ";
           sb.append(child.toString());
        }
        return sb.append(")").toString();
    }
}
