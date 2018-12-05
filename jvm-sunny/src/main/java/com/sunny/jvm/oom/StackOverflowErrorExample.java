package com.sunny.jvm.oom;

/**
 * <Description> java.lang.StackOverflowError<br>
 *
 * @author Sunny<br>
 * @version 1.0<br>
 * @taskId: <br>
 * @createDate 2018/09/05 15:48 <br>
 * @see com.sunny.jvm.oom <br>
 */
public class StackOverflowErrorExample {
    public static void recursivePrint(int num) {
        System.out.println("Number: " + num);

        if(num == 0)
            return;
        else
            recursivePrint(++num);
    }

    public static void main(String[] args) {
        StackOverflowErrorExample.recursivePrint(1);
    }
}
