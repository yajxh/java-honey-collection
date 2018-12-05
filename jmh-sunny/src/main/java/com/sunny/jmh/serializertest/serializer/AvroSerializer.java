package com.sunny.jmh.serializertest.serializer;

import org.apache.avro.io.*;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;
import org.apache.avro.specific.SpecificRecordBase;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

/**
 * <Description> <br>
 *
 * @author Sunny<br>
 * @version 1.0<br>
 * @taskId: <br>
 * @createDate 2018/12/05 0:30 <br>
 * @see com.sunny.jmh.serializertest.serializer <br>
 */
public class AvroSerializer implements ISerializer {

	private Objenesis objenesis = new ObjenesisStd();

	@Override
	public <T> byte[] serialize(T obj) {
		try {
			if (!(obj instanceof SpecificRecordBase)) {
				throw new UnsupportedOperationException("not supported obj type");
			}
			DatumWriter writer = new SpecificDatumWriter(obj.getClass());
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			BinaryEncoder binaryEncoder = EncoderFactory.get().directBinaryEncoder(bos, null);
			writer.write(obj, binaryEncoder);
			return bos.toByteArray();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public <T> T deserialize(byte[] data, Class<T> clazz) {
		try {
			if (!SpecificRecordBase.class.isAssignableFrom(clazz)) {
				throw new UnsupportedOperationException("not supported clazz type");
			}
			DatumReader reader = new SpecificDatumReader(clazz);
			ByteArrayInputStream bis = new ByteArrayInputStream(data);
			BinaryDecoder binaryDecoder = DecoderFactory.get().directBinaryDecoder(bis, null);
			return (T) reader.read(objenesis.newInstance(clazz), binaryDecoder);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
