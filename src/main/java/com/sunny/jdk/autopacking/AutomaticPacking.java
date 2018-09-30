package com.sunny.jdk.autopacking;

/**
 * <Description> <br>
 *
 * @author Sunny<br>
 * @version 1.0<br>
 * @taskId: <br>
 * @createDate 2018/09/29 14:37 <br>
 * @see com.sunny.jdk.autopacking <br>
 */
public class AutomaticPacking {
    public static void main(String[] args) {
        Integer a = 1;
        Integer b = 2;
        Integer c = 3;
        Integer d = 3;
        Integer e = 321;
        Integer f = 321;
        Long g = 3L;
        //所有的包装器类的比较，全部要用equals方法比较；包装类的"=="运算在不遇到算术运算的情况下不会自动拆箱；
//        System.out.println(c == d);
//        System.out.println(e == f);
        System.out.println(c.equals(a + b));
        System.out.println(g == (a + b));
        System.out.println(g.equals(a + b));
    }
}
