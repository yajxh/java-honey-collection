package com.sunny.netty.chat.protocol.response;

import com.sunny.netty.chat.protocol.Packet;
import lombok.Data;

import static com.sunny.netty.chat.protocol.command.Command.LOGIN_RESPONSE;

/**
 * <Description> <br>
 *
 * @author Sunny<br>
 * @version 1.0<br>
 * @taskId: <br>
 * @createDate 2018/10/25 20:47 <br>
 * @see com.sunny.netty.chat.protocol.response <br>
 */
@Data
public class LoginResponsePacket extends Packet {

    private boolean success;

    private String reason;

    @Override
    public Byte getCommand() {
        return LOGIN_RESPONSE;
    }
}
