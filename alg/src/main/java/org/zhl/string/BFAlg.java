package org.zhl.string;

import java.util.Arrays;

/**
 * BF算法
 */
public class BFAlg implements StringFind {


    @Override
    public boolean find(String origin,String match){

        char[] origins = origin.toCharArray();
        char[] matchs = match.toCharArray();

        int length = match.length();

        for (int i = 0; i < origins.length-matchs.length; i++) {
            for (int j = 0; j < length; j++) {
                if (!(origins[i+j] == matchs[j])) {
                    break;
                }
                // 到达尾部，匹配成功
                if (j == length-1) {
                    return true;
                }
            }

        }

        return false;
    }


}
