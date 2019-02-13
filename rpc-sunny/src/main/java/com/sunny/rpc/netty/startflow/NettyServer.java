package com.sunny.rpc.netty.startflow;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.AttributeKey;

/**
 * <Description> Server启动流程<br>
 *
 * @author Sunny<br>
 * @version 1.0<br>
 * @taskId: <br>
 * @createDate 2018/10/18 9:35 <br>
 * @see com.sunny.rpc.netty.startflow <br>
 */
public class NettyServer {
    private static final int BEGIN_PORT = 8000;

    public static void main(String[] args) {
        // bossGroup表示监听端口，accept 新连接的线程组
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        // workerGroup表示处理每一条连接的数据读写的线程组
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        // 引导类
        final ServerBootstrap serverBootstrap = new ServerBootstrap();
        final AttributeKey<Object> clientKey = AttributeKey.newInstance("clientKey");
        serverBootstrap
                .group(bossGroup, workerGroup) // 给引导类配置两大线程组，这个引导类的线程模型也就定型了。
                .channel(NioServerSocketChannel.class) //指定 IO 模型，当然，这里也有其他的选择，如果你想指定 IO 模型为 BIO，那么这里配置上OioServerSocketChannel.class类型即可，当然通常我们也不会这么做，因为Netty的优势就在于NIO.
                .handler(new ChannelInboundHandlerAdapter() {
                    @Override
                    public void channelActive(ChannelHandlerContext ctx) throws Exception {
                        super.channelActive(ctx);
                    }
                })
                .attr(AttributeKey.newInstance("serverName"), "nettyServer")
                .childAttr(clientKey, "clientValue")
                .option(ChannelOption.SO_BACKLOG, 1024)
                .childOption(ChannelOption.TCP_NODELAY, true)
                .childHandler(new ChannelInitializer<NioSocketChannel>() { // 这里主要就是定义后续每条连接的数据读写，业务处理逻辑.

                    // NioSocketChannel对应Socket， NioServerSocketChannel对应ServerSocket
                    @Override
                    protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
                        System.out.println(nioSocketChannel.attr(clientKey).get());
                    }

                });

        bind(serverBootstrap, BEGIN_PORT);
    }

    private static void bind(final ServerBootstrap serverBootstrap, final int port) {
        serverBootstrap.bind(port).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println("端口[" + port + "]绑定成功！");
            } else {
                System.err.println("端口[" + port + "]绑定失败！");
                bind(serverBootstrap, port + 1);
            }
        });
    }

}
