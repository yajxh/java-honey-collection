package com.sunny.jmh.serializertest.bean.pojo;

import java.io.Serializable;

/**
 * <Description> <br>
 *
 * @author Sunny<br>
 * @version 1.0<br>
 * @taskId: <br>
 * @createDate 2018/12/05 0:30 <br>
 * @see com.sunny.jmh.serializertest.bean.pojo <br>
 */
public enum EnumTypeObj implements Serializable {
	UNIVERSAL(0),
	WEB(1),
	IMAGES(2),
	LOCAL(3),
	NEWS(4),
	PRODUCTS(5),
	VIDEO(6);

	int value;

	EnumTypeObj(int value) {
		this.value = value;
	}
}
