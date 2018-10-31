package com.sunny.netty.chat.server.handler;

import com.sunny.netty.chat.protocol.request.HeartBeatRequestPacket;
import com.sunny.netty.chat.protocol.response.HeartBeatResponsePacket;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

@ChannelHandler.Sharable
public class HeartBeatRequestHandler extends SimpleChannelInboundHandler<HeartBeatRequestPacket> {
    public static final HeartBeatRequestHandler INSTANCE = new HeartBeatRequestHandler();

    private HeartBeatRequestHandler() {

    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HeartBeatRequestPacket requestPacket) {
        System.out.println("netty server heart beat!");
        ctx.writeAndFlush(new HeartBeatResponsePacket());
    }
}