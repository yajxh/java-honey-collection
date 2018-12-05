package com.sunny.jmh.serializertest.bean;

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
 * @see com.sunny.jmh.serializertest.bean <br>
 */
@Data
@NoArgsConstructor
public class Person implements Serializable {
    private int age;
    private String name;

    public Person(int age, String name) {
        this.age = age;
        this.name = name;
    }
}
