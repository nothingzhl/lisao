package org.zhl.netty.protool.res;

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
public class LoginResponsePacket extends Packet {

    private Boolean success;

    private String reason;

    @Override
    public Byte getCommand() {
        return Command.LOGIN_RESPONSE;
    }
}
