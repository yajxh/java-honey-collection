package com.sunny.jmh.serializertest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sunny.jmh.serializertest.bean.Person;
import com.sunny.jmh.serializertest.bean.avro.User;
import com.sunny.jmh.serializertest.bean.protobuf.AddressBookProtos;
import com.sunny.jmh.serializertest.serializer.*;
import com.google.protobuf.InvalidProtocolBufferException;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.*;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;
import org.msgpack.core.MessageBufferPacker;
import org.msgpack.core.MessagePack;
import org.msgpack.core.MessageUnpacker;
import org.msgpack.jackson.dataformat.MessagePackFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/**
 * <Description> <br>
 *
 * @author Sunny<br>
 * @version 1.0<br>
 * @taskId: <br>
 * @createDate 2018/12/05 0:30 <br>
 * @see com.sunny.jmh.serializertest <br>
 */
public class SerializerTest {

    private void testJavaSerializer() {
        // 需要实现Serializable接口
        Person person = new Person(21, "ben");
        ISerializer serializer = new DefaultJavaSerializer();
        byte[] bytes = serializer.serialize(person);
        Person dePerson = serializer.deserialize(bytes, Person.class);
        System.out.println(dePerson);
    }

    private void testXmlSerializer() {
        Person person = new Person(21, "ben");
        ISerializer serializer = new XmlSerializer();
        byte[] bytes = serializer.serialize(person);
        System.out.println(new String(bytes));
    }

    private void testXml2Serializer() {
        // 需要有无参构造函数
        Person person = new Person(21, "ben");
        ISerializer serializer = new Xml2Serializer();
        byte[] bytes = serializer.serialize(person);
        System.out.println(new String(bytes));
    }

    private void testFastJsonSerializer() {
        Person person = new Person(21, "ben");
        ISerializer serializer = new FastJsonSerializer();
        byte[] bytes = serializer.serialize(person);
        System.out.println(new String(bytes));
    }

    private void testHessianSerializer() {
        // 需要实现Serializable接口
        Person person = new Person(21, "ben");
        ISerializer serializer = new HessianSerializer();
        byte[] bytes = serializer.serialize(person);
        Person dePerson = serializer.deserialize(bytes, Person.class);
        System.out.println(dePerson);
    }

    private void testProtobufSerializer() throws InvalidProtocolBufferException {
        AddressBookProtos.Person person = AddressBookProtos.Person.newBuilder()
                .setEmail("benjaminwhx@sina.com")
                .setId(10000)
                .setName("benjamin")
                .addPhones(AddressBookProtos.Person.PhoneNumber.newBuilder()
                        .setNumber("18888888888")
                        .setType(AddressBookProtos.Person.PhoneType.HOME)
                        .build()).build();

        // 序列化方式1
        System.out.println(person.toByteString());
        // 序列化方式2
        System.out.println(Arrays.toString(person.toByteArray()));

        // 反序列化方式1
        AddressBookProtos.Person newPerson = AddressBookProtos.Person.parseFrom(person.toByteString());
        System.out.println("反序列化1：" + newPerson);
        // 反序列化方式2
        newPerson = AddressBookProtos.Person.parseFrom(person.toByteArray());
        System.out.println("反序列化2：" + newPerson);

        // 使用protobuf序列化工具
        ISerializer serializer = new ProtobufSerializer();
        byte[] data = serializer.serialize(person);
        AddressBookProtos.Person dePerson = serializer.deserialize(data, AddressBookProtos.Person.class);
        System.out.println("反序列化3：" + dePerson);
    }

	private void testProtostuffSerializer() {
        Person person = new Person(21, "ben");
		ISerializer serializer = new ProtostuffSerializer();
		byte[] bytes = serializer.serialize(person);
		Person dePerson = serializer.deserialize(bytes, Person.class);
		System.out.println(dePerson);
	}

	private void testAvroSerializer() throws IOException {
		User userAvro = new User();
		userAvro.setAge(26);
		userAvro.setEmail("benjaminwhx@sina.com");
		userAvro.setName("benjamin");
		// 1、序列化
		DatumWriter<User> writer = new SpecificDatumWriter<>(User.class);
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		BinaryEncoder binaryEncoder = EncoderFactory.get().directBinaryEncoder(bos, null);
		writer.write(userAvro, binaryEncoder);
		byte[] data = bos.toByteArray();

		// 2、反序列化
		DatumReader<User> reader = new SpecificDatumReader<>(User.class);
		ByteArrayInputStream bis = new ByteArrayInputStream(data);
		BinaryDecoder binaryDecoder = DecoderFactory.get().directBinaryDecoder(bis, null);
		User deUser = reader.read(userAvro, binaryDecoder);
		System.out.println(deUser);
	}

    private void testAvroSerializer2() throws IOException {
        Schema schema = new Schema.Parser().parse(new File("/Users/Benjamin/idea_project/local/src/main/avro/user.avsc"));
        GenericRecord user1 = new GenericData.Record(schema);
		user1.put("age", 18);
		user1.put("name", "benjamin");
		user1.put("email", "benjaminwhx@sina.com");
		// 1、序列化
        DatumWriter<GenericRecord> datumWriter = new GenericDatumWriter<GenericRecord>(schema);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        BinaryEncoder binaryEncoder = EncoderFactory.get().directBinaryEncoder(bos, null);
        datumWriter.write(user1, binaryEncoder);
        byte[] bytes = bos.toByteArray();

        // 2、反序列化
        DatumReader<GenericRecord> datumReader = new GenericDatumReader<GenericRecord>(schema);
		BinaryDecoder binaryDecoder = DecoderFactory.get().directBinaryDecoder(new ByteArrayInputStream(bytes), null);
		GenericRecord read = datumReader.read(new GenericData.Record(schema), binaryDecoder);
		System.out.println(read);
	}

	private void testAvroSerializer3() {
		User userAvro = new User();
		userAvro.setAge(26);
		userAvro.setEmail("benjaminwhx@sina.com");
		userAvro.setName("benjamin");

    	ISerializer serializer = new AvroSerializer();
		byte[] bytes = serializer.serialize(userAvro);

		User user = serializer.deserialize(bytes, User.class);
		System.out.println(user);
	}

	private void testMarshallingSerializer() {
		// 需要实现Serializable接口
		Person person = new Person(21, "ben");
		ISerializer serializer = new MarshallingSerializer();
		byte[] bytes = serializer.serialize(person);
		Person dePerson = serializer.deserialize(bytes, Person.class);
		System.out.println(dePerson);
	}

	private void testMsgPackSerializer() throws IOException {
		MessageBufferPacker packer = MessagePack.newDefaultBufferPacker();
		packer.packInt(21);
		packer.packString("ben");
		packer.close();
		byte[] bytes = packer.toByteArray();
		MessageUnpacker unpacker = MessagePack.newDefaultUnpacker(bytes);
		int age = unpacker.unpackInt();
		String name = unpacker.unpackString();
		unpacker.close();
		System.out.println("age: " + age + ", name: " + name);
	}

	private void testMsgPackSerializer2() throws IOException {
		// Instantiate ObjectMapper for MessagePack
		ObjectMapper objectMapper = new ObjectMapper(new MessagePackFactory());

		// Serialize a Java object to byte array
		Person person = new Person(21, "ben");
		byte[] bytes = objectMapper.writeValueAsBytes(person);

		// Deserialize the byte array to a Java object
		Person deserialized = objectMapper.readValue(bytes, Person.class);
		System.out.println(deserialized);
	}

    public static void main(String[] args) throws Exception {
        SerializerTest serializerTest = new SerializerTest();
//        serializerTest.testJavaSerializer();
//        serializerTest.testXmlSerializer();
//        serializerTest.testXml2Serializer();
//        serializerTest.testFastJsonSerializer();
//        serializerTest.testHessianSerializer();
//        serializerTest.testProtobufSerializer();
//		  serializerTest.testProtostuffSerializer();
//		  serializerTest.testAvroSerializer();
//        serializerTest.testAvroSerializer2();
//        serializerTest.testAvroSerializer3();
//		  serializerTest.testMarshallingSerializer();
//		serializerTest.testMsgPackSerializer();
		serializerTest.testMsgPackSerializer2();
	}
}
