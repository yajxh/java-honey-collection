package com.sunny.jmh.serializertest;

import com.sunny.jmh.serializertest.bean.avro.EnumTypeAvro;
import com.sunny.jmh.serializertest.bean.avro.InnerMessageAvro;
import com.sunny.jmh.serializertest.bean.avro.MessageAvro;
import com.sunny.jmh.serializertest.bean.pojo.EnumTypeObj;
import com.sunny.jmh.serializertest.bean.pojo.InnerMessageObj;
import com.sunny.jmh.serializertest.bean.pojo.MessageObj;
import com.sunny.jmh.serializertest.bean.protobuf.EnumTypeProtos;
import com.sunny.jmh.serializertest.bean.protobuf.InnerMessageProtos;
import com.sunny.jmh.serializertest.bean.protobuf.MessageProtos;
import com.sunny.jmh.serializertest.serializer.*;
import com.google.protobuf.ByteString;
import org.openjdk.jmh.annotations.*;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * <Description> <br>
 *
 * @author Sunny<br>
 * @version 1.0<br>
 * @taskId: <br>
 * @createDate 2018/12/05 0:30 <br>
 * @see com.sunny.jmh.serializertest <br>
 */
@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 3, time = 5, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 5, timeUnit = TimeUnit.SECONDS)
@Fork(3)
@Threads(8)
public class BenchmarkTest {
    public ISerializer javaSerializer = new DefaultJavaSerializer();
    public ISerializer xStreamSerializer = new XmlSerializer();
    public ISerializer javaXmlSerializer = new Xml2Serializer();
    public ISerializer fastJsonSerializer = new FastJsonSerializer();
    public ISerializer hessianSerializer = new HessianSerializer();
    public ISerializer protobufSerializer = new ProtobufSerializer();
    public ISerializer protostuffSerializer = new ProtostuffSerializer();
    public ISerializer avroSerializer = new AvroSerializer();
    public ISerializer marshallingSerializer = new MarshallingSerializer();
    public ISerializer msgPack2Serializer = new MsgPack2Serializer();
    private MessageProtos.Message probufBean = getProtobufBean();
    private MessageAvro avroBean = getAvroBean();
    private MessageObj pojoBean = getPojoBean();

    byte[] javaSerializeBytes = javaSerializer.serialize(pojoBean);
    byte[] xStreamSerializeBytes = xStreamSerializer.serialize(pojoBean);
    byte[] javaXmlSerializeBytes = javaXmlSerializer.serialize(pojoBean);
    byte[] fastJsonSerializeBytes = fastJsonSerializer.serialize(pojoBean);
    byte[] hessianSerializeBytes = hessianSerializer.serialize(pojoBean);
    byte[] protobufSerializeBytes = protobufSerializer.serialize(probufBean);
    byte[] protostuffSerializeBytes = protostuffSerializer.serialize(pojoBean);
    byte[] avroSerializeBytes = avroSerializer.serialize(avroBean);
    byte[] marshallingSerializeBytes = marshallingSerializer.serialize(pojoBean);
    byte[] msgPack2SerializeBytes = msgPack2Serializer.serialize(pojoBean);

    private MessageProtos.Message getProtobufBean() {
        MessageProtos.Message.Builder messageBuilder = MessageProtos.Message.newBuilder();

        messageBuilder.setStrObj("message");
        messageBuilder.setFolatObj(1f);
        messageBuilder.addDoubleObj(1d);
        messageBuilder.addDoubleObj(2d);
        messageBuilder.setBoolObj(true);

        messageBuilder.setBytesObj(ByteString.copyFrom(new byte[] { 1, 2, 3 }));
        messageBuilder.setInt32Obj(32);
        messageBuilder.setInt64Obj(64l);

        InnerMessageProtos.InnerMessage.Builder innerMessageBuilder = InnerMessageProtos.InnerMessage.newBuilder();
        innerMessageBuilder.setId(1);
        innerMessageBuilder.setName("inner");
        innerMessageBuilder.setType(EnumTypeProtos.EnumType.PRODUCTS);

        messageBuilder.setInnerMessage(innerMessageBuilder);
        return messageBuilder.build();
    }

    private MessageAvro getAvroBean() {
        MessageAvro messageAvro = new MessageAvro();
        messageAvro.setStrObj("message");
        messageAvro.setFloatObj(1f);
        List<Double> doubleList = new ArrayList<>();
        doubleList.add(1d);
        doubleList.add(2d);
        messageAvro.setDoubleObjList(doubleList);
        messageAvro.setBytesObj(ByteBuffer.wrap(new byte[] {1, 2, 3}));
        messageAvro.setInt32Obj(32);
        messageAvro.setInt64Obj(64L);

        InnerMessageAvro innerMessageAvro = new InnerMessageAvro();
        innerMessageAvro.setId(1);
        innerMessageAvro.setName("inner");
        innerMessageAvro.setEnumTypeObj(EnumTypeAvro.PRODUCTS);
        messageAvro.setInnerMessageObj(innerMessageAvro);
        return messageAvro;
    }

    private MessageObj getPojoBean() {
        InnerMessageObj innerMessageObj = new InnerMessageObj();
        innerMessageObj.setId(1);
        innerMessageObj.setName("inner");
        innerMessageObj.setEnumTypeObj(EnumTypeObj.PRODUCTS);

        MessageObj msg = new MessageObj();
        msg.setStrObj("message");
        msg.setFloatObj(1f);
        List<Double> doubleList = new ArrayList<>();
        doubleList.add(1d);
        doubleList.add(2d);
        msg.setDoubleObjList(doubleList);
        msg.setBoolObj(true);
        msg.setBytesObj(new byte[] {1, 2, 3});
        msg.setInt32Obj(32);
        msg.setInt64Obj(64L);
        msg.setInnerMessageObj(innerMessageObj);
        return msg;
    }

    @Benchmark
    public byte[] testDefaultJavaSerialize() {
        return javaSerializer.serialize(pojoBean);
    }
    @Benchmark
    public MessageObj testDefaultJavaDeSerialize() {
        return javaSerializer.deserialize(javaSerializeBytes, MessageObj.class);
    }

    @Benchmark
    public byte[] testXStreamSerialize() {
        return xStreamSerializer.serialize(pojoBean);
    }
    @Benchmark
    public MessageObj testXStreamDeSerialize() {
        return xStreamSerializer.deserialize(xStreamSerializeBytes, MessageObj.class);
    }

    @Benchmark
    public byte[] testJavaXmlSerialize() {
        return javaXmlSerializer.serialize(pojoBean);
    }
    @Benchmark
    public MessageObj testJavaXmlDeSerialize() {
        return javaXmlSerializer.deserialize(javaXmlSerializeBytes, MessageObj.class);
    }

    @Benchmark
    public byte[] testFastJsonSerialize() {
        return fastJsonSerializer.serialize(pojoBean);
    }
    @Benchmark
    public MessageObj testFastJsonDeSerialize() {
        return fastJsonSerializer.deserialize(fastJsonSerializeBytes, MessageObj.class);
    }

    @Benchmark
    public byte[] testHessianSerialize() {
        return hessianSerializer.serialize(pojoBean);
    }
    @Benchmark
    public MessageObj testHessianDeSerialize() {
        return hessianSerializer.deserialize(hessianSerializeBytes, MessageObj.class);
    }

    @Benchmark
    public byte[] testProtobufSerialize() {
        return protobufSerializer.serialize(probufBean);
    }
    @Benchmark
    public MessageProtos.Message testProtobufDeSerialize() {
        return protobufSerializer.deserialize(protobufSerializeBytes, MessageProtos.Message.class);
    }

    @Benchmark
    public byte[] testProtostuffSerialize() {
        return protostuffSerializer.serialize(pojoBean);
    }
    @Benchmark
    public MessageObj testProtostuffDeSerialize() {
        return protostuffSerializer.deserialize(protostuffSerializeBytes, MessageObj.class);
    }

    @Benchmark
    public byte[] testAvroSerialize() {
        return avroSerializer.serialize(avroBean);
    }
    @Benchmark
    public MessageAvro testAvroDeSerialize() {
        return avroSerializer.deserialize(avroSerializeBytes, MessageAvro.class);
    }

    @Benchmark
    public byte[] testMarshallingSerialize() {
        return marshallingSerializer.serialize(pojoBean);
    }
    @Benchmark
    public MessageObj testMarshallingDeSerialize() {
        return marshallingSerializer.deserialize(marshallingSerializeBytes, MessageObj.class);
    }

    @Benchmark
    public byte[] testMsgPackSerialize() throws IOException {
        // BenchmarkTest.testMsgPackSerialize  avgt   15  4692.698 ± 336.441  ns/op
        return msgPack2Serializer.serialize(pojoBean);
    }

    @Benchmark
    public MessageObj testMsgPackDeSerialize() {
        // BenchmarkTest.testMsgPackDeSerialize  avgt   15  4935.865 ± 391.811  ns/op
        return msgPack2Serializer.deserialize(msgPack2SerializeBytes, MessageObj.class);
    }
}
