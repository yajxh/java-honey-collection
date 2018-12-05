package com.sunny.jmh.serializertest;

/**
 * <Description> <br>
 *
 * @author Sunny<br>
 * @version 1.0<br>
 * @taskId: <br>
 * @createDate 2018/12/05 0:30 <br>
 * @see com.sunny.jmh.serializertest <br>
 */
public class SerializeSizeTest {

	public static void main(String[] args) {
        BenchmarkTest test = new BenchmarkTest();
		System.out.println("Java-Serialize序列化bytes长度：" + test.javaSerializeBytes.length);
		System.out.println("XStream序列化bytes长度：" + test.xStreamSerializeBytes.length);
		System.out.println("Java-XML序列化bytes长度：" + test.javaXmlSerializeBytes.length);
		System.out.println("FastJson序列化bytes长度：" + test.fastJsonSerializeBytes.length);
		System.out.println("Hessian序列化bytes长度：" + test.hessianSerializeBytes.length);
		System.out.println("Protobuf序列化bytes长度：" + test.protobufSerializeBytes.length);
		System.out.println("Protostuff序列化bytes长度：" + test.protostuffSerializeBytes.length);
		System.out.println("Avro序列化bytes长度：" + test.avroSerializeBytes.length);
		System.out.println("JBoss-Marshalling序列化bytes长度：" + test.marshallingSerializeBytes.length);
		System.out.println("MsgPack序列化bytes长度：" + test.msgPack2SerializeBytes.length);
	}
}
