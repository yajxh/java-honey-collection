package com.sunny.jvm.classload.passivereference;

/**
 * <Description> 输出：Initialize class Dgrandpa
 						Initialize class Dfather<br>
 *  对于静态字段，只有直接定义这个字段的类才会被初始化，因此通过其子类来引用父类中定义的静态字段，
 *  只会触发父类的初始化而不会触发子类的初始化。至于是否要触发子类的加载和验证，在虚拟机中并未明确规定，
 *  这点取决于虚拟机的具体实现。对于Sun HotSpot虚拟机来说，可通过-XX:+TraceClassLoading参数观察到此操作
 *  会导致子类的加载。
 * @author Sunny<br>
 * @version 1.0<br>
 * @taskId: <br>
 * @createDate 2018/11/21 9:11 <br>
 * @see com.sunny.jvm.classload <br>
 */
public class PrTest1 {
	public static void main(String[] args) {
		int x = Dson.count;
	}
}

class Dgrandpa {
	static {
		System.out.println("Initialize class Dgrandpa");
	}
}
class Dfather extends Dgrandpa{
    static int count = 1;
	static{
		System.out.println("Initialize class Dfather");
	}
}
 
class Dson extends Dfather{
	static{
		System.out.println("Initialize class Dson");
	}
}