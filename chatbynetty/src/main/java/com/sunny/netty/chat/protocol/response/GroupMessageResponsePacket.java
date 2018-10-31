package com.sunny.netty.chat.protocol.response;

import com.sunny.netty.chat.Session.Session;
import com.sunny.netty.chat.protocol.Packet;
import lombok.Data;

import static com.sunny.netty.chat.protocol.command.Command.GROUP_MESSAGE_RESPONSE;

/**
 * <Description> <br>
 *
 * @author Sunny<br>
 * @version 1.0<br>
 * @taskId: <br>
 * @createDate 2018/10/30 22:00 <br>
 * @see com.sunny.netty.chat.protocol.response <br>
 */
@Data
public class GroupMessageResponsePacket extends Packet {

    private String fromGroupId;

    private Session fromUser;

    private String message;

    @Override
    public Byte getCommand() {

        return GROUP_MESSAGE_RESPONSE;
    }
}
