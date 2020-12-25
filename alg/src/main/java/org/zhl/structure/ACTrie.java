package org.zhl.structure;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * AC 自动机
 * todo:还未实现
 */
public class ACTrie {






    @Data
    @RequiredArgsConstructor
    public class AcNode {

        @NonNull
        private char data;
        private AcNode[] children = new AcNode[26];
        private boolean isEndingChar = false;
        private int length = -1;
        /**
         * 失败指针
         */
        private AcNode fail;

    }

}
