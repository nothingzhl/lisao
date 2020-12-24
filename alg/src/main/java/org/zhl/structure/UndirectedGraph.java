package org.zhl.structure;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 无向图的实现:<br/>
 * 使用领接表
 */
public class UndirectedGraph {

    // 顶点的个数
    private int v;

    //邻接表
    private LinkedList<Integer> adj[];

    public UndirectedGraph(int v) {
        this.v = v;
        adj = new LinkedList[v];
        for (int i = 0; i < v; i++) {
            adj[i] = new LinkedList<>();
        }
    }

    /**
     * 加边<br/>
     * 无向图一条边存俩次
     *
     * @param s
     * @param t
     */
    public void addEdge(int s, int t) {
        adj[s].add(t);
        adj[t].add(s);
    }

    /**
     * 广度优先搜索
     *
     * @param s 启动点
     * @param t 结束点
     */
    public void bfs(int s, int t) {

        if (s == t) {
            return;
        }

        // 记录被访问的点
        boolean[] visited = new boolean[v];
        visited[s] = true;

        // 广度一般都用queue
        Queue<Integer> queue = new LinkedList<>();
        // 压入启动点
        queue.add(s);

        // 记录搜索路径
        int[] prev = new int[v];
        Arrays.fill(prev, -1);

        while (queue.size() != 0) {

            int w = queue.poll();

            for (int i = 0; i < adj[w].size(); i++) {

                int q = adj[w].get(i);

                if (!visited[q]) {
                    prev[q] = w;
                    if (q == t) {
                        print(prev, s, t);
                        return;
                    }
                    visited[q] = true;
                    queue.add(q);
                }

            }
        }

    }

    boolean found = false;

    /**
     * 深度遍历
     *
     * @param s
     * @param t
     */
    public void dfs(int s, int t) {
        found = false;
        boolean[] visited = new boolean[v];
        int[] prev = new int[v];
        Arrays.fill(prev, -1);
        recurDfs(s, t, visited, prev);
        print(prev, s, t);
    }

    private void recurDfs(int w, int t, boolean[] visited, int[] prev) {

        // 已经遍历到t
        if (found == true) {
            return;
        }

        // 设置访问标志位
        visited[w] = true;

        // 判断是否遍历到t
        if (w == t) {
            found = true;
            return;
        }

        for (int i = 0; i < adj[w].size(); i++) {
            int q = adj[w].get(i);
            if (!visited[q]) {
                prev[q] = w;
                recurDfs(q, t, visited, prev);
            }
        }

    }

    /**
     * 打印s->t的路径
     *
     * @param prev
     * @param s
     * @param t
     */
    private void print(int[] prev, int s, int t) {
        if (prev[t] != -1 && t != s) {
            print(prev, s, prev[t]);
        }
        System.out.println(t + "");
    }

}
