package com.sunny.netty.chat.client.console;

import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * <Description> <br>
 *
 * @author Sunny<br>
 * @version 1.0<br>
 * @taskId: <br>
 * @createDate 2018/10/27 14:10 <br>
 * @see com.sunny.netty.chat.client.console <br>
 */
public interface ConsoleCommand {
    void exec(Scanner scanner, Channel channel);
}
