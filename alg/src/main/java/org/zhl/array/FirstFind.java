package org.zhl.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: lisao
 * @description:
 * @author: zhanghanlin
 * @create: 2021-05-17 21:15
 **/
public class FirstFind {

    public static void main(String[] args) {
        int[] origin = {1, 3, 1, 2, 1};
        final int[] ints = find(origin);
        System.out.println(Arrays.toString(ints));
        final int[] withMap = findWithMap(origin);
        System.out.println(Arrays.toString(withMap));
    }

    public static int[] find(int[] origin) {
        int[] target = new int[origin.length];
        Arrays.fill(target, -1);
        for (int i = 0; i < origin.length; i++) {
            if (target[i] != -1) {
                continue;
            }
            for (int j = i + 1; j < origin.length; j++) {
                if (origin[j] == origin[i]) {
                    target[j] = 0;
                }
            }
        }
        return target;
    }

    public static int[] findWithMap(int[] origin) {
        int[] target = new int[origin.length];
        Arrays.fill(target, -1);
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < origin.length; i++) {
            if (map.get(origin[i]) == null) {
                map.put(origin[i], 0);
            } else {
                target[i] = 0;
            }
        }
        return target;

    }

}
