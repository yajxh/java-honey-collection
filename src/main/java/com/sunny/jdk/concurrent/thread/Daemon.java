package com.sunny.jdk.concurrent.thread;

/**
 * <Description> 运行Daemon程序，可以看到在终端或者命令提示符上没有任何输出。main线程（非Daemon线程）
 * 在启动了线程DaemonRunner之后随着main方法执行完毕而终止，而此时Java虚拟机中已经没有非Daemon线程，
 * 虚拟机需要退出。Java虚拟机中的所有Daemon线程都需要立即终止，因此DaemonRunner立即终止，
 * 但是DaemonRunner中的finally块并没有执行。 <br>
 *
 * 注意:在构建Daemon线程时，不能依靠finally块中的内容来确保执行关闭或清理资源的逻辑。 <br>
 *
 * @author Sunny<br>
 * @version 1.0<br>
 * @taskId: <br>
 * @createDate 2018/08/07 17:55 <br>
 * @see com.sunny.jdk.concurrent.thread <br>
 */
public class Daemon {
    public static void main(String[] args) {
        Thread thread = new Thread(new DaemonRunner(), "DaemonRunner");
        thread.setDaemon(true);
        thread.start();

    }

    static class DaemonRunner implements Runnable {

        @Override
        public void run() {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println("DaemonThread finally run.");
            }
        }
    }
}
