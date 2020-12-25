package org.zhl.structure;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TrieTreeTest {

    @Test
    void testTrieTree() {
        TrieTree trieTree = new TrieTree();
        trieTree.insert("hello".toCharArray());
        trieTree.insert("her".toCharArray());
        trieTree.insert("he".toCharArray());

        boolean b = trieTree.find("he".toCharArray());
        System.out.println(b);
    }
}