package com.sunny.jmh.serializertest.util;

import java.io.Closeable;
import java.io.IOException;

/**
 * <Description> <br>
 *
 * @author Sunny<br>
 * @version 1.0<br>
 * @taskId: <br>
 * @createDate 2018/12/05 0:30 <br>
 * @see com.sunny.jmh.serializertest.util <br>
 */
public class IOUtils {

	public static void closeIO(Closeable... closeables) {
		if (closeables == null) {
			return;
		}
		try {
			for (Closeable closeable : closeables) {
				if (closeable != null) {
					closeable.close();
				}
			}
		} catch (IOException e) {
		}
	}
}
