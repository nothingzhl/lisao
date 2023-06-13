package org.zhl.disruptor;

import com.lmax.disruptor.RingBuffer;

import java.nio.ByteBuffer;

/**
 * @author zhanghanlin
 * @date 2023/6/12
 **/
public class DataProducer<E> {

    private RingBuffer<E> ringBuffer;
    public DataProducer(RingBuffer<E> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    public void sendData(ByteBuffer bb){
        // ringBuff 的可用的序号
        final long sequence = ringBuffer.next();
        try {
            // 根据sequence 找编码
            final Data e = (Data)ringBuffer.get(sequence);
            e.setValue(bb.getLong(0));
        } finally {
            ringBuffer.publish(sequence);
        }
    }
}
