package org.zhl.sort;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

class SortTest extends TestData {

    @RepeatedTest(10)
    void testBubbleSort() {
        sortTemple(new BubbleSort<Integer>());
    }

    @RepeatedTest(10)
    void testBubbleSortOptimization() {
        sortTemple(new BubbleSortOptimization<Integer>());
    }

    @RepeatedTest(10)
    void testInsertionSort(){
        sortTemple(new InsertionSort<Integer>());
    }

    @RepeatedTest(10)
    void testSectionSort() {
        sortTemple(new SelectionSort<Integer>());
    }

    @RepeatedTest(10)
    void testMergeTest() {
        sortTemple(new MergeSort<Integer>());
    }

    private void sortTemple(Sort sort){
        Integer[] data = genTestData();
        sort.sort(data);
        Assertions.assertTrue(sort.isSort(data));
    }

}