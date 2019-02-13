package com.sunny.rpc.socket;

import java.io.IOException;
import java.net.Socket;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * <Description> <br>
 *
 * @author Sunny<br>
 * @version 1.0<br>
 * @taskId: <br>
 * @createDate 2018/10/11 23:23 <br>
 * @see com.sunny.rpc.socket <br>
 */
public class IOClient {
    public static void main(String[] args) throws IOException, InterruptedException {
        Socket socket = new Socket("127.0.0.1", 8000);
        while(true) {
            socket.getOutputStream().write((new Date() + ": hello world ").getBytes());
            //Thread.sleep(2000);
            TimeUnit.MILLISECONDS.sleep(2000);
        }
    }
}
