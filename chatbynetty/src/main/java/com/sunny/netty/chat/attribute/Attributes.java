package com.sunny.netty.chat.attribute;

import com.sunny.netty.chat.Session.Session;
import io.netty.util.AttributeKey;

/**
 * <Description> <br>
 *
 * @author Sunny<br>
 * @version 1.0<br>
 * @taskId: <br>
 * @createDate 2018/10/25 23:36 <br>
 * @see com.sunny.netty.chat.attribute <br>
 */
public interface Attributes {
    AttributeKey<Boolean> LOGIN = AttributeKey.newInstance("login");
    AttributeKey<Session> SESSION = AttributeKey.newInstance("session");
}
