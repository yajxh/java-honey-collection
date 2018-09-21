package com.sunny.jdk.classload;

/**
 * <Description> <br>
 *
 * @author Sunny<br>
 * @version 1.0<br>
 * @taskId: <br>
 * @createDate 2018/09/20 14:29 <br>
 * @see com.sunny.jdk.classload <br>
 */
public class DynamicDispatch {
    static abstract class Human {
        protected  abstract void sayHello();
    }

    static class Man extends Human {

        @Override
        protected void sayHello() {
            System.out.println("Man, sayHello!");
        }
    }

    static class Woman extends Human {

        @Override
        protected void sayHello() {
            System.out.println("Woman, sayHello!");
        }
    }

    public static void main(String[] args) {
        Human man = new Man();
        Human woman = new Woman();
        man.sayHello();
        woman.sayHello();
        man = new Woman();
        man.sayHello();

    }
}
