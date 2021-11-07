package org.zhl.parser;

/**
 * @author zhanghanlin
 * @date 2021/11/3
 **/
public class Precedence {

    int value;
    /**
     * left associative
     */
    boolean leftAssoc;

    public Precedence(int v, boolean a) {
        value = v;
        leftAssoc = a;
    }

}
