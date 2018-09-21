package com.sunny.jdk.classload;

/**
 * <Description> 静态分派<br>
 *
 * @author Sunny<br>
 * @version 1.0<br>
 * @taskId: <br>
 * @createDate 2018/09/20 13:18 <br>
 * @see com.sunny.jdk.classload <br>
 */
public class StaticDispatch {
    static abstract class Human {

    }

    static class Man extends  Human {

    }

    static class Woman extends  Human {

    }

    public void sayHello(Human human) {
        System.out.println("Hello, guy!");
    }

    public void sayHello(Man man) {
        System.out.println("Hello, man!");
    }

    public void sayHello(Woman woman) {
        System.out.println("Hello, woman!");
    }

    public static void main(String[] args) {
        Human hm = new Man();
        Human hw = new Woman();
        StaticDispatch sd = new StaticDispatch();
        sd.sayHello(hm);
        sd.sayHello(hw);
    }
}
