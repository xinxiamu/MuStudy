package com.j8.multiThread.javasynchronized;

public class Data {
	int a = 0;

	public int get() {
		return a;
	}

	public void set(int a) {
		this.a = a;
	}

	/*
	 * Block synchronized
	 */
	public void count() {
		synchronized (this) {
			a++;
		}
	}

	/*	
 	// Method Synchronized
 	public synchronized void count() {
		a++;
	}
	
	*/
}