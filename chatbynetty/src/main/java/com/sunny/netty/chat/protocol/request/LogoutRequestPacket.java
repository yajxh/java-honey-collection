package com.sunny.netty.chat.protocol.request;

import com.sunny.netty.chat.protocol.Packet;

import static com.sunny.netty.chat.protocol.command.Command.LOGOUT_REQUEST;

/**
 * <Description> <br>
 *
 * @author Sunny<br>
 * @version 1.0<br>
 * @taskId: <br>
 * @createDate 2018/10/27 14:01 <br>
 * @see com.sunny.netty.chat.protocol.request <br>
 */
public class LogoutRequestPacket extends Packet {
    @Override
    public Byte getCommand() {

        return LOGOUT_REQUEST;
    }
}