package com.mu.logistics.quartz;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.mu.common.exception.BaseException;
import com.mu.common.utils.DateUtil;
import com.mu.common.utils.Log;
import com.mu.logistics.service.IUserService;

@Component
public class TransferA88UserToIwlUserEveryDay implements IBaseQuertz {
	
	@Resource
	private IUserService userService;

	@Override
	public void execute() {
		Log.d(TransferA88UserToIwlUserEveryDay.class,
				DateUtil.dateToString(new Date()) + ":开始同步会员信息");
		try {
			userService.a88UserMoveToIwlEveryday();
		} catch (BaseException e) {
			e.printStackTrace();
		}
		
		Log.d(TransferA88UserToIwlUserEveryDay.class,
				DateUtil.dateToString(new Date()) + ":结束同步会员信息");
	}

}
