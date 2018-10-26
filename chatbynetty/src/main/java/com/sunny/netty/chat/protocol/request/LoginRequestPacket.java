package com.sunny.netty.chat.protocol.request;


import com.sunny.netty.chat.protocol.Packet;
import lombok.Data;

import static com.sunny.netty.chat.protocol.command.Command.LOGIN_REQUEST;

/**
 * <Description> <br>
 *
 * @author Sunny<br>
 * @version 1.0<br>
 * @taskId: <br>
 * @createDate 2018/10/25 16:26 <br>
 * @see com.sunny.netty.chat.protocol.request <br>
 */
@Data
public class LoginRequestPacket extends Packet {
    private String userId;

    private String username;

    private String password;

    @Override
    public Byte getCommand() {
        return LOGIN_REQUEST;
    }
}
