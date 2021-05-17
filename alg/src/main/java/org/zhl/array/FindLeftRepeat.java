package org.zhl.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: lisao
 * @description:
 * @author: zhanghanlin
 * @create: 2021-05-17 21:40
 **/
public class FindLeftRepeat {

    public static void main(String[] args) {
        int[] origin = {1, 3, 1, 2, 1};
        final int[] ints = findLeft(origin);
        System.out.println(Arrays.toString(ints));
        final int[] withMap = findWithMap(origin);
        System.out.println(Arrays.toString(withMap));
    }

    public static int[] findLeft(int[] origin) {
        int[] target = new int[origin.length];
        Arrays.fill(target, -1);

        for (int i = 0; i < origin.length; i++) {
            int index = i;
            if (target[i] != -1) {
                continue;
            }
            for (int j = i + 1; j < origin.length; j++) {
                if (origin[i] == origin[j]) {
                    target[j] = index;
                    index = j;
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
                map.put(origin[i], i);
            } else {
                target[i] = map.get(origin[i]);
                map.replace(origin[i], i);
            }
        }
        return target;

    }
}
