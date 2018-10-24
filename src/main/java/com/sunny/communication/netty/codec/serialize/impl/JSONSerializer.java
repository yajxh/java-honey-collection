package com.sunny.communication.netty.codec.serialize.impl;

import com.alibaba.fastjson.JSON;
import com.sunny.communication.netty.codec.serialize.Serializer;
import com.sunny.communication.netty.codec.serialize.SerializerAlogrithm;

/**
 * <Description> <br>
 *
 * @author Sunny<br>
 * @version 1.0<br>
 * @taskId: <br>
 * @createDate 2018/10/19 14:43 <br>
 * @see com.sunny.communication.netty.codec.serialize.impl <br>
 */
public class JSONSerializer implements Serializer {
    /**
     * 序列化算法
     *
     * @return
     */
    @Override
    public byte getSerializerAlogrithm() {
        return SerializerAlogrithm.JSON;
    }

    /**
     * java 对象转换成二进制
     *
     * @param object
     * @return
     */
    @Override
    public byte[] serialize(Object object) {
        return JSON.toJSONBytes(object);
    }

    /**
     * 二进制转换成java对象
     *
     * @param clazz
     * @param bytes
     * @return
     */
    @Override
    public <T> T deserialize(Class<T> clazz, byte[] bytes) {
        return JSON.parseObject(bytes, clazz);
    }
}
