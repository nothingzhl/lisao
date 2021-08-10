package org.zhl.func;

import org.junit.jupiter.api.Test;
import org.zhl.func.self.SelfCollector;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.IntStream;

/**
 * @author zhanghanlin
 */
public class SteamTest {

    private Collector<Integer, Map<Integer, Integer>, Map<Integer, Integer>> getCollector() {
        return Collector.of(HashMap::new, (map, element) -> map.put(element, element), (leftMap, rightMap) -> {
            leftMap.putAll(rightMap);
            return leftMap;
        }, Collector.Characteristics.CONCURRENT);
    }

    @Test
    void testSelfCollector() {
        final Set<Integer> collect = IntStream.range(0, 100).boxed().collect(new SelfCollector<>());
        System.out.println(collect);
    }

    @Test
    void testCollectorWithFactory() {
        final Map<Integer, Integer> collect = IntStream.range(0, 100).boxed().collect(getCollector());
        System.out.println(collect);
    }

    @Test
    void name() {
        
    }
}
