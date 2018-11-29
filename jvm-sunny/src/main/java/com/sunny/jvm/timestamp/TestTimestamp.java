package com.sunny.jvm.timestamp;

import java.sql.Timestamp;

/**
 * <Description> <br>
 *
 * @author Sunny<br>
 * @version 1.0<br>
 * @taskId: <br>
 * @createDate 2018/07/09 19:06 <br>
 * @see com.sunny.jvm.timestamp <br>
 */
public class TestTimestamp {
    public static void main(String[] args) {
        Timestamp ts = new Timestamp(1531185768000L);
        System.out.println(ts);
    }
}
