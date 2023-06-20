package org.zhl.netty.jj;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.zhl.netty.protool.req.LoginRequestPacket;
import org.zhl.netty.protool.PacketCodeC;

import java.time.LocalDateTime;

/**
 * @author zhanghanlin
 * @date 2023/6/15
 **/
public class ClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(LocalDateTime.now()+"客户端开始登录");

        final LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        loginRequestPacket.setVersion(Byte.valueOf("1"));
        loginRequestPacket.setUserId(1);
        loginRequestPacket.setUserName("1");
        loginRequestPacket.setPassword("1");

        ByteBuf byteBuf = PacketCodeC.DEFAULT_INSTANCE.encode(loginRequestPacket);

        ctx.channel().writeAndFlush(byteBuf);
    }
}
