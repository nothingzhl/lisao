package org.zhl.netty.protool;

import lombok.Data;

/**
 * @author zhanghanlin
 * @date 2023/6/14
 **/
@Data
public class LoginRequestPacket extends Packet{

    private Integer userId;

    private String userName;

    private String password;

    @Override
    public Byte getCommand() {
        return Command.LOGIN_REQUEST;
    }
}
