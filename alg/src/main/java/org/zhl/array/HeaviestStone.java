package org.zhl.array;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 最后一块石头
 */
public class HeaviestStone {

    public static void main(String[] args) {
        int[] stones = new int[] {
                2, 7, 4, 1, 8, 1
        };

        int i = lastStoneWeightWithHeap(stones);
        System.out.println(i);
    }

    public static int lastStoneWeightWithSort(int[] stones) {

        int index = stones.length;

        while (index > 1) {
            Arrays.sort(stones);
            stones[index - 2] = stones[index - 1] - stones[index - 2];
            stones[index - 1] = Integer.MAX_VALUE;
            index--;
        }
        return stones[index - 1];

    }

    public static int lastStoneWeightWithHeap(int[] stones) {

        Queue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);

        // 构造大顶堆
        for (int stone : stones) {
            pq.offer(stone);
        }

        while (pq.size() > 1) {
            Integer max = pq.poll();
            Integer lmax = pq.poll();

            // 相等就不放入
            if (max > lmax) {
                pq.offer(max - lmax);
            }
        }

        return pq.isEmpty() ? 0 : pq.poll();

    }

}
