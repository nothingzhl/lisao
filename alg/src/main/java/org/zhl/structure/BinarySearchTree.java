package org.zhl.structure;

import java.util.List;

import com.google.common.collect.Lists;

import lombok.Data;
import lombok.Getter;

/**
 * 二叉查找树
 */
@Getter
public class BinarySearchTree {

    private Node tree;

    /**
     * 查找树
     *
     * @param data
     *
     * @return
     */
    public Node find(int data) {
        Node p = tree;
        while (p != null) {
            if (data < p.data) {
                p = p.left;
            } else if (data > p.data) {
                p = p.right;
            } else {
                return p;
            }
        }
        return null;
    }

    /**
     * 插入
     *
     * @param data
     */
    public void insert(int data) {
        if (tree == null) {
            tree = new Node(data);
            return;
        }

        Node p = tree;

        while (p != null) {
            if (data > p.data) {
                if (p.right == null) {
                    p.right = new Node(data);
                    return;
                }
                p = p.right;
            } else {

                if (p.left == null) {
                    p.left = new Node(data);
                    return;
                }
                p = p.left;
            }
        }

    }

    public void delete(int data) {
        // p 指向要删除的节点，初始化指向根节点
        Node p = tree;
        // pp 记录的是p的父节点
        Node pp = null;

        // 寻找要删除的节点
        while (p != null && p.data != data) {
            pp = p;
            if (data > p.data) {
                p = p.right;
            } else {
                p = p.left;
            }
        }

        // 没有找到
        if (p == null) {
            return;
        }

        // 要删除的节点有俩个子节点
        // 查找右子树中最小节点
        if (p.left != null && p.right != null) {

            Node minP = p.right;
            // minPP 表示minP的父节点
            Node minPP = p;

            // 最小节点没有左子节点
            while (minP.left != null) {
                minPP = minP;
                minP = minP.left;
            }

            // 将minP的数据替换到p中
            p.data = minP.data;

            p = minP;

            // 下面就变成了删除minP
            pp = minPP;
        }

        // 删除节点是叶子节点或者仅有一个子节点
        // p 的子节点
        Node child;
        if (p.left != null) {
            child = p.left;
        } else if (p.right != null) {
            child = p.right;
        } else {
            child = null;
        }

        // 删除的是根节点
        if (pp == null) {
            tree = child;
        } else if (pp.left == p) {
            pp.left = child;
        } else {
            pp.right = child;
        }

    }

    @Data
    public static class Node {
        private int data;
        private Node left;
        private Node right;

        public Node(int data) {
            this.data = data;
        }

        public List<Node> getChildren() {
            List<Node> result = Lists.newArrayList();
            if (left != null) {
                result.add(left);
            }
            if (right != null){
                result.add(right);
            }
            return result;
        }

        @Override
        public String toString() {
            return String.valueOf(data)+",";
        }
    }

}
