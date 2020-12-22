package org.zhl.sort;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.zhl.TestData;

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

    private void sortTemple(Sort sort){
        Integer[] data = getSortTestData();
        sort.sort(data);
        Assertions.assertTrue(sort.isSort(data));
    }

}