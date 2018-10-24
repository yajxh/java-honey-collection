package com.sunny.communication.netty.codec.protocol.command;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * <Description> <br>
 *
 * @author Sunny<br>
 * @version 1.0<br>
 * @taskId: <br>
 * @createDate 2018/10/19 14:21 <br>
 * @see com.sunny.communication.netty.codec.protocol.command <br>
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
