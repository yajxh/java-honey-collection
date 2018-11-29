package com.sunny.jvm.classload;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodType;

import static java.lang.invoke.MethodHandles.lookup;

/**
 * <Description> <br>
 *
 * @author Sunny<br>
 * @version 1.0<br>
 * @taskId: <br>
 * @createDate 2018/09/21 13:48 <br>
 * @see com.sunny.jvm.classload <br>
 */
public class MethodHandleTest {
    static class ClassA {
        public void println(String s) {
            System.out.println("classA " + s);
        }
    }

    public static void main(String[] args) throws Throwable {
        Object obj = System.currentTimeMillis() % 2 == 0 ? System.out : new ClassA();
        getPrintlnMH(obj).invokeExact("sunny");
    }

    private static MethodHandle getPrintlnMH(Object receiver) throws Throwable {
        // 方法类型
        MethodType mt = MethodType.methodType(void.class, String.class);

        return lookup().findVirtual(receiver.getClass(), "println", mt)
                .bindTo(receiver);
    }
}
