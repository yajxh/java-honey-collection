package com.sunny.jdk.classload;

/**
 * <Description> 静态多分派，动态单分派<br>
 *
 * @author Sunny<br>
 * @version 1.0<br>
 * @taskId: <br>
 * @createDate 2018/09/21 9:11 <br>
 * @see com.sunny.jdk.classload <br>
 */
public class Dispatch {
    static class QQ {}
    static class _360 {}

    public static class Father {
        public void hardChoice(QQ arg) {
            System.out.println("Father choice qq.");
        }

        public void hardChoice(_360 arg) {
            System.out.println("Father choice 360.");
        }
    }

    public static class Son extends Father {
        @Override
        public void hardChoice(QQ arg) {
            System.out.println("Son choice qq.");
        }

        @Override
        public void hardChoice(_360 arg) {
            System.out.println("Son choice 360.");
        }
    }

    public static void main(String[] args) {
        Father father = new Father();
        Father son = new Son();
        father.hardChoice(new _360());
        son.hardChoice(new QQ());
    }
}
