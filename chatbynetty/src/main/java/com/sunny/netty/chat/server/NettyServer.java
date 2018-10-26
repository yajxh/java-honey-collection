package com.sunny.netty.chat.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Date;

/**
 * <Description> <br>
 *
 * @author Sunny<br>
 * @version 1.0<br>
 * @taskId: <br>
 * @createDate 2018/10/25 22:21 <br>
 * @see com.sunny.netty.chat.server <br>
 */
public class NettyServer {
    private static final int PORT = 8000;

    public static void main(String[] args) {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        final ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap
                .group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                // ChannelOption.SO_BACKLOG对应的是tcp/ip协议listen函数中的backlog参数，
                // 函数listen(int socketfd,int backlog)用来初始化服务端可连接队列，
                // 服务端处理客户端连接请求是顺序处理的，所以同一时间只能处理一个客户端连接，
                // 多个客户端来的时候，服务端将不能处理的客户端连接请求放在队列中等待处理，
                // backlog参数指定了队列的大小
                .option(ChannelOption.SO_BACKLOG, 1024)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childOption(ChannelOption.TCP_NODELAY, true)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {

                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new ServerHandler());
                    }
                });

        bind(serverBootstrap, PORT);
    }

    public static void bind(final ServerBootstrap serverBootstrap, final int port) {
        serverBootstrap.bind(port).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println(new Date() + ":端口[" + port + "]绑定成功！");
            } else {
                System.err.println(new Date() + ":端口[" + port + "]绑定成功！");
            }
        });
    }
}
