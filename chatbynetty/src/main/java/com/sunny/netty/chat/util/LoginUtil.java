package com.sunny.netty.chat.util;

import com.sunny.netty.chat.attribute.Attributes;
import io.netty.channel.Channel;
import io.netty.util.Attribute;

/**
 * <Description> <br>
 *
 * @author Sunny<br>
 * @version 1.0<br>
 * @taskId: <br>
 * @createDate 2018/10/25 23:51 <br>
 * @see com.sunny.netty.chat.util <br>
 */
public class LoginUtil {
    public static void markAsLogin(Channel channel) {
        channel.attr(Attributes.LOGIN).set(true);
    }

    public static boolean hasLogin(Channel channel) {
        Attribute<Boolean> loginAttr = channel.attr(Attributes.LOGIN);

        return loginAttr.get() != null;
    }
}
