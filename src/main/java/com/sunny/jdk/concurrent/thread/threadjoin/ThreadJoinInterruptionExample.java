package com.sunny.jdk.concurrent.thread.threadjoin;

/**
 * <Description>  <br>
 *
 * @author Sunny<br>
 * @version 1.0<br>
 * @taskId: <br>
 * @createDate 2018/08/15 17:55 <br>
 * @see com.sunny.jdk.concurrent.thread <br>
 */
public class ThreadJoinInterruptionExample {
	public static void main(String[] args) {
		ThreadJoinInterruptionExample caller = new ThreadJoinInterruptionExample();
		caller.startSubTask();
	}

	public void startSubTask() {
		System.out.println("Start subtask");
		SubTask subTask = new SubTask(Thread.currentThread());
		Thread t = new Thread(subTask);
		t.start();
		try {
			t.join();
		} catch (InterruptedException e) {
			System.out.println("Interrupted, lets check the result");
		}
		System.out.println("Got the result? " + (subTask.getResult() != null));
	}

	private class SubTask implements Runnable {
		private Thread callerThread;
		private Object result;

		SubTask(Thread callerThread) {
			this.callerThread = callerThread;
		}

		@Override
		public void run() {
			doTask();
			System.out
					.println("task done, interrupt the thread waiting on the result");
			callerThread.interrupt();
			doSomeMoreTask();
		}

		private void doTask() {
			System.out.println("do task");
			result = new Object();
		}

		private void doSomeMoreTask() {
			System.out.println("do some more task");
		}

		public Object getResult() {
			return result;
		}

	}
}
