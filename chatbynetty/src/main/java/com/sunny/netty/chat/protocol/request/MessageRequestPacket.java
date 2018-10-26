package com.sunny.netty.chat.protocol.request;

import com.sunny.netty.chat.protocol.Packet;
import lombok.Data;

import static com.sunny.netty.chat.protocol.command.Command.MESSAGE_REQUEST;

/**
 * <Description> <br>
 *
 * @author Sunny<br>
 * @version 1.0<br>
 * @taskId: <br>
 * @createDate 2018/10/25 23:52 <br>
 * @see com.sunny.netty.chat.protocol.request <br>
 */
@Data
public class MessageRequestPacket extends Packet{
    private String message;

    @Override
    public Byte getCommand() {
        return MESSAGE_REQUEST;
    }
}
