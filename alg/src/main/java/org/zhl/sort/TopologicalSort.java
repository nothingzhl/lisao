package org.zhl.sort;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * 拓扑排序
 */
public class TopologicalSort {

    public class Graph {
        /**
         * 顶点的个数
         */
        private int v;

        /**
         * 领接表
         */
        private LinkedList<Integer>[] adj;

        public Graph(int v) {

            this.v = v;
            adj = new LinkedList[v];
            Arrays.fill(adj, new LinkedList<Integer>());

        }

        /**
         * s 先于t,s->t
         * @param s
         * @param t
         */
        public void addEdge(int s,int t){
            adj[s].add(t);
        }

        /**
         * 拓扑排序
         */
        public void topoSortBuKahn(){

            int[] inDegree = new int[v];

            // 统计每个节点的入度
            for (int i = 0; i < v; i++) {
                for (int j = 0; j < adj[i].size(); j++) {
                    int w = adj[i].get(j);
                    inDegree[w]++;
                }
            }

            LinkedList<Integer> queue = new LinkedList<>();

            // 查找入度为0的
            for (int i = 0; i < v; i++) {
                if (inDegree[i] == 0) {
                    queue.add(i);
                }
            }

            while (!queue.isEmpty()) {
                int i =queue.remove();
                System.out.println("->" + i);
                for (int j = 0; j < adj[i].size(); j++) {
                    int k = adj[i].get(j);
                    inDegree[k]--;
                    if (inDegree[k] == 0) {
                        queue.add(k);
                    }
                }
            }

        }



    }




}
