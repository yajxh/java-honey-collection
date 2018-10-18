package com.sunny.communication.netty.twoway.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;
import java.util.Date;

/**
 * <Description> <br>
 *
 * @author Sunny<br>
 * @version 1.0<br>
 * @taskId: <br>
 * @createDate 2018/10/18 14:31 <br>
 * @see com.sunny.communication.netty.twoway.server <br>
 */
public class FirstServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf)msg;

        System.out.println(new Date() + ": 服务端读到的数据 -> " + byteBuf.toString(Charset.forName("utf-8")));

        // 回复数据到客户端
        System.out.println(new Date() + "： 服务端写出数据");
        ByteBuf out = getByteBuf(ctx);
        ctx.channel().writeAndFlush(out);
    }

    private ByteBuf getByteBuf(ChannelHandlerContext ctx) {
        byte[] bytes = "你好，欢迎来到sunny的github！".getBytes(Charset.forName("utf-8"));
        ByteBuf buffer = ctx.alloc().buffer();

        buffer.writeBytes(bytes);

        return buffer;
    }

//    @Override
//    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//        System.out.println(new Date() + ": 服务端写出数据");
//
//        // 1. 获取数据
//        byte[] bytes = "你好，我是服务端".getBytes(Charset.forName("utf-8"));
//        ByteBuf buffer = ctx.alloc().buffer();
//
//        buffer.writeBytes(bytes);
//
//        // 2. 写数据
//        ctx.channel().writeAndFlush(buffer);
//    }
}
