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
public class ThreadJoinMillisExample {
	public static void main(String[] args) {
		SubTask subTask = new SubTask();
		Thread t = new Thread(subTask);
		t.start();
		try {
			System.out
					.println("Join on subTask thread but only for 100 millis");
			t.join(100);
			System.out.println("Main thread came out of join");
		} catch (InterruptedException e) {
			System.out.println("Main thread Interrupted!");
		}
		if (t.isAlive()) {
			System.out.println("SubTask is still alive, interrupt it");
			t.interrupt();
		}
	}

	private static class SubTask implements Runnable {

		@Override
		public void run() {
			try {
				Thread.sleep(100000);
			} catch (InterruptedException e) {
				System.out.println("Subtask Interrupted!");
			}
		}
	}

}
