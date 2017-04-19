package co.jufeng.logger;

import java.io.IOException;

import org.junit.Test;

import co.jufeng.logger.LoggerUtil;


public class LoggerTest {
	
	@Test
	public void test() throws IOException {
		for (int i = 0; i < 10; i++) {
			LoggerUtil.info(getClass(), getClass() + "测试日志系统");
		}
	}

}
