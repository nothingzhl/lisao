package org.zhl.structure;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

class BinarySearchTreeTest {

    private Random random = ThreadLocalRandom.current();

    BinarySearchTree binarySearchTree;

    @BeforeEach
    void setUp() {
        binarySearchTree = new BinarySearchTree();
        IntStream.range(0, 100)
                .map(item -> random.nextInt(100))
                .distinct()
                .forEach(binarySearchTree::insert);
    }

    @Test
    void testFind() {
        TreeUtil.deep(binarySearchTree.getTree());
        System.out.println("-------------");
        TreeUtil.breadth(binarySearchTree.getTree());
        BinarySearchTree.Node node = binarySearchTree.find(10);
    }

    @RepeatedTest(10)
    void testDelete() {
        binarySearchTree.delete(19);
    }
}