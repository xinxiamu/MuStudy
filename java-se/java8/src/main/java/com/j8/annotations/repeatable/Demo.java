package com.j8.annotations.repeatable;

/**
 * 重复注解是java8特性。
 * @author mutou
 *
 */
public class Demo {

	@Schedule(dayOfMonth = "last")
	@Schedule(dayOfWeek = "Fri", hour = 23) 
	@Schedule(dayOfMonth = "mu",hour=12)
	public void doPeriodicCleanup() {
	}
}
