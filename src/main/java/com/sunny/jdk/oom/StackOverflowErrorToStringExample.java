package com.sunny.jdk.oom;

class A {
	private int aValue;
	private B bInstance = null;
	
	public A() {
		aValue = 0;
		bInstance = new B();
	}
	
	@Override
	public String toString() {
		return "<" + aValue + ", " + bInstance + ">";
	}
}

class B {
	private int bValue;
	private A aInstance = null;
	
	public B() {
		bValue = 10;
		aInstance = new A();
	}
	
	@Override
	public String toString() {
		return "<" + bValue + ", " + aInstance + ">";
	}
}

/**
 * <Description> StackOverflowError<br>
 *
 * @author Sunny<br>
 * @version 1.0<br>
 * @taskId: <br>
 * @createDate 2018/09/05 10:41 <br>
 * @see com.sunny.jdk.oom <br>
 */
public class StackOverflowErrorToStringExample {
	public static void main(String[] args) {
		A obj = new A();
		System.out.println(obj.toString());
	}
}
