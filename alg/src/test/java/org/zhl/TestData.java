package org.zhl;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

public class TestData {

    private Random random = ThreadLocalRandom.current();

    protected Integer[] getSortTestData() {
        return IntStream.range(0, 100)
                .map(item -> random.nextInt(100))
                .distinct()
                .mapToObj(Integer::new)
                .toArray(Integer[]::new);
    }

    protected Integer[] getSearchTestData() {
        return IntStream.range(0, 100)
                .map(item -> random.nextInt(100))
                .distinct()
                .mapToObj(Integer::new)
                .sorted(Integer::compareTo)
                .toArray(Integer[]::new);
    }

}
