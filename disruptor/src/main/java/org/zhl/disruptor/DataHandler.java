package org.zhl.disruptor;

import com.lmax.disruptor.EventHandler;

/**
 * @author zhanghanlin
 * @date 2023/6/9
 **/
public class DataHandler implements EventHandler<Data> {
    @Override
    public void onEvent(Data event, long sequence, boolean endOfBatch) throws Exception {
        System.out.println(sequence +":"+event);
    }
}
