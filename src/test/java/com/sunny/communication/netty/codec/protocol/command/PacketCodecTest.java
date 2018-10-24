package com.sunny.communication.netty.codec.protocol.command;

import com.sunny.communication.netty.codec.serialize.Serializer;
import com.sunny.communication.netty.codec.serialize.impl.JSONSerializer;
import io.netty.buffer.ByteBuf;
import org.junit.Assert;
import org.junit.Test;

/**
 * <Description> <br>
 *
 * @author Sunny<br>
 * @version 1.0<br>
 * @taskId: <br>
 * @createDate 2018/10/19 15:12 <br>
 * @see com.sunny.communication.netty.codec.protocol.command <br>
 */
public class PacketCodecTest {

    @Test
    public void encode() {
        Serializer serializer = new JSONSerializer();
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();

        loginRequestPacket.setVersion((byte)1);
        loginRequestPacket.setUserId(123);
        loginRequestPacket.setUsername("sunny");
        loginRequestPacket.setPassword("password");

        PacketCodec packetCodec = new PacketCodec();
        ByteBuf byteBuf = packetCodec.encode(loginRequestPacket);
        Packet decodedPacket = packetCodec.decode(byteBuf);

        Assert.assertArrayEquals(serializer.serialize(loginRequestPacket), serializer.serialize(decodedPacket));
    }
}
