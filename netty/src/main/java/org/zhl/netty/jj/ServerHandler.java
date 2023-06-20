package org.zhl.netty.jj;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.zhl.netty.protool.req.LoginRequestPacket;
import org.zhl.netty.protool.PacketCodeC;
import org.zhl.netty.protool.res.LoginResponsePacket;

/**
 * @author zhanghanlin
 * @date 2023/6/15
 **/
public class ServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        final ByteBuf request = (ByteBuf)msg;


        final LoginRequestPacket loginRequestPacket = PacketCodeC.DEFAULT_INSTANCE.decode(request, LoginRequestPacket.class);

        final LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
        loginResponsePacket.setVersion(loginRequestPacket.getVersion());
        if (valid(loginRequestPacket)){
            System.out.println(loginRequestPacket);
            loginResponsePacket.setSuccess(true);
        }else {
            loginResponsePacket.setSuccess(false);
            loginResponsePacket.setReason("账户密码校验失败！");
        }

        final ByteBuf responseBuf = PacketCodeC.DEFAULT_INSTANCE.encode(loginResponsePacket);
        ctx.channel().writeAndFlush(responseBuf);
    }

    private boolean valid(LoginRequestPacket packet){
        return true;
    }
}
