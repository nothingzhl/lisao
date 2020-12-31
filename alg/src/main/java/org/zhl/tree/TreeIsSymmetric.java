package org.zhl.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 判断二叉树是不是镜像的
 */
public class TreeIsSymmetric {


    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public boolean isSymmetric(TreeNode root) {

        isE(root.left, root.right);

        return false;
    }

    public boolean isE(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        if (left == null || right == null) {
            return false;
        }

        return left.val == right.val && isE(left.left, right.right) && isE(left.right, right.left);
    }

    public boolean isSymmetric2(TreeNode root) {

        return check(root.left, root.right);

    }

    public boolean check(TreeNode q, TreeNode p) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(q);
        queue.offer(p);

        while (!queue.isEmpty()) {
            TreeNode u = queue.poll();
            TreeNode v = queue.poll();

            if (u == null && v == null) {
                continue;
            }

            if (u == null || v == null || u.val != v.val) {
                return false;
            }

            queue.offer(u.left);
            queue.offer(v.right);

            queue.offer(u.right);
            queue.offer(v.left);

        }

        return true;

    }

    public int[] bfs1(TreeNode node){
        List<Integer> ii = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(node);

        while (!queue.isEmpty()){
            TreeNode poll = queue.poll();
            ii.add(poll.val);
            if (poll.left != null) {
                queue.offer(poll.left);
            }
            if (poll.right != null) {
                queue.offer(poll.right);
            }
        }
        return ii.stream().mapToInt(Integer::intValue).toArray();
    }

    public int[] bfs2(TreeNode node){
        List<Integer> ii = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(node);

        while (!queue.isEmpty()){
            TreeNode poll = queue.poll();
            ii.add(poll.val);
            if (poll.right != null) {
                queue.offer(poll.left);
            }
            if (poll.left != null) {
                queue.offer(poll.right);
            }
        }
        return ii.stream().mapToInt(Integer::intValue).toArray();
    }

    public void d(){


    }

    public boolean iE(int[] a,int[] b){
        if (a.length != b.length) {
            return false;
        }
        for (int i = 0; i < a.length; i++) {
            if (a[i] != b[i]) {
                return false;
            }
        }
        return true;
    }

}
