package org.zhl.as;

import java.util.Iterator;

/**
 * @author zhanghanlin
 * @date 2021/10/31
 **/
public abstract class AsTree  implements Iterable<AsTree>{

    /**
     * 节点
     *
     * @param i 我
     * @return {@link AsTree}
     */
    public abstract AsTree child(int i);

    /**
     * n节点数
     *
     * @return int
     */
    public  abstract  int numChildren();

    /**
     * 迭代器
     *
     * @return {@link Iterator<AsTree>}
     */
    public abstract Iterator<AsTree> children();

    /**
     * 位置
     *
     * @return {@link String}
     */
    public abstract String location();

    @Override
    public Iterator<AsTree> iterator() {
       return children();
    }
}
