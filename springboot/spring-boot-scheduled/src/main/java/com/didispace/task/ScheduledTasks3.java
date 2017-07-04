package com.didispace.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks3 {

	// 改为每天凌晨2点执行
//	@Scheduled(cron = "0 0 2 * * ?")
	@Scheduled(cron = "0 0 23 * * ?")
	public void reportCurrentTime2() {
		System.out.println("-----------");
	}

}
