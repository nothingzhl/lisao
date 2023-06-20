package org.zhl.netty.protool;

import io.netty.buffer.ByteBuf;

public interface SerializerAlgorithm {

    byte JSON = 1;


    default byte parseAlgorithm(ByteBuf byteBuf){
    }
}
