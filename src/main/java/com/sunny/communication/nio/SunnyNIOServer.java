package com.sunny.communication.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * <Description>  Step: <br>
 *      1. Create SunnyNIOServer.java which opens connection on port 1111
 *      2. use isAcceptable() to check if channel is ready to accept a new socket connection
 *          If yes – connect it
g *          if yes – read from buffer and print on Eclipse console
 *      4. Once you get last company name “crunchify”
 *          close connection
 *
 * @author Sunny<br>
 * @version 1.0<br>
 * @taskId: <br>
 * @createDate 2018/10/17 10:27 <br>
 * @see com.sunny.communication.nio <br>
 */
public class SunnyNIOServer {
    public static void main(String[] args) throws IOException {
        // Selector: multiplexor of SelectableChannel objects
        Selector selector = Selector.open(); // selector is open here

        // ServerSocketChannel: selectable channel for stream-oriented listening sockets
        ServerSocketChannel sunnySocket = ServerSocketChannel.open();
        InetSocketAddress sunnyAddr = new InetSocketAddress("127.0.0.1", 1111);

        // Binds the channel's socket to a local address and configures the socket to listen for connections
        sunnySocket.bind(sunnyAddr);

        // Adjusts the channel's blocking mode.
        sunnySocket.configureBlocking(false);

        int ops = sunnySocket.validOps();
        sunnySocket.register(selector, ops, null);

        // Infinite loop...
        // Keep server running
        while (true) {
            log("i'm a server and i'm waiting for new connection and buffer select...");;
            // Selects a set of keys whose corresponding channels are ready for I/O operations
            selector.select();

            // token representing the registration of a SelectableChannel with a Selector
            Set<SelectionKey> sunnyKeys = selector.selectedKeys();
            Iterator<SelectionKey> sunnyIterator = sunnyKeys.iterator();

            while (sunnyIterator.hasNext()) {
                SelectionKey myKey = sunnyIterator.next();

                // Tests whether this key's channel is ready to accept a new socket connection
                if (myKey.isAcceptable()) {
                    SocketChannel sunnyClient = sunnySocket.accept();

                    // Adjusts this channel's blocking mode to false
                    sunnyClient.configureBlocking(false);

                    // Operation-set bit for read operations
                    sunnyClient.register(selector, SelectionKey.OP_READ);

                    log("Connection Accepted: " + sunnyClient.getLocalAddress() + "\n");

                    // Tests whether this key's channel is ready for reading
                } else if (myKey.isReadable()) {
                    SocketChannel  sunnyClient = (SocketChannel)myKey.channel();
                    ByteBuffer sunnyBuffer = ByteBuffer.allocate(256);
                    sunnyClient.read(sunnyBuffer);
                    String result = new String(sunnyBuffer.array()).trim();

                    System.out.println("Message received: " + result);

                    if (result.equals("Sunny")) {
                        sunnyClient.close();
                        log("\nIt's time to close connection as we got last company name 'Crunchify'");
                        log("\nServer will keep running. Try running client again to establish new connection");
                    }
                }
                sunnyIterator.remove();
            }
        }

    }

    private static void log(String str) {
        System.out.println(str);
    }
}
