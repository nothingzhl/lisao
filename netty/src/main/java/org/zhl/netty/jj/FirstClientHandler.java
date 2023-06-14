package org.zhl.netty.jj;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

/**
 * @author zhanghanlin
 * @date 2023/6/14
 **/
public class FirstClientHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(LocalDateTime.now()+":客户端Active写出数据");

        // 1. 获取数据
        ByteBuf buffer = getByteBuf(ctx);

        // 2. 写数据
        ctx.channel().writeAndFlush(buffer);
    }

    private ByteBuf getByteBuf(ChannelHandlerContext ctx) {
        // 获取buffer
        final ByteBuf buffer = ctx.alloc().buffer();

        // 准备数据

        final byte[] bytes = "ping".getBytes(StandardCharsets.UTF_8);

        // 填充数据
        buffer.writeBytes(bytes);

        return buffer;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        final ByteBuf buf = (ByteBuf)msg;

        final String obj = buf.toString(StandardCharsets.UTF_8);

        System.out.println(LocalDateTime.now() + ":"+"客户端读取到数据->"+ obj);
    }
}
