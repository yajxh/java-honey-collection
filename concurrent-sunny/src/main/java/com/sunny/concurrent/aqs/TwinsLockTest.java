package com.sunny.concurrent.aqs;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

public class TwinsLockTest {
	public static void main(String[] args) {
		final Lock lock = new TwinsLock();

		class Worker extends Thread {
			@Override
			public void run() {
				while (true) {
					lock.lock();

					try {
						//Thread.sleep(1000L);
						TimeUnit.MILLISECONDS.sleep(1000);
						System.out.println(Thread.currentThread());
						//Thread.sleep(1000L);
						TimeUnit.MILLISECONDS.sleep(1000);
					} catch (Exception ex) {

					} finally {
						lock.unlock();
					}
				}
			}
		}

		for (int i = 0; i < 10; i++) {
			Worker w = new Worker();
			w.start();
		}

		new Thread() {
			@Override
			public void run() {
				while (true) {

					try {
						//Thread.sleep(200L);
						TimeUnit.MILLISECONDS.sleep(200);
						System.out.println();
					} catch (Exception ex) {

					}
				}
			}
		}.start();

		try {
			//Thread.sleep(20000L);
			TimeUnit.MILLISECONDS.sleep(20000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}