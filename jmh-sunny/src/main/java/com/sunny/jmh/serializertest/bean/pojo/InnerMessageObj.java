package com.sunny.jmh.serializertest.bean.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

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
@Data
@NoArgsConstructor
public class InnerMessageObj implements Serializable {
	private int id;
	private String name;
	private EnumTypeObj enumTypeObj;
}
