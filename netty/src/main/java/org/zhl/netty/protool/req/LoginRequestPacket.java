package org.zhl.netty.protool.req;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.zhl.netty.protool.Command;
import org.zhl.netty.protool.Packet;

/**
 * @author zhanghanlin
 * @date 2023/6/14
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class LoginRequestPacket extends Packet {

    private Integer userId;

    private String userName;

    private String password;

    @Override
    public Byte getCommand() {
        return Command.LOGIN_REQUEST;
    }
}
