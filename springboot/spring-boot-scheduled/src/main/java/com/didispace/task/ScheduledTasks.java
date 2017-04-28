package com.didispace.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class ScheduledTasks {

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

//	@Scheduled(fixedRate = 5000)
	public void reportCurrentTime() {
		System.out.println("当前时间：" + dateFormat.format(new Date()));
	}

	/**
	 * 每天中午12点触发
	 */
	@Scheduled(cron = "0 0 12 * * ?")
	public void atSomeTimeExe() {
		System.out.println("--------每天中午12点执行我");
	}

}
