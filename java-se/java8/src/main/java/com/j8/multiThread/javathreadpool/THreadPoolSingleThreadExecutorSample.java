package com.j8.multiThread.javathreadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class THreadPoolSingleThreadExecutorSample {
	public static void main(String[] args) {
		// create ExecutorService
		ExecutorService executor = Executors.newSingleThreadExecutor();

		for (int i = 0; i < 10; i++) {
			Runnable worker = new Worker(Integer.toString(i));
			executor.execute(worker);
		}

		executor.shutdown();
		while (!executor.isTerminated()) {
		}

		System.out.println("Finished all threads");
	}
}