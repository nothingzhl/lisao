package org.zhl.netty.protool;

import lombok.Data;

/**
 * @author zhanghanlin
 * @date 2023/6/14
 **/
@Data
public abstract class Packet {

    /**
     * 版本
     */
    private Byte version = 1;

    /**
     * 指令
     * @return
     */
    public abstract Byte getCommand();
}
