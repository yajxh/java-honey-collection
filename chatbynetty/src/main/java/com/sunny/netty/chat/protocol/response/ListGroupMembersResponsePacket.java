package com.sunny.netty.chat.protocol.response;

import com.sunny.netty.chat.Session.Session;
import com.sunny.netty.chat.protocol.Packet;
import lombok.Data;

import java.util.List;

import static com.sunny.netty.chat.protocol.command.Command.LIST_GROUP_MEMBERS_RESPONSE;

/**
 * <Description> <br>
 *
 * @author Sunny<br>
 * @version 1.0<br>
 * @taskId: <br>
 * @createDate 2018/10/30 15:16 <br>
 * @see com.sunny.netty.chat.protocol.response <br>
 */
@Data
public class ListGroupMembersResponsePacket extends Packet {

    private String groupId;

    private List<Session> sessionList;

    @Override
    public Byte getCommand() {

        return LIST_GROUP_MEMBERS_RESPONSE;
    }
}