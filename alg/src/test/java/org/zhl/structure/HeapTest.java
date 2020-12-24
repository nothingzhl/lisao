package org.zhl.structure;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.zhl.TestData;

class HeapTest extends TestData {

    @Test
    void testHeadInsert() {

        Heap heap = new Heap(200);

        Integer[] headTestData = getHeadTestData();

        Arrays.stream(headTestData)
                .mapToInt(Integer::intValue)
                .forEach(heap::insert);

        System.out.println(heap.getMaxValue());

    }
}