package org.zhl.structure;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingQueue;

import com.google.common.collect.Lists;

public class TreeUtil {

    /**
     * 深度遍历
     *
     * @param root
     */
    public static void deep(BinarySearchTree.Node root) {

        Stack<BinarySearchTree.Node> stack = new Stack<>();

        List<BinarySearchTree.Node> result = Lists.newArrayList();

        stack.push(root);

        while (!stack.isEmpty()) {

            BinarySearchTree.Node top = stack.pop();
            result.add(top);

            List<BinarySearchTree.Node> children = top.getChildren();

            if (children != null && children.size() > 0) {

                for (int i = children.size() - 1; i >= 0; i--) {
                    stack.push(children.get(i));
                }

            }

        }

        result.forEach(System.out::print);
        System.out.println("");

    }

    /**
     * 广度优先
     * @param root
     */
    public static void breadth(BinarySearchTree.Node root){

        Queue<BinarySearchTree.Node> queue = new LinkedBlockingQueue<>();
        List<BinarySearchTree.Node> result = new ArrayList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            BinarySearchTree.Node first = queue.poll();
            result.add(first);
            List<BinarySearchTree.Node> children = first.getChildren();
            if (children != null && children.size() > 0) {
                for (int i = 0; i < children.size(); i++) {
                    queue.add(children.get(i));
                }
            }
        }

        result.forEach(System.out::print);
        System.out.println("");
    }



}
