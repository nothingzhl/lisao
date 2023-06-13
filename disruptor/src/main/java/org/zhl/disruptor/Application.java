package org.zhl.disruptor;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.WaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.nio.ByteBuffer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zhanghanlin
 * @date 2023/6/9
 **/
public class Application {

    public static void main(String[] args) {

        final DataFactory dataFactory = new DataFactory();
        int ringBuffSize = 1024*1024;
        final ExecutorService executorService =
            Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        Disruptor<Data> disruptor = new Disruptor<>(dataFactory,ringBuffSize,executorService, ProducerType.SINGLE,
            new BlockingWaitStrategy());

        disruptor.handleEventsWith(new DataHandler());

        disruptor.start();

        final RingBuffer<Data> ringBuffer = disruptor.getRingBuffer();

        final DataProducer<Data> dataProducer = new DataProducer<>(ringBuffer);

        ByteBuffer bb = ByteBuffer.allocate(8);

        for (int i = 0; i < 100; i++) {
            bb.putLong(0,i);
            dataProducer.sendData(bb);
        }

        disruptor.shutdown();
        executorService.shutdown();
    }
}
