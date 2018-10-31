package com.sunny.netty.chat.protocol.response;

import com.sunny.netty.chat.protocol.Packet;

import static com.sunny.netty.chat.protocol.command.Command.HEARTBEAT_RESPONSE;

public class HeartBeatResponsePacket extends Packet {
    @Override
    public Byte getCommand() {
        return HEARTBEAT_RESPONSE;
    }
}