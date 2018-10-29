package com.sunny.netty.chat.protocol.response;

import com.sunny.netty.chat.protocol.Packet;
import lombok.Data;

import static com.sunny.netty.chat.protocol.command.Command.LOGOUT_RESPONSE;

/**
 * <Description> <br>
 *
 * @author Sunny<br>
 * @version 1.0<br>
 * @taskId: <br>
 * @createDate 2018/10/27 14:04 <br>
 * @see com.sunny.netty.chat.protocol.response <br>
 */
@Data
public class LogoutResponsePacket extends Packet {
    private boolean success;

    private String reason;


    @Override
    public Byte getCommand() {

        return LOGOUT_RESPONSE;
    }
}
