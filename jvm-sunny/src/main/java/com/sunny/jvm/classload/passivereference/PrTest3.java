package com.sunny.jvm.classload.passivereference;

/**
 * <Description> 输出：1 <br>
 *  常量在编译阶段会存入调用类的常量池中，本质上并没有直接引用到定义常量的类，
 *  因此不会触发定义常量的类的初始化
 *
 * @author Sunny<br>
 * @version 1.0<br>
 * @taskId: <br>
 * @createDate 2018/11/29 22:42 <br>
 * @see com.sunny.jvm.classload.passivereference <br>
 */
public class PrTest3 {
    public static void main(String[] args) {
        System.out.println(ConstClass.COUNT);
    }
}

class ConstClass{
    static final int COUNT = 1;
    static{
        System.out.println("Initialize class ConstClass");
    }
}
