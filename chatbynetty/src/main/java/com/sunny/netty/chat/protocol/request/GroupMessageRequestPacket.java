package com.sunny.netty.chat.protocol.request;

import com.sunny.netty.chat.protocol.Packet;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.sunny.netty.chat.protocol.command.Command.GROUP_MESSAGE_REQUEST;

/**
 * <Description> <br>
 *
 * @author Sunny<br>
 * @version 1.0<br>
 * @taskId: <br>
 * @createDate 2018/10/30 21:58 <br>
 * @see com.sunny.netty.chat.protocol.request <br>
 */
@Data
@NoArgsConstructor
public class GroupMessageRequestPacket extends Packet {
    private String toGroupId;
    private String message;

    public GroupMessageRequestPacket(String toGroupId, String message) {
        this.toGroupId = toGroupId;
        this.message = message;
    }

    @Override
    public Byte getCommand() {
        return GROUP_MESSAGE_REQUEST;
    }
}