package org.zhl.disruptor;

import com.lmax.disruptor.EventFactory;

/**
 * @author zhanghanlin
 * @date 2023/6/9
 **/

public class DataFactory implements EventFactory<Data> {
    @Override
    public Data newInstance() {
        return new Data();
    }
}
