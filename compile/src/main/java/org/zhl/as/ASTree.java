package org.zhl.as;

import java.util.Iterator;

/**
 * @author zhanghanlin
 * @date 2021/10/31
 **/
public abstract class ASTree implements Iterable<ASTree>{

    /**
     * 节点
     *
     * @param i 我
     * @return {@link ASTree}
     */
    public abstract ASTree child(int i);

    /**
     * n节点数
     *
     * @return int
     */
    public  abstract  int numChildren();

    /**
     * 迭代器
     *
     * @return {@link Iterator< ASTree >}
     */
    public abstract Iterator<ASTree> children();

    /**
     * 位置
     *
     * @return {@link String}
     */
    public abstract String location();

    @Override
    public Iterator<ASTree> iterator() {
       return children();
    }
}
