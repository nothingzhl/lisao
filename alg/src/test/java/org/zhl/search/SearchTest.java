package org.zhl.search;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.zhl.TestData;

public class SearchTest extends TestData {

    @RepeatedTest(10)
    void testBSearch() {
        searchTemplate(new BSearch<>());
    }

    @RepeatedTest(10)
    void testBSearchVariantOne(){
        searchTemplate(new BSearchVariantOne<>());
    }

    @RepeatedTest(10)
    void testBSearchVariantTwo(){
        searchTemplate(new BSearchVariantTwo<>());
    }

    private void searchTemplate(Search<Integer> searchObject){
        Integer[] searchTestData = getSearchTestData();
        int length = searchTestData.length;
        int targetIndex = (int) (Math.random() * (length - 0)) + 0;
        Integer target = searchTestData[targetIndex];
        int search = searchObject.search(searchTestData, target);
        Assertions.assertEquals(targetIndex,search);
    }

}
