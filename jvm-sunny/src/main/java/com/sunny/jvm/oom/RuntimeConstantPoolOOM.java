package com.sunny.jvm.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * <Description> 运行时常量池导致的内存溢出异常
 * VM Args: -XX:PermSize=10M -XX:MaxPermSize=10M
 * 这两个配置项在 JDK 1.8 已经不支持了 <br>
 *
 * @author Sunny<br>
 * @version 1.0<br>
 * @taskId: <br>
 * @createDate 2018/09/06 8:57 <br>
 * @see com.sunny.jvm.oom <br>
 */
public class RuntimeConstantPoolOOM {
    public static void main(String[] args) {

        testEqual();

        //使用 List 保持着常量引用,避免被 Full GC 回收常量池行为
        List<String> list = new ArrayList<>();

        //10MB 的 PermSize 在 integer 范围内足够产生 OOM 了
        int i = 0;
        while (true) {
            list.add(String.valueOf(i++).intern());
        }
    }

    /**
     * JDK 1.8
     * true
     * false
     * intern()不会再复制实例,只是在常量池中记录首次出现的实例引用
     *
     * * 在 jdk1.6 中，intern() 方法会将 首次遇到 的字符串实例
     * 复制 到永久代中，返回永久代中这个字符串的引用，
     * 而 StringBuilder 创建的字符串实例在堆上，
     * 因此两个对象显然不同。
     * <p>
     * 在 jdk1.7 中，intern() 方法不会复制字符串实例，
     * 只是在常量池中保存 首次出现 的实例引用，
     * 因此和 StringBuilder 创建的字符串如果是初次出现，
     * 则 intern() 和 StringBuilder.toString() 实际为同一个，
     * 否则不同一个。
     * <p>
     * 例子1:
     * {@code String s2 = new StringBuilder ( "计算机" ).append ( "科学" ).toString ();}
     * {@code s2 == s2.intern()} 返回 {@code true} 比较好理解，
     * 因为执行{@code s2 == s2.intern()} 时常量池里面并没有
     * 计算机科学 这个字符串，所以 {@code StringBuilder.toString() 和常量池中的是同一个实例对象 }
     * <p>
     * 例子2:
     * {@code String s3 = new StringBuilder ( "()" ).append ( "V" ).toString ();}
     * 这里的 {@code s3 == s3.intern()} 又为什么返回 {@code false} ，
     * 对 java 的 class 文件了解的人应该知道 class 文件中保存着编译后的字节码，
     * 而 ()V 就是 class 文件中表示方法的写法，
     * () 表示方法，V 表示返回值是 void ，这个测试方法 {@code oddStringConstant}
     * 本身就是一个这样的方法，因此{@code oddStringConstant}
     * 方法编译后就是 ()V 这样的表示，在 class 文件中是一个常量，
     * 因此 ()V 在常量池中出现过，所以返回 {@code false}
     * <p>
     * TODO
     * 例子3:
     * {@code String s1 = new StringBuilder ( "ja" ).append ( "va" ).toString ();}
     * 这里的 {@code s1 == s1.intern()} 返回false，
     * 较真的朋友可能会使用 javap 工具去看看 class 文件里面是不是真的有 java 这个常量，
     * 找了好久竟然真的没有发现有 java 单独的字符串常量，只有类似 java/lang/String 这样的常量，
     * 但是这样的常量并不能作为 java 字符串的引用。
     * 因为官方文档说名 {@code s1 == s2.intern()} 当且仅当 {@code s1.equals(s2);} 为 {@code true} 时
     */
    private static void testEqual(){
        String str1 = new StringBuilder("计算机").append("软件").toString();
        System.out.println(str1.intern() == str1);// true

        String str2 = new StringBuilder("ja").append("va").toString();
        System.out.println(str2.intern() == str2);// false, java在字符串常量池里面已经有了

        String str3 = new StringBuilder("io").append("t").toString();
        System.out.println(str3.intern() == str3);// true

        String str4 = new StringBuilder("fin").append("al").toString();
        System.out.println(str4.intern() == str4);// true

        String str5 = new StringBuilder("fin").append("al").toString();
        System.out.println(str5.intern() == str5);// false
    }
}
