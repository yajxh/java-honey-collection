package com.sunny.netty.chat.serialize;


import com.sunny.netty.chat.serialize.impl.JSONSerializer;

/**
 * <Description> <br>
 *
 * @author Sunny<br>
 * @version 1.0<br>
 * @taskId: <br>
 * @createDate 2018/10/25 20:49 <br>
 * @see com.sunny.netty.chat.serialize <br>
 */
public interface Serializer {
    Serializer DEFAULT = new JSONSerializer();

    /**
     * 序列化算法
     * @return
     */
    byte getSerializerAlogrithm();

    /**
     * java对象转换成二进制
     * @param object
     * @return
     */
    byte[] serialize(Object object);

    /**
     *  二进制转换成java对象
     * @param clazz
     * @param bytes
     * @param <T>
     * @return
     */
    <T> T deserialize(Class<T> clazz, byte[] bytes);
}
