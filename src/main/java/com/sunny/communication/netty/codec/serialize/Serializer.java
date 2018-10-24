package com.sunny.communication.netty.codec.serialize;

import com.sunny.communication.netty.codec.serialize.impl.JSONSerializer;

/**
 * <Description> <br>
 *
 * @author Sunny<br>
 * @version 1.0<br>
 * @taskId: <br>
 * @createDate 2018/10/19 14:38 <br>
 * @see com.sunny.communication.netty.codec.serialize <br>
 */
public interface Serializer {
    Serializer DEFAULT = (Serializer) new JSONSerializer();

    /**
     * 序列化算法
     * @return
     */
    byte getSerializerAlogrithm();

    /**
     * java 对象转换成二进制
     * @param object
     * @return
     */
    byte[] serialize(Object object);

    /**
     * 二进制转换成java对象
     * @param clazz
     * @param bytes
     * @param <T>
     * @return
     */
    <T> T deserialize(Class<T> clazz, byte[] bytes);
}
