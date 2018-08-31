package com.sunny.jdk.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * <Description> Java 堆内存溢出异常测试
 *      VM Args: -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError<br>
 *
 * @author Sunny<br>
 * @version 1.0<br>
 * @taskId: <br>
 * @createDate 2018/08/31 14:01 <br>
 * @see com.sunny.jdk.oom <br>
 */
public class HeapOOM {
    static class OOMObject {

    }

    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<OOMObject>();
        while (true) {
            list.add(new OOMObject());
        }
    }
}
