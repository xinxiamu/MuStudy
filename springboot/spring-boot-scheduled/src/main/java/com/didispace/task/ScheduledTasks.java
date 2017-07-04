//package com.didispace.task;
//
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//@Component
//public class ScheduledTasks {
//
//	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
//
//	/**
//	 * 每隔5秒执行一次，不管上次执行没执行完，都定率执行
//	 */
//	 @Scheduled(fixedRate = 1000)
//	public void reportCurrentTime() {
////		System.out.println("当前时间：" + dateFormat.format(new Date()));
//			System.out.println("---a");
//			new Thread(){
//				public void run() {
//					try {
//						Thread.sleep(1000 * 10);
//						System.out.println("执行我鸟"); 
//					} catch (InterruptedException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//				};
//			}.start();
////			System.out.println("---b");
//	}
//	
//
//	/**
//	 * 每隔5秒执行一次，上一次执行完，隔5秒就执行下一次。
//	 */
//	 @Scheduled(fixedDelay = 1000 * 6)
//	public void reportCurrentTime2() { 
////		System.out.println("当前时间：" + dateFormat.format(new Date()));
//		System.out.println("---b");
//		new Thread(){
//			public void run() {
//				try {
//					Thread.sleep(1000 * 10);  
//					System.out.println("哈哈");
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			};
//		}.start();
//	}
//
//	/**
//	 * 每天中午12点触发
//	 */
////	@Scheduled(cron = "0 0 12 * * ?")
//	public void atSomeTimeExe() {
//		System.out.println("--------每天中午12点执行我");
//	}
//
//	
//	DateFormat sdf = new SimpleDateFormat("HH:mm:ss");
//
//	int[] delays = new int[] { 8, 3, 6, 2, 2 };
//	int index = 0;
//
////	@Scheduled(cron = "0/5 * * * * ?}")
//	public void process() {
//		try {
//			if (index > delays.length - 1) {
//				if (index == delays.length) {
//					System.out.println("---------- test end at " + sdf.format(new Date()) + " ---------");
//				}
//				index++;
//				return;
//			} else {
//				System.out.println(index + ":start run at" + sdf.format(new Date()));
//			}
//			Thread.sleep(delays[index] * 1000);
//			System.out.println(index + ":end run at " + sdf.format(new Date()));
//			index++;
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//	}
//	
//	/**
//	 * 测试证明，每隔五秒执行一次，定率
//	 */
////	@Scheduled(cron = "0/5 * * * * ?}")
//	public void process2() {
//		System.out.println("---a");
//		new Thread(){
//			public void run() {
//				try {
//					Thread.sleep(1000 * 10);
//					System.out.println("执行我鸟");
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			};
//		}.start();
//	}
//
//}
