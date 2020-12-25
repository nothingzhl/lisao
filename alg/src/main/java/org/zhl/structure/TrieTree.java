package org.zhl.structure;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * Trie树的实现
 */
public class TrieTree {

    /**
     * 存储无意义字符
     */
    private TrieNode root = new TrieNode('/');

    /**
     * 在TrieTree中插入一个字符串
     *
     * @param text
     */
    public void insert(char[] text) {
        TrieNode p = root;
        for (int i = 0; i < text.length; i++) {
            int index = text[i] - 'a';
            if (p.children[index] == null) {
                TrieNode trieNode = new TrieNode(text[i]);
                p.children[index] = trieNode;
            }
            p = p.children[index];
        }
        p.isEndingChar = true;
    }

    /**
     * 在Trie树中查找一个字符串
     *
     * @param pattern
     *
     * @return
     */
    public boolean find(char[] pattern) {

        TrieNode p = root;

        for (int i = 0; i < pattern.length; i++) {
            int index = pattern[i] - 'a';
            if (p.children[index] == null) {
                return false;
            }
            p = p.children[index];
        }

        // 不能完全匹配，只是前缀
        if (p.isEndingChar == false) {
            return false;
        } else {
            return true;
        }

    }

    @Data
    class TrieNode {

        private char data;
        private TrieNode[] children = new TrieNode[26];
        public boolean isEndingChar = false;

        public TrieNode(char data) {
            this.data = data;
        }
    }

}
