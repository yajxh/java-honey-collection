package com.sunny.netty.chat.protocol.request;

import com.sunny.netty.chat.protocol.Packet;

import static com.sunny.netty.chat.protocol.command.Command.HEARTBEAT_REQUEST;

public class HeartBeatRequestPacket extends Packet {
    @Override
    public Byte getCommand() {
        return HEARTBEAT_REQUEST;
    }
}