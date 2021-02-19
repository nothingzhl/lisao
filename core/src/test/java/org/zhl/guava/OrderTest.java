package org.zhl.guava;

import com.github.jsonzou.jmockdata.JMockData;
import com.google.common.collect.Ordering;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 */
public class OrderTest {

    @Test
    void testOrder() {
        final int[] mock = JMockData.mock(int[].class);
        final Ordering<Comparable> reverse = Ordering.natural().reverse();
        final List<Integer> collect = Arrays.stream(mock).boxed().collect(Collectors.toList());
        Collections.sort(collect, reverse);
        collect.forEach(System.out::println);
    }
}
