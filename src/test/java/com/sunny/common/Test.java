package com.sunny.common;

/**
 * <Description> <br>
 *
 * @author Sunny<br>
 * @version 1.0<br>
 * @taskId: <br>
 * @createDate 2018/08/09 15:35 <br>
 * @see com.sunny.common <br>
 */
public class Test {
    static {
        i = 1;
        //System.out.println(i); // Illegal forward reference
    }
    static int i = 0;
    public static void main(String[] args) {
//        System.out.println(1 << 3);
//        System.out.println(1024 >>> 1);
//        System.out.println(1024 >> 1);

        System.out.println(2 & -2);
        System.out.println(Integer.toBinaryString(-2));
        System.out.println(Integer.toBinaryString(2));

    }
}
