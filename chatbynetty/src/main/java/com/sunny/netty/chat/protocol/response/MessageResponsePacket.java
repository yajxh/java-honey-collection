package com.sunny.netty.chat.protocol.response;

import com.sunny.netty.chat.protocol.Packet;
import lombok.Data;

import static com.sunny.netty.chat.protocol.command.Command.MESSAGE_RESPONSE;

/**
 * <Description> <br>
 *
 * @author Sunny<br>
 * @version 1.0<br>
 * @taskId: <br>
 * @createDate 2018/10/25 23:54 <br>
 * @see com.sunny.netty.chat.protocol.response <br>
 */
@Data
public class MessageResponsePacket extends Packet{
    private String message;

    @Override
    public Byte getCommand() {

        return MESSAGE_RESPONSE;
    }
}
