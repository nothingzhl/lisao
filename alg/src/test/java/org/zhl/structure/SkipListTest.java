package org.zhl.structure;

import java.util.stream.IntStream;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

/**
 * 跳表测试类
 */
class SkipListTest {

    @Test
    void testSkipList() {
        SkipList skipList = new SkipList();
        IntStream.range(0,19)
                .forEach(skipList::insert);
        System.out.println(skipList.toString());

    }

    @RepeatedTest(10)
    void testFindSkipList() {
        SkipList skipList = new SkipList();
        IntStream.range(0,19)
                .forEach(skipList::insert);
        System.out.println(skipList.toString());
        SkipList.Node node = skipList.find(9);
        System.out.println(node.toString());
    }
}