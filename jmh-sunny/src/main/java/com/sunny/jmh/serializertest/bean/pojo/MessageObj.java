package com.sunny.jmh.serializertest.bean.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
/**
 * <Description> <br>
 *
 * @author Sunny<br>
 * @version 1.0<br>
 * @taskId: <br>
 * @createDate 2018/12/05 0:30 <br>
 * @see com.sunny.jmh.serializertest.bean.pojo <br>
 */
@Data
@NoArgsConstructor
public class MessageObj implements Serializable {
	private String strObj;
	private float floatObj;
	private List<Double> doubleObjList;
	private boolean boolObj;
	private byte[] bytesObj;
	private int int32Obj;
	private long int64Obj;
	private InnerMessageObj innerMessageObj;
}
