package com.sunny.jmh.serializertest.exception;
/**
 * <Description> <br>
 *
 * @author Sunny<br>
 * @version 1.0<br>
 * @taskId: <br>
 * @createDate 2018/12/05 0:30 <br>
 * @see com.sunny.jmh.serializertest.exception <br>
 */
public class SerializationException extends RuntimeException {
	private static final long serialVersionUID = 8729751979877029879L;

	public SerializationException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public SerializationException(String msg) {
		super(msg);
	}
}
