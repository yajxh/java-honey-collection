package com.sunny.netty.chat.util;

import java.util.UUID;

/**
 * <Description> <br>
 *
 * @author Sunny<br>
 * @version 1.0<br>
 * @taskId: <br>
 * @createDate 2018/10/27 13:55 <br>
 * @see com.sunny.netty.chat.util <br>
 */
public class IDUtil {
    public static String randomId() {
        return UUID.randomUUID().toString().split("-")[0];
    }
}
