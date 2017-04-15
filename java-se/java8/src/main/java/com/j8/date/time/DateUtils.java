package com.j8.date.time;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class DateUtils {

	public static void main(String[] args) {
		System.out.println("本地化的当前年月日："
				+ LocalDate.now().plusMonths(2).plusDays(3).toString());
		System.out.println("本地化的当前日期：" + LocalDateTime.now());
	}

}
