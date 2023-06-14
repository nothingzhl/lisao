package org.zhl.netty.jj;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author zhanghanlin
 * @date 2023/6/14
 **/
public class FirstServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        final ByteBuf buf = (ByteBuf)msg;

        final String obj = buf.toString(StandardCharsets.UTF_8);

        System.out.println(LocalDateTime.now() + ":"+"服务端读取到数据->"+ obj);

        if (Objects.equals(obj,"ping")){
            ctx.channel().writeAndFlush(getByteBuf(ctx));
        }

    }

    private ByteBuf getByteBuf(ChannelHandlerContext ctx) {
        byte[] bytes = "pong".getBytes(StandardCharsets.UTF_8);

        ByteBuf buffer = ctx.alloc().buffer();

        buffer.writeBytes(bytes);

        return buffer;
    }

}
