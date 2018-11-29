package com.sunny.jvm.oom;

/**
 * <Description> 创建线程导致内存溢出异常
 * VM Args: -Xss2M<br>
 * 注意：如果读者要尝试运行上面这段代码，记得先保存当前的工作。由于windows平台的虚拟机中，Java的线程是映射到操作系统的
 *      内核线程上的，因此上述代码执行时有较大的风险，可能导致操作系统假死。
 *
 * @author Sunny<br>
 * @version 1.0<br>
 * @taskId: <br>
 * @createDate 2018/09/06 9:50 <br>
 * @see com.sunny.jvm.oom <br>
 */
public class JavaVMStackOOM {
    private void dontStop() {
        while (true) {
        }
    }

    public void stackLeakByThread() {
        while (true) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    dontStop();
                }
            });
            thread.start();
        }
    }

    public static void main(String[] args) {
        JavaVMStackOOM oom = new JavaVMStackOOM();
        oom.stackLeakByThread();
    }
}
