package com.sunny.jdk.oom;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * <Description> 借助CGLib使方法区出现内存溢出异常<br>
 *
 * @author Sunny<br>
 * @version 1.0<br>
 * @taskId: <br>
 * @createDate 2018/09/06 10:56 <br>
 * @see com.sunny.jdk.oom <br>
 */
public class JavaMethodAreaOOM {
    public static void main(final String[] args) {
        while (true) {
            final Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(OOMObject.class);
            enhancer.setUseCache(false);
            enhancer.setCallback(new MethodInterceptor() {

                @Override
                public Object intercept(final Object obj, final Method method,
                                        final Object[] args, final MethodProxy proxy) throws Throwable {
                    return proxy.invokeSuper(obj, args);
                }
            });
            enhancer.create();
        }
    }

    static class OOMObject {}
}
