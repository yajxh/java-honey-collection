package com.sunny.jvm.classload.passivereference;

/**
 * <Description> 没有任何输出<br>
 *  通过数组来定义引用类，不会触发此类的初始化
 *
 * @author Sunny<br>
 * @version 1.0<br>
 * @taskId: <br>
 * @createDate 2018/11/29 22:16 <br>
 * @see com.sunny.jvm.classload.passivereference <br>
 */
public class PrTest2 {
    public static void main(String[] args) {
        E[] e = new E[10];
    }
}

class E{
    static{
        System.out.println("Initialize class E");
    }
}

