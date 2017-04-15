package com.j8.annotations.repeatable;

import java.lang.annotation.Repeatable;

/**
 * @author mutou
 *
 */
@Repeatable(Schedules.class)	//java 8才可以用重复注解。
public @interface Schedule {
	String dayOfMonth() default "first";

	String dayOfWeek() default "Mon";

	int hour() default 12;
}
