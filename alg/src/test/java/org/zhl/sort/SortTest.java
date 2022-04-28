package org.zhl.sort;

import com.google.common.base.Stopwatch;
import org.junit.jupiter.api.*;
import org.zhl.TestData;

import java.util.concurrent.TimeUnit;

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
    void testInsertionSort() {
        sortTemple(new InsertionSort<Integer>());
    }

    @RepeatedTest(10)
    void testSectionSort() {
        sortTemple(new SelectionSort<Integer>());
    }

    @RepeatedTest(10)
    void testMergeSort() {
        sortTemple(new MergeSort<Integer>());
    }

    @RepeatedTest(10)
    void testQuickSort() {
        sortTemple(new QuickSort<Integer>());
    }

    @RepeatedTest(10)
    void testCountingSort() {
        Integer[] data = getSortTestData();
        CountingSort countingSort = new CountingSort();
        countingSort.sort(data);
    }

    @RepeatedTest(10)
    void testBSSort() {
        sortTemple(new BubbleSortOptimization2());
    }

    private void sortTemple(Sort sort) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        Integer[] data = getSortTestData(100000, 10000);
        sort.sort(data);
        System.out.println(sort.getClass().getSimpleName() + "==>>" + stopwatch.elapsed(TimeUnit.MILLISECONDS) + "ms");
        stopwatch.stop();
        Assertions.assertTrue(sort.isSort(data));
    }

}