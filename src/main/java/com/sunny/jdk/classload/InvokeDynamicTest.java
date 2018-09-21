package com.sunny.jdk.classload;

import java.lang.invoke.*;

import static java.lang.invoke.MethodHandles.lookup;

/**
 * <Description> <br>
 *
 * @author Sunny<br>
 * @version 1.0<br>
 * @taskId: <br>
 * @createDate 2018/09/21 15:42 <br>
 * @see com.sunny.jdk.classload <br>
 */
public class InvokeDynamicTest {
    public static void main(String[] args) throws Throwable {
        INDY_BootstrapMethod().invokeExact("Sunny");
    }

    public static void testMethod(String s) {
        System.out.println("hello String:" + s);
    }

    public static CallSite BootstrapMethod(
            MethodHandles.Lookup lookup, String name, MethodType mt)
            throws Throwable {
        return new ConstantCallSite(lookup.findStatic(InvokeDynamicTest.class, name, mt));
    }

    private static MethodType MT_BootstrapMethod() {
        return MethodType.fromMethodDescriptorString(
                "(Ljava/lang/invoke/MethodHandles$Lookup;" +
                        "Ljava/lang/String;Ljava/lang/invoke/MethodType;)" +
                        "Ljava/lang/invoke/CallSite;", null
        );
    }

    private static MethodHandle MH_BootstrapMethod() throws Throwable {
        return lookup().findStatic(InvokeDynamicTest.class, "BootstrapMethod",
                MT_BootstrapMethod());
    }

    private static MethodHandle INDY_BootstrapMethod() throws Throwable {
        CallSite cs = (CallSite) MH_BootstrapMethod().invokeWithArguments(
                lookup(),
                "testMethod",
                MethodType.fromMethodDescriptorString(
                        "(Ljava/lang/String;)V", null
                ));
        return cs.dynamicInvoker();
    }
}
