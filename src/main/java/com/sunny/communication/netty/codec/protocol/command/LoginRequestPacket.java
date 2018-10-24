package com.sunny.communication.netty.codec.protocol.command;

import lombok.Data;

import static com.sunny.communication.netty.codec.protocol.command.Command.LOGIN_REQUEST;

/**
 * <Description> <br>
 *
 * @author Sunny<br>
 * @version 1.0<br>
 * @taskId: <br>
 * @createDate 2018/10/19 14:31 <br>
 * @see com.sunny.communication.netty.codec.protocol.command <br>
 */
@Data
public class LoginRequestPacket extends Packet {
    private Integer userId;

    private String username;

    private String password;

    @Override
    public Byte getCommand() {
        return LOGIN_REQUEST;
    }
}
