package org.zhl.sort;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

public class TestData {

    private Random random = ThreadLocalRandom.current();

    protected Integer[] genTestData() {
        return IntStream.range(0, 100)
                .map(item -> random.nextInt(100))
                .distinct()
                .mapToObj(Integer::new)
                .toArray(Integer[]::new);
    }

}
