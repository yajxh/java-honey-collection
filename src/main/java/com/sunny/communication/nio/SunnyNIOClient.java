package com.sunny.communication.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;

/**
 * <Description> step: <br>
 *     1. Create CrunchifyNIOClient.java which tries to connect to server on port 1111
 *     2. Create ArrayList with 5 company names
 *     3. Iterate through ArrayList and send each companyName to server
 *     4. Close connection after task finish
 *
 * @author Sunny<br>
 * @version 1.0<br>
 * @taskId: <br>
 * @createDate 2018/10/17 11:03 <br>
 * @see com.sunny.communication.nio <br>
 */
public class SunnyNIOClient {

    public static void main(String[] args) throws IOException, InterruptedException {
        InetSocketAddress sunnyAddr = new InetSocketAddress("127.0.0.1", 8000);
        SocketChannel sunnyClient = SocketChannel.open(sunnyAddr);

        log("Connecting to Server on port 1111...");

        ArrayList<String> companyDetails = new ArrayList<String>();

        // create a ArrayList with companyName list
        companyDetails.add("Facebook");
        companyDetails.add("Twitter");
        companyDetails.add("IBM");
        companyDetails.add("Google");
        companyDetails.add("Alibaba");

        for (String companyName : companyDetails) {
            byte[] message = new String(companyName).getBytes();
            ByteBuffer buffer = ByteBuffer.wrap(message);
            sunnyClient.write(buffer);

            log("sending: " + companyName);
            buffer.clear();

            //wait for 2 seconds before sending next message
            Thread.sleep(2000);
        }

        sunnyClient.close();

    }

    private static void log(String str) {
        System.out.println(str);
    }
}
