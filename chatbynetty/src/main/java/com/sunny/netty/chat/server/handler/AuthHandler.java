package com.sunny.netty.chat.server.handler;

import com.sunny.netty.chat.util.LoginUtil;
import com.sunny.netty.chat.util.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * <Description> <br>
 *
 * @author Sunny<br>
 * @version 1.0<br>
 * @taskId: <br>
 * @createDate 2018/10/27 12:25 <br>
 * @see com.sunny.netty.chat.server.handler <br>
 */
public class AuthHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (!SessionUtil.hasLogin(ctx.channel())) {
            ctx.channel().close();
        } else {
            ctx.pipeline().remove(this);
            super.channelRead(ctx, msg);
        }
    }

//    @Override
//    public void handlerRemoved(ChannelHandlerContext ctx) {
//        if (SessionUtil.hasLogin(ctx.channel())) {
//            System.out.println("当前连接登录验证完毕，无需再次验证, AuthHandler 被移除");
//        } else {
//            System.out.println("无登录验证，强制关闭连接!");
//        }
//    }
}
