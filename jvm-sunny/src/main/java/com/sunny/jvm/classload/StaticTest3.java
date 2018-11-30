package com.sunny.jvm.classload;

/**
 * <Description> <br>
 *
 * @author Sunny<br>
 * @version 1.0<br>
 * @taskId: <br>
 * @createDate 2018/11/30 10:33 <br>
 * @see com.sunny.jvm.classload <br>
 */
public class StaticTest3 {
    public static void main(String[] args) {
        staticFunction();
    }

    static {
        System.out.println("1");
    }

    {
        System.out.println("2");
    }

    StaticTest3() {
        System.out.println("3");
        System.out.println("a=" + a + ",b=" + b);
    }

    public static void staticFunction() {
        System.out.println("4");
    }

    int a = 110;
    static int b = 112;
}
