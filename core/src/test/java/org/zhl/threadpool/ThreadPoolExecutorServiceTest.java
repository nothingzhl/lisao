package org.zhl.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.jupiter.api.Test;

class ThreadPoolExecutorServiceTest {

    private ExecutorService executorService;

    @Test
    void testSubmit() {
       executorService = Executors.newFixedThreadPool(10);
       executorService.submit(()->"5");
       executorService.submit(()-> System.out.println(10));
    }
}