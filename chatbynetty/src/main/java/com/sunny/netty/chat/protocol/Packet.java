package com.sunny.netty.chat.protocol;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * <Description> <br>
 *
 * @author Sunny<br>
 * @version 1.0<br>
 * @taskId: <br>
 * @createDate 2018/10/25 16:27 <br>
 * @see com.sunny.netty.chat.protocol <br>
 */
@Data
public abstract class Packet {
    /**
     * 协议版本
     */
    @JSONField(deserialize = false, serialize = false)
    private Byte version = 1;

    @JSONField(serialize = false)
    public abstract Byte getCommand();
}
